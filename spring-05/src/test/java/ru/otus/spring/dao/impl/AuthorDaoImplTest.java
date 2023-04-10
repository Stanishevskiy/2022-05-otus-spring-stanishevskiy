package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO for Author")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoImplTest {

    @Autowired
    AuthorDaoJdbc authorDao;

    @DisplayName("Retrieving valid author list")
    @Test
    void getAllAuthors_validResult() {
        var expectedAuthors = List.of(
                new Author(1, "J. R. R. Tolkien"),
                new Author(2, "Agatha Christie"),
                new Author(3, "Fedor Dostoevsky"));

        var authors = authorDao.getAllAuthors();
        assertThat(authors).containsExactlyElementsOf(expectedAuthors);
    }

    @DisplayName("Retrieving valid searching authors")
    @Test
    void findAuthorsByName_validResult() {
        var expectedAuthor = List.of(new Author(3, "Fedor Dostoevsky"));

        var author = authorDao.findAuthorsByName("Fedor");
        assertThat(author).containsExactlyElementsOf(expectedAuthor);
    }

    @DisplayName("Retrieving valid author by id")
    @Test
    void findAuthorById_validResult() {
        var expectedAuthor = new Author(1, "J. R. R. Tolkien");

        var author = authorDao.findAuthorById(1);
        assertThat(author).isEqualTo(expectedAuthor);
    }
}
