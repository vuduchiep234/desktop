/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao.impl;

import com.example.library.dao.BookDAO;
import com.example.library.entities.Author;
import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vuduchiep
 */

@Repository
public class BookDAOImpl implements BookDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    
    @Override
    public Book getBook(int id) {

        Session session = sessionFactory.getCurrentSession();
        Book book = (Book)session.get(Book.class, id);
        return book;
    }

    @Override
    public Book addBook(Book book) {

        Session session = sessionFactory.getCurrentSession();
//        List<Integer> authors_id = book.getAuthors_id();
//        List<Integer> genres_id = book.getGenres_id();
//        Set<Author> listAuthor = new HashSet<>();
//        for(int author_id : authors_id){
//            Author author = new Author(author_id);
//            listAuthor.add(author);
//        }
//        Set<Genre> listGenre = new HashSet<>();
//        for(int genre_id : genres_id){
//            Genre genre = new Genre(genre_id);
//            listGenre.add(genre);
//        }
//        book.setAuthors(listAuthor);
//        book.setGenres(listGenre);
        session.save(book);
//        session.clear();
//        Book b = (Book)session.get(Book.class, book.getId());
        return book;
    }

    @Override
    public Book updateBook(Book book) {

        Session session = sessionFactory.getCurrentSession();
        Book b = (Book)session.get(Book.class, book.getId());
        b.setTitle(book.getTitle());
        b.setPublishedYear(book.getPublishedYear());
        session.update(b);
        return b;
    }

    @Override
    public void deleteBook(int id) {

        Session session = sessionFactory.getCurrentSession();
        Book book = (Book)session.get(Book.class, id);
        session.delete(book);
    }

    @Override
    public Collection<Book> getAll() {

        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        Collection<Book> list = (Collection<Book>)session.createQuery("From " + Book.class.getName()).list();
        return list;
    }
    
}
