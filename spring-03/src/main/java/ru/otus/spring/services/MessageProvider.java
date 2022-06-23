package ru.otus.spring.services;

import org.springframework.stereotype.Component;
import ru.otus.spring.config.AppProperties;

import java.util.ResourceBundle;

@Component
public class MessageProvider {

    public static final String LINE_SEPARATOR = "------------";

    private final ResourceBundle resourceBundle;

    public MessageProvider(AppProperties appProps) {
        this.resourceBundle = ResourceBundle.getBundle("questionnaire", appProps.getLocale());
    }


    public String getQuestionsFilePath() {
        return resourceBundle.getString("source.questions");
    }

    public String getLineSeparator() {
        return LINE_SEPARATOR;
    }


    public String getStudentInfoHeader() {
        return resourceBundle.getString("student.info");
    }

    public String getStudentInputFirstName() {
        return resourceBundle.getString("student.input-first-name");
    }

    public String getStudentInputLastName() {
        return resourceBundle.getString("student.input-last-name");
    }


    public String getQuestionnaireHeader() {
        return resourceBundle.getString("questionnaire.header");
    }

    public String getQuestionInputChoice() {
        return resourceBundle.getString("question.input-choice");
    }

    public String getQuestionIncorrectValue() {
        return resourceBundle.getString("question.incorrect-value");
    }


    public String getQuestionnaireResultHeader() {
        return resourceBundle.getString("questionnaire.result-header");
    }

    public String getQuestionnaireStudentResult() {
        return resourceBundle.getString("questionnaire.student-result");
    }


    public String getResultSuccess() {
        return resourceBundle.getString("result.success");
    }

    public String getResultFail() {
        return resourceBundle.getString("result.fail");
    }
}
