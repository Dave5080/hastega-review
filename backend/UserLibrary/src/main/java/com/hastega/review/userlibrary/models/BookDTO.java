package com.hastega.review.userlibrary.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.util.Optional;

public class BookDTO {
    private String ISBN;
    private Integer userId;
    private String titolo;
    private String autore;
    private String trama;
    private Integer letture;

    public BookDTO(String ISBN, Integer userId, String titolo, String autore, String trama, Integer letture){
        setISBN(ISBN);
        setUserId(userId);
        setAutore(autore);
        setTitolo(titolo);
        setTrama(trama);
        setLetture(letture);
    }

    public static Optional<BookDTO> fromJSON(String json){
        ObjectMapper mapper = new ObjectMapper();
        Optional<BookDTO> book = Optional.empty();
        try{
            book = Optional.of(mapper.readValue(json, BookDTO.class));
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return book;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public Integer getLetture() {
        return letture;
    }

    public void setLetture(Integer letture) {
        this.letture = letture;
    }

    public String toString(){
        return String.format("{ISBN: %s, userId: %d, titolo: %s, autore: %s, trama: %s, letture: %d }",
                getISBN(), getUserId(),getTitolo(), getAutore(), getTrama(), getLetture());
    }
}
