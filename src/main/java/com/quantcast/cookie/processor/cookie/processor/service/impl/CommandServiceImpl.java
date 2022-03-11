package com.quantcast.cookie.processor.cookie.processor.service.impl;

import com.quantcast.cookie.processor.cookie.processor.Constants.ErrorCode;
import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.model.Command;
import com.quantcast.cookie.processor.cookie.processor.service.CommandService;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.quantcast.cookie.processor.cookie.processor.Constants.Constant.*;
import static java.time.LocalDate.parse;

/**
 * This service is used to process input command.
 */
@Service
public class CommandServiceImpl implements CommandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandServiceImpl.class);

    /**
     * Process input command.
     * @param args
     * @return Command
     * @throws CookieProcessorException
     */
    @Override
    public Command getInputCommand(String[] args) throws CookieProcessorException {

        LOGGER.info("Getting command line inputs");

        Command command = new Command();
        CommandLineParser commandLineParser = new DefaultParser();

        try {
            Options options = getCommandOption();
            CommandLine commandLine = commandLineParser.parse(options, args);
            command.setFile(commandLine.getOptionValue(FILE));
            command.setDate(parse(commandLine.getOptionValue(DATE)));
            return command;
        } catch (Exception exception) {
            throw new CookieProcessorException(ErrorCode.COMMAND_ERROR
                    + " : " + exception.getMessage());
        }
    }

    /**
     * Get command options i.e. File and Date
     * @return Options
     */
    public Options getCommandOption() {
        Options commandOptions = new Options();

        Option file = new Option(OPTION_F, FILE, Boolean.TRUE, "The file path");
        file.setRequired(Boolean.TRUE);
        commandOptions.addOption(file);

        Option date = new Option(OPTION_D, DATE, Boolean.TRUE, "The date");
        date.setRequired(Boolean.TRUE);
        commandOptions.addOption(date);

        return commandOptions;
    }

}
