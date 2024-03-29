package com.shopme.admin.user;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final UserService userService;
    
    public UserRestController(UserService userService) {
	this.userService = userService;
    }

    @PostMapping("/users/check_email")
    public String checkEmailDuplication(@Param(value = "email") String email) {
	
	return userService.isEmailUnique(email) ? "OK" : "Duplicated";
    }
}
