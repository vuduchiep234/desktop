/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.PublisherDAO;
import com.example.library.entities.Publisher;
import com.example.library.service.PublisherService;
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
public class PublisherServiceImpl implements PublisherService{

    @Autowired
    private PublisherDAO publisherDAO;
    
    @Override
    public Publisher get(int id) {

        return publisherDAO.getPublisher(id);
    }

    @Override
    public Publisher add(Publisher publisher) {

        return publisherDAO.addPublisher(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {

        return publisherDAO.updatePublisher(publisher);
    }

    @Override
    public Publisher delete(int id) {

        return publisherDAO.deletePublisher(id);
    }

    @Override
    public List<Publisher> getAll() {

        return publisherDAO.getAll();
    }

    @Override
    public List<Publisher> search(String publisherName) {

        return publisherDAO.search(publisherName);
    }
    
}
