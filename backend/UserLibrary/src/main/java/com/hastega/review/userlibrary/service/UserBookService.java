package com.hastega.review.userlibrary.service;

import com.hastega.review.userlibrary.models.BookDTO;
import com.hastega.review.userlibrary.persistance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBookService {

    UserRepository userRepository;
    UserBookRepository userBookRepository;

    @Autowired
    public UserBookService(UserBookRepository userBookRepository){
        this.userBookRepository = userBookRepository;
    }

    public Optional<UserBookEntity> getUserBookById(BookID bookId){
        return userBookRepository.findById(bookId);
    }

    public List<UserBookEntity> getUserBooksByUserEmail(String email){
        Integer userID = userRepository.findByEmail(email).getId();
        return getUserBooksByUserId(userID);
    }

    public List<UserBookEntity> getUserBooksByUserId(Integer id){
        if(id == null) return new ArrayList<>();
        return userBookRepository.findByBookIdUserID(id);
    }

    public List<UserBookEntity> getUsersBooks(){
        return userBookRepository.findAll();
    }

    public Optional<UserBookEntity> insertNewUserBook(BookDTO dto){
        Optional<UserBookEntity> insertedBook;
        UserBookEntity newBook = new UserBookEntity(dto);
        insertedBook = Optional.of(userBookRepository.saveAndFlush(newBook));
        return insertedBook;
    }

    public Optional<UserBookEntity> updateUserBook(BookID bookID, BookDTO dto){
        Optional<UserBookEntity>updated = Optional.empty();
        Optional<UserBookEntity> old = getUserBookById(bookID);
        if(old.isEmpty()) return  updated;
        UserBookEntity b = old.get().update(dto);
        updated = Optional.of(userBookRepository.saveAndFlush(b));
        return updated;
    }

    public Optional<UserBookEntity> deleteUserBook(BookID bookID){
        Optional<UserBookEntity> book = getUserBookById(bookID);
        book.ifPresent(userBookEntity -> userBookRepository.delete(userBookEntity));
        return book;
    }

}
