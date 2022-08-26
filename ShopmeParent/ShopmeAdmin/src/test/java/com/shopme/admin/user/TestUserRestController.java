package com.shopme.admin.user;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TestUserRestController {

    @Autowired
    MockMvc mockMvc;
    
    @Test
    void testDuplicatedEmail() {
	try {
	    mockMvc.perform(post("/users/check_email")
		    .with(csrf())
		    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .content(buildUrlEncodedFormEntity(
	         "email", "pardayev.sharofiddin@gmail.com" ))).andExpect(status().isOk())
	    .andExpect(content().string(containsString("Duplicated")));

	} catch (Exception e) {
	    fail(e);
	}
    }
    
    private String buildUrlEncodedFormEntity(String... params) {
	    if( (params.length % 2) > 0 ) {
	       throw new IllegalArgumentException("Need to give an even number of parameters");
	    }
	    StringBuilder result = new StringBuilder();
	    for (int i=0; i<params.length; i+=2) {
	        if( i > 0 ) {
	            result.append('&');
	        }
	        try {
	            result.
	            append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
	            append('=').
	            append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
	        }
	        catch (UnsupportedEncodingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    return result.toString();
	 }

}
