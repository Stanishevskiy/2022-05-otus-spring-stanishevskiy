package ru.otus.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.services.IOService;
import ru.otus.spring.services.impl.MessageServiceImpl;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Поставщик сообщений")
class MessageProviderTest {

    @Autowired
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
    void englishMessagesWithEnLocale() {
        var enLocale = new Locale.Builder().setLanguage("en").setRegion("EN").build();
        var appProps = new AppProperties();
        appProps.setLangLocale(enLocale);

        var messageService = new MessageServiceImpl(appProps, new MessageProvider(), ioService);
        assertAll(
                () -> assertEquals("Input Your First Name:", messageService.getMessage("student.input-first-name")),
                () -> assertEquals("Result", messageService.getMessage("questionnaire.result-header")),
                () -> assertEquals("Input Your choice:", messageService.getMessage("question.input-choice"))
        );
    }
}
