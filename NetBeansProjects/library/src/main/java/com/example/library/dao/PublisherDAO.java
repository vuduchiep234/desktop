/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao;

import com.example.library.entities.Publisher;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface PublisherDAO {
    
    public Publisher getPublisher(int id);
    
    public Publisher addPublisher(Publisher publisher);
    
    public Publisher updatePublisher(Publisher publisher);
    
    public void deletePublisher(int id);
    
    public List<Publisher> getAll();
}
