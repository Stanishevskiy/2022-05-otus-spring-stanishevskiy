package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.services.IOServiceConsole;

@Configuration
public class IOConfig {

    @Bean
    public IOServiceConsole ioService() {
        return new IOServiceConsole(System.in, System.out);
    }
}
