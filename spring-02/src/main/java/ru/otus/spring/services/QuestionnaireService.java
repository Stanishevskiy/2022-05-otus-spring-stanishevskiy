package ru.otus.spring.services;

import org.springframework.beans.factory.annotation.Value;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Student;

public class QuestionnaireService {
    private final QuestionsDao questionsDao;
    private final IOService ioService;

    @Value("${app.minscore}")
    private int minScore;

    public QuestionnaireService(QuestionsDao questionsDao, IOService ioService) {
        this.questionsDao = questionsDao;
        this.ioService = ioService;
    }

    public void startQuestionnaire() {
        var student = collectStudentInfo();
        var result = askQuestions();
        printResult(student, result);
    }

    private Student collectStudentInfo() {
        ioService.outputString("Student Info");
        ioService.outputString("------------");
        var studentFirstName = ioService.readStringWithPrompt("Input Your First Name:");
        var studentLastName = ioService.readStringWithPrompt("Input Your Last Name:");
        ioService.outputString("------------\n");
        return new Student(studentFirstName, studentLastName);
    }

    private int askQuestions() {
        ioService.outputString("Questionnaire");
        ioService.outputString("-------------");
        var questions = questionsDao.read();

        var studentScore = 0;
        for (int i = 0; i < questions.size(); i++) {
            var question = questions.get(i);
            ioService.outputString((i + 1) + ". " + question.text());

            var answers = question.answers();
            int correctAnswer = 0;
            for (int j = 0; j < answers.size(); j++) {
                var answer = answers.get(j);
                if (answer.correct()) {
                    correctAnswer = j + 1;
                }
                ioService.outputString("\t" + (j + 1) + ") " + answer.text());
            }
            var studentAnswer = ioService.readAllowedIntWithPrompt("Input Your choice:", answers.size());
            if (studentAnswer == correctAnswer) {
                studentScore++;
            }
        }
        ioService.outputString("-------------");
        return studentScore;
    }

    private void printResult(Student student, int result) {
        ioService.outputString("Result");
        ioService.outputString("------");
        var resultStr = (result >= 4) ? "SUCCESS" : "FAIL";
        ioService.outputString(student.firstName() + " " + student.lastName() + ", Your result: " + result + " - " + resultStr);
    }

    public void printAllQuestions() {
        var questions = questionsDao.read();
        ioService.outputString("Questionnaire");
        ioService.outputString("-------------");
        for (int i = 0; i < questions.size() ; i++) {
            ioService.outputString((i + 1) + ". " + questions.get(i).text());
            var answers = questions.get(i).answers();
            for (int j = 0; j < answers.size(); j++) {
                ioService.outputString("\t" + (j + 1) + ") " + answers.get(j).text());
            }
        }
    }
}
