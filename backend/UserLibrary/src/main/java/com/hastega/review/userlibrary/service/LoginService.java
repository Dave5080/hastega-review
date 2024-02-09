package com.hastega.review.userlibrary.service;

import com.hastega.review.userlibrary.Utils;
import com.hastega.review.userlibrary.models.UserDTO;
import com.hastega.review.userlibrary.persistance.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoginService {

    UserService userService;

    @Autowired
    public LoginService(UserService userService){
        this.userService = userService;
    }
    public Optional<Integer> login(UserDTO userIn) {

        Optional<UserEntity> dbUser = userService.getUserByEmail(userIn.getEmail());

        if (dbUser.isPresent() && Utils.verify(userIn.getPassword(), dbUser.get().getP_hash()).verified)
            return Optional.of(dbUser.get().getId());
        return Optional.empty();
    }

    public Optional<Integer> register(UserDTO userIn){
        Optional<UserEntity> alreadyUser = userService.getUserByEmail(userIn.getEmail());
        Optional<Integer> res = Optional.empty();
        if(alreadyUser.isEmpty()) res = userService.insertNewUser(userIn).map(UserEntity::getId);
        return res;
    }
}