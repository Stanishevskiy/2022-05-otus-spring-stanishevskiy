package ru.otus.spring.domain;

public record Author(long id, String name) {
    @Override
    public String toString() {
        return id + " | " + name;
    }
}
