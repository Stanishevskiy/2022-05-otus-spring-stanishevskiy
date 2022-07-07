package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.SourceProvider;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exceptions.ReadingFileException;
import ru.otus.spring.services.QuestionsConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class QuestionsDaoCsv implements QuestionsDao {

    private final SourceProvider sourceProvider;
    private final QuestionsConverter questionsConverter;

    @Override
    public List<Question> read() {
        try (var questionsIS = new ClassPathResource(sourceProvider.getSourcePath()).getInputStream();
             var questionsReader = new BufferedReader(new InputStreamReader(questionsIS))) {

            var questionsStr = new ArrayList<String>();
            while (questionsReader.ready()) {
                questionsStr.add(questionsReader.readLine());
            }
            return questionsConverter.convertIntoObject(questionsStr);

        } catch (IOException e) {
            throw new ReadingFileException("Failed to read file: " + sourceProvider.getSourcePath(), e);
        }
    }
}
