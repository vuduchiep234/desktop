package com.example.test.controller;

import com.example.test.service.EmployeeService;
import com.example.test.entities.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class  EmployeeController{
    
    @Autowired
    private EmployeeService empService;
    
    // GETALL
    @RequestMapping(value = "/employees",
            method = RequestMethod.GET,
            produces = {
                                MediaType.APPLICATION_JSON_VALUE,
                                MediaType.APPLICATION_XML_VALUE})
    
    @ResponseBody
    public List<Employee> getAll(){
        List<Employee> list = empService.getAll();
        return list;
    }
    
    // GET
    @RequestMapping(value = "/employees/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Employee get(@PathVariable("id") int id){
        return  empService.get(id);
    }
    
    
    // POST
    @RequestMapping(value = "/employees",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ ResponseBody
    public Employee post(@RequestBody Employee emp){
        return empService.add(emp);
    }
    
    
    // PUT
    @RequestMapping(value = "/employees",
            method = RequestMethod.PUT,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Employee put(@RequestBody Employee emp){
        return empService.update(emp);
    }
    
    
    // DELETE
    @RequestMapping(value = "/employees/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        empService.delete(id);
        return "Delete Successfully";
    } 
    
}