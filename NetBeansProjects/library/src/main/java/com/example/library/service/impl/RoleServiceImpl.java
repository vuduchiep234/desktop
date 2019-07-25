/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.RoleDAO;
import com.example.library.dao.impl.RoleDAOImpl;
import com.example.library.entities.Role;
import com.example.library.service.RoleService;
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
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDAO roleDAO = new RoleDAOImpl();
    
    @Override
    public Role getRole(int id) {

        Role role = roleDAO.getRole(id);
        return role;
    }

    @Override
    public Role addRole(Role role) {

        return roleDAO.addRole(role);
    }

    @Override
    public Role updateRole(Role role) {

        return roleDAO.updateRole(role);
    }

    @Override
    public void deleteRole(int id) {

        roleDAO.deleteRole(id);
    }

    @Override
    public List<Role> getAll() {

        List<Role> list = roleDAO.getAll();
        return list;
    }
    
}
