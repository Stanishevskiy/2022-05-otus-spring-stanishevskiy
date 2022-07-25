package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.config.AppProperties;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Контекст приложения")
class QuestionnaireAppTest {

    @Autowired
    AppProperties appProperties;

    @Test
    @DisplayName("Контекст поднимается корректно")
    void main_contextLoadWithoutErrors() {
        assertAll(
                () -> assertEquals(new Locale("ru"), appProperties.getLangLocale()),
                () -> assertEquals("myFile", appProperties.getFileName()),
                () -> assertEquals(1L, appProperties.getMinScore()),
                () -> assertEquals("./myFile_ru.csv", appProperties.getSourcePath())
        );
    }
}
