package ru.otus.spring.services;

public interface MessageService {

    String readWithPromptCode(String promptCode);

    int readAllowedNumWithPromptCode(String promptCode, int number, String errorStrCode);

    void writeMsgByCode(String msgCode);

    String getMessage(String msgCode);
}
