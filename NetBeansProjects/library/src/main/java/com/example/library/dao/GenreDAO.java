/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao;

import com.example.library.entities.Genre;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface GenreDAO {
    
    public Genre getGenre(int id);
    
    public Genre addGenre(Genre genre);
    
    public Genre updateGenre(Genre genre);
    
    public void deleteGenre(int id);
    
    public List<Genre> getAll();
}
