package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.exceptions.ReadingFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionsDao implements QuestionsDao {

    private final String filePath;

    public CsvQuestionsDao(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> read() {
        try {
            var questionsIS = new ClassPathResource(filePath).getInputStream();
            var questionsReader = new BufferedReader(new InputStreamReader(questionsIS));

            var questionsStr = new ArrayList<String>();
            while (questionsReader.ready()) {
                questionsStr.add(questionsReader.readLine());
            }
            return questionsStr;
        } catch (IOException e) {
            throw new ReadingFileException(e.getMessage(), e);
        }
    }
}
