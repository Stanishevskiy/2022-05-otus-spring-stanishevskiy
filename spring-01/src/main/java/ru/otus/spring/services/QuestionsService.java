package ru.otus.spring.services;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionsService {
    private final QuestionsDao questionsDao;
    private final QuestionsConverter questionsConverter;

    public QuestionsService(QuestionsDao questionsDao, QuestionsConverter questionsConverter) {
        this.questionsDao = questionsDao;
        this.questionsConverter = questionsConverter;
    }

    public void printAllQuestions() {
        List<Question> questions = questionsConverter.convertIntoObject(questionsDao.read());
        System.out.println("Questionnaire");
        System.out.println("-------------");
        for (int i = 0; i < questions.size() ; i++) {
            System.out.println((i + 1) + ". " + questions.get(i).text());
            List<Answer> answers = questions.get(i).answers();
            for (int j = 0; j < answers.size(); j++) {
                System.out.println("\t" + (j + 1) + ") " + answers.get(j).text());
            }
        }
    }
}
