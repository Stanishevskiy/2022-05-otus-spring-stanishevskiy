package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
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

    @ShellMethod(key = "genre-add", value = "Command allow to add genre)")
    public void addGenre(@ShellOption({"-n", "--name"}) String name) {
        log.info("Add genre: {}", name);
        bookstore.addGenre(name);
    }

    @ShellMethod(key = "genre-update", value = "Command allow to update genre")
    public void updateGenre(@ShellOption({"-i", "--id"}) long id,
                           @ShellOption({"-n", "--name"}) String name) {
        log.info("Update genre: {}", id);
        var genre = new Genre(id, name);
        bookstore.updateGenre(genre);
    }

    @ShellMethod(key = "genre-delete", value = "Command allow to delete genre by id")
    public void deleteGenre(@ShellOption({"-i", "--id"}) long id) {
        log.info("Delete genre by id: {}", id);
        bookstore.deleteGenreById(id);
    }

    //-----------------------------------------------------------------------------------------------------------------
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

    @ShellMethod(key = "author-add", value = "Command allow to add author")
    public void addAuthor(@ShellOption({"-n", "--name"}) String name) {
        log.info("Add author: {}", name);
        bookstore.addAuthor(name);
    }

    @ShellMethod(key = "author-update", value = "Command allow to update author")
    public void updateAuthor(@ShellOption({"-i", "--id"}) long id,
                             @ShellOption({"-n", "--name"}) String name) {
        log.info("Update author: {}", id);
        var author = new Author(id, name);
        bookstore.updateAuthor(author);
    }

    @ShellMethod(key = "author-delete", value = "Command allow to delete author by id")
    public void deleteAuthor(@ShellOption({"-i", "--id"}) long id) {
        log.info("Delete author by id: {}", id);
        bookstore.deleteAuthorById(id);
    }

    //-----------------------------------------------------------------------------------------------------------------

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

    @ShellMethod(key = "book-add", value = "Command allow to add book")
    public void addBook(@ShellOption({"-n", "--name"}) String name,
                        @ShellOption({"-a", "--author-id"}) long authorId,
                        @ShellOption({"-g", "--genre-id"}) long genreId) {
        log.info("Add book: {}", name);
        bookstore.addBook(name, authorId, genreId);
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
        var book = new Book(id, name, genreId);
        bookstore.updateBook(book);
    }

    @ShellMethod(key = "book-delete", value = "Command allow to delete book by id")
    public void deleteBook(@ShellOption({"-i", "--id"}) long id) {
        log.info("Delete book by id: {}", id);
        bookstore.deleteBookById(id);
    }
}
