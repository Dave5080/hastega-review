package com.hastega.review.userlibrary;

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

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping(value="/books", consumes={"*/*"})
    public List<UserBookEntity> getUserBooks(@RequestParam String Authorization){
        return userBookService.getUserBooksByUserId(tokenService.getUserIdFromToken(Authorization))
                .stream().filter(b -> b.getDelDate() == null || b.getDelDate().after(Timestamp.from(Instant.now()))).toList();
    }

    @PostMapping(value="/add-book", consumes={"application/json"})
    public ResponseEntity<UserBookEntity> postNewBook(@RequestParam String Authorization, @RequestBody BookDTO book){
        System.out.println(book);
        book.setUserId(tokenService.getUserIdFromToken(Authorization));
        Optional<UserBookEntity> newBook = userBookService.insertNewUserBook(book);
        return newBook.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.ALREADY_REPORTED));
    }

    @PostMapping(value="/edit-book", consumes={"application/json"})
    public ResponseEntity<UserBookEntity> editBook(@RequestParam String Authorization, @RequestParam String ISBN, @RequestBody BookDTO book){
        System.out.println(book);
        Integer userId = tokenService.getUserIdFromToken(Authorization);
        book.setUserId(userId);
        Optional<UserBookEntity> updatedBook = userBookService.updateUserBook(new BookID(ISBN, userId), book);
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
