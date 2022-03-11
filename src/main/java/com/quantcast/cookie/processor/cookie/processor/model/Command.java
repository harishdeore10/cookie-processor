package com.quantcast.cookie.processor.cookie.processor.model;

import java.time.LocalDate;

public class Command {

    private String file;
    private LocalDate date;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
