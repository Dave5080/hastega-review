package com.hastega.review.userlibrary.controller;

import com.hastega.review.userlibrary.models.UserDTO;
import com.hastega.review.userlibrary.persistance.UserEntity;
import com.hastega.review.userlibrary.service.JwtTokenService;
import com.hastega.review.userlibrary.service.LoginService;
import com.hastega.review.userlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class TokenController {
    UserService userService;
    private final LoginService loginService;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public TokenController(UserService userService, LoginService loginService, JwtTokenService jwtTokenService){
        this.userService = userService;
        this.loginService = loginService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> getTokenByRegistration(@RequestBody UserDTO userDTO){
        return loginService.register(userDTO).map(userId -> new ResponseEntity<>(userId, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/login")
    public ResponseEntity<String> getTokenByLogin(@RequestBody UserDTO userDTO){
        return loginService.login(userDTO).map(userID -> {
            String token = jwtTokenService.generateToken(userID, "USER");
            return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }
}
