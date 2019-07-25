/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author vuduchiep
 */
@Entity
@Table(name = "genre")
public class Genre implements Serializable {

    private int id;
    private String genreType;
    private Set<Book> books;

    public Genre() {
    }

    public Genre(int id) {
        this.id = id;
    }

    
    
    public Genre(int id, String genreType) {
        this.id = id;
        this.genreType = genreType;
//        this.books = books;
    }

    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "genreType", nullable = false)
    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    @ManyToMany(mappedBy = "genres",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
            )
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}
