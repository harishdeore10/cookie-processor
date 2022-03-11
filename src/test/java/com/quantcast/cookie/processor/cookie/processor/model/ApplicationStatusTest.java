package com.quantcast.cookie.processor.cookie.processor.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ApplicationStatusTest {

    @Test
    public void testSuccess() {
        assertEquals(0, ApplicationStatus.SUCCESS.getValue());
    }

    @Test
    public void testFailedScenario() {
        assertEquals(1, ApplicationStatus.APPLICATION_FAILED.getValue());
    }
}
