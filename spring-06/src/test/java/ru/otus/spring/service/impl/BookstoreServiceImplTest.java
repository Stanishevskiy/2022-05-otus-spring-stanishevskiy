package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.*;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.BookstoreService;
import ru.otus.spring.service.IOService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("Bookstore Service")
@ExtendWith(MockitoExtension.class)
class BookstoreServiceImplTest {

    private final String stabName = "Dummy Name";
    private final Genre stabGenre = new Genre(1, "Genre 1");
    private final List<Genre> stabGenreList = List.of(new Genre(1, "Genre 1"),
            new Genre(2, "Genre 2"));
    private final Author stabAuthor = new Author(1, "Author 1");
    private final List<Author> stabAuthorList = List.of(new Author(1, "Author 1"),
            new Author(2, "Author 2"));
    private final Book stabBook = new Book(1, "Book 1", 1);
    private final List<Book> stabBookList = List.of(new Book(1, "Book 1", 1),
            new Book(2, "Book 2", 1));
    private final Comment stabComment = new Comment(1, "Comment 1", 1);
    private final List<Comment> stabCommentList = List.of(new Comment(1, "Comment 1", 1),
            new Comment(2, "Comment 2", 1));

    @Mock
    private GenreDao genreDao;
    @Mock
    private AuthorDao authorDao;
    @Mock
    private BookDao bookDao;
    @Mock
    private AuthorBookRelationDao authorBookRelationDao;
    @Mock
    private CommentDao commentDao;
    @Mock
    private IOService ioService;

    private BookstoreService bookstoreService;

    @BeforeEach
    void setUp() {
        bookstoreService = new BookstoreServiceImpl(genreDao,
                authorDao,
                bookDao,
                authorBookRelationDao,
                commentDao,
                ioService);
    }

    @DisplayName("add genre - ok")
    @Test
    void addGenre() {
        doNothing().when(genreDao).addGenre(stabName);
        assertDoesNotThrow(() -> bookstoreService.addGenre(stabName));
    }

    @DisplayName("get all genres - ok")
    @Test
    void getAllGenres() {
        when(genreDao.getAllGenres()).thenReturn(stabGenreList);
        assertDoesNotThrow(() -> bookstoreService.getAllGenres());
    }

    @DisplayName("find genres by name - ok")
    @Test
    void findGenresByName() {
        when(genreDao.findGenresByName(stabName)).thenReturn(stabGenreList);
        doNothing().when(ioService).outputCollection(stabGenreList);
        assertDoesNotThrow(() -> bookstoreService.findGenresByName(stabName));
    }

    @DisplayName("update genre - ok")
    @Test
    void updateGenre() {
        doNothing().when(genreDao).updateGenre(stabGenre);
        assertDoesNotThrow(() -> bookstoreService.updateGenre(stabGenre));
    }

    @DisplayName("delete genre by id - ok")
    @Test
    void deleteGenreById() {
        when(bookDao.findBooksByGenreId(1)).thenReturn(stabBookList);
        doNothing().when(bookDao).deleteBookById(1);
        doNothing().when(bookDao).deleteBookById(2);
        doNothing().when(genreDao).deleteGenreById(1);
        assertDoesNotThrow(() -> bookstoreService.deleteGenreById(1));
    }

    //-----------------------------------------------------------------------------------------------------------------

    @DisplayName("add author - ok")
    @Test
    void addAuthor() {
        doNothing().when(authorDao).addAuthor(stabName);
        assertDoesNotThrow(() -> bookstoreService.addAuthor(stabName));
    }

    @DisplayName("get all authors - ok")
    @Test
    void getAllAuthors() {
        when(authorDao.getAllAuthors()).thenReturn(stabAuthorList);
        doNothing().when(ioService).outputCollection(stabAuthorList);
        assertDoesNotThrow(() -> bookstoreService.getAllAuthors());
    }

    @DisplayName("find author by name - ok")
    @Test
    void findAuthorsByName() {
        when(authorDao.findAuthorsByName(stabName)).thenReturn(stabAuthorList);
        doNothing().when(ioService).outputCollection(stabAuthorList);
        assertDoesNotThrow(() -> bookstoreService.findAuthorsByName(stabName));
    }

    @DisplayName("update author - ok")
    @Test
    void updateAuthor() {
        doNothing().when(authorDao).updateAuthor(stabAuthor);
        assertDoesNotThrow(() -> bookstoreService.updateAuthor(stabAuthor));
    }

    @DisplayName("delete author by id - ok")
    @Test
    void deleteAuthorById() {
        doNothing().when(authorDao).deleteAuthorById(1);
        assertDoesNotThrow(() -> bookstoreService.deleteAuthorById(1));
    }

    //-----------------------------------------------------------------------------------------------------------------

    @DisplayName("add book - ok")
    @Test
    void addBook() {
        doNothing().when(bookDao).addBook(stabName, 1);
        when(bookDao.findBooksByName(stabName)).thenReturn(List.of(stabBook));
        doNothing().when(authorBookRelationDao).addAuthorBookIds(1, 1);
        assertDoesNotThrow(() -> bookstoreService.addBook(stabName, 1, 1));
    }

    @DisplayName("get all books - ok")
    @Test
    void getAllBooks() {
        when(bookDao.getAllBooks()).thenReturn(stabBookList);
        doNothing().when(ioService).outputCollection(stabBookList);
        assertDoesNotThrow(() -> bookstoreService.getAllBooks());
    }

    @DisplayName("find books by name - ok")
    @Test
    void findBooksByName() {
        when(bookDao.findBooksByName(stabName)).thenReturn(stabBookList);
        doNothing().when(ioService).outputCollection(stabBookList);
        assertDoesNotThrow(() -> bookstoreService.findBooksByName(stabName));
    }

    @DisplayName("find books by genre name - ok")
    @Test
    void findBooksByGenreName() {
        when(genreDao.findGenresByName(stabName)).thenReturn(stabGenreList);
        when(bookDao.findBooksByGenreId(1)).thenReturn(stabBookList);
        doNothing().when(ioService).outputCollection(stabBookList);
        assertDoesNotThrow(() -> bookstoreService.findBooksByGenreName(stabName));
    }

    @DisplayName("find books by author name - ok")
    @Test
    void findBooksByAuthorName() {
        when(authorDao.findAuthorsByName(stabName)).thenReturn(stabAuthorList);
        when(authorBookRelationDao.bookIdsByAuthor(1)).thenReturn(List.of(1L));
        when(bookDao.findBooksByIds(List.of(1L))).thenReturn(stabBookList);
        doNothing().when(ioService).outputCollection(stabBookList);
        assertDoesNotThrow(() -> bookstoreService.findBooksByAuthorName(stabName));
    }

    @DisplayName("update book - ok")
    @Test
    void updateBook() {
        doNothing().when(bookDao).updateBook(stabBook);
        doNothing().when(authorBookRelationDao).updateAuthorIdByBookId(stabBook.id(), 1);
        assertDoesNotThrow(() -> bookstoreService.updateBook(stabBook, 1));
    }

    @DisplayName("delete book by id - ok")
    @Test
    void deleteBookById() {
        doNothing().when(authorBookRelationDao).deleteBookId(1);
        doNothing().when(bookDao).deleteBookById(1);
        assertDoesNotThrow(() -> bookstoreService.deleteBookById(1));
    }

    //-----------------------------------------------------------------------------------------------------------------

    @DisplayName("add comment - ok")
    @Test
    void addComment() {
        doNothing().when(commentDao).addComment(stabComment.description(), stabComment.bookId());
        assertDoesNotThrow(() -> bookstoreService.addComment(stabComment.description(), stabComment.bookId()));
    }

    @DisplayName("get all comments - ok")
    @Test
    void getAllComments() {
        when(commentDao.getAllComments()).thenReturn(stabCommentList);
        doNothing().when(ioService).outputCollection(stabCommentList);
        assertDoesNotThrow(() -> bookstoreService.getAllComments());
    }

    @DisplayName("get comments by book name - ok")
    @Test
    void getCommentsByBookName() {
        when(bookDao.findBooksByName(stabName)).thenReturn(stabBookList);
        when(commentDao.getCommentByBookId(1)).thenReturn(stabCommentList);
        doNothing().when(ioService).outputCollection(stabCommentList);
        assertDoesNotThrow(() -> bookstoreService.getCommentsByBookName(stabName));
    }

    @DisplayName("update comment - ok")
    @Test
    void updateComment() {
        doNothing().when(commentDao).updateComment(stabComment);
        assertDoesNotThrow(() -> bookstoreService.updateComment(stabComment));
    }

    @DisplayName("delete comment by id")
    @Test
    void deleteCommentById() {
        doNothing().when(commentDao).deleteCommentById(4);
        assertDoesNotThrow(() -> bookstoreService.deleteCommentById(4));
    }
}