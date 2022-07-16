package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.spring.services.QuestionnaireService;

@RequiredArgsConstructor
@Component
public class CommandLineRunnerConfig {

    private final QuestionnaireService questionnaireService;

    @Bean
    public CommandLineRunner commandLineRunnerBean() {
        return args -> questionnaireService.startQuestionnaire();
    }
}
