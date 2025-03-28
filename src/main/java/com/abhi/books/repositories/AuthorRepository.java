package com.abhi.books.repositories;

import com.abhi.books.domain.entities.AuthorEntity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "authors")
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    Iterable<AuthorEntity> ageLessThan(int age);

    /* Ignores dead authors where age = 0 */
    @Query("SELECT a FROM AuthorEntity a WHERE a.age < ?1 AND a.age > 0")
    Iterable<AuthorEntity> currentAuthorsWithAgeLessThan(int age);
}
