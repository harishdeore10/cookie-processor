package com.quantcast.cookie.processor.cookie.processor.Constants;

/**
 * This class is used to represent error messages and map them to separate
 * error code based of functionality.
 */
public enum ErrorCode {

    COMMAND_ERROR(0, "An Input command error has occurred."),
    FILE_ERROR(1, "A file related error has occurred.");

    private final int code;
    private final String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Error code : " + code + " : " + description;
    }
}
