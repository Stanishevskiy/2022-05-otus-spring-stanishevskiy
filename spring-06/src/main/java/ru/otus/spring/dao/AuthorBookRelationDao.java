package ru.otus.spring.dao;

import java.util.List;

public interface AuthorBookRelationDao {

    void addAuthorBookIds(long authorId, long bookId);

    List<Long> bookIdsByAuthor(long id);

    List<Long> authorIdsByBook(long id);

    void updateAuthorIdByBookId(long bookId, long authorId);

    void updateBookIdByAuthorId(long authorId, long bookId);

    void deleteAuthorId(long id);

    void deleteBookId(long id);
}
