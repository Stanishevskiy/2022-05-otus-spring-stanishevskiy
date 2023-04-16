package ru.otus.spring.dao;

import java.util.List;

public interface AuthorBookMergingDao {

    void addAuthorBookIds(long authorId, long bookId);

    List<Long> bookIdsByAuthor(long id);

    List<Long> authorIdsByBook(long id);

    void updateAuthorId(long oldId, long newId);

    void updateBookId(long oldId, long newId);

    void deleteAuthorId(long id);

    void deleteBookId(long id);
}
