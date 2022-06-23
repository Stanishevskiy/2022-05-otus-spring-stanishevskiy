package ru.otus.spring.domain;

import java.util.List;

public class Questionnaire {

    private final List<Question> questions;
    private final Student student;
    private int score = 0;

    public Questionnaire(List<Question> questions, Student student) {
        this.questions = questions;
        this.student = student;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Student getStudent() {
        return student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
