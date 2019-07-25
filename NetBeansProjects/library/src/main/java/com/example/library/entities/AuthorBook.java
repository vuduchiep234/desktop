/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vuduchiep
 */

@Entity
@Table(name = "author_book")
public class AuthorBook {
    
    private int book_id;
    private int author_id;
    private Book book;
    private Author author;

    public AuthorBook() {
    }

    
    public AuthorBook(int book_id, int author_id) {
        this.book_id = book_id;
        this.author_id = author_id;
    }

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Column(name = "author_id", nullable = false)
    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    

    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, unique = true, insertable = false, updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, insertable = false, updatable = false)
    
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    
}
