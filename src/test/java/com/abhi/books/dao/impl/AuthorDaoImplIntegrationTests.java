package com.abhi.books.dao.impl;

import com.abhi.books.TestUtil;
import com.abhi.books.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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
        testAuthorDaoImpl.safeCreate(testAuthor);
        Optional<Author> queryResultAuthor = testAuthorDaoImpl.findById(testAuthor.getId());

        /*
         * Verifications -
         * Result is present, and same as original object
         */
        assertThat(queryResultAuthor).isPresent();
        assertThat(queryResultAuthor.get()).isEqualTo(testAuthor);
    }

    @Test
    public void test_CreateAuthorList_QueryPastAuthors() {

        List<Author> authorList = TestUtil.getTestAuthorList();
        List<Author> pastAuthors = TestUtil.getPastAuthors();
        int numberOfPastAuthors = pastAuthors.size();

        for (Author author : authorList) {
            testAuthorDaoImpl.safeCreate(author);
        }
        List<Author> queryResultPastAuthors = testAuthorDaoImpl.findPastAuthors();

        assertThat(queryResultPastAuthors).hasSize(numberOfPastAuthors)
                .containsExactly(pastAuthors.toArray(new Author[0]));
    }
}
