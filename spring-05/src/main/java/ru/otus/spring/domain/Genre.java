package ru.otus.spring.domain;

public record Genre(long id, String name) {
    @Override
    public String toString() {
        return id + " | " + name;
    }
}
