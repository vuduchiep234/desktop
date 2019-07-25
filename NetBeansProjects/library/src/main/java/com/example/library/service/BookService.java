/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import com.example.library.entities.Book;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface BookService {

    public Book addBook(Book book);

    public Book getBook(int bookId);

    public Book updateBook(Book book);

    public void deleteBook(int bookId);

    public Collection<Book> getAllBooks();
}
