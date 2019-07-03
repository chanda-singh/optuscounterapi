package au.com.optus.counterapi.service;

import au.com.optus.counterapi.exception.CounterServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

@Service
public interface CounterService {

    /**
     * Method to fetch top word count
     *
     * @param descSortedMap
     * @param number
     * @return
     */
    String returnTopCountList(Map<String, Integer> descSortedMap, Integer number) throws CounterServiceException;

    /**
     * Method to map word and count
     *
     * @return
     */
    Map getTextCountMap() throws CounterServiceException;

    /**
     * Method to return requested response based on input search data
     * @param inputWordList
     * @return
     * @throws CounterServiceException
     */
    List<Map<String, Integer>> getSearchDataList(Map<String, Integer> textCountMap, List<String> inputWordList) throws CounterServiceException;

}
