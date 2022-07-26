package ru.otus.spring.services.impl;

import ru.otus.spring.services.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final Scanner input;
    private final PrintStream output;

    public IOServiceImpl(InputStream input, PrintStream output) {
        this.input = new Scanner(input);
        this.output = output;
    }

    @Override
    public String inputStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }

    @Override
    public int inputAllowedNumWithPrompt(String prompt, int number, String errorStr) {
        outputString(prompt);
        do {
            try {
                var answerNum = Integer.parseInt(input.nextLine());
                if (answerNum < 1 || answerNum > number) {
                    throw new NumberFormatException();
                }
                return answerNum;
            } catch (NumberFormatException e) {
                outputString(String.format(errorStr, number));
            }
        } while (true);
    }

    @Override
    public void outputString(String str) {
        output.println(str);
    }
}
