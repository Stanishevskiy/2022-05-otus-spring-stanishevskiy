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
    public void addBook(String name, long genreId) {
        var query = "insert into book (name, genre_id) " +
                "values (:name, :genre_id)";
        var params = Map.of("name", name, "genre_id", genreId);
        jdbc.update(query, params);
    }

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
    public List<Book> findBooksByIds(List<Long> ids) {
        var query = "select * from book where id in (:ids)";
        var params = Map.of("ids", ids);
        return jdbc.query(query, params, bookRowMapper());
    }

    @Override
    public List<Book> findBooksByGenreId(long genreId) {
        var query = "select * from book where genre_id = :genreId";
        var params = Map.of("genreId", genreId);
        return jdbc.query(query, params, bookRowMapper());
    }

    @Override
    public void updateBook(Book book) {
        var query = "update book " +
                "set name = :name, genre_id = :genreId " +
                "where id = :id";
        var params = Map.of("name", book.name(),
                "genreId", book.genreId(),
                "id", book.id());
        jdbc.update(query, params);
    }

    @Override
    public void deleteBookById(long id) {
        var query = "delete from book where id = :id";
        var params = Map.of("id", id);
        jdbc.update(query, params);
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> new Book(rs.getLong(1),
                rs.getString(2),
                rs.getLong(3));
    }
}
