/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao.impl;

import com.example.library.dao.RoleDAO;
import com.example.library.entities.Role;
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
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Role getRole(int id) {

       Session session = sessionFactory.getCurrentSession();
       Role role = (Role)session.get(Role.class, id);
       return role;
    }

    @Override
    public Role addRole(Role role) {

        Session session = sessionFactory.getCurrentSession();
        session.save(role);
        return role;
    }

    @Override
    public Role updateRole(Role role) {

        Session session = sessionFactory.getCurrentSession();
        Role ro = (Role)session.get(Role.class, role.getId());
        ro.setType(role.getType());
        session.update(ro);
        return ro;
    }

    @Override
    public void deleteRole(int id) {

        Session session = sessionFactory.getCurrentSession();
        Role role = (Role)session.get(Role.class, id);
        session.delete(role);
    }

    @Override
    public List<Role> getAll() {

        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Role> list = (List<Role>)session.createQuery("From " + Role.class.getName()).list();
        return list;
    }
    
}
