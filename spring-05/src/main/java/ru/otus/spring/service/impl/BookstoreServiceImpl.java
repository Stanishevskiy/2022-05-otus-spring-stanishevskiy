package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.BookstoreService;
import ru.otus.spring.service.IOService;

@Service
@RequiredArgsConstructor
public class BookstoreServiceImpl implements BookstoreService {

    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    private final IOService ioService;

    public void getAllGenres() {
        var genres = genreDao.getAllGenres();
        ioService.outputCollection(genres);
    }

    public void findGenresByName(String name) {
        var genres = genreDao.findGenresByName(name);
        ioService.outputCollection(genres);
    }

    public void getAllAuthors() {
        var authors = authorDao.findAllAuthors();
        ioService.outputCollection(authors);
    }

    public void findAuthorsByName(String name) {
        var authors = authorDao.findAuthorsByName(name);
        ioService.outputCollection(authors);
    }

    public void getAllBooks() {
        var books = bookDao.getAllBooks();
        ioService.outputCollection(books);
    }

    public void findBooksByName(String name) {
        var books = bookDao.findBooksByName(name);
        ioService.outputCollection(books);
    }

    public void findBooksByGenreName(String genreName) {
        var genres = genreDao.findGenresByName(genreName);
        var genreId = genres.stream().anyMatch(g -> g.name().equals(genreName))
                ? genres.stream().filter(g -> g.name().equals(genreName)).findFirst().orElseThrow().id()
                : genres.stream().findFirst().orElse(new Genre(0, "Not Present")).id();

        var books = bookDao.findBooksByGenreId(genreId);
        ioService.outputCollection(books);
    }

    public void findBooksByAuthorName(String authorName) {
        var authors = authorDao.findAuthorsByName(authorName);
        var authorId = authors.stream().anyMatch(a -> a.name().equals(authorName))
                ? authors.stream().filter(a -> a.name().equals(authorName)).findFirst().orElseThrow().id()
                : authors.stream().findFirst().orElse(new Author(0, "Not Present")).id();

        var books = bookDao.findBooksByAuthorId(authorId);
        ioService.outputCollection(books);
    }
}
