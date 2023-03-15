package com.shopme.admin.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shopme.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByEmail(String email);

    @Query("SELECT u FROM users u WHERE email = :email")
    User getUserByEmail(@Param(value = "email") String email);
}
