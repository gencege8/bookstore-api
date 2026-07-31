package com.ege.bookstore.service;

import com.ege.bookstore.entity.Book;
import com.ege.bookstore.exception.BookNotFoundException;
import com.ege.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {   // Spring injects the
        this.repository = repository;                  // generated implementation
    }
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    @Transactional
    public Book createBook(Book book) {
        return repository.save(book);   // save returns the saved entity, now WITH its id
    }
    @Transactional
    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }
    @Transactional
    public Book updateBook(Long id, Book updated) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));  // 1. fetch or 404
        existing.setTitle(updated.getTitle());                      // 2. copy fields onto it
        existing.setAuthor(updated.getAuthor());
        existing.setPrice(updated.getPrice());
        return repository.save(existing);                           // 3. save
    }
    @Transactional(readOnly = true)
    public List<Book> getBooksByPriceLessThan(BigDecimal price){
        return repository.findByPriceLessThan(price);
    }
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(String author) {
        return repository.findByAuthor(author);
    }
    @Transactional(readOnly = true)
    public Book getBookByTitle(String title){
        return repository.findByTitle(title).orElseThrow(()->new BookNotFoundException(title));
    }
}