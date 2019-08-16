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
import com.example.library.entities.Publisher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vuduchiep
 */
@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book getBook(int id) {

        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);

        return book;
    }

    @Override
    public Book addBook(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id) {

        Session session = sessionFactory.getCurrentSession();
        Publisher publisher = (Publisher) session.get(Publisher.class, book.getPublisher_id());
        book.setPublisher(publisher);

        Set<Author> authors = new HashSet<Author>();
        for (Integer author_id : authors_id) {
            System.out.println(author_id);
            Author author = (Author) session.get(Author.class, (int)author_id);
            System.out.println(author);
            authors.add(author);
            System.out.println("success");
        }
        
        Set<Genre> genres = new HashSet<Genre>();
        for (Integer genre_id : genres_id) {
            Genre genre = (Genre) session.get(Genre.class, (int)genre_id);
            genres.add(genre);
        }
        
        book.setAuthors(authors);
        book.setGenres(genres);
        session.save(book);
        return book;
    }

    @Override
    public Book updateBook(Book book, ArrayList<Integer> authors_id, ArrayList<Integer> genres_id) {

        Session session = sessionFactory.getCurrentSession();
        Book b = (Book) session.get(Book.class, book.getId());
        b.setTitle(book.getTitle());
        b.setPublishedYear(book.getPublishedYear());
        b.setPublisher_id(book.getPublisher_id());
        Publisher publisher = (Publisher) session.get(Publisher.class, b.getPublisher_id());
        b.setPublisher(publisher);
        Set<Author> authors = new HashSet<Author>();
        for (Integer author_id : authors_id) {
            System.out.println(author_id);
            Author author = (Author) session.get(Author.class, (int)author_id);
            System.out.println(author);
            authors.add(author);
            System.out.println("success");
        }
        
        Set<Genre> genres = new HashSet<Genre>();
        for (Integer genre_id : genres_id) {
            Genre genre = (Genre) session.get(Genre.class, (int)genre_id);
            genres.add(genre);
        }
        
        b.setAuthors(authors);
        b.setGenres(genres);
        session.update(b);
        return b;
    }

    @Override
    public Book deleteBook(int id) {

        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);
        session.delete(book);
        return book;
    }

    @Override
    public Collection<Book> getAll() {

        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        Collection<Book> list = (Collection<Book>) session.createQuery("FROM " + Book.class.getName()).list();
        return list;
    }

    @Override
    public Collection<Book> search(String title, String publisherYear, String publisher, String author, String genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
