package ru.otus.spring.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.config.MessageProvider;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Поставщик сообщений")
class MessageProviderTest {

    @Test
    @DisplayName("при заданной локали RU возвращаются сообщения на русском")
    void russianMessagesWithRULocale() {
        var ruLocale = new Locale.Builder().setLanguage("ru").setRegion("RU").build();
        var appProps = new AppProperties();
        appProps.setLangLocale(ruLocale);

        var messageService = new MessageService(appProps, new MessageProvider());
        assertAll(
                () -> assertEquals("Введите Ваше имя:", messageService.getMessage("student.input-first-name")),
                () -> assertEquals("Результат", messageService.getMessage("questionnaire.result-header")),
                () -> assertEquals("Введите Ваш ответ:", messageService.getMessage("question.input-choice"))
        );
    }

    @Test
    @DisplayName("при локали по умолчанию возвращаются сообщения на английском")
    void englishMessagesWithDefaultLocale() {
        var defaultLocale = Locale.getDefault();
        var appProps = new AppProperties();
        appProps.setLangLocale(defaultLocale);

        var messageService = new MessageService(appProps, new MessageProvider());
        assertAll(
                () -> assertEquals("Input Your First Name:", messageService.getMessage("student.input-first-name")),
                () -> assertEquals("Result", messageService.getMessage("questionnaire.result-header")),
                () -> assertEquals("Input Your choice:", messageService.getMessage("question.input-choice"))
        );
    }
}
