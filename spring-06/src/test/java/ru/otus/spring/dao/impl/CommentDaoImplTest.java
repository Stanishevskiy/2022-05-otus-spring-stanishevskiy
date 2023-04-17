package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.CommentDao;
import ru.otus.spring.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO for Comment")
@JdbcTest
@Import(CommentDaoImpl.class)
class CommentDaoImplTest {

    @Autowired
    CommentDao commentDao;

    @DisplayName("add comment - ok")
    @Test
    void addComment() {
        var expectedComment = new Comment(5, "description stab", 3);

        commentDao.addComment(expectedComment.description(), expectedComment.bookId());

        var comment = commentDao.getCommentById(5);
        assertThat(comment).isEqualTo(expectedComment);
    }

    @DisplayName("get all comments - ok")
    @Test
    void getAllComments() {
        var expectedComments = List.of(
                new Comment(1, "description Hobbit", 1),
                new Comment(2, "description The Lord 1", 2),
                new Comment(3, "description The Lord 2", 2),
                new Comment(4, "description Idiot", 4)
        );

        var result = commentDao.getAllComments();
        assertThat(result).containsExactlyElementsOf(expectedComments);
    }

    @DisplayName("get comment by book id - ok")
    @Test
    void getCommentByBookId() {
        var expectedComments = List.of(
                new Comment(2, "description The Lord 1", 2),
                new Comment(3, "description The Lord 2", 2)
        );

        var result = commentDao.getCommentByBookId(2);
        assertThat(result).containsExactlyElementsOf(expectedComments);
    }

    @DisplayName("get comment by id - ok")
    @Test
    void getCommentById() {
        var expectedComment = new Comment(4, "description Idiot", 4);

        var result = commentDao.getCommentById(4);
        assertThat(result).isEqualTo(expectedComment);
    }

    @DisplayName("update comment - ok")
    @Test
    void updateComment() {
        var expectedComment = new Comment(4, "description new Idiot", 4);

        commentDao.updateComment(expectedComment);
        var result = commentDao.getCommentById(4);
        assertThat(result).isEqualTo(expectedComment);
    }

    @DisplayName("delete comment by id - ok")
    @Test
    void deleteCommentById() {
        var expectedComments = List.of(
                new Comment(1, "description Hobbit", 1),
                new Comment(2, "description The Lord 1", 2),
                new Comment(3, "description The Lord 2", 2)
        );

        commentDao.deleteCommentById(4);
        var result = commentDao.getAllComments();
        assertThat(result).containsExactlyElementsOf(expectedComments);
    }
}