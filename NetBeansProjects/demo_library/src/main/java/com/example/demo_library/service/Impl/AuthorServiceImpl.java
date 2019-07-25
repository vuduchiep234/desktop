/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo_library.service.Impl;

import com.example.demo_library.dao.AuthorDAO;
import com.example.demo_library.dao.Impl.AuthorDAOImpl;
import com.example.demo_library.entities.Author;
import com.example.demo_library.service.AuthorService;
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
    private AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public Author getAuthor(int id) {

        return authorDAO.getAuthor(id);
    }

    @Override
    public Author addAuthor(Author author) {

        return authorDAO.addAuthor(author);
    }

    @Override
    public Author updateAuthor(Author author) {

        return authorDAO.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(int id) {

        authorDAO.deleteAuthor(id);
    }

    @Override
    public List<Author> getAll() {

        return authorDAO.getAll();
    }

}
