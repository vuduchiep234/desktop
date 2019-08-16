/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao;

import com.example.library.entities.Author;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface AuthorDAO {
    
    public Author getAuthor(int id);
    
    public Author addAuthor(Author author);
    
    public Author updateAuthor(Author author);
    
    public Author deleteAuthor(int id);
    
    public List<Author> getAll();
    
    public List<Author> search(String name);
}
