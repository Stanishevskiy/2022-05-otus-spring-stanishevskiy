package ru.otus.spring.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.LocaleProvider;
import ru.otus.spring.config.MessageProvider;

@RequiredArgsConstructor
@Component
public class MessageService {

    public static final String LINE_SEPARATOR = "------------";
    private final LocaleProvider localeProvider;
    private final MessageProvider msgProvider;

    public String getLineSeparator() {
        return LINE_SEPARATOR;
    }

    public String getMessage(String msgCode) {
        return msgProvider.getMessage(msgCode, localeProvider.getLocale());
    }

}
