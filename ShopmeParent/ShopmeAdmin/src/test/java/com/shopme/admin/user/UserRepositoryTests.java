package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    void testCreateuserWithRole() {
	Role role = entityManager.find(Role.class, 2);
	User user = new User("pardayev.sharofiddin@gmail.com", "Sharofiddin", "Pardayev", "1qazxsw2");
	user.addRole(role);
	user = userRepository.save(user);
	assertThat(user.getId()).isPositive();
    }
    
    void testCreateuserWithTwoRoles() {
	
	User user = new User("leakin@gmail.com"
		, "Hanna", "Goetz", "1qazxsw2");
	user.addRole(new Role(3));
	user.addRole(new Role(4));
	user = userRepository.save(user);
	assertThat(user.getId()).isPositive();
    }
    
    @Test
    void testGetUserByEmail() {
	User user = userRepository.getUserByEmail("pardayev.sharofiddin@gmail.com");
	assertNotNull(user);
    }
    

}
