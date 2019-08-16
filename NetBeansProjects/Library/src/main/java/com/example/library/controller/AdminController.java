/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author vuduchiep
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/home")
    public String getHome() {
       
        return "admin/home";
    }

    @RequestMapping(value = "/author")
    public String getAuthor() {
        return "admin/author";
    }

    @RequestMapping("/genre")
    public String getGenre() {
        return "admin/genre";
    }

    @RequestMapping("/publisher")
    public String getPublisher() {
        return "admin/publisher";
    }
}
