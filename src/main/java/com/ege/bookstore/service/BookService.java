package com.ege.bookstore.service;

import com.ege.bookstore.dto.BookRequest;
import com.ege.bookstore.dto.BookResponse;
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
    public List<BookResponse> getAllBooks() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return toResponse(book);
    }
    @Transactional
    public BookResponse createBook(BookRequest request) {
        Book saved = repository.save(toEntity(request));   // save returns the saved entity, now WITH its id
        return toResponse(saved);
    }
    @Transactional
    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }
    @Transactional
    public BookResponse updateBook(Long id, BookRequest updated) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));  // 1. fetch or 404
        existing.setTitle(updated.getTitle());                      // 2. copy fields onto it
        existing.setAuthor(updated.getAuthor());
        existing.setPrice(updated.getPrice());
        Book saved = repository.save(existing);                           // 3. save
        return toResponse(saved);
    }
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByPriceLessThan(BigDecimal price){
        return repository.findByPriceLessThan(price).stream().map(this::toResponse).toList();
    }
    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByAuthor(String author) {
        return repository.findByAuthor(author).stream().map(this::toResponse).toList();
    }
    @Transactional(readOnly = true)
    public BookResponse getBookByTitle(String title){
        Book book = repository.findByTitle(title).orElseThrow(()->new BookNotFoundException(title));
        return toResponse(book);
    }
    private Book toEntity(BookRequest request) {
        return new Book(request.getTitle(), request.getAuthor(), request.getPrice());
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice());
    }
}