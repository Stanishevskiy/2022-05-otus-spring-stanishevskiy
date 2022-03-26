package ru.otus.spring.services;

public interface InputService {

    String readStringWithPrompt(String prompt);

    int readAllowedIntWithPrompt(String prompt, int answersCount);
}
