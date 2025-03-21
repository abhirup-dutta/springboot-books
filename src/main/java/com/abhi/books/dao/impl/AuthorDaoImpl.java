package com.abhi.books.dao.impl;

import com.abhi.books.dao.AuthorDao;
import com.abhi.books.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * Check if the author entry is already present,
     * Only if not, then create author entity in DB
     * If present, do nothing
     */
    public void safeCreate(Author author) {
        List<Author> queryResultAuthorList = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ?",
                new AuthorRowMapper(),
                author.getId()
        );
        if(queryResultAuthorList.isEmpty()) {
            jdbcTemplate.update(
                    "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                    author.getId(),
                    author.getName(),
                    author.getAge()
            );
        }
    }

    public Optional<Author> findById(long authorId) {
        List<Author> authorList = jdbcTemplate.query(
          "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
          new AuthorRowMapper(),
          authorId
        );
        return authorList.stream().findFirst();
    }

    public List<Author> findPastAuthors() {
        return jdbcTemplate.query(
          "SELECT id, name, age FROM authors WHERE age = ?",
          new AuthorRowMapper(),
          IS_DEAD
        );
    }

    /*
     * RowMapper converts a Result Set (returned entity after Database Query)
     * and turns it into a Java Object.
     * It is a callback object, that is passed as a parameter while querying
     * so that the results are filled into the RowMapper
     * However, we have to manually define the mapRow() method
     * where we map the Result Set Data referred by a string
     * into our specific Java Object.
     * The rowNum is not used by us, this will be filled internally
     * where each object will be mapped to a row number of a returned list.
     * Basically, we are defining what happens to each row, and that
     * will be replicated across all rows.
     * This is a nested class because each class's RowMapper will have a
     * different mapping and has to be defined separately
     * inside their respective class.
     */
    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
