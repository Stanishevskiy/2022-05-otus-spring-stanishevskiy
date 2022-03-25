package ru.otus.spring.services;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionsService {
    private final QuestionsDao questionsDao;
    private final ConsoleIOService consoleIOService;

    public QuestionsService(QuestionsDao questionsDao, ConsoleIOService consoleIOService) {
        this.questionsDao = questionsDao;
        this.consoleIOService = consoleIOService;
    }

    public void printAllQuestions() {
        List<Question> questions = questionsDao.read();
        consoleIOService.outputString("Questionnaire");
        consoleIOService.outputString("-------------");
        for (int i = 0; i < questions.size() ; i++) {
            consoleIOService.outputString((i + 1) + ". " + questions.get(i).text());
            List<Answer> answers = questions.get(i).answers();
            for (int j = 0; j < answers.size(); j++) {
                consoleIOService.outputString("\t" + (j + 1) + ") " + answers.get(j).text());
            }
        }
    }
}
