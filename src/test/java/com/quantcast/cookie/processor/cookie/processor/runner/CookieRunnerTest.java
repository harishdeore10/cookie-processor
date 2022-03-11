package com.quantcast.cookie.processor.cookie.processor.runner;

import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CookieRunnerTest {

    @InjectMocks
    private CookieRunner cookieRunner;

    /**
     * Test valid command scenario when file path and date are correctly present
     */

    @Test
    public void testValidCommandInput() throws CookieProcessorException {

        String[] input = {"-f", "src/test/resources/records.csv", "-d", "2018-12-09"};
        cookieRunner.run(input);
    }

}
