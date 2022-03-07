package ru.otus.services;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionsConverter {

    public List<Question> convertIntoObject(List<String> questionsStr) {
        return questionsStr.stream()
                .map(questionStr -> {
                    var strArr = questionStr.split(",");
                    if (strArr.length > 1) {
                        var answers = Arrays.stream(strArr)
                                .skip(1)
                                .map(Answer::new)
                                .collect(Collectors.toList());
                        return new Question(strArr[0], answers);
                    } else {
                        return new Question(questionStr, new ArrayList<>());
                    }
                }).collect(Collectors.toList());
    }
}
