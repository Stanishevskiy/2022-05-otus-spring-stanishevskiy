package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.domain.Student;
import ru.otus.spring.services.IOService;
import ru.otus.spring.services.QuestionnaireService;

@RequiredArgsConstructor
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final AppProperties appProps;
    private final QuestionsDao questionsDao;
    private final IOService ioService;
    private final MessageServiceImpl msgService;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void startQuestionnaire() {
        var student = collectStudentInfo();
        var questions = questionsDao.read();
        var survey = new Questionnaire(questions, student);
        askQuestions(survey);
        printResult(survey);
    }

    private Student collectStudentInfo() {
        msgService.writeMsgByCode("student.info");
        msgService.writeLineSeparator();
        var studentFirstName = msgService.readWithPromptCode("student.input-first-name");
        var studentLastName = msgService.readWithPromptCode("student.input-last-name");
        ioService.outputString("\n");
        return new Student(studentFirstName, studentLastName);
    }

    private void askQuestions(Questionnaire questionnaire) {
        msgService.writeMsgByCode("questionnaire.header");
        msgService.writeLineSeparator();
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
            var studentAnswer = msgService.readAllowedNumWithPromptCode("question.input-choice",
                                                                        answers.size(),
                                                                        "question.invalid-value");
            if (studentAnswer == correctAnswer) {
                questionnaire.setScore(questionnaire.getScore() + 1);
            }
        }
        ioService.outputString("\n");
    }

    private void printResult(Questionnaire questionnaire) {
        msgService.writeMsgByCode("questionnaire.result-header");
        msgService.writeLineSeparator();
        var resultStr = (questionnaire.getScore() >= appProps.getMinScore())
                ? msgService.getMessage("result.success")
                : msgService.getMessage("result.fail");
        var questionnaireResult = String.format(msgService.getMessage("questionnaire.student-result"),
                                                questionnaire.getStudent().firstName(),
                                                questionnaire.getStudent().lastName(),
                                                questionnaire.getScore(),
                                                resultStr);
        ioService.outputString(questionnaireResult);
    }
}
