/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.example.library.service.MailService;

/**
 *
 * @author vuduchiep
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public SimpleMailMessage sendSimpleMessage(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();

//        message.setFrom(to);
        
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);

        return message;
    }

    @Override
    public MimeMessage sendMessageWithAttachment(String to, String subject, String content, String pathToAttachment) {

        MimeMessage mess = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mess, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));

            helper.addAttachment("demo", file);

        } catch (MessagingException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        mailSender.send(mess);
        return mess;

    }

}
