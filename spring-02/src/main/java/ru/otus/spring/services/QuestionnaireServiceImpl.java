package ru.otus.spring.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Student;
import ru.otus.spring.domain.Survey;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final QuestionsDao questionsDao;
    private final IOService ioService;
    private final int minScore;

    public QuestionnaireServiceImpl(QuestionsDao questionsDao,
                                    IOService ioService,
                                    @Value("${app.questions.minScore}") int minScore) {
        this.questionsDao = questionsDao;
        this.ioService = ioService;
        this.minScore = minScore;
    }

    @Override
    public void startQuestionnaire() {
        var student = collectStudentInfo();
        var questions = questionsDao.read();
        var survey = new Survey(questions, student);
        askQuestions(survey);
        printResult(survey);
    }

    private Student collectStudentInfo() {
        ioService.outputString("Student Info");
        ioService.outputString("------------");
        var studentFirstName = ioService.readStringWithPrompt("Input Your First Name:");
        var studentLastName = ioService.readStringWithPrompt("Input Your Last Name:");
        ioService.outputString("\n");
        return new Student(studentFirstName, studentLastName);
    }

    private void askQuestions(Survey survey) {
        ioService.outputString("Questionnaire");
        ioService.outputString("-------------");
        var questions = survey.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            var question = questions.get(i);
            ioService.outputString((i + 1) + ". " + question.text());

            var answers = question.answers();
            var correctAnswer = 0;
            for (int j = 0; j < answers.size(); j++) {
                var answer = answers.get(j);
                if (answer.correct()) {
                    correctAnswer = j + 1;
                }
                ioService.outputString("\t" + (j + 1) + ") " + answer.text());
            }
            var studentAnswer = ioService.readAllowedIntWithPrompt("Input Your choice:", answers.size());
            if (studentAnswer == correctAnswer) {
                survey.setScore(survey.getScore() + 1);
            }
        }
        ioService.outputString("\n");
    }

    private void printResult(Survey survey) {
        ioService.outputString("Result");
        ioService.outputString("-------");
        var resultStr = (survey.getScore() >= minScore) ? "SUCCESS" : "FAIL";
        ioService.outputString(survey.getStudent().firstName() + " " + survey.getStudent().lastName()
                + ", Your result: " + survey.getScore() + " - " + resultStr);
    }
}
