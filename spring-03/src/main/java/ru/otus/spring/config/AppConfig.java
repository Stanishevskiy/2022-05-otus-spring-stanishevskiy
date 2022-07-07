package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.services.IOService;
import ru.otus.spring.services.IOServiceImpl;

@RequiredArgsConstructor
@Configuration
public class AppConfig {

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }
}
