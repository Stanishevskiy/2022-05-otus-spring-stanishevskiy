package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.services.IOService;
import ru.otus.spring.services.QuestionsConverter;
import ru.otus.spring.services.QuestionnaireService;

@Configuration
public class ServicesConfig {

    @Bean
    public QuestionsConverter questionsConverter() {
        return new QuestionsConverter();
    }

    @Bean
    public QuestionnaireService questionsService(QuestionsDao questionsDao,
                                                 IOService ioService) {
        return new QuestionnaireService(questionsDao, ioService);
    }

}
