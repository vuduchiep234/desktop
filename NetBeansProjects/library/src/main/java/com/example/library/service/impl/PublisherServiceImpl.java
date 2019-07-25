/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.PublisherDAO;
import com.example.library.dao.impl.PublisherDAOImpl;
import com.example.library.entities.Publisher;
import com.example.library.service.PublisherService;
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
public class PublisherServiceImpl implements PublisherService{

    @Autowired
    private PublisherDAO publisherDAO = new PublisherDAOImpl();
    
    @Override
    public Publisher get(int id) {

        Publisher publisher = publisherDAO.getPublisher(id);
        return publisher;
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
    public void delete(int id) {

        publisherDAO.deletePublisher(id);
    }

    @Override
    public List<Publisher> getAll() {

        List<Publisher> list = publisherDAO.getAll();
        return list;
    }
    
}
