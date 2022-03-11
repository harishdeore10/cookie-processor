package com.quantcast.cookie.processor.cookie.processor.excutor;

import com.quantcast.cookie.processor.cookie.processor.service.CookieProcessorService;
import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.executor.CookieProcessExecutor;
import com.quantcast.cookie.processor.cookie.processor.service.impl.CommandServiceImpl;
import com.quantcast.cookie.processor.cookie.processor.model.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static java.time.LocalDate.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CookieProcessExecutorTest {


    @InjectMocks
    private CookieProcessExecutor cookieProcessExecutor;

    @Mock
    private CommandServiceImpl commandService;

    @Mock
    private CookieProcessorService cookieProcessorService;

    /**
     * Test valid command scenario when file path and date are correctly present
     */

    @Test
    public void testSuccessScenario() throws CookieProcessorException, IOException {

        String[] input = {"-f", "src/test/resources/records.csv", "-d", "2018-12-09"};

        Command command = new Command();
        command.setFile("src/test/resources/records.csv");
        command.setDate(parse("2018-12-09"));
        when(commandService.getInputCommand(any())).thenReturn(command);
        doNothing().when(cookieProcessorService).process(any());

        int result = cookieProcessExecutor.execute(input);
        assertEquals(0, result);
    }

    /**
     * Test invalid command scenario.
     */
    @Test
    public void testFailureScenario() throws CookieProcessorException, IOException {
        String[] input = {"-f", "src/test/resources/records.csv", "-d", "2018-12-09"};

        when(commandService.getInputCommand(any())).thenThrow(CookieProcessorException.class);
        doNothing().when(cookieProcessorService).process(any());

        int result = cookieProcessExecutor.execute(input);
        assertEquals(1, result);
    }

}


