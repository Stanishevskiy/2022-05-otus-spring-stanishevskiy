package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.services.QuestionsService;

public class QuestionnaireApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionsService questionsService = context.getBean(QuestionsService.class);
        questionsService.printAllQuestions();
    }
}
