package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Questionnaire {

    private final List<Question> questions;
    private final Student student;
    private int score = 0;
}
