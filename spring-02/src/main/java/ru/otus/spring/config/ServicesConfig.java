package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.services.IOService;
import ru.otus.spring.services.QuestionsConverter;
import ru.otus.spring.services.QuestionnaireServiceImpl;

@Configuration
public class ServicesConfig {

    @Bean
    public QuestionsConverter questionsConverter() {
        return new QuestionsConverter();
    }

    @Bean
    public QuestionnaireServiceImpl questionsService(QuestionsDao questionsDao,
                                                     IOService ioService) {
        return new QuestionnaireServiceImpl(questionsDao, ioService);
    }

}
