package com.abhi.books.repositories;

import com.abhi.books.TestUtil;
import com.abhi.books.domain.entities.AuthorEntity;
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
public class AuthorEntityRepositoryIntegrationTests {

    private final AuthorRepository testAuthorRepo;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository testAuthorRepo) {
        this.testAuthorRepo = testAuthorRepo;
    }

    @Test
    public void test_CreateAuthor_QuerySameAuthor() {

        AuthorEntity testAuthorEntity = TestUtil.getTestAuthor();

        /*
         * Tests -
         * Write into DB, and then Query back from DB
         */
        testAuthorRepo.save(testAuthorEntity);
        Optional<AuthorEntity> queryResultAuthor = testAuthorRepo.findById(testAuthorEntity.getId());

        /*
         * Verifications -
         * Result is present, and same as original object
         */
        assertThat(queryResultAuthor).isPresent();
        assertThat(queryResultAuthor.get()).isEqualTo(testAuthorEntity);
    }

    @Test
    public void test_CreateAuthorList_QueryAuthorsAgeLessThan() {

        /* Create List and populate DB */
        List<AuthorEntity> authorEntityList = TestUtil.getTestAuthorList();
        for (AuthorEntity authorEntity : authorEntityList) {
            testAuthorRepo.save(authorEntity);
        }

        /* Get Age Less Than Raw (Include past authors with age=0) */
        Iterable<AuthorEntity> queryResultAgeLessThanRaw =
                testAuthorRepo.ageLessThan(TestUtil.getTestAge());
        assertThat(queryResultAgeLessThanRaw)
                .hasSize(TestUtil.get_NoOfAuthors_AgeLessThan(Boolean.TRUE));

        /* Get Age Less Than (Only current/alive authors with age>0) */
        Iterable<AuthorEntity> queryResultAgeLessThanCurrent =
                testAuthorRepo.currentAuthorsWithAgeLessThan(TestUtil.getTestAge());
        assertThat(queryResultAgeLessThanCurrent)
                .hasSize(TestUtil.get_NoOfAuthors_AgeLessThan(Boolean.FALSE));

    }
}
