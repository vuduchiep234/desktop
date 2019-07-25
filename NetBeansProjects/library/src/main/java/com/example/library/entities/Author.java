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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author vuduchiep
 */
@Entity
@Table(name = "author")
public class Author implements Serializable {

    private int id;
    private String name;
    private Set<Book> books;

    public Author() {
    }

    public Author(int id) {
        this.id = id;
    }
    
    

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
//        this.books = books;
    }

    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @javax.persistence.Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "authors",
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
