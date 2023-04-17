package ru.otus.spring.dao;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentDao {

    void addComment(String description, long bookId);

    List<Comment> getAllComments();

    List<Comment> getCommentByBookId(long bookId);

    Comment getCommentById(long id);

    void updateComment(Comment comment);

    void deleteCommentById(long id);
}
