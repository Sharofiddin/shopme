package com.shopme.admin.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService( UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
    }
    
    public List<User> getAllUsers(){
	return (List<User>) userRepository.findAll();
    }

    public List<Role> getRoleList() {
	return (List<Role>) roleRepository.findAll();
    }

    public void save(User user) {
	encryptUserPassword(user);
	userRepository.save(user);
    }
    
    public void encryptUserPassword(User user) {
	user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
