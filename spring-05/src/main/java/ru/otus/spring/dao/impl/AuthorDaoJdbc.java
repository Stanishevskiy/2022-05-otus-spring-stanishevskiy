package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Author> findAllAuthors() {
        var query = "select * from author";
        return jdbc.query(query, authorRowMapper());
    }

    @Override
    public List<Author> findAuthorsByName(String name) {
        var query = "select * from author where name like :name";
        var params = Map.of("name", name + "%");
        return jdbc.query(query, params, authorRowMapper());
    }

    @Override
    public Author findAuthorById(long id) {
        var query = "select * from author where id = :id";
        var params = Map.of("id", id);
        return jdbc.queryForObject(query, params, authorRowMapper());
    }

    private RowMapper<Author> authorRowMapper() {
        return (rs, rowNum)
                -> new Author(rs.getLong(1), rs.getString(2));
    }
}
