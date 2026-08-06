package com.ege.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class BookRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

    public BookRequest() {}   // Jackson needs a no-args constructor

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public BigDecimal getPrice() { return price; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
