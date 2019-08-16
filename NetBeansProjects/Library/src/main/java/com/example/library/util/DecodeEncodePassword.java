/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

/**
 *
 * @author vuduchiep
 */

@Component
public class DecodeEncodePassword {

    public String encode(String pass) {
        // Encode data on your side using BASE64
        byte[] bytesEncoded = Base64.encodeBase64(pass.getBytes());
        String passEncoded = bytesEncoded.toString();
        System.out.println("encoded value is " + new String(bytesEncoded));
        return passEncoded;
    }

    public String decode(String bytesEncoded) {
        // Decode data on other side, by processing encoded data
        byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);
        String pass = new String(valueDecoded);
        System.out.println("Decoded value is " + pass);
        return pass;

    }

}
