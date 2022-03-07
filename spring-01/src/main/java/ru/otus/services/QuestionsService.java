package ru.otus.services;

import ru.otus.dao.QuestionsDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

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
            System.out.println((i + 1) + ". " + questions.get(i).getText());
            List<Answer> answers = questions.get(i).getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                System.out.println("\t" + (j + 1) + ") " + answers.get(j).getText());
            }
        }
    }
}
