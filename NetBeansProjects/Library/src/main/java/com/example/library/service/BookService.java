/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import com.example.library.entities.Book;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author vuduchiep
 */
public interface BookService {
    
    public Book get(int id);
    
    public Book add(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id);
    
    public Book update(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id);
    
    public Book delete(int id);
    
    public Collection<Book> getAll();
}
