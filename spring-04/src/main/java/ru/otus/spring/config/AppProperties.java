package ru.otus.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Data
@Component
@ConfigurationProperties("app.config")
public class AppProperties implements SourceProvider, LocaleProvider {

    private Locale langLocale;
    private String fileName;
    private long minScore;

    @Override
    public String getSourcePath() {
        return String.format("./%s_%s.csv", fileName, langLocale);
    }

    @Override
    public Locale getLocale() {
        return langLocale;
    }
}
