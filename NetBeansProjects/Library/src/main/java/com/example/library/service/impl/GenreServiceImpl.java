/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.GenreDAO;
import com.example.library.entities.Genre;
import com.example.library.service.GenreService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuduchiep
 */
@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDAO genreDAO;

    @Override
    public Genre get(int id) {

        return genreDAO.getGenre(id);
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
    public Genre delete(int id) {

        return genreDAO.deleteGenre(id);
    }

    @Override
    public List<Genre> getAll() {

        return genreDAO.getAll();
    }

    @Override
    public List<Genre> search(String genreType) {
        
        return genreDAO.search(genreType);
    }

}
