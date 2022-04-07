package ru.otus.spring.services;

public class MessageProvider {

    private static final String INPUT_NUMBER_VALUE_INCORRECT = "Input number value, from 1 to %d !";

    public String getInputNumberValueIncorrect() {
        return INPUT_NUMBER_VALUE_INCORRECT;
    }
}
