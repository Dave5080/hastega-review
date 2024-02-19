package com.hastega.review.userlibrary.persistance;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookID implements Serializable {
    private String ISBN;
    private Integer userID;

    public BookID(String ISBN, Integer userID){
        setISBN(ISBN);
        setUserID(userID);
    }

    public BookID(Integer userID, String ISBN){
        this(ISBN, userID);
    }

    public BookID() {

    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
