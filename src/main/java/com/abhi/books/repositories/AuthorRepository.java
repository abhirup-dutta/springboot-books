package com.abhi.books.repositories;

import com.abhi.books.domain.Author;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "authors")
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Iterable<Author> ageLessThan(int age);

    /* Ignores dead authors where age = 0 */
    @Query("SELECT a FROM Author a WHERE a.age < ?1 AND a.age > 0")
    Iterable<Author> currentAuthorsWithAgeLessThan(int age);
}
