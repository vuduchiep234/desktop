/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import com.example.library.entities.Publisher;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface PublisherService {
    
    public Publisher get(int id);
    
    public Publisher add(Publisher publisher);
    
    public Publisher update(Publisher publisher);
    
    public Publisher delete(int id);
    
    public List<Publisher> getAll();
    
    public List<Publisher> search(String publisherName);
}
