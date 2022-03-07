package ru.otus.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.exceptions.ReadQuestionnaireException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class CsvQuestionsDao implements QuestionsDao {

    private final java.lang.String filePath;

    public CsvQuestionsDao(java.lang.String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> read() {
        try {
            var questionsFile = new ClassPathResource(filePath).getFile();
            return Files.readAllLines(questionsFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadQuestionnaireException(e.getMessage(), e);
        }
    }
}
