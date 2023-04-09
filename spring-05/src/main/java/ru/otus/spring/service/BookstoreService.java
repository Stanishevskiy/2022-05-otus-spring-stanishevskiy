package ru.otus.spring.service;

public interface BookstoreService {

    void getAllGenres();

    void findGenresByName(String name);

    void getAllAuthors();

    void findAuthorsByName(String name);

    void getAllBooks();

    void findBooksByName(String name);

    void findBooksByGenreName(String genreName);

    void findBooksByAuthorName(String authorName);
}
