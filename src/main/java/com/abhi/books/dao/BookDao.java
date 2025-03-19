package com.abhi.books.dao;

import com.abhi.books.domain.Book;

import java.util.Optional;

public interface BookDao {

    public void create(Book book);

    public Optional<Book> findByIsbn(String isbn);
}
