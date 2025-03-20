package com.abhi.books.dao.impl;

import com.abhi.books.TestUtil;
import com.abhi.books.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTests {

    private final AuthorDaoImpl testAuthorDaoImpl;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl testAuthorDaoImpl) {
        this.testAuthorDaoImpl = testAuthorDaoImpl;
    }

    @Test
    public void test_CreateAuthor_QuerySameAuthor() {

        Author testAuthor = TestUtil.getTestAuthor();

        /*
         * Tests -
         * Write into DB, and then Query back from DB
         */
        testAuthorDaoImpl.create(testAuthor);
        Optional<Author> queryResultAuthor = testAuthorDaoImpl.findById(testAuthor.getId());

        /*
         * Verifications -
         * Result is present, and same as original object
         */
        assertThat(queryResultAuthor).isPresent();
        assertThat(queryResultAuthor.get()).isEqualTo(testAuthor);
    }
}
