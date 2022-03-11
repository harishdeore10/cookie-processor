package com.quantcast.cookie.processor.cookie.processor.service;

import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.model.Command;

import java.io.IOException;

public interface CookieProcessorService {
    void process(Command command) throws IOException, CookieProcessorException;
}
