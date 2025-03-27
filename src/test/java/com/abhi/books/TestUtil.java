package com.abhi.books;

import com.abhi.books.domain.Author;
import com.abhi.books.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static final long COMMON_AUTHOR_ID = 1L;
    private static final int IS_DEAD = 0;
    private static final int TEST_AGE = 80;

    public static Author getTestAuthor() {
        return Author.builder()
                .id(COMMON_AUTHOR_ID)
                .name("Ursula le Guin")
                .age(IS_DEAD)
                .build();
    }

    public static List<Author> getTestAuthorList() {
        List<Author> authorList = new ArrayList<Author>();
        authorList.add(getTestAuthor());
        authorList.add(
          Author.builder()
                  .id(2L)
                  .name("George RR Martin")
                  .age(70)
                  .build()
        );
        authorList.add(
          Author.builder()
                  .id(3L)
                  .name("JRR Tolkein")
                  .age(IS_DEAD)
                  .build()
        );
        return authorList;
    }

    public static List<Author> getPastAuthors() {
        List<Author> allAuthorList = getTestAuthorList();
        List<Author> pastAuthorList = new ArrayList<Author>();

        for (Author author : allAuthorList) {
            if (author.getAge() == IS_DEAD) {
                pastAuthorList.add(author);
            }
        }

        return pastAuthorList;
    }

    public static Book getTestBook() {
        return Book.builder()
                .isbn("M7B59")
                .title("The Left Hand of Darkness")
                .author(getTestAuthor())
                .build();
    }

    public static int getTestAge() {
        return TEST_AGE;
    }

    public static int get_NoOfAuthors_AgeLessThan(Boolean includePast) {
        List<Author> allAuthorList = getTestAuthorList();
        int num = 0;

        for (Author author : allAuthorList) {
            if ( (author.getAge() < TEST_AGE) &&
                    (author.getAge() > 0 || includePast == Boolean.TRUE)) {
                num = num + 1;
            }
        }
        return num;
    }
}
