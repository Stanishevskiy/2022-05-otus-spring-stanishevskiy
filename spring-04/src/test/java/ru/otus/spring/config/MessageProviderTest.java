package ru.otus.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.services.IOService;
import ru.otus.spring.services.impl.MessageServiceImpl;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Поставщик сообщений")
class MessageProviderTest {

    @Mock
    private IOService ioService;
    @Test
    @DisplayName("при заданной локали RU возвращаются сообщения на русском")
    void russianMessagesWithRULocale() {
        var ruLocale = new Locale.Builder().setLanguage("ru").setRegion("RU").build();
        var appProps = new AppProperties();
        appProps.setLangLocale(ruLocale);


        var messageService = new MessageServiceImpl(appProps, new MessageProvider(), ioService);
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

        var messageService = new MessageServiceImpl(appProps, new MessageProvider(), ioService);
        assertAll(
                () -> assertEquals("Input Your First Name:", messageService.getMessage("student.input-first-name")),
                () -> assertEquals("Result", messageService.getMessage("questionnaire.result-header")),
                () -> assertEquals("Input Your choice:", messageService.getMessage("question.input-choice"))
        );
    }
}
