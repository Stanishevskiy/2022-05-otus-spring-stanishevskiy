package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

public interface BookstoreService {

    void addGenre(String name);

    void getAllGenres();

    void findGenresByName(String name);

    void updateGenre(Genre genre);

    void deleteGenreById(long id);

    //-----------------------------------------------------------------------------------------------------------------

    void addAuthor(String name);

    void getAllAuthors();

    void findAuthorsByName(String name);

    void updateAuthor(Author author);

    void deleteAuthorById(long id);

    //-----------------------------------------------------------------------------------------------------------------

    void addBook(String name, long authorId, long genreId);

    void getAllBooks();

    void findBooksByName(String name);

    void findBooksByGenreName(String genreName);

    void findBooksByAuthorName(String authorName);
}
