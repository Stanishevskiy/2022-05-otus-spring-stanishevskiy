package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("DAO for Genre")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    GenreDaoJdbc genreDao;

    @DisplayName("Retrieving valid genres list")
    @Test
    void getAllGenres_validResult() {
        var expectedGenres = List.of(
                new Genre(1, "Fantasy"),
                new Genre(2, "Novel"));

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
}
