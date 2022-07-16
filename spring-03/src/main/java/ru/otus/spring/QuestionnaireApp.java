package ru.otus.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class QuestionnaireApp {

    public static void main(String[] args) {

        SpringApplication.run(QuestionnaireApp.class, args);
    }
}
