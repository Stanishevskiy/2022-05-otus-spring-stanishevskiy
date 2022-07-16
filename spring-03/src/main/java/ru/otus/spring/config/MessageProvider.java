package ru.otus.spring.config;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class MessageProvider {

    public String getMessage(String msgCode, Locale locale) {
        var resourceBundle = ResourceBundle.getBundle("questionnaire", locale);
        return resourceBundle.getString(msgCode);
    }
}
