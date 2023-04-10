package ru.otus.spring.service;

import java.util.Collection;

public interface OutputService {

    void outputString(String str);

    <T> void outputCollection(Collection<T> collection);
}
