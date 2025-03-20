package com.abhi.books.dao;

import com.abhi.books.domain.Author;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AuthorDao {

    public void create(Author author);

    /*
     * Optional means - it can either return Author
     * or return "Empty" a special data entity of Optional
     * This is to make things type-safe and avoid Null returns
     */
    public Optional<Author> findById(long authorId);


}
