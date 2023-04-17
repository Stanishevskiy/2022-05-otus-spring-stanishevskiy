package ru.otus.spring.domain;

public record Comment(long id, String description, long bookId) {
    @Override
    public String toString() { return id + " | " + description + " | " + bookId; }
}
