package com.abhi.books.dao;

import com.abhi.books.domain.Book;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface BookDao {

    public void create(Book book);

    public Optional<Book> findByIsbn(String isbn);
}
