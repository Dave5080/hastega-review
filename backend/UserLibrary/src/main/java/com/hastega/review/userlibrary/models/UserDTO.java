package com.hastega.review.userlibrary.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class UserDTO {
    private String email;
    private String password;
    private String nome;
    private String cognome;

    public UserDTO(String email, String password, String nome, String cognome){
        setEmail(email);
        setNome(nome);
        setCognome(cognome);
        getPassword(password);
    }

    public static Optional<UserDTO> fromJSON(String json){
        ObjectMapper mapper = new ObjectMapper();
        Optional<UserDTO> user = Optional.empty();
        try{
            user = Optional.of(mapper.readValue(json, UserDTO.class));
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void getPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
