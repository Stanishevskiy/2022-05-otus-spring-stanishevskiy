package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorBookMergingDao;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorBookMergingDaoJdbc implements AuthorBookMergingDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void addAuthorBookIds(long authorId, long bookId) {
        var query = "insert into author_book (author_id, book_id) " +
                "values (:author_id, :book_id)";
        var params = Map.of("author_id", authorId, "book_id", bookId);
        jdbc.update(query, params);
    }

    @Override
    public List<Long> bookIdsByAuthor(long id) {
        var query = "select book_id " +
                "from author_book " +
                "where author_id = :author_id";
        var params = Map.of("author_id", id);
        return jdbc.query(query, params, mergedIdRowMapper());
    }

    @Override
    public List<Long> authorIdsByBook(long id) {
        var query = "select author_id " +
                "from author_book " +
                "where book_id = :book_id";
        var params = Map.of("book_id", id);
        return jdbc.query(query, params, mergedIdRowMapper());
    }

    @Override
    public void updateAuthorId(long oldId, long newId) {
        var query = "update author_book " +
                "set author_id = :author_new " +
                "where author_id = :author_old";
        var params = Map.of("author_old", oldId, "author_new", newId);
        jdbc.update(query, params);
    }

    @Override
    public void updateBookId(long oldId, long newId) {
        var query = "update author_book " +
                "set book_id = :book_new " +
                "where book_id = :book_old";
        var params = Map.of("book_old", oldId, "book_new", newId);
        jdbc.update(query, params);
    }

    @Override
    public void deleteAuthorId(long id) {
        var query = "delete author_book " +
                "where author_id = :id";
        var params = Map.of("id", id);
        jdbc.update(query, params);
    }

    @Override
    public void deleteBookId(long id) {
        var query = "delete author_book " +
                "where book_id = :id";
        var params = Map.of("id", id);
        jdbc.update(query, params);
    }

    private RowMapper<Long> mergedIdRowMapper() {
        return (rs, rowNum) -> rs.getLong(1);
    }
}
