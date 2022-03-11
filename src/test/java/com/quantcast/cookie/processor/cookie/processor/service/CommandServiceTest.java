package com.quantcast.cookie.processor.cookie.processor.service;

import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.model.Command;
import com.quantcast.cookie.processor.cookie.processor.service.impl.CommandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class CommandServiceTest {

    @InjectMocks
    private CommandServiceImpl commandService;

    /**
     * Test valid command scenario when file path and date are correctly present
     */

    @Test
    public void testValidCommandInput() throws CookieProcessorException {

        String[] input = {"-f", "src/test/resources/records.csv", "-d", "2018-12-09"};

        Command command = commandService.getInputCommand(input);
        assertNotNull(command);
        assertEquals("src/test/resources/records.csv", command.getFile());
        assertEquals(parse("2018-12-09"), command.getDate());
    }

    /**
     * Test invalid command scenario when file path is missing
     */
    @Test
    public void testMissingFilepathCommandInput() {

        String[] input = {"-d", "2018-12-09"};

        assertThatThrownBy(() -> commandService.getInputCommand(input))
                .isInstanceOf(CookieProcessorException.class).hasMessage("Error code : 0 : An Input command error has occurred. : Missing required option: f");

    }

    /**
     * Test invalid command scenario when date is missing
     */
    @Test
    public void testMissingDateCommandInput() {

        String[] input = {"-f", "src/test/resources/records.csv"};

        assertThatThrownBy(() -> commandService.getInputCommand(input))
                .isInstanceOf(CookieProcessorException.class).hasMessage("Error code : 0 : An Input command error has occurred. : Missing required option: d");

    }
}
