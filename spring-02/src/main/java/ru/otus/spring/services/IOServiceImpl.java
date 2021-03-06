package ru.otus.spring.services;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final Scanner input;
    private final PrintStream output;
    private final MessageProvider messageProvider;

    public IOServiceImpl(InputStream input, PrintStream output, MessageProvider messageProvider) {
        this.input = new Scanner(input);
        this.output = output;
        this.messageProvider = messageProvider;
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }

    @Override
    public int readAllowedIntWithPrompt(String prompt, int answersCount) {
        outputString(prompt);
        do {
            try {
                var answerNum = Integer.parseInt(input.nextLine());
                if (answerNum < 1 || answerNum > answersCount) {
                    throw new NumberFormatException();
                }
                return answerNum;
            } catch (NumberFormatException e) {
                outputString(String.format(messageProvider.getInputNumberValueIncorrect(), answersCount));
            }
        } while (true);
    }

    @Override
    public void outputString(String str) {
        output.println(str);
    }
}
