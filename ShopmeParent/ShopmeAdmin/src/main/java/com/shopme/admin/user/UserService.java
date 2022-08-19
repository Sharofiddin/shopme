package com.shopme.admin.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopme.common.entity.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService( UserRepository userRepository) {
	this.userRepository = userRepository;
    }
    
    public List<User> getAllUsers(){
	return (List<User>) userRepository.findAll();
    }
}
