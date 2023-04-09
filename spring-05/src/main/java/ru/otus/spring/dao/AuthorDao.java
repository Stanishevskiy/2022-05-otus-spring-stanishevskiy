package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Set;

public interface AuthorDao {

    List<Author> findAllAuthors();

    List<Author> findAuthorsByName(String name);

    Author findAuthorById(long id);
}
