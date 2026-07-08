package com.ege.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book not found with id: " + id);
    }
    public BookNotFoundException(String title) {
        super("Book not found with title: " + title);
    }

}