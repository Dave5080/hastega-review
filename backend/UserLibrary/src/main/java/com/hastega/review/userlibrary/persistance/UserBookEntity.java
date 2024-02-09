package com.hastega.review.userlibrary.persistance;

import com.hastega.review.userlibrary.models.BookDTO;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name="USER_BOOKS", schema="UserLibrary")
public class UserBookEntity {
    @EmbeddedId
    private BookID bookId;
    private String titolo;
    private String autore;
    private Timestamp add_date;
    private Timestamp del_date;
    private String trama;
    private Integer letture;

    public UserBookEntity(){}

    public UserBookEntity(BookDTO dto){
        this.update(dto);
    }

    public BookID getBookId() {
        return bookId;
    }
    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public Timestamp getAddDate() {
        return add_date;
    }

    public Timestamp getDelDate() {
        return del_date;
    }

    public String getTrama() {
        return trama;
    }

    public Integer getLetture() {
        return letture;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setAdd_date(Timestamp add_date) {
        this.add_date = add_date;
    }

    public void setDel_date(Timestamp del_date) {
        this.del_date = del_date;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public void setLetture(Integer letture){
        this.letture = letture;
    }
    public UserBookEntity update(BookDTO dto){
        if(dto.getTitolo() != null) this.setTitolo(dto.getTitolo());
        if(dto.getAutore() != null) this.setAutore(dto.getAutore());
        if(dto.getAdd_date() != null) this.setAdd_date(dto.getAdd_date());
        if(dto.getDel_date() != null) this.setDel_date(dto.getDel_date());
        if(dto.getTrama() != null) this.setTrama(dto.getTrama());
        if(dto.getLetture() != null) this.setLetture(dto.getLetture());
        return this;
    }
}
