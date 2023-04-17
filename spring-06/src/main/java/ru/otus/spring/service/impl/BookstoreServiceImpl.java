package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.*;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.BookstoreService;
import ru.otus.spring.service.IOService;

@Service
@RequiredArgsConstructor
public class BookstoreServiceImpl implements BookstoreService {

    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final AuthorBookRelationDao relationDao;
    private final CommentDao commentDao;

    private final IOService ioService;

    @Override
    public void addGenre(String name) {
        genreDao.addGenre(name);
    }

    @Override
    public void getAllGenres() {
        var genres = genreDao.getAllGenres();
        ioService.outputCollection(genres);
    }

    @Override
    public void findGenresByName(String name) {
        var genres = genreDao.findGenresByName(name);
        ioService.outputCollection(genres);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreDao.updateGenre(genre);
    }

    @Override
    public void deleteGenreById(long id) {
        var books = bookDao.findBooksByGenreId(id);
        books.forEach(b -> bookDao.deleteBookById(b.id()));

        genreDao.deleteGenreById(id);
    }

    //-----------------------------------------------------------------------------------------------------------------

    @Override
    public void addAuthor(String name) {
        authorDao.addAuthor(name);
    }

    @Override
    public void getAllAuthors() {
        var authors = authorDao.getAllAuthors();
        ioService.outputCollection(authors);
    }

    @Override
    public void findAuthorsByName(String name) {
        var authors = authorDao.findAuthorsByName(name);
        ioService.outputCollection(authors);
    }

    @Override
    public void updateAuthor(Author author) {
        authorDao.updateAuthor(author);
    }

    @Override
    public void deleteAuthorById(long id) {
        authorDao.deleteAuthorById(id);
    }

    //-----------------------------------------------------------------------------------------------------------------

    @Override
    public void addBook(String name, long authorId, long genreId) {
        bookDao.addBook(name, genreId);
        var bookId = bookDao.findBooksByName(name).stream()
                .findFirst()
                .orElseThrow()
                .id();

        relationDao.addAuthorBookIds(authorId, bookId);
    }

    @Override
    public void getAllBooks() {
        var books = bookDao.getAllBooks();
        ioService.outputCollection(books);
    }

    @Override
    public void findBooksByName(String name) {
        var books = bookDao.findBooksByName(name);
        ioService.outputCollection(books);
    }

    @Override
    public void findBooksByGenreName(String genreName) {
        var genres = genreDao.findGenresByName(genreName);
        var genreId = genres.stream().anyMatch(g -> g.name().equals(genreName))
                ? genres.stream().filter(g -> g.name().equals(genreName)).findFirst().orElseThrow().id()
                : genres.stream().findFirst().orElse(new Genre(0, "Not Present")).id();

        var books = bookDao.findBooksByGenreId(genreId);
        ioService.outputCollection(books);
    }

    @Override
    public void findBooksByAuthorName(String authorName) {
        var authors = authorDao.findAuthorsByName(authorName);
        var authorId = authors.stream().anyMatch(a -> a.name().equals(authorName))
                ? authors.stream().filter(a -> a.name().equals(authorName)).findFirst().orElseThrow().id()
                : authors.stream().findFirst().orElse(new Author(0, "Not Present")).id();

        var bookIds = relationDao.bookIdsByAuthor(authorId);
        var books = bookDao.findBooksByIds(bookIds);
        ioService.outputCollection(books);
    }

    @Override
    public void updateBook(Book book, long authorId) {
        bookDao.updateBook(book);
        relationDao.updateAuthorIdByBookId(book.id(), authorId);
    }

    @Override
    public void deleteBookById(long id) {
        relationDao.deleteBookId(id);
        bookDao.deleteBookById(id);
    }

    //-----------------------------------------------------------------------------------------------------------------

    @Override
    public void addComment(String description, long bookId) {
        commentDao.addComment(description, bookId);
    }

    @Override
    public void getAllComments() {
        var comments = commentDao.getAllComments();
        ioService.outputCollection(comments);
    }

    @Override
    public void getCommentsByBookName(String bookName) {
        var bookId = bookDao.findBooksByName(bookName).stream()
                .findFirst()
                .orElseThrow()
                .id();
        var comments = commentDao.getCommentByBookId(bookId);
        ioService.outputCollection(comments);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public void deleteCommentById(long id) {
        commentDao.deleteCommentById(id);
    }
}
