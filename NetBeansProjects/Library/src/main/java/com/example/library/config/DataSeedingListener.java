/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.config;

import com.example.library.dao.RoleDAO;
import com.example.library.dao.UserDAO;
import com.example.library.entities.Role;
import com.example.library.entities.User;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author vuduchiep
 */
@Component
@Transactional
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        
        if (roleDAO.search("ROLE_ADMIN") == null) {
            Role role = new Role();
            role.setRoleType("ROLE_ADMIN");
            roleDAO.addRole(role);
        }

        if (roleDAO.search("ROLE_USER") == null) {
            Role role = new Role();
            role.setRoleType("ROLE_USER");
            roleDAO.addRole(role);
        }

        // Admin account
        if (userDAO.loadUserByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder().encode("12345678"));
            admin.setRole_id(1);
//            Role role = new Role();
//            role.add(roleDAO.findByName("ROLE_ADMIN"));
//            role.add(roleDAO.findByName("ROLE_MEMBER"));
//            admin.setRole(role);
            userDAO.addUser(admin);
        }

        // Member account
        if (userDAO.loadUserByEmail("user@gmail.com") == null) {
            User user = new User();
            user.setEmail("user@gmail.com");
            user.setUsername("user");
            user.setPassword(passwordEncoder().encode("12345678"));
            user.setRole_id(2);
            userDAO.addUser(user);
        }
    }

}
