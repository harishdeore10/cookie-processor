package com.quantcast.cookie.processor.cookie.processor.model;

public enum ApplicationStatus {
    SUCCESS(0),
    APPLICATION_FAILED(1);

    private final int value;

    ApplicationStatus(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
