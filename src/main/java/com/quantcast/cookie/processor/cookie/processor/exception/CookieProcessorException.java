package com.quantcast.cookie.processor.cookie.processor.exception;

/**
 * Custom exception specific to Cookie Processor.
 *
 */
public class CookieProcessorException extends Exception {

    private static final long serialVersionUID = 1L;

    public CookieProcessorException(String errorMessage) {
        super(errorMessage);
    }

}
