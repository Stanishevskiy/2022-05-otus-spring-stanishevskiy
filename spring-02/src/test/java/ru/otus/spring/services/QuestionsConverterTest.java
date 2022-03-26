package ru.otus.spring.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Конвертер csv-строк вопросов в объекты")
class QuestionsConverterTest {

    private static final List<String> questionsStr = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        questionsStr.add("My question 1?,Answer One|true,Answer Two|false");
        questionsStr.add("My question 2?");
    }

    @Test
    @DisplayName("Строки преобразованы в объекты корректно")
    void convertIntoObject() {
        var converter = new QuestionsConverter();
        var questions = converter.convertIntoObject(questionsStr);
        var expectedQuestion1 = new Question("My question 1?",
                                            List.of(new Answer("Answer One", true),
                                                    new Answer("Answer Two", false)));
        var expectedQuestion2 = new Question("My question 2?", List.of());

        assertThat(questions).hasSize(2)
                .containsExactlyInAnyOrder(expectedQuestion1, expectedQuestion2);
    }
}