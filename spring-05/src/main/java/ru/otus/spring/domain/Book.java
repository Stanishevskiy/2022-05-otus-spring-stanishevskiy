package ru.otus.spring.domain;

public record Book(long id, String name, long authorId, long genreId) {
    @Override
    public String toString() {
        return id + " | " + name;
    }
}
