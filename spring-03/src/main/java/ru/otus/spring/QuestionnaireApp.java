package ru.otus.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.services.QuestionnaireService;

@SpringBootApplication
public class QuestionnaireApp implements CommandLineRunner {

    private final QuestionnaireService questionnaireService;

    public QuestionnaireApp(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApp.class, args);
    }

    @Override
    public void run(String... args) {
        questionnaireService.startQuestionnaire();
    }
}
