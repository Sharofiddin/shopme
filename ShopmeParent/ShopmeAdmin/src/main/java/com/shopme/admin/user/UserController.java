package com.shopme.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}