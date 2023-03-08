package com.shopme.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.user.exception.UserNotFoundException;
import com.shopme.common.entity.User;

@Controller
public class UserController {

	private static final String PAGE_TITLE = "pageTitle";
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String listAll(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}

	@GetMapping("/users/new")
	public String createUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", userService.getRoleList());
		model.addAttribute(PAGE_TITLE,"Create new user");
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		userService.save(user);
		redirectAttributes.addFlashAttribute("message", "New user has been saved successfully");
		return "redirect:/users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			var user = userService.get(id);
			model.addAttribute("user", user);
			model.addAttribute("roles",userService.getRoleList());
			model.addAttribute(PAGE_TITLE,"Edit user (ID: "+id + ")");
			redirectAttributes.addFlashAttribute("message", "New user has been saved successfully");
			return "user_form";
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/users";
		}
	}
}
