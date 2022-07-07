package ru.otus.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.otus.spring.services.QuestionnaireService;

@RequiredArgsConstructor
@SpringBootApplication
public class QuestionnaireApp {

    private final QuestionnaireService questionnaireService;

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunnerBean() {
        return args -> questionnaireService.startQuestionnaire();
    }
}
