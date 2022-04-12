package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.services.IOServiceImpl;
import ru.otus.spring.services.MessageProvider;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public IOServiceImpl ioService() {
        return new IOServiceImpl(System.in, System.out, new MessageProvider());
    }
}
