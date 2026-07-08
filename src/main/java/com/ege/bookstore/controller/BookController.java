package com.ege.bookstore.controller;

import com.ege.bookstore.entity.Book;
import com.ege.bookstore.service.BookService;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;     // controller injects SERVICE,
    // never the repository directly
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping                            // GET /api/books
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")                   // GET /api/books/5
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping                           // POST /api/books
    @ResponseStatus(HttpStatus.CREATED)    // respond 201, not 200
    public Book createBook(@Valid @RequestBody Book book) {
        return service.createBook(book);
    }

    @DeleteMapping("/{id}")                // DELETE /api/books/5
    @ResponseStatus(HttpStatus.NO_CONTENT) // respond 204
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }

    @GetMapping("/by-author")              // GET /api/books/by-author?author=Tolkien
    public List<Book> getByAuthor(@RequestParam String author) {
        return service.getBooksByAuthor(author);
    }
    @GetMapping("/by-title")
    public Book getByTitle(@RequestParam String title){
        return service.getBookByTitle(title);
    }
}