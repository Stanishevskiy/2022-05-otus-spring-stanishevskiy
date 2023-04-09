package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.impl.BookstoreServiceImpl;

@ShellComponent
@Slf4j
@RequiredArgsConstructor
public class BookstoreCommands {

    private final BookstoreServiceImpl bookstore;

    @ShellMethod(key = "genres", value = "Command allow to get all stored genres in Bookstore")
    public void getAllGenres() {
        log.info("Get all stored genres:");
        bookstore.getAllGenres();
    }

    @ShellMethod(key = "genre", value = "Command allow to find genres by name")
    public void findGenresByName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find genres by name:");
        bookstore.findGenresByName(name);
    }

    @ShellMethod(key = "authors", value = "Command allow to get all stored authors in Bookstore")
    public void getAllAuthors() {
        log.info("Get all stored authors:");
        bookstore.getAllAuthors();
    }

    @ShellMethod(key = "author", value = "Command allow to find authors by name")
    public void findAuthorsByName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find authors by name:");
        bookstore.findAuthorsByName(name);
    }

    @ShellMethod(key = "books", value = "Command allow to get all stored books in Bookstore")
    public void getAllBooks() {
        log.info("Get all stored books:");
        bookstore.getAllBooks();
    }

    @ShellMethod(key = "book", value = "Command allow to find books by name")
    public void findBooksByName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find books by name:");
        bookstore.findBooksByName(name);
    }

    @ShellMethod(key = "book-genre", value = "Command allow to find books by genre_name")
    public void findBooksByGenreName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find books by genre name:");
        bookstore.findBooksByGenreName(name);
    }

    @ShellMethod(key = "book-author", value = "Command allow to find books by author_name")
    public void findBooksByAuthorName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find books by author name:");
        bookstore.findBooksByAuthorName(name);
    }
}
