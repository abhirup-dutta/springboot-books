package com.abhi.books.repositories;

import com.abhi.books.TestUtil;
import com.abhi.books.domain.entities.AuthorEntity;
import com.abhi.books.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookEntityRepositoryIntegrationTests {

    /*
     * AuthorDao is required because in the DB,
     * Books have a foreign key constraint on Author ID
     * Such that this ID has to be present in the Authors Table,
     * So, we need to first create the Authors' Table.
     *
     * Now, since we are not testing AuthorDao,
     * we can use the Dao alone, and not the Impl.
     */

    private final BookRepository testBookRepo;
    private final AuthorRepository supportAuthorRepo;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository testBookRepo, AuthorRepository supportAuthorRepo) {
        this.testBookRepo = testBookRepo;
        this.supportAuthorRepo = supportAuthorRepo;
    }

    @Test
    public void test_CreateBook_QuerySameBook() {

        BookEntity testBookEntity = TestUtil.getTestBook();
        long authorIdOfBook = testBookEntity.getAuthorEntity().getId();
        AuthorEntity supportAuthorEntity = TestUtil.getTestAuthor();
        supportAuthorEntity.setId(authorIdOfBook);

        /*
         * Tests -
         * Write into DB and then Query back from DB
         */
        supportAuthorRepo.save(supportAuthorEntity);
        testBookRepo.save(testBookEntity);
        Optional<BookEntity> queryResultBook = testBookRepo.findById(testBookEntity.getIsbn());

        /*
         * Verifications -
         * Result is present, and same as original object
         */
        assertThat(queryResultBook).isPresent();
        assertThat(queryResultBook.get()).isEqualTo(testBookEntity);
    }
}
