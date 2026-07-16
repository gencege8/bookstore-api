package com.ege.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ege.bookstore.entity.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByPriceLessThan(BigDecimal price);
    Optional<Book> findByTitle(String title);

}
