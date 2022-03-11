package com.quantcast.cookie.processor.cookie.processor.configuration;

import com.quantcast.cookie.processor.cookie.processor.executor.CookieExecutor;
import com.quantcast.cookie.processor.cookie.processor.executor.CookieProcessExecutor;
import com.quantcast.cookie.processor.cookie.processor.runner.CookieRunner;
import com.quantcast.cookie.processor.cookie.processor.service.CommandService;
import com.quantcast.cookie.processor.cookie.processor.service.CookieProcessorService;
import com.quantcast.cookie.processor.cookie.processor.service.impl.CommandServiceImpl;
import com.quantcast.cookie.processor.cookie.processor.service.impl.CookieProcessorServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext applicationContext,
                                               final CookieExecutor cookieExecutor) {
        return new CookieRunner(applicationContext, cookieExecutor);
    }

    @Bean
    public CookieProcessorService cookieProcessorService() {
        return new CookieProcessorServiceImpl();
    }

    @Bean
    public CommandService commandService() {
        return new CommandServiceImpl();
    }

    @Bean
    public CookieExecutor cookieExecutor(final CookieProcessorService cookieProcessorService, final CommandService commandService) {
        return new CookieProcessExecutor(cookieProcessorService, commandService);
    }

}
