package com.ege.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ege.bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    
    Optional<Book> findByTitle(String title);

}
