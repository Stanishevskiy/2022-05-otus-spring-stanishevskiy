package ru.otus.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.services.IOServiceImpl;
import ru.otus.spring.services.MessageProvider;

import java.util.Locale;

@Data
@Configuration
@ConfigurationProperties("app.config")
public class AppProperties {

    private Locale locale;
    private long minScore;

    @Bean
    public IOServiceImpl ioService() {
        return new IOServiceImpl(System.in, System.out, new MessageProvider(this));
    }
}
