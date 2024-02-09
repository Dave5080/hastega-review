package com.hastega.review.userlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class UserLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserLibraryApplication.class, args);
    }

}
