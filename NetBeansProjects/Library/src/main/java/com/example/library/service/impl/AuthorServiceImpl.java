/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.AuthorDAO;
import com.example.library.entities.Author;
import com.example.library.service.AuthorService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuduchiep
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public Author get(int id) {

        Author author = authorDAO.getAuthor(id);
        return author;
    }

    @Override
    public Author add(Author author) {

        Author au = authorDAO.addAuthor(author);
        return au;
    }

    @Override
    public Author update(Author author) {

        Author au = authorDAO.updateAuthor(author);
        return au;
    }

    @Override
    public Author delete(int id) {

        return authorDAO.deleteAuthor(id);
    }

    @Override
    public List<Author> getAll() {

        List<Author> list = authorDAO.getAll();
        return list;
    }

    @Override
    public List<Author> search(String name) {

        return authorDAO.search(name);
    }

}
