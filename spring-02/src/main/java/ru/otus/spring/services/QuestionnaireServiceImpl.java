package ru.otus.spring.services;

import org.springframework.beans.factory.annotation.Value;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Student;

public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final QuestionsDao questionsDao;
    private final IOService ioService;

    @Value("${app.questions.minScore}")
    private int minScore;

    public QuestionnaireServiceImpl(QuestionsDao questionsDao, IOService ioService) {
        this.questionsDao = questionsDao;
        this.ioService = ioService;
    }

    @Override
    public void startQuestionnaire() {
        var student = collectStudentInfo();
        askQuestions(student);
        printResult(student);
    }

    private Student collectStudentInfo() {
        ioService.outputString("Student Info");
        ioService.outputString("------------");
        var studentFirstName = ioService.readStringWithPrompt("Input Your First Name:");
        var studentLastName = ioService.readStringWithPrompt("Input Your Last Name:");
        ioService.outputString("\n");
        return new Student(studentFirstName, studentLastName);
    }

    private void askQuestions(Student student) {
        ioService.outputString("Questionnaire");
        ioService.outputString("-------------");
        var questions = questionsDao.read();

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
                student.setScore(student.getScore() + 1);
            }
        }
        ioService.outputString("\n");
    }

    private void printResult(Student student) {
        ioService.outputString("Result");
        ioService.outputString("-------");
        var resultStr = (student.getScore() >= minScore) ? "SUCCESS" : "FAIL";
        ioService.outputString(student.getFirstName() + " " + student.getLastName()
                + ", Your result: " + student.getScore() + " - " + resultStr);
    }
}
