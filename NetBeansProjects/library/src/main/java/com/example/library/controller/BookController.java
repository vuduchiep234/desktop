/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Book;
import com.example.library.entities.Book;
import com.example.library.service.BookService;
import com.example.library.service.impl.BookServiceImpl;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vuduchiep
 */
@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService = new BookServiceImpl();
    
    // Get
    @RequestMapping(value = "/books/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Book get(@PathVariable("id") int id){
        Book book = bookService.getBook(id);
        return book;
    }
    
     // Post
    @RequestMapping(value = "/books",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Book add(@RequestBody Book book){
        return bookService.addBook(book);
    }
    
    // Patch
    @RequestMapping(value = "/books",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Book update(@RequestBody Book book){
        return bookService.updateBook(book);
    }
    
    // Delete
    @RequestMapping(value = "/books/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        bookService.deleteBook(id);
        return "Delete Successfully";
    }
    
    // Get All
    @RequestMapping(value = "/books",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Collection<Book> getAll(){
        Collection<Book> list = bookService.getAllBooks();
        return list;
    }
}
