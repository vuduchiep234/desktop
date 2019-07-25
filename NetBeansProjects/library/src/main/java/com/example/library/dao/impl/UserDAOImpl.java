/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao.impl;

import com.example.library.dao.UserDAO;
import com.example.library.entities.Role;
import com.example.library.entities.User;
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
public class UserDAOImpl implements UserDAO{

    @Autowired 
    private SessionFactory sessionFactory;
    
    
    @Override
    public User get(int id) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.get(User.class, id);
        return user;
    }

    @Override
    public User addUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        session.clear();
//        Session session1 = sessionFactory.getCurrentSession();
        User u = (User)session.get(User.class, user.getId());
        return u;
    }

    @Override
    public User updateUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        User u = (User)session.get(User.class, user.getId());
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRole_id(user.getRole_id());
        Role role = (Role)session.get(Role.class, u.getRole_id());
        u.setRole(role);
        session.update(u);
        return u;
    }

    @Override
    public void deleteUser(int id) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.get(User.class, id);
        session.delete(user);
    }

    @Override
    public List<User> getAll() {

        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<User> list = (List<User>)session.createQuery("From " + User.class.getName()).list();
        return list;
    }
    
}
