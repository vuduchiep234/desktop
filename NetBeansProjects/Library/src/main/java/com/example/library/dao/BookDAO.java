/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao;

import com.example.library.entities.Book;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author vuduchiep
 */
public interface BookDAO {
    
    public Book getBook(int id);
    
    public Book addBook(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id);
    
    public Book updateBook(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id);
    
    public Book deleteBook(int id);
    
    public Collection<Book> getAll();
    
    public Collection<Book> search(String title, String publisherYear, String publisher, String author, String genre);
}
