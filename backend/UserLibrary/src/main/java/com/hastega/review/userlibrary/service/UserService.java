package com.hastega.review.userlibrary.service;

import com.hastega.review.userlibrary.models.UserDTO;
import com.hastega.review.userlibrary.persistance.UserEntity;
import com.hastega.review.userlibrary.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Optional<UserEntity> getUserByEmail(String email){
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<UserEntity> getUserById(Integer id){
        return userRepository.findById(id);
    }

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> insertNewUser(UserDTO user){
        Optional<UserEntity> insertedUser;

        UserEntity newUser = new UserEntity(user);
        insertedUser = Optional.of(userRepository.saveAndFlush(newUser));
        return insertedUser;
    }

    public Optional<UserEntity> updateUser(Integer id, UserDTO user){
        Optional<UserEntity> updated = Optional.empty();
        Optional<UserEntity> old = userRepository.findById(id);

        if(old.isEmpty()) return updated;

        UserEntity u = old.get().update(user);
        updated = Optional.of(userRepository.saveAndFlush(u));
        return updated;
    }

    public Optional<UserEntity> deleteUser(Integer id){
        Optional<UserEntity> user = userRepository.findById(id);
        user.ifPresent(userEntity -> userRepository.delete(userEntity));
        return user;
    }
}
