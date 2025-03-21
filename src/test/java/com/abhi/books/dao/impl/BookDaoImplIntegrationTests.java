package com.abhi.books.dao.impl;

import com.abhi.books.TestUtil;
import com.abhi.books.dao.AuthorDao;
import com.abhi.books.domain.Author;
import com.abhi.books.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTests {

    /*
     * AuthorDao is required because in the DB,
     * Books have a foreign key constraint on Author ID
     * Such that this ID has to be present in the Authors Table,
     * So, we need to first create the Authors' Table.
     *
     * Now, since we are not testing AuthorDao,
     * we can use the Dao alone, and not the Impl.
     */

    private final BookDaoImpl testBookDaoImpl;
    private final AuthorDao supportAuthorDao;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl testBookDaoImpl, AuthorDao supportAuthorDao) {
        this.testBookDaoImpl = testBookDaoImpl;
        this.supportAuthorDao = supportAuthorDao;
    }

    @Test
    public void test_CreateBook_QuerySameBook() {

        Book testBook = TestUtil.getTestBook();
        long authorIdOfBook = testBook.getAuthorId();
        Author supportAuthor = TestUtil.getTestAuthor();
        supportAuthor.setId(authorIdOfBook);

        /*
         * Tests -
         * Write into DB and then Query back from DB
         */
        supportAuthorDao.safeCreate(supportAuthor);
        testBookDaoImpl.create(testBook);
        Optional<Book> queryResultBook = testBookDaoImpl.findByIsbn(testBook.getIsbn());

        /*
         * Verifications -
         * Result is present, and same as original object
         */
        assertThat(queryResultBook).isPresent();
        assertThat(queryResultBook.get()).isEqualTo(testBook);
    }
}
