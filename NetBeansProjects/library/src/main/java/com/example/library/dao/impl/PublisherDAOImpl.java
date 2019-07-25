/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao.impl;

import com.example.library.dao.PublisherDAO;
import com.example.library.entities.Publisher;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vuduchiep
 */
@Repository
public class PublisherDAOImpl implements PublisherDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Publisher getPublisher(int id) {

        Session session = sessionFactory.getCurrentSession();

        Publisher publisher = (Publisher) session.get(Publisher.class, id);
        return publisher;
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {

        Session session = sessionFactory.getCurrentSession();
        session.save(publisher);
        return publisher;
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {

        Session session = sessionFactory.getCurrentSession();
        Publisher pub = (Publisher) session.get(Publisher.class, publisher.getId());
        pub.setPublisherName(publisher.getPublisherName());
        session.update(pub);
        return pub;
    }

    @Override
    public void deletePublisher(int id) {

        Session session = sessionFactory.getCurrentSession();
        Publisher publisher = (Publisher) session.get(Publisher.class, id);
        session.delete(publisher);
    }

    @Override
    public List<Publisher> getAll() {

        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Publisher> list = (List<Publisher>) session.createQuery("From " + Publisher.class.getName()).list();
        return list;
    }

}
