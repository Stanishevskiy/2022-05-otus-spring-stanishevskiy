package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Genre> getAllGenres() {
        var query = "select * from genre";
        return jdbc.query(query, genreRowMapper());
    }

    @Override
    public List<Genre> findGenresByName(String name) {
        var query = "select * from genre where name like :name";
        var params = Map.of("name", name + "%");
        return jdbc.query(query, params, genreRowMapper());
    }

    @Override
    public Genre findGenreById(long id) {
        var query = "select * from genre where id = :id";
        var params = Map.of("id", id);
        return jdbc.queryForObject(query, params, genreRowMapper());
    }

    private RowMapper<Genre> genreRowMapper() {
        return (rs, rowNum)
                -> new Genre(rs.getLong(1), rs.getString(2));
    }
}
