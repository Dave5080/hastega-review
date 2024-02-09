package com.hastega.review.userlibrary.persistance;

import com.hastega.review.userlibrary.Utils;
import com.hastega.review.userlibrary.models.UserDTO;
import jakarta.persistence.*;

@Entity
@Table(name="USERS", schema="UserLibrary")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String p_hash;

    public UserEntity(){}

    public UserEntity(UserDTO userDTO){
        this.update(userDTO);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getP_hash() {
        return p_hash;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setP_hash(String p_hash){
        this.p_hash = p_hash;
    }

    public UserEntity update(UserDTO dto){
        if(dto.getNome() != null)
            this.setNome(dto.getNome());
        if(dto.getNome() != null)
            this.setCognome(dto.getCognome());
        if(dto.getEmail() != null)
            this.setEmail(dto.getEmail());
        if(dto.getPassword() != null)
            this.setP_hash(dto.getPassword());
        return this;
    }
}
