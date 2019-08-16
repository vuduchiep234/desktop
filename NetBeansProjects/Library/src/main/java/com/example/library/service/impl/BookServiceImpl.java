/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.BookDAO;
import com.example.library.entities.Book;
import com.example.library.service.BookService;
import java.util.ArrayList;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuduchiep
 */

@Service
@Transactional
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO bookDAO;
    
    @Override
    public Book get(int id) {

        return bookDAO.getBook(id);
    }

    @Override
    public Book add(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id) {

        return bookDAO.addBook(book, authors_id, genres_id);
    }

    @Override
    public Book update(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id) {

        return bookDAO.updateBook(book, authors_id, genres_id);
    }

    @Override
    public Book delete(int id) {

        return bookDAO.deleteBook(id);
    }

    @Override
    public Collection<Book> getAll() {

        return bookDAO.getAll();
    }
    
}
