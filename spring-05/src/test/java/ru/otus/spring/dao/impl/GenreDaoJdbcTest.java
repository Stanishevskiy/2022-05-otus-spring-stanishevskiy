package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import ru.otus.spring.domain.Genre;

@DisplayName("DAO for Genre")
@JdbcTest
public class GenreDaoJdbcTest {



    @DisplayName("Retrieving object by name - correct result")
    @Test
    void getByName_validResult() {
        var findGenre = "Novel";
        var retrievedGenre = new Genre(3, "Novel");


    }

    @DisplayName("Retrieving object by name - incorrect result")
    void getByName_invalidResult() {
        var findGenre = "F";
        var retrievedGenre = new Genre(1, "Science fiction");

    }

}
