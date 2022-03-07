package ru.otus.exceptions;

public class ReadQuestionnaireException extends RuntimeException{

    public ReadQuestionnaireException(String message, Throwable cause) {
        super(message, cause);
    }
}
