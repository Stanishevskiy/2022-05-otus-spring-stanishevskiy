package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Map;

@SuppressWarnings("java:S1192")
@Repository
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void addComment(String description, long bookId) {
        var query = """
            insert into comment (description, book_id)
                values (:description, :bookId)""";
        var params = Map.of("description", description,
                "bookId", bookId);
        jdbc.update(query, params);
    }

    @Override
    public List<Comment> getAllComments() {
        var query = "select * from comment";
        return jdbc.query(query, commentRowMapper());
    }

    @Override
    public List<Comment> getCommentByBookId(long bookId) {
        var query = """
                select *
                from comment
                where book_id = :bookId""";
        var params = Map.of("bookId", bookId);
        return jdbc.query(query, params, commentRowMapper());
    }

    @Override
    public Comment getCommentById(long id) {
        var query = """
                select *
                from comment
                where id = :id
                """;
        var params = Map.of("id", id);
        return jdbc.queryForObject(query, params, commentRowMapper());
    }

    @Override
    public void updateComment(Comment comment) {
        var query = """
                update comment
                set description = :description, book_id = :bookId
                where id = :id""";
        var params = Map.of("id", comment.id(),
                "description", comment.description(),
                "bookId", comment.bookId());
        jdbc.update(query, params);
    }

    @Override
    public void deleteCommentById(long id) {
        var query = """
                delete from comment
                where id = :id""";
        var params = Map.of("id", id);
        jdbc.update(query, params);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> new Comment(rs.getLong(1),
                rs.getString(2),
                rs.getLong(3));
    }
}
