package com.shopme.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = {"com.shopme.common.entity", "com.shopme.admin.user"})
public class ShopmeAdminApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(ShopmeAdminApplication.class, args);
	}

}
