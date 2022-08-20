package com.shopme.admin.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class TestPasswordEncoder {

    @Test
    void testPasswordEncoding() {
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	String plain = "1qazxsw2";
	String encodedPassword = bCryptPasswordEncoder.encode(plain);
	System.out.println(encodedPassword);
	assertTrue(bCryptPasswordEncoder.matches(plain, encodedPassword));
    }

}
