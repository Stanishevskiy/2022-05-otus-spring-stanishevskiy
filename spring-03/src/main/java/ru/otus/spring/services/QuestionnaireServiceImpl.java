package ru.otus.spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.Student;

@RequiredArgsConstructor
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final AppProperties appProps;
    private final QuestionsDao questionsDao;
    private final IOService ioService;
    private final MessageService messageService;

    @Override
    public void startQuestionnaire() {
        var student = collectStudentInfo();
        var questions = questionsDao.read();
        var survey = new Questionnaire(questions, student);
        askQuestions(survey);
        printResult(survey);
    }

    private Student collectStudentInfo() {
        ioService.outputString(messageService.getMessage("student.info"));
        ioService.outputString(messageService.getLineSeparator());
        var studentFirstName = ioService
                .readStringWithPrompt(messageService.getMessage("student.input-first-name"));
        var studentLastName = ioService
                .readStringWithPrompt(messageService.getMessage("student.input-last-name"));
        ioService.outputString("\n");
        return new Student(studentFirstName, studentLastName);
    }

    private void askQuestions(Questionnaire questionnaire) {
        ioService.outputString(messageService.getMessage("questionnaire.header"));
        ioService.outputString(messageService.getLineSeparator());
        var questions = questionnaire.getQuestions();
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
            var studentAnswer = ioService
                    .readAllowedIntWithPrompt(messageService.getMessage("question.input-choice"),
                                              answers.size(),
                                              messageService.getMessage("question.invalid-value"));
            if (studentAnswer == correctAnswer) {
                questionnaire.setScore(questionnaire.getScore() + 1);
            }
        }
        ioService.outputString("\n");
    }

    private void printResult(Questionnaire questionnaire) {
        ioService.outputString(messageService.getMessage("questionnaire.result-header"));
        ioService.outputString(messageService.getLineSeparator());
        var resultStr = (questionnaire.getScore() >= appProps.getMinScore())
                ? messageService.getMessage("result.success")
                : messageService.getMessage("result.fail");
        var questionnaireResult = String.format(messageService.getMessage("questionnaire.student-result"),
                questionnaire.getStudent().firstName(), questionnaire.getStudent().lastName(),
                questionnaire.getScore(), resultStr);
        ioService.outputString(questionnaireResult);
    }
}
