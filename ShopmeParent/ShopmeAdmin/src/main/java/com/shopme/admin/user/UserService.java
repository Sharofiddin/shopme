package com.shopme.admin.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserService( UserRepository userRepository, RoleRepository roleRepository) {
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
    }
    
    public List<User> getAllUsers(){
	return (List<User>) userRepository.findAll();
    }

    public List<Role> getRoleList() {
	return (List<Role>) roleRepository.findAll();
    }

    public void save(User user) {
	userRepository.save(user);
    }
}
