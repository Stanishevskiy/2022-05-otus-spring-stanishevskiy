package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;
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
        log.info("Find genres by name: {}", name);
        bookstore.findGenresByName(name);
    }

    @ShellMethod(key = "authors", value = "Command allow to get all stored authors in Bookstore")
    public void getAllAuthors() {
        log.info("Get all stored authors:");
        bookstore.getAllAuthors();
    }

    @ShellMethod(key = "author", value = "Command allow to find authors by name")
    public void findAuthorsByName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find authors by name: {}", name);
        bookstore.findAuthorsByName(name);
    }

    @ShellMethod(key = "books", value = "Command allow to get all stored books in Bookstore")
    public void getAllBooks() {
        log.info("Get all stored books:");
        bookstore.getAllBooks();
    }

    @ShellMethod(key = "book", value = "Command allow to find books by name")
    public void findBooksByName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find books by name: {}", name);
        bookstore.findBooksByName(name);
    }

    @ShellMethod(key = "book-genre", value = "Command allow to find books by genre_name")
    public void findBooksByGenreName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find books by genre name: {}", name);
        bookstore.findBooksByGenreName(name);
    }

    @ShellMethod(key = "book-author", value = "Command allow to find books by author_name")
    public void findBooksByAuthorName(@ShellOption({"-n", "--name"}) String name) {
        log.info("Find books by author name: {}", name);
        bookstore.findBooksByAuthorName(name);
    }

    @ShellMethod(key = "book-update", value = "Command allow to update book")
    public void updateBook(@ShellOption({"-i", "--id"}) long id,
                           @ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author-id"}) long authorId,
                           @ShellOption({"-g", "--genre-id"}) long genreId) {
        log.info("Update book: {}", id);
        var book = new Book(id, name, authorId, genreId);
        bookstore.updateBook(book);
    }

    @ShellMethod(key = "book-delete", value = "Command allow to delete book by id")
    public void deleteBook(@ShellOption({"-i", "--id"}) long id) {
        log.info("Delete book by id: {}", id);
        bookstore.deleteBookById(id);
    }
}
