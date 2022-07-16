package ru.otus.spring.services;

public interface InputService {

    String inputStringWithPrompt(String prompt);

    int inputAllowedNumWithPrompt(String prompt, int number, String errorStr);
}
