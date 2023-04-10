package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllGenres();

    List<Genre> findGenresByName(String name);

    Genre findGenreById(long id);
}
