/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao;

import com.example.library.entities.Book;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface BookDAO {

    public Book getBook(int id);

    public Book addBook(Book book);

    public Book updateBook(Book book);
    
    public void deleteBook(int id);
    
    public Collection<Book> getAll();
}
