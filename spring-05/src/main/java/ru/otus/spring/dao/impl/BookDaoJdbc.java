package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Book> getAllBooks() {
        var query = "select * from book";
        return jdbc.query(query, bookRowMapper());
    }

    @Override
    public List<Book> findBooksByName(String name) {
        var query = "select * from book where name like :name";
        var params = Map.of("name", name + "%");
        return jdbc.query(query, params, bookRowMapper());
    }

    @Override
    public List<Book> findBooksByGenreId(long genreId) {
        var query = "select * from book where genre_id = :genreId";
        var params = Map.of("genreId", genreId);
        return jdbc.query(query, params, bookRowMapper());
    }

    @Override
    public List<Book> findBooksByAuthorId(long authorId) {
        var query = "select * from book where author_id = :authorId";
        var params = Map.of("authorId", authorId);
        return jdbc.query(query, params, bookRowMapper());
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> new Book(rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getLong(4));
    }
}
