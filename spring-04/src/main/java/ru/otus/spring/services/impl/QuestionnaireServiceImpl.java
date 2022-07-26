package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
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


    private Student student;
    private Questionnaire survey;

    @Override
    public void setStudentInfo(Student student) {
        this.student = student;
    }

    @Override
    public void startQuestionnaire() {
        var questions = questionsDao.read();
        survey = new Questionnaire(questions, student);
        askQuestions();
        printResult();
    }


    private void askQuestions() {
        msgService.writeMsgByCode("questionnaire.header");
        msgService.writeLineSeparator();
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
            var studentAnswer = msgService.readAllowedNumWithPromptCode("question.input-choice",
                                                                        answers.size(),
                                                                        "question.invalid-value");
            if (studentAnswer == correctAnswer) {
                survey.setScore(survey.getScore() + 1);
            }
        }
        ioService.outputString("\n");
    }

    @Override
    public void printResult() {
        msgService.writeMsgByCode("questionnaire.result-header");
        msgService.writeLineSeparator();
        var resultStr = (survey.getScore() >= appProps.getMinScore())
                ? msgService.getMessage("result.success")
                : msgService.getMessage("result.fail");
        var questionnaireResult = String.format(msgService.getMessage("questionnaire.student-result"),
                                                survey.getStudent().firstName(),
                                                survey.getStudent().lastName(),
                                                survey.getScore(),
                                                resultStr);
        ioService.outputString(questionnaireResult);
    }
}
