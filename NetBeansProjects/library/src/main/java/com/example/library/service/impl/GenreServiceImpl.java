/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.GenreDAO;
import com.example.library.dao.impl.GenreDAOImpl;
import com.example.library.entities.Genre;
import com.example.library.service.GenreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuduchiep
 */
@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDAO genreDAO = new GenreDAOImpl();

    @Override
    public Genre get(int id) {

        Genre genre = genreDAO.getGenre(id);
        return genre;
    }

    @Override
    public Genre add(Genre genre) {

        return genreDAO.addGenre(genre);
    }

    @Override
    public Genre update(Genre genre) {

        return genreDAO.updateGenre(genre);
    }

    @Override
    public void delete(int id) {

        genreDAO.deleteGenre(id);
    }

    @Override
    public List<Genre> getAll() {

        List<Genre> list = genreDAO.getAll();
        return list;
    }

}
