package ru.otus.spring.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.config.AppProperties;

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
        appProps.setLocale(ruLocale);

        var messageProvider = new MessageProvider(appProps);
        assertAll(
                () -> assertEquals("Введите Ваше имя:", messageProvider.getStudentInputFirstName()),
                () -> assertEquals("Результат", messageProvider.getQuestionnaireResultHeader()),
                () -> assertEquals("Введите Ваш ответ:", messageProvider.getQuestionInputChoice())
        );
    }

    @Test
    @DisplayName("при локали по умолчанию возвращаются сообщения на английском")
    void englishMessagesWithDefaultLocale() {
        var defaultLocale = Locale.getDefault();
        var appProps = new AppProperties();
        appProps.setLocale(defaultLocale);

        var messageProvider = new MessageProvider(appProps);
        assertAll(
                () -> assertEquals("Input Your First Name:", messageProvider.getStudentInputFirstName()),
                () -> assertEquals("Result", messageProvider.getQuestionnaireResultHeader()),
                () -> assertEquals("Input Your choice:", messageProvider.getQuestionInputChoice())
        );
    }
}
