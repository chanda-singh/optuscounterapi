package au.com.optus.counterapi.util;

import au.com.optus.counterapi.exception.CounterServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

public class CounterUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CounterUtil.class);

    /**
     * This method is to sort the data based on map value (count of the word)
     *
     * @param countMap
     * @return
     */
    public static Map<String, Integer> sortByCount(Map<String, Integer> countMap) {
        LOG.info("counterUtil::sortByCount:start");

        //Below logic will sort the map based on "max count" value and will store sorted map in a LinkedHashMap
        Map<String, Integer> sortedMap = countMap.entrySet().stream().
                sorted(Collections.reverseOrder(comparingByValue())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        LOG.info("counterUtil::sortByCount:end");
        return sortedMap;
    }

    /**
     *
     * @return
     * @throws CounterServiceException
     */
    public static Properties fetchProperties() throws CounterServiceException {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            File file = ResourceUtils.getFile("classpath:data.txt");
            in = new FileInputStream(file);
            properties.load(in);

        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new CounterServiceException(e.getMessage(),e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LOG.error(e.getMessage());
                throw new CounterServiceException(e.getMessage(),e);
            }
        }

        return properties;
    }

}
