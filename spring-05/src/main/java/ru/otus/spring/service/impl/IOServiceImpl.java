package ru.otus.spring.service.impl;

import ru.otus.spring.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Scanner;

public class IOServiceImpl implements IOService {
    private final Scanner input;
    private final PrintStream output;

    public IOServiceImpl(InputStream input, PrintStream output) {
        this.input = new Scanner(input);
        this.output = output;
    }

    @Override
    public String inputString() {
        return input.nextLine();
    }

    @Override
    public void outputString(String str) {
        output.println(str);
    }

    @Override
    public <T> void outputCollection(Collection<T> collection) {
        collection.forEach(System.out::println);
    }
}
