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
@Table(name = "book_genre")
public class BookGenre {
    
    private int book_id;
    private int genre_id;
    private Book book;
    private Genre genre;

    public BookGenre() {
    }

    public BookGenre(int book_id, int genre_id) {
        this.book_id = book_id;
        this.genre_id = genre_id;
    }

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Column(name = "genre_id", nullable = false)
    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", unique = true, nullable = false, insertable = false, updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false, insertable = false, updatable = false)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
