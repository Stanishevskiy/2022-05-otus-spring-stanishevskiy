package ru.otus.spring.exceptions;

public class ReadingFileException extends RuntimeException{

    public ReadingFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
