/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.service.MailService;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
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
public class MailController {
    
    @Autowired 
    private MailService mailService;
    
    @RequestMapping(value = "/sendSimpleEmail",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public SimpleMailMessage send(@RequestBody Map<String, String> data){
        return mailService.sendSimpleMessage(data.get("to").toString(), data.get("subject").toString(), data.get("content"));
    }
    
    
    @RequestMapping(value = "/sendAttachmentEmail",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public MimeMessage sendAttachmentEmail(@RequestBody Map<String, String> data){
        return mailService.sendMessageWithAttachment(data.get("to").toString(), data.get("subject").toString(), data.get("content"), data.get("path"));
    }
}
