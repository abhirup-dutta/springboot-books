package com.abhi.books;

import com.abhi.books.domain.entities.AuthorEntity;
import com.abhi.books.domain.entities.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static final long COMMON_AUTHOR_ID = 1L;
    private static final int IS_DEAD = 0;
    private static final int TEST_AGE = 80;

    public static AuthorEntity getTestAuthor() {
        return AuthorEntity.builder()
                .id(COMMON_AUTHOR_ID)
                .name("Ursula le Guin")
                .age(IS_DEAD)
                .build();
    }

    public static List<AuthorEntity> getTestAuthorList() {
        List<AuthorEntity> authorEntityList = new ArrayList<AuthorEntity>();
        authorEntityList.add(getTestAuthor());
        authorEntityList.add(
          AuthorEntity.builder()
                  .id(2L)
                  .name("George RR Martin")
                  .age(70)
                  .build()
        );
        authorEntityList.add(
          AuthorEntity.builder()
                  .id(3L)
                  .name("JRR Tolkein")
                  .age(IS_DEAD)
                  .build()
        );
        return authorEntityList;
    }

    public static List<AuthorEntity> getPastAuthors() {
        List<AuthorEntity> allAuthorEntityList = getTestAuthorList();
        List<AuthorEntity> pastAuthorEntityList = new ArrayList<AuthorEntity>();

        for (AuthorEntity authorEntity : allAuthorEntityList) {
            if (authorEntity.getAge() == IS_DEAD) {
                pastAuthorEntityList.add(authorEntity);
            }
        }

        return pastAuthorEntityList;
    }

    public static BookEntity getTestBook() {
        return BookEntity.builder()
                .isbn("M7B59")
                .title("The Left Hand of Darkness")
                .authorEntity(getTestAuthor())
                .build();
    }

    public static int getTestAge() {
        return TEST_AGE;
    }

    public static int get_NoOfAuthors_AgeLessThan(Boolean includePast) {
        List<AuthorEntity> allAuthorEntityList = getTestAuthorList();
        int num = 0;

        for (AuthorEntity authorEntity : allAuthorEntityList) {
            if ( (authorEntity.getAge() < TEST_AGE) &&
                    (authorEntity.getAge() > 0 || includePast == Boolean.TRUE)) {
                num = num + 1;
            }
        }
        return num;
    }
}
