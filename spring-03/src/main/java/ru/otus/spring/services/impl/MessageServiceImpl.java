package ru.otus.spring.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.LocaleProvider;
import ru.otus.spring.config.MessageProvider;
import ru.otus.spring.services.IOService;
import ru.otus.spring.services.MessageService;

@RequiredArgsConstructor
@Component
public class MessageServiceImpl implements MessageService {

    public static final String LINE_SEPARATOR = "------------";

    private final LocaleProvider localeProvider;
    private final MessageProvider msgProvider;
    private final IOService ioService;

    public void writeLineSeparator() {
        ioService.outputString(LINE_SEPARATOR);
    }

    @Override
    public String readWithPromptCode(String promptCode) {
        var prompt = getMessage(promptCode);
        return ioService.inputStringWithPrompt(prompt);
    }

    @Override
    public int readAllowedNumWithPromptCode(String promptCode, int number, String errorStrCode) {
        var prompt = getMessage(promptCode);
        var errorStr = getMessage(errorStrCode);
        return ioService.inputAllowedNumWithPrompt(prompt, number, errorStr);
    }

    @Override
    public void writeMsgByCode(String msgCode) {
        var msg = getMessage(msgCode);
        ioService.outputString(msg);
    }

    @Override
    public String getMessage(String msgCode) {
        return msgProvider.getMessage(msgCode, localeProvider.getLocale());
    }

}
