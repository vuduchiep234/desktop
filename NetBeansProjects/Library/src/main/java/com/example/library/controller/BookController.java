/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Book;
import com.example.library.service.BookService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private BookService bookService;

    @RequestMapping(value = "/books/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Book get(@PathVariable("id") int id) {

        return bookService.get(id);
    }

    @RequestMapping(value = "/books",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Book post(@RequestBody Map<String, Object> data) {
        Book book = new Book();
        book.setTitle(data.get("title").toString());
        book.setPublishedYear(data.get("publishedYear").toString());
        book.setPublisher_id((int) data.get("publisher_id"));
        ArrayList<Integer> authors_id = (ArrayList<Integer>) data.get("authors_id");
        ArrayList<Integer> genres_id = (ArrayList<Integer>) data.get("genres_id");

        System.out.println(authors_id);
        return bookService.add(book, authors_id, genres_id);
    }

    @RequestMapping(value = "/books",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Book patch(@RequestBody Map<String, Object> data) {

        Book book = new Book();
        book.setId((int)data.get("id"));
        book.setTitle(data.get("title").toString());
        book.setPublishedYear(data.get("publishedYear").toString());
        book.setPublisher_id((int) data.get("publisher_id"));
        ArrayList<Integer> authors_id = (ArrayList<Integer>) data.get("authors_id");
        ArrayList<Integer> genres_id = (ArrayList<Integer>) data.get("genres_id");

        System.out.println(authors_id);
        return bookService.update(book, authors_id, genres_id);
//        return bookService.update(book);
    }

    @RequestMapping(value = "/books/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Book delete(@PathVariable("id") int id) {

        return bookService.delete(id);
//        return "Delete Successfully";
    }

    @RequestMapping(value = "/books",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Collection<Book> getAll() {

        return bookService.getAll();
    }
}
