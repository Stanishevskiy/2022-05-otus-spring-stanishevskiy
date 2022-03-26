package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.services.ConsoleIOService;

@Configuration
public class IOConfig {

    @Bean
    public ConsoleIOService ioService() {
        return new ConsoleIOService(System.in, System.out);
    }
}
