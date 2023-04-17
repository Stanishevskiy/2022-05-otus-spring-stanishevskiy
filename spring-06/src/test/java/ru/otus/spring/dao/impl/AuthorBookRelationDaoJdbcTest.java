package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("DAO for AuthorBookRelation")
@JdbcTest
@Import(AuthorBookRelationDaoJdbc.class)
class AuthorBookRelationDaoJdbcTest {

    @Autowired
    AuthorBookRelationDaoJdbc jdbc;

    @DisplayName("Add valid author book ids")
    @Test
    void addAuthorBookIds_validResult() {
        var expectedResult = List.of(1L, 2L, 3L);

        jdbc.addAuthorBookIds(1L, 3L);
        var resultIds = jdbc.bookIdsByAuthor(1);
        assertThat(resultIds).containsExactlyElementsOf(expectedResult);
    }

    @DisplayName("Retrieving valid book ids by author")
    @Test
    void booksIdsByAuthor() {
        var expectedResult = List.of(1L, 2L);

        var resultIds = jdbc.bookIdsByAuthor(1);
        assertThat(resultIds).containsExactlyElementsOf(expectedResult);
    }

    @DisplayName("Retrieving valid author ids by book")
    @Test
    void authorsIdByBooks() {
        var expectedResult = List.of(3L);
        var resultIds = jdbc.authorIdsByBook(4);

        assertThat(resultIds).containsExactlyElementsOf(expectedResult);
    }

    @DisplayName("Correct update author id")
    @Test
    void updateAuthorId() {
        var expectedResult = List.of(1L, 3L);
        jdbc.updateAuthorIdByBookId(1, 2);

        var resultIds = jdbc.bookIdsByAuthor(2);
        assertThat(resultIds).containsExactlyElementsOf(expectedResult);
    }

    @DisplayName("Correct update book id")
    @Test
    void updateBookId() {
        var expectedResult = List.of(1L, 3L);
        jdbc.updateBookIdByAuthorId(3, 1);

        var resultIds = jdbc.authorIdsByBook(1);
        assertThat(resultIds).containsExactlyElementsOf(expectedResult);
    }

    @DisplayName("Correct delete author id row")
    @Test
    void deleteAuthorId() {
        List<Long> expectedResult = List.of();
        jdbc.deleteAuthorId(1);

        var resultIds = jdbc.bookIdsByAuthor(1);
        assertThat(resultIds).containsExactlyElementsOf(expectedResult);
    }

    @DisplayName("Correct delete book id row")
    @Test
    void deleteBookId() {
        List<Long> expectedResult = List.of();
        jdbc.deleteBookId(1);

        var resultIds = jdbc.authorIdsByBook(1);
        assertThat(resultIds).containsExactlyElementsOf(expectedResult);
    }
}