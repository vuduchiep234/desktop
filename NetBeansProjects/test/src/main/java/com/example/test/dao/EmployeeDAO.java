/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.dao;

import com.example.test.entities.Employee;
import java.util.ArrayList;
import java.util.Collection;
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
public class EmployeeDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    public Employee getEmployee(int id){
        Session session = sessionFactory.getCurrentSession();
        Employee emp = (Employee)session.get(Employee.class, id);
        return emp;
    }
    
    public Employee addEmployee(Employee emp){
        Session session = sessionFactory.getCurrentSession();
        session.save(emp);
        return emp;
    }
    
    public Employee updateEmployee(Employee e){
        Session session = sessionFactory.getCurrentSession();
        Employee emp =  (Employee)session.get(Employee.class, e.getId());
        emp.setName(e.getName());
        emp.setAddress(e.getAddress());
        session.update(emp);
        return emp;
    }
    
    public void deleteEmployee(int id){
        Session session = sessionFactory.getCurrentSession();
        Employee emp =  (Employee)session.get(Employee.class, id);
        session.delete(emp);
    }
    
    public List<Employee> getAll(){
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Employee> list =  (List<Employee>)session.createQuery(" FROM " + Employee.class.getName()).list();
        return list;
    }
    
}
