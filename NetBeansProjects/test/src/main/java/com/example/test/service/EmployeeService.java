/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.service;

import com.example.test.dao.EmployeeDAO;
import com.example.test.entities.Employee;
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
public class EmployeeService {
    
    @Autowired
    private EmployeeDAO empDAO;
    
    public Employee get(int id){
        return empDAO.getEmployee(id);
    }
    
    public Employee add(Employee emp){
        return empDAO.addEmployee(emp);
    }
    
    public Employee update(Employee emp){
        return empDAO.updateEmployee(emp);
    }
    
    public void delete(int id){
        empDAO.deleteEmployee(id);
    }
    
    public List<Employee> getAll(){
        return empDAO.getAll();
    }
}
