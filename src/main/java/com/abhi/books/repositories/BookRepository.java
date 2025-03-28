package com.abhi.books.repositories;

import com.abhi.books.domain.entities.BookEntity;
import jakarta.persistence.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "books")
public interface BookRepository extends CrudRepository<BookEntity, String> {
}
