package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.CsvQuestionsDao;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.services.QuestionsConverter;

@Configuration
@PropertySource("classpath:application.properties")
public class DaoConfig {

    @Bean
    public QuestionsDao questionsDao(@Value("${app.questions.filepath}") String filePath,
                                     QuestionsConverter questionConverter) {
        return new CsvQuestionsDao(filePath, questionConverter);
    }

}
