package com.abhi.books.dao.impl;

import com.abhi.books.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    /*
     * See explanations in AuthorDaoImplTests
     */

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl bookDaoTest;

    @Test
    public void test_CreateBook() {
        Book testBook = Book.builder()
                .isbn("M7B59")
                .title("The Left Hand of Darkness")
                .authorId(1L)
                .build();

        this.bookDaoTest.create(testBook);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq(testBook.getIsbn()),
                eq(testBook.getTitle()),
                eq(testBook.getAuthorId())
        );
    }
}
