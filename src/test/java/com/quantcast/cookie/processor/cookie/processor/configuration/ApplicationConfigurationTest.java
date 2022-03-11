package com.quantcast.cookie.processor.cookie.processor.configuration;

import com.quantcast.cookie.processor.cookie.processor.executor.CookieExecutor;
import com.quantcast.cookie.processor.cookie.processor.service.impl.CommandServiceImpl;
import com.quantcast.cookie.processor.cookie.processor.service.impl.CookieProcessorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ApplicationConfigurationTest {

    ApplicationContextRunner context = new ApplicationContextRunner()
            .withUserConfiguration(ApplicationConfiguration.class);

    @Test
    public void testPresenceOfCookieProcessorServiceImpl() {
        context.run(it -> {
            assertThat(it).hasSingleBean(CookieProcessorServiceImpl.class);
        });
    }

    @Test
    public void testPresenceOfCommandLineRunner() {
        context.run(it -> {
            assertThat(it).hasSingleBean(CommandLineRunner.class);
        });
    }

    @Test
    public void testPresenceOfCommandService() {
        context.run(it -> {
            assertThat(it).hasSingleBean(CommandServiceImpl.class);
        });
    }

    @Test
    public void testPresenceOfCookieExecutor() {
        context.run(it -> {
            assertThat(it).hasSingleBean(CookieExecutor.class);
        });
    }
}

