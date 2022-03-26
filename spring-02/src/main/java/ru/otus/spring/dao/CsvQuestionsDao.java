package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.ReadingFileException;
import ru.otus.spring.services.QuestionsConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionsDao implements QuestionsDao {

    private final String questionsFilePath;
    private final QuestionsConverter questionsConverter;

    public CsvQuestionsDao(String questionsFilePath, QuestionsConverter questionsConverter) {
        this.questionsFilePath = questionsFilePath;
        this.questionsConverter = questionsConverter;
    }

    @Override
    public List<Question> read() {
        try {
            var questionsIS = new ClassPathResource(questionsFilePath).getInputStream();
            var questionsReader = new BufferedReader(new InputStreamReader(questionsIS));

            var questionsStr = new ArrayList<String>();
            while (questionsReader.ready()) {
                questionsStr.add(questionsReader.readLine());
            }
            return questionsConverter.convertIntoObject(questionsStr);

        } catch (IOException e) {
            throw new ReadingFileException(e.getMessage(), e);
        }
    }
}
