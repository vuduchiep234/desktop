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
public class MyController {
    
    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @RequestMapping("/register")
    public String getRegister() {
        return "register";
    }
}
