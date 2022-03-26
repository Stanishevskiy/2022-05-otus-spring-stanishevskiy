package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.services.QuestionnaireService;

@Configuration
@ComponentScan
public class QuestionnaireApp {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(QuestionnaireApp.class);
        var questionnaireService = context.getBean(QuestionnaireService.class);
        questionnaireService.startQuestionnaire();
    }
}
