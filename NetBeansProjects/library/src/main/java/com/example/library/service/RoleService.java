/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import com.example.library.entities.Role;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface RoleService {
    
    public Role getRole(int id);
    
    public Role addRole(Role role);
    
    public Role updateRole(Role role);
    
    public void deleteRole(int id);
    
    public List<Role> getAll();
}
