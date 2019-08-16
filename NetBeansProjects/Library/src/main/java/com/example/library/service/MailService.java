/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author vuduchiep
 */
public interface MailService {
    
    public SimpleMailMessage sendSimpleMessage(String to, String subject, String content);
    
    public MimeMessage sendMessageWithAttachment(String to, String subject, String content, String pathToAttachment);
}
