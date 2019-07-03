package au.com.optus.counterapi.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */
@RunWith(MockitoJUnitRunner.class)
public class CounterServiceImplTest {

    @InjectMocks
    private CounterServiceImpl counterService;

    Map<String, Integer> dataMap;
    List<String> inputWordList;

    @After
    public void destroy(){
        counterService = null;
        dataMap = null;
        inputWordList = null;
    }

    @Before
    public void setup(){
        counterService =  new CounterServiceImpl();
        //Arrange dataMap
        dataMap = new HashMap<String, Integer>();
        dataMap.put("eget", 17);
        dataMap.put("in", 15);
        dataMap.put("et", 14);
        dataMap.put("ut", 13);
        dataMap.put("metus", 12);

        //Arrange inputWordList
        inputWordList = new ArrayList<String>();
        inputWordList.add("vel");
        inputWordList.add("eget");
        inputWordList.add("sed");
        inputWordList.add("in");
        inputWordList.add("123");
    }

    @Test
    public void testReturnTopCountList_whenSearchDataInMap_shouldReturnTrue() throws Exception{
        //Act
        Map<String, Integer> wordCountMap = counterService.getTextCountMap();

        //Assert
        assertNotNull(wordCountMap);
        assertTrue(wordCountMap.containsKey("metus"));
    }

    @Test
    public void testReturnTopCountList_whenSearchDataNotInMap_shouldReturnFalse() throws Exception{
        //Act
        Map<String, Integer> wordCountMap = counterService.getTextCountMap();

        //Assert
        //assertNotNull(wordCountMap);
        assertFalse(wordCountMap.containsKey("chandan"));
    }

    @Test
    public void testReturnTopCountList_whenTopMostRequested_shouldReturnTopMostWordCount() throws Exception {
        //Act
        String topCountList = counterService.returnTopCountList(dataMap, 1);

        //Assert
        assertNotNull(topCountList);
        assertTrue("in|15".equalsIgnoreCase(topCountList.trim()));
    }

    @Test
    public void testGetSearchDataList_whenInputWordList_shouldReturnWordWithCount() throws Exception {

        //Act
        Map<String, Integer> wordCountMap = counterService.getTextCountMap();
        List<Map<String, Integer>> responseDataList = counterService.getSearchDataList(wordCountMap,inputWordList);

        //Assert
        assertNotNull(responseDataList);
        assertTrue(responseDataList.get(4).get("123").equals(0));
        assertTrue(responseDataList.get(0).get("vel").equals(17));
    }
}
