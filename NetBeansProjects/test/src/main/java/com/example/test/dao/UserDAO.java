/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.dao;

import com.example.test.entities.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vuduchiep
 */

@Repository
public class UserDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings({
        "unchecked", "rawtypes"
    })
    public User loadUserByUsername(String username){
        
        Session session = sessionFactory.getCurrentSession();
        String hql = "From User where username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        
        List<User> users = query.list();
        
        if(users != null && users.size() > 0){
            return users.get(0);
        }
        else{
            return null;
        }
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean checkLogin(User account){
        
        Session session = sessionFactory.getCurrentSession();
        
        String hql = "From User Where username = :username And password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("username", account.getUsername());
        query.setParameter("password", account.getPassword());
        
        List<User> users = query.list();
        
        if(users != null && users.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
