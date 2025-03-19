package com.abhi.books.dao.impl;

import com.abhi.books.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl testAuthorDao;

    @Test
    public void test_CreateAuthor() {
        Author testAuthor = Author.builder()
                .id(1L)
                .name("Ursula le Guin")
                .age(0) // Dead :(
                .build();

        /*
         * Within the authorDaoTest, there is a jdbcTemplate.
         * This jdbcTemplate is mocked and inserted into authorDaoTest.
         * When create() is invoked, it uses the jdbcTemplate for DB update
         * However, here a mocked jdbcTemplate is used.
         */
        testAuthorDao.create(testAuthor);

        /*
         * Here, verify verifies if the mocked entity was interacted with
         * in the previous statement while invoking create()
         * The manner of interaction is update() with the exact same parameters
         * ie, we are checking if update() was invoked on the mocked entity
         * jdbcTemplate inside our authorDaoTest
         * Argument matchers can be any() for any value and
         * eq() for a specific value
         */
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(testAuthor.getId()),
                eq(testAuthor.getName()),
                eq(testAuthor.getAge())
        );
    }

    @Test
    public void test_FindById() {

        long testAuthorId = 1L;

        testAuthorDao.findById(testAuthorId);

        verify(jdbcTemplate).query(
          eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
          any(AuthorDaoImpl.AuthorRowMapper.class),
          eq(testAuthorId)
        );
    }
}
