/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.entities;

/**
 *
 * @author vuduchiep
 */
 public class BookInfo{
        private Book book;
        private int[] authors_id;
        private int[] genres_id;

        public BookInfo() {
        }

        public BookInfo(Book book, int[] authors_id, int[] genres_id) {
            this.book = book;
            this.authors_id = authors_id;
            this.genres_id = genres_id;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public int[] getAuthors_id() {
            return authors_id;
        }

        public void setAuthors_id(int[] authors_id) {
            this.authors_id = authors_id;
        }

        public int[] getGenres_id() {
            return genres_id;
        }

        public void setGenres_id(int[] genres_id) {
            this.genres_id = genres_id;
        }
        
        
    }

