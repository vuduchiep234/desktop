/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import com.example.library.entities.Genre;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface GenreService {
    
    public Genre get(int id);
    
    public Genre add(Genre genre);
    
    public Genre update(Genre genre);
    
    public void delete(int id);
    
    public List<Genre> getAll();
}
