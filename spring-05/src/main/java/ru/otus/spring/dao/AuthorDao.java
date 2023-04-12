package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    void addAuthor(String name);

    List<Author> getAllAuthors();

    List<Author> findAuthorsByName(String name);

    Author findAuthorById(long id);

    void updateAuthor(Author author);

    void deleteAuthorById(long id);
}
