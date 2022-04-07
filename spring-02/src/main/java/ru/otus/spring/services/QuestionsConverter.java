package ru.otus.spring.services;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionsConverter {

    public List<Question> convertIntoObject(List<String> questionsStr) {
        return questionsStr.stream().map(this::strToQuestion).toList();
    }

    private Question strToQuestion(String questionStr) {
        var strArr = questionStr.split(",");
        if (strArr.length <= 1) {
            return new Question(questionStr, new ArrayList<>());
        }
        var answers = Arrays.stream(strArr)
                .skip(1)
                .map(this::strToAnswer).toList();
        return new Question(strArr[0], answers);
    }

    private Answer strToAnswer(String answerStr) {
        var answerStrSplit = answerStr.split("\\|");
        if (answerStrSplit.length <= 1) {
            return new Answer(answerStr, false);
        }
        return new Answer(answerStrSplit[0], Boolean.parseBoolean(answerStrSplit[1]));
    }
}
