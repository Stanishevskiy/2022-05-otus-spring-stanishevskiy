package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    void addGenre(String name);

    List<Genre> getAllGenres();

    Genre findGenreById(long id);

    List<Genre> findGenresByName(String name);

    void updateGenre(Genre genre);

    void deleteGenreById(long id);
}
