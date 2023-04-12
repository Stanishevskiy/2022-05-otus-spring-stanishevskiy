package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    void addBook(String name, long genreId);

    List<Book> getAllBooks();

    List<Book> findBooksByName(String name);

    List<Book> findBooksByIds(List<Long> ids);

    List<Book> findBooksByGenreId(long genreId);

    void updateBook(Book book);

    void deleteBookById(long id);
}
