package com.quantcast.cookie.processor.cookie.processor.executor;

import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.model.Command;
import com.quantcast.cookie.processor.cookie.processor.service.CommandService;
import com.quantcast.cookie.processor.cookie.processor.service.CookieProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.quantcast.cookie.processor.cookie.processor.model.ApplicationStatus.APPLICATION_FAILED;
import static com.quantcast.cookie.processor.cookie.processor.model.ApplicationStatus.SUCCESS;

/**
 * Executor class to process command line arguments and cookie logs file
 * and return success/failure based on result.
 */

@Component
public class CookieProcessExecutor implements CookieExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieProcessExecutor.class);

    private CookieProcessorService cookieProcessorService;
    private CommandService commandService;

    public CookieProcessExecutor(CookieProcessorService cookieProcessorService, CommandService commandService) {
        this.cookieProcessorService = cookieProcessorService;
        this.commandService = commandService;
    }

    @Override
    public Integer execute(String[] args) {
        try {
            final Command command = commandService.getInputCommand(args);
            cookieProcessorService.process(command);
            return SUCCESS.getValue();
        } catch (RuntimeException | CookieProcessorException | IOException exception) {
            LOGGER.error(exception.getMessage());
        }
        return APPLICATION_FAILED.getValue();
    }
}
