/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao.impl;

import com.example.library.dao.GenreDAO;
import com.example.library.entities.Genre;
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
public class GenreDAOImpl implements GenreDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    
    @Override
    public Genre getGenre(int id) {

        Session session = sessionFactory.getCurrentSession();
    
        Genre genre = (Genre)session.get(Genre.class, id);
        return genre;
    }

    @Override
    public Genre addGenre(Genre genre) {

        Session session = sessionFactory.getCurrentSession();
    
        session.save(genre);
        return genre;
    }

    @Override
    public Genre updateGenre(Genre genre) {

        Session session = sessionFactory.getCurrentSession();
    
        Genre ge = (Genre)session.get(Genre.class, genre.getId());
        ge.setGenreType(genre.getGenreType());
        session.update(ge);
        return ge;
    }

    @Override
    public void deleteGenre(int id) {

        Session session = sessionFactory.getCurrentSession();
    
        Genre genre = (Genre)session.get(Genre.class, id);
        session.delete(genre);
    }

    @Override
    public List<Genre> getAll() {

        Session session = sessionFactory.getCurrentSession();
    
        @SuppressWarnings("unchecked")
        List<Genre> list = (List<Genre>)session.createQuery("From " + Genre.class.getName()).list();
        return list;
    }
    
}
