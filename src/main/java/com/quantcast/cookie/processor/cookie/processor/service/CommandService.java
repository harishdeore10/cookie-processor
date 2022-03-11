package com.quantcast.cookie.processor.cookie.processor.service;

import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.model.Command;

public interface CommandService {

    Command getInputCommand(String[] args) throws CookieProcessorException;

}
