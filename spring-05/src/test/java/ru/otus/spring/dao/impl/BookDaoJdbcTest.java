package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO for Book")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorBookMergingDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    BookDaoJdbc bookDao;
    @Autowired
    AuthorBookMergingDaoJdbc mergingDao;


    @DisplayName("Add valid book")
    @Test
    void addBook_validResult() {
        var expectedBooks = List.of(
                new Book(1, "Hobbit", 1),
                new Book(2, "The Lord of the Rings", 1),
                new Book(3, "Murder on the Orient Express", 2),
                new Book(4, "The Idiot", 2),
                new Book(5, "My New Book", 2));

        bookDao.addBook("My New Book", 2);
        mergingDao.addAuthorBookIds(1, 5);
        var books = bookDao.getAllBooks();
        assertThat(books).containsExactlyElementsOf(expectedBooks);
    }

    @DisplayName("Retrieving valid book list")
    @Test
    void getAllBooks_validResult() {
        var expectedBooks = List.of(
                new Book(1, "Hobbit", 1),
                new Book(2, "The Lord of the Rings", 1),
                new Book(3, "Murder on the Orient Express", 2),
                new Book(4, "The Idiot", 2));

        var books = bookDao.getAllBooks();
        assertThat(books).containsExactlyElementsOf(expectedBooks);
    }

    @DisplayName("Retrieving valid searching books")
    @Test
    void findBooksByName_validResult() {
        var expectedBook = List.of(new Book(4, "The Idiot", 2));

        var book = bookDao.findBooksByName("The Idiot");
        assertThat(book).containsExactlyElementsOf(expectedBook);
    }

    @DisplayName("Retrieving valid searching books")
    @Test
    void findBooksByIds() {
        var expectedBook = List.of(new Book(1, "Hobbit", 1));

        var books = bookDao.findBooksByIds(List.of(1L));
        assertThat(books).containsExactlyElementsOf(expectedBook);
    }

    @DisplayName("Retrieving valid searching books by genre id")
    @Test
    void findBooksByGenreId_validResult() {
        var expectedBooks = List.of(
                new Book(1, "Hobbit", 1),
                new Book(2, "The Lord of the Rings", 1));

        var books = bookDao.findBooksByGenreId(1);
        assertThat(books).containsExactlyElementsOf(expectedBooks);
    }

    @DisplayName("Update correct book record")
    @Test
    void updateBook_validResult() {
        var expectedUpdatedBook = List.of(new Book(1, "New Hobbit", 2));

        var updatedBook = new Book(1, "New Hobbit", 2);
        bookDao.updateBook(updatedBook);

        var bookResult = bookDao.findBooksByName(updatedBook.name());
        assertThat(bookResult).containsExactlyElementsOf(expectedUpdatedBook);
    }

    @DisplayName("Delete correct book by id")
    @Test
    void deleteBookById_validResult() {
        var expectedRemainingBooks = List.of(
                new Book(1, "Hobbit", 1),
                new Book(2, "The Lord of the Rings", 1),
                new Book(4, "The Idiot", 2)
        );

        mergingDao.deleteBookId(3);
        bookDao.deleteBookById(3);
        var bookResult = bookDao.getAllBooks();
        assertThat(bookResult).containsExactlyElementsOf(expectedRemainingBooks);
    }
}
