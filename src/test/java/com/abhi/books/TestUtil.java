package com.abhi.books;

import com.abhi.books.domain.Author;
import com.abhi.books.domain.Book;

public class TestUtil {

    public static Author getTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Ursula le Guin")
                .age(0) // Dead :(
                .build();
    }

    public static Book getTestBook() {
        return Book.builder()
                .isbn("M7B59")
                .title("The Left Hand of Darkness")
                .authorId(1L)
                .build();
    }
}
