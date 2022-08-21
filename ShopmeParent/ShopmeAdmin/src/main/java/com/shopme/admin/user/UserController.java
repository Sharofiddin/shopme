package com.shopme.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.User;

@Controller
public class UserController {

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
	return "create_user";
    }
    
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
	userService.save(user);
	redirectAttributes.addFlashAttribute("message", "New user has been saved successfully");
	return "redirect:/users";
    }
}
