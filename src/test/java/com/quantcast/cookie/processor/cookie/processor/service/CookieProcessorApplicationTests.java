package com.quantcast.cookie.processor.cookie.processor.service;

import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.model.Command;
import com.quantcast.cookie.processor.cookie.processor.service.impl.CookieProcessorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
class CookieProcessorApplicationTests {

    @InjectMocks
    private CookieProcessorServiceImpl cookieProcessorService;

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
    }

    /**
     * Test valid scenario when file path and date are correctly present
     */

    @Test
    public void testMostActiveCookie() throws CookieProcessorException {

        Command command = new Command();
        command.setFile("src/test/resources/records.csv");
        command.setDate(parse("2018-12-09"));

        cookieProcessorService.process(command);
        assertThat(byteArrayOutputStream.toString().contains("AtY0laUfhglK3lC7"));
    }

    /**
     * Test valid scenario when file path and date are correctly present but only single entries are present for cookies.
     * ### ASSUMPTION
     * Most active cookie should be present at least twice in a file.
     */

    @Test
    public void testMostActiveCookieWhenSingleEntriesPresent() throws CookieProcessorException {

        Command command = new Command();
        command.setFile("src/test/resources/records_single_entry.csv");
        command.setDate(parse("2018-12-09"));

        cookieProcessorService.process(command);
        assertThat(byteArrayOutputStream.toString().isEmpty());
    }

    /**
     * Test invalid scenario when date is incorrect
     */
    @Test
    public void testInvalidDate() throws CookieProcessorException {
        Command command = new Command();
        command.setFile("src/test/resources/records.csv");
        command.setDate(parse("2018-12-19"));

        cookieProcessorService.process(command);
        assertThat(byteArrayOutputStream.toString().isEmpty());
    }

    /**
     * Test invalid scenario when file path is incorrect
     */
    @Test
    public void testInvalidFile() {
        Command command = new Command();
        command.setFile("src/test/resources/records_1.csv");
        command.setDate(parse("2018-12-19"));

        assertThatThrownBy(() -> cookieProcessorService.process(command))
                .isInstanceOf(CookieProcessorException.class);
    }

}
