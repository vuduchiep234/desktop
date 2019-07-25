/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.BookDAO;
import com.example.library.dao.impl.BookDAOImpl;
import com.example.library.entities.Book;
import com.example.library.service.BookService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuduchiep
 */

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public Book addBook(Book book) {

        return bookDAO.addBook(book);
    }

    @Override
    public Book getBook(int bookId) {

        return bookDAO.getBook(bookId);
    }

    @Override
    public Book updateBook(Book book) {

        return bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int bookId) {

        bookDAO.deleteBook(bookId);
    }

    @Override
    public Collection<Book> getAllBooks() {

        return bookDAO.getAll();
    }

}
