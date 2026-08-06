package com.ege.bookstore.dto;

import java.math.BigDecimal;

public class BookResponse {

    private final Long id;
    private final String title;
    private final String author;
    private final BigDecimal price;

    public BookResponse(Long id, String title, String author, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public BigDecimal getPrice() { return price; }
}