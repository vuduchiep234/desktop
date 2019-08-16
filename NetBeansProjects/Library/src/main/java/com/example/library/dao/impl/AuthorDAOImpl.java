/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao.impl;

import com.example.library.dao.AuthorDAO;
import com.example.library.entities.Author;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vuduchiep
 */

@Repository
public class AuthorDAOImpl implements AuthorDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Author getAuthor(int id) {
        
        Session session = sessionFactory.getCurrentSession();
        Author author = (Author)session.get(Author.class, id);
        return author;
    }

    @Override
    public Author addAuthor(Author author) {

        Session session = sessionFactory.getCurrentSession();
        session.save(author);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {

        Session session = sessionFactory.getCurrentSession();
        Author au = (Author)session.get(Author.class, author.getId());
        au.setName(author.getName());
        session.update(au);
        return au;
    }

    @Override
    public Author deleteAuthor(int id) {

        Session session = sessionFactory.getCurrentSession();
        Author author = (Author)session.get(Author.class, id);
        session.delete(author);
        return author;
    }

    @Override
    public List<Author> getAll() {

        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Author> list = (List<Author>)session.createQuery("FROM " + Author.class.getName()).list();
        return list;
        
    }

    @Override
    public List<Author> search(String name) {

        Session session = sessionFactory.getCurrentSession();
        
        List<Author> author = (List<Author>)session.createQuery("From " + Author.class.getName() +" Where name LIKE :name").setParameter("name", "%"+name+"%").list();
        return author;
    }
    
}
