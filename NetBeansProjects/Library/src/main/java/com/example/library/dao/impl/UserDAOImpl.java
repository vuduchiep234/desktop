/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao.impl;

import com.example.library.dao.UserDAO;
import com.example.library.entities.User;
import com.example.library.util.DecodeEncodePassword;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vuduchiep
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DecodeEncodePassword dep;

    @Override
    public User getUser(int id) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        return user;
    }

    @Override
    public User addUser(User user) {

        Session session = sessionFactory.getCurrentSession();
//        user.setRole_id(2);
        String encodePass = passwordEncoder().encode(user.getPassword());

        user.setPassword(encodePass);
        session.save(user);
        session.clear();
        return getUser(user.getId());
    }

    @Override
    public User updateUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        User u = (User) session.get(User.class, user.getId());
        u.setUsername(user.getUsername());
        u.setPassword(passwordEncoder().encode(user.getPassword()));
        u.setRole_id(user.getRole_id());
        session.update(u);
        session.clear();
        return getUser(u.getId());
//        return u;
    }

    @Override
    public User deleteUser(int id) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        return user;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<User> getAll() {

        Session session = sessionFactory.getCurrentSession();

        List<User> list = (List<User>) session.createQuery("From " + User.class.getName()).list();
        return list;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean checkLogin(User user) {
//        Session session = this.sessionFactory.getCurrentSession();
//
//        String hql = "From " + User.class.getName() + " Where username = :username AND password = :password";
//        Query query = session.createQuery(hql);
//        
//        
//        query.setParameter("username", user.getUsername());
//        query.setParameter("password", passwordEncoder().encode(user.getPassword()));
//
//        List<User> users = query.list();
//        System.out.println("checkLogin");

        User u = loadUserByUsername(user.getUsername());

        if (u != null) {
            if (passwordEncoder().matches(user.getPassword(), u.getPassword())) {
                return true;

            }
        }
        return false;

//        if (users != null && users.size() > 0) {
//            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public User loadUserByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "From " + User.class.getName() + " Where username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);

        List<User> users = query.list();

        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
    
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public User loadUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "From " + User.class.getName() + " Where email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);

        List<User> users = query.list();

        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
