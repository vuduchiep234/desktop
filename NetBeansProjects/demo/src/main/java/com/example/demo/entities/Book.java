/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;




/**
 *
 * @author vuduchiep
 */

@Entity
@Table("book")
public class Book {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    
    @Column(name="title", nullable=false)
    private String title;
    
    @Column(name="publishedYear", nullable=false)
    private String publishedYear;
    
    @Column(name="publisher_id", nullable=false)
    private int publisher_id;

    public Book() {
    }

    public Book(int id, String title, String publishedYear, int publisher_id) {
        this.id = id;
        this.title = title;
        this.publishedYear = publishedYear;
        this.publisher_id = publisher_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }
    
    
}
