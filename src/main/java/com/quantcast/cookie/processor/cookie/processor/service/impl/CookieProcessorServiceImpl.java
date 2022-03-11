package com.quantcast.cookie.processor.cookie.processor.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.quantcast.cookie.processor.cookie.processor.Constants.ErrorCode;
import com.quantcast.cookie.processor.cookie.processor.exception.CookieProcessorException;
import com.quantcast.cookie.processor.cookie.processor.model.Command;
import com.quantcast.cookie.processor.cookie.processor.model.Cookie;
import com.quantcast.cookie.processor.cookie.processor.service.CookieProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for processing of cookies and return most
 * active cookie.
 */
@Service
public class CookieProcessorServiceImpl implements CookieProcessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieProcessorServiceImpl.class);

    /**
     * This method will process the cookie file.
     *
     * @param command
     * @throws CookieProcessorException
     */
    @Override
    public void process(Command command) throws CookieProcessorException {
        LOGGER.info("Cookie processing started");

        try (FileReader fileReader = new FileReader(command.getFile())) {
            // Get list of Cookie objects from CSV file
            List<Cookie> cookies = new CsvToBeanBuilder(fileReader)
                    .withType(Cookie.class)
                    .withSkipLines(1)
                    .build()
                    .parse();

            //Sort the list based on cookie value
            Collections.sort(cookies);

            Map<String, Long> result = new HashMap<>();
            Long maxCount = 1l;

            //Orthodox way to process cookies and find out most active in one loop.
            for (int i = 0; i < cookies.size(); i++) {

                if (cookies.get(i).getTimestamp().toLocalDate().equals(command.getDate())) {

                    if ((cookies.get(i).getCookie().equals(cookies.get(i + 1).getCookie()))
                            && cookies.get(i).getTimestamp().toLocalDate().equals(cookies.get(i + 1).getTimestamp().toLocalDate())) {
                        maxCount++;
                        continue;
                    } else {
                        findMostActiveCookies(result, cookies.get(i), maxCount);
                        maxCount = 1l;
                    }
                }

            }

            //Assumption : Most active cookie should be present at least twice in a file.
            //print most active cookies from resulted hashmap
            result.entrySet().stream()
                    .filter(x -> x.getValue() > (1))
                    .map(Map.Entry::getKey).forEach(System.out::println);


           /*****  JAVA 8 Streams way to find out most active cookie. ******
           // Get cookies for given date
            Map<String, Long> cookiesByDate = cookies.stream()
                    .filter(cookie -> command.getDate().isEqual(cookie.getTimestamp().toLocalDate()))
                    .collect(groupingBy(Cookie::getCookie, counting()));


            // Calculate the count of most active cookies
            OptionalLong maxCount = cookiesByDate.values().stream().mapToLong(count -> count).max();

            // find the max count & print it to console
            maxCount.ifPresent(count -> printMostActiveCookies(cookiesByDate, count));

            //Assumption : Most active cookie should be present at least twice in a file.
            if (maxCount.isPresent()) {
                cookiesByDate.entrySet().stream()
                        .filter(x -> x.getValue().equals(maxCount))
                        .map(Map.Entry::getKey)
                        .forEach(System.out::println);
            }
            */

        } catch (Exception exception) {
            throw new CookieProcessorException(ErrorCode.FILE_ERROR
                    + " : " + exception.getMessage());
        }
    }

    /**
     * Find the most active cookies
     * @param result
     * @param cookie
     * @param maxCount
     */
    private void findMostActiveCookies(Map<String, Long> result, Cookie cookie, long maxCount) {

        //First entry in result hashmap
        if (maxCount > 1 && result.isEmpty()) {
            result.put(cookie.getCookie(), maxCount);
        }
        //if initial entry is present then update result based on maximum count for the cookie
        else if (!result.isEmpty()) {
            Map.Entry<String, Long> entry = result.entrySet().iterator().next();

            //Clear result and update with cookie having maximum count
            if (maxCount > entry.getValue()) {
                result.clear();
                result.put(cookie.getCookie(), maxCount);
            } else if (maxCount == entry.getValue()) {
                //Update result with more than one cookies qualifying the most active criteria
                result.put(cookie.getCookie(), maxCount);
            }
        }
    }

}
