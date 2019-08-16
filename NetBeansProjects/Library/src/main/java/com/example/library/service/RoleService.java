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
 * @role vuduchiep
 */
public interface RoleService {
    
    public Role get(int id);
    
    public Role add(Role role);
    
    public Role update(Role role);
    
    public Role delete(int id);
    
    public List<Role> getAll();
    
    public List<Role> search(String roleType);
}
