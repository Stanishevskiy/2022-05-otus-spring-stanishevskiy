package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.config.AppProperties;
import ru.otus.spring.domain.Student;
import ru.otus.spring.services.QuestionnaireService;

import javax.validation.constraints.Pattern;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@ShellComponent
public class QuestionnaireCommands {

    private final AppProperties appProperties;
    private final QuestionnaireService questionnaireService;

    private boolean isLoggedIn = false;
    private boolean isTestTried = false;
    private Student student;

    @ShellMethod(key = "lang", value = "Command allow select app language from: en, ru")
    public void chooseLang(@ShellOption({"-l", "--lang"}) @Pattern(regexp = "^ru$|^en$") String lang) {
        var locale = new Locale(lang);
        appProperties.setLangLocale(locale);
        log.info("Set app language: {}", lang);
    }

    @ShellMethod("Login to questionnaire app")
    public void login(@ShellOption({"-fn", "--firstname"}) String firstName,
                      @ShellOption({"-ln", "--lastname"}) String lastName) {
        student = new Student(firstName, lastName);
        log.info("Student {} {} - login", firstName, lastName);
        questionnaireService.setStudentInfo(student);
        isLoggedIn = true;
    }

    @ShellMethod("Student try pass test")
    public void test() {
        log.info("Student {} {} - try test", student.firstName(), student.lastName());
        questionnaireService.startQuestionnaire();
        isTestTried = true;
    }

    @ShellMethod("Print student test result")
    public void result() {
        log.info("Student {} {} - has result", student.firstName(), student.lastName());
        questionnaireService.printResult();
    }

    @ShellMethod("Student logout from app")
    public void logout() {
        log.info("Student {} {} - logout", student.firstName(), student.lastName());
        isLoggedIn = false;
        isTestTried = false;
    }

    @ShellMethodAvailability({"test", "logout"})
    public Availability studentLoginCheck() {
        return isLoggedIn
                ? Availability.available()
                : Availability.unavailable("You must login first!");
    }

    @ShellMethodAvailability({"login"})
    public Availability studentLogoutCheck() {
        return isLoggedIn
                ? Availability.unavailable("You login already")
                : Availability.available();
    }

    @ShellMethodAvailability({"result"})
    public Availability studentTryTestCheck() {
        return isTestTried
                ? Availability.available()
                : Availability.unavailable("Your are still not trying test");
    }
}
