package com.hastega.review.userlibrary.controller;

import com.hastega.review.userlibrary.models.BookDTO;
import com.hastega.review.userlibrary.persistance.BookID;
import com.hastega.review.userlibrary.persistance.UserBookEntity;
import com.hastega.review.userlibrary.service.JwtTokenService;
import com.hastega.review.userlibrary.service.UserBookService;
import com.hastega.review.userlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class LibraryController {

    UserService userService;
    JwtTokenService tokenService;
    UserBookService userBookService;

    @Autowired
    public LibraryController(UserService userService, JwtTokenService jwtTokenService, UserBookService userBookService){
        this.userService = userService;
        this.tokenService = jwtTokenService;
        this.userBookService = userBookService;
    }

    @GetMapping("/books")
    public List<UserBookEntity> getUserBooks(@RequestParam String Authorization){
        return userBookService.getUserBooksByUserId(tokenService.getUserIdFromToken(Authorization));
    }

    @PostMapping("/add-book")
    public ResponseEntity<UserBookEntity> postNewBook(@RequestParam String Authorization, @RequestBody BookDTO book){
        book.setUserId(tokenService.getUserIdFromToken(Authorization));
        Optional<UserBookEntity> newBook = userBookService.insertNewUserBook(book);
        return newBook.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.ALREADY_REPORTED));
    }

    @PostMapping("/edit-book")
    public ResponseEntity<UserBookEntity> editBook(@RequestParam String Authorization, @RequestBody BookDTO book){
        book.setUserId(tokenService.getUserIdFromToken(Authorization));
        Optional<UserBookEntity> updatedBook = userBookService.insertNewUserBook(book);
        return updatedBook.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.ALREADY_REPORTED));
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity<UserBookEntity> deleteBook(@RequestParam String Authorization, @RequestParam String ISBN){
        Optional<UserBookEntity> deletedBook = userBookService.deleteUserBook(new BookID(ISBN, tokenService.getUserIdFromToken(Authorization)));
        return deletedBook.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
