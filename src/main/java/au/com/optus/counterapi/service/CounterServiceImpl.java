package au.com.optus.counterapi.service;

import au.com.optus.counterapi.constant.CounterConstant;
import au.com.optus.counterapi.exception.CounterServiceException;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

public class CounterServiceImpl implements CounterService {

    private static final Logger LOG = LoggerFactory.getLogger(CounterServiceImpl.class);


    private static volatile Map<String, Integer> wordCountMap = null;
    private static File file;

    /**
     * Method to fetch top word count
     *
     * @param descSortedMap
     * @param number
     * @return
     */
    @Override
    public String returnTopCountList(Map<String, Integer> descSortedMap, Integer number) throws CounterServiceException {
        LOG.info("counterServiceImpl:returnTopCountList:start");

        Integer count = 0;
        StringBuilder strBuilder = new StringBuilder();
        Iterator entries = descSortedMap.entrySet().iterator();

        while (entries.hasNext() && count < number) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) entries.next();
            strBuilder.append(entry.getKey());
            strBuilder.append("|");
            strBuilder.append(entry.getValue());
            strBuilder.append("\n");
            count++;
        }
        LOG.info("counterServiceImpl:returnTopCountList:end");
        return strBuilder.toString();
    }

    /**
     * Method to map word and count
     *
     * @return
     * @throws CounterServiceException
     */
    @Override
    public Map getTextCountMap() throws CounterServiceException {
        generateWordMap();
        return wordCountMap;
    }

    /**
     * Method to return requested response based on input search data
     *
     * @param inputWordList
     * @return
     * @throws CounterServiceException
     */
    @Override
    public List<Map<String, Integer>> getSearchDataList(Map<String, Integer> textCountMap, List<String> inputWordList) throws CounterServiceException {
        LOG.info("counterServiceImpl:getSearchDataList:start");
        List<Map<String, Integer>> dataList = new ArrayList<>();

        //Below code will iterate through user "search input" data list and will retrieve count of "words"
        // for each input words from map and put data in map.
        inputWordList.forEach(text -> {
            Map<String, Integer> mappedData = new HashMap<String, Integer>();
            Integer count = textCountMap.containsKey(text.toLowerCase()) ?
                    textCountMap.get(text.toLowerCase()) : CounterConstant.NULL_COUNT;
            mappedData.put(text, count);
            dataList.add(mappedData);
        });

        LOG.info("counterServiceImpl:getSearchDataList:end");
        return dataList;
    }

    /**
     * Make sure function is not loaded in memory without any call, this will load once it is called 1st time.
     *
     * @throws CounterServiceException
     */
    private static void generateWordMap() throws CounterServiceException {
        LOG.info("counterServiceImpl:loading static block");
        if (wordCountMap == null) {
            LOG.info("counterServiceImpl:wordCountMap is not loaded previously");
            wordCountMap = new HashMap<String, Integer>();
                /*ClassLoader classLoader = new CounterServiceImpl().getClass().getClassLoader();
                file = new File(classLoader.getResource(CounterConstant.FILE_NAME).getFile());
                fetchWordCountMap();*/
            Class clazz = Matchers.class;
            List<String> listOfWords = null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream("/data.txt")))) {
                String currentLine = br.readLine();
                while (currentLine != null) {
                    //replace all the comms and special dots from words and generate the list of all the words.
                    currentLine = currentLine.replaceAll("[\\.$|,|;]", "");
                    listOfWords = Arrays.asList(currentLine.split(" "));

                    for (String word : listOfWords) {
                        word = word.toLowerCase();
                        if (wordCountMap.containsKey(word)) {
                            wordCountMap.put(word, wordCountMap.get(word) + 1);
                        } else {
                            wordCountMap.put(word, 1);
                        }
                    }
                    currentLine = br.readLine();
                }
            } catch (IOException io) {
                LOG.error("IOException occured in generateWordMap ", io);
                throw new CounterServiceException(io.getMessage(), io);
            }
        }
    }

}
