package ru.otus.spring.services;

import ru.otus.spring.domain.Student;

public interface QuestionnaireService {

    void setStudentInfo(Student student);

    void startQuestionnaire();

    void printResult();
}
