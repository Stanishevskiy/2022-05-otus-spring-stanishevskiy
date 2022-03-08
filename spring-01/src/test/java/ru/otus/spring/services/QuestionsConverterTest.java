package ru.otus.spring.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Конвертер csv-строк вопросов в объекты")
class QuestionsConverterTest {

    private static final List<String> questionsStr = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        questionsStr.add("My question 1,Answer One,Answer Two");
        questionsStr.add("My question 2");
    }

    @Test
    @DisplayName("Строки преобразованы в объекты корректно")
    void convertIntoObject() {
        var converter = new QuestionsConverter();
        var questions = converter.convertIntoObject(questionsStr);
        var expectedQuestion1 = new Question("My question 1",
                                            List.of(new Answer("Answer One"),
                                                    new Answer("Answer Two")));
        var expectedQuestion2 = new Question("My question 2", List.of());
        assertAll(
                () -> assertThat(questions).hasSize(2),
                () -> assertThat(questions.get(0)).isEqualTo(expectedQuestion1),
//                () -> assertThat(questions.get(0).getAnswers()).hasSize(2),
//                () -> assertThat(questions.get(0).getText()).isEqualTo(expectedQuestion1.getText()),
//                () -> assertThat(questions.get(0).getAnswers().get(0)).isEqualTo(expectedQuestion1.getAnswers().get(0)),
//                () -> assertThat(questions.get(0).getAnswers().get(1)).isEqualTo(expectedQuestion1.getAnswers().get(1)),
                () -> assertThat(questions.get(1)).isEqualTo(expectedQuestion2)
        );
    }
}