/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.RoleDAO;
import com.example.library.entities.Role;
import com.example.library.service.RoleService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @role vuduchiep
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role get(int id) {

        Role role = roleDAO.getRole(id);
        return role;
    }

    @Override
    public Role add(Role role) {

        Role au = roleDAO.addRole(role);
        return au;
    }

    @Override
    public Role update(Role role) {

        Role au = roleDAO.updateRole(role);
        return au;
    }

    @Override
    public Role delete(int id) {

        return roleDAO.deleteRole(id);
    }

    @Override
    public List<Role> getAll() {

        List<Role> list = roleDAO.getAll();
        return list;
    }

    @Override
    public List<Role> search(String roleType) {

        return roleDAO.search(roleType);
    }

}
