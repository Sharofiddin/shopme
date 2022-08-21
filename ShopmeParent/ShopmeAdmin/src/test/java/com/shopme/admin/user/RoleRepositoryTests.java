package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class RoleRepositoryTests {
    
    @Autowired
    private RoleRepository roleRepository;
    
    void testCreateRole() {
	Role role = roleRepository.save(new Role("admin", "Manage everything"));
	assertThat(role.getId()).isPositive();
    } 
    
    
    void testCreateRoles() {
	Role salesPerson = roleRepository.save(new Role("salesperson", "Manage customer, product prices"));
	Role editor = roleRepository.save(new Role("editor", "Manage products, menus, catalogs and brands"));
	Role shipper = roleRepository.save(new Role("shipper", "View products and orders, change order status"));
	Role assitant = roleRepository.save(new Role("assistant", "Manage questions and views"));
	Iterable<Role> savedRoles = roleRepository.saveAll(List.of(salesPerson, editor, shipper, assitant));
	assertEquals(4, StreamSupport.stream(savedRoles.spliterator(), false).count());
    }

}
