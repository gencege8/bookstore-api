package com.ege.bookstore.service;

import com.ege.bookstore.entity.Book;
import com.ege.bookstore.exception.BookNotFoundException;
import com.ege.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {   // Spring injects the
        this.repository = repository;                  // generated implementation
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book createBook(Book book) {
        return repository.save(book);   // save returns the saved entity, now WITH its id
    }

    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }
    public List<Book> getBooksByPriceLessThan(BigDecimal price){
        return repository.findByPriceLessThan(price);
    }
    public List<Book> getBooksByAuthor(String author) {
        return repository.findByAuthor(author);
    }
    public Book getBookByTitle(String title){
        return repository.findByTitle(title).orElseThrow(()->new BookNotFoundException(title));
    }
}