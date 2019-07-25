/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.AuthorDAO;
import com.example.library.dao.impl.AuthorDAOImpl;
import com.example.library.entities.Author;
import com.example.library.service.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuduchiep
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public Author get(int id) {

        return authorDAO.getAuthor(id);
    }

    @Override
    public Author add(Author author) {

        return authorDAO.addAuthor(author);
    }

    @Override
    public Author update(Author author) {

        return authorDAO.updateAuthor(author);
    }

    @Override
    public void delete(int id) {

        authorDAO.deleteAuthor(id);
    }

    @Override
    public List<Author> getAll() {

        return authorDAO.getAll();
    }

}
