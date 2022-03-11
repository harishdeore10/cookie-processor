package com.quantcast.cookie.processor.cookie.processor.runner;

import com.quantcast.cookie.processor.cookie.processor.executor.CookieExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import static java.lang.System.exit;

/**
 * Command Line Runner class to process command line arguments and cookie logs file
 */

public class CookieRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieRunner.class);

    private ApplicationContext applicationContext;
    private CookieExecutor cookieExecutor;

    public CookieRunner(ApplicationContext applicationContext, CookieExecutor cookieExecutor) {
        this.applicationContext = applicationContext;
        this.cookieExecutor = cookieExecutor;
    }

    @Override
    public void run(String... args) {
        try {
            LOGGER.info("Process started");
            terminateApplication(() -> cookieExecutor.execute(args));
        } catch (Exception exception) {
            LOGGER.error("Exception occurred : " + exception.getMessage());
        }
    }

    private void terminateApplication(final ExitCodeGenerator exitCodeGenerator) {
        exit(SpringApplication.exit(applicationContext, exitCodeGenerator));
    }

}
