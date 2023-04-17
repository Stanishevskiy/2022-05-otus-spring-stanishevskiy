package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO for Genre")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    GenreDaoJdbc genreDao;

    @DisplayName("Add valid genre")
    @Test
    void addGenre_validResult() {
        var expectedGenres = List.of(
                new Genre(1, "Fantasy"),
                new Genre(2, "Novel"),
                new Genre(3, "Old Genre"),
                new Genre(4, "New Genre"));

        genreDao.addGenre("New Genre");
        var genres = genreDao.getAllGenres();
        assertThat(genres).containsExactlyElementsOf(expectedGenres);
    }

    @DisplayName("Retrieving valid genres list")
    @Test
    void getAllGenres_validResult() {
        var expectedGenres = List.of(
                new Genre(1, "Fantasy"),
                new Genre(2, "Novel"),
                new Genre(3, "Old Genre"));

        var genres = genreDao.getAllGenres();
        assertThat(genres).containsExactlyElementsOf(expectedGenres);
    }

    @DisplayName("Retrieving valid searching genres")
    @Test
    void findGenresByName_validResult() {
        var expectedGenre = List.of(new Genre(2, "Novel"));

        var genres = genreDao.findGenresByName("Novel");
        assertThat(genres).containsExactlyElementsOf(expectedGenre);
    }

    @DisplayName("Retrieving valid genre by id")
    @Test
    void findGenreById_validResult() {
        var expectedGenre = new Genre(2, "Novel");

        var genre = genreDao.findGenreById(2);
        assertThat(genre).isEqualTo(expectedGenre);
    }

    @DisplayName("Correct update genre")
    @Test
    void updateGenre_validResult() {
        var expectedGenre = new Genre(2, "Drama");

        var updatedGenre = new Genre(2, "Drama");
        genreDao.updateGenre(updatedGenre);

        var resultGenre = genreDao.findGenreById(2);
        assertThat(resultGenre).isEqualTo(expectedGenre);
    }

    @DisplayName("Correct delete genre")
    @Test
    void deleteGenreById_validResult() {
        var expectedGenres = List.of(new Genre(1, "Fantasy"),
                new Genre(2, "Novel"));

        genreDao.deleteGenreById(3);

        var resultGenres = genreDao.getAllGenres();
        assertThat(resultGenres).containsExactlyElementsOf(expectedGenres);
    }
}
