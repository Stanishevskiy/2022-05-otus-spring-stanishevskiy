package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAllBooks();

    List<Book> findBooksByName(String name);

    List<Book> findBooksByGenreId(long genreId);

    List<Book> findBooksByAuthorId(long genreId);

    void updateBook(Book book);

    void deleteBookById(long id);
}
