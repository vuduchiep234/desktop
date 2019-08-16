/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Upload File
 * @author vuduchiep
 */

// Tu dong rang buoc cac thuoc tinh duoc dinh nghia trong application.properties
// voi frefix=file
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
    
    
}
