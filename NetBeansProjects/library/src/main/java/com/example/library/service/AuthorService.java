/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import com.example.library.entities.Author;
import java.util.List;


/**
 *
 * @author vuduchiep
 */

public interface AuthorService {
    
    public Author get(int id);
    
    public Author add(Author author);
    
    public Author update(Author author);
    
    public void delete(int id);
    
    public List<Author> getAll();
    
}
