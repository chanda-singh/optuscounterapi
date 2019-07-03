package au.com.optus.counterapi.controller;

import au.com.optus.counterapi.constant.CounterConstant;
import au.com.optus.counterapi.exception.CounterServiceException;
import au.com.optus.counterapi.model.CounterData;
import au.com.optus.counterapi.model.CounterResponse;
import au.com.optus.counterapi.service.CounterService;
import au.com.optus.counterapi.util.CounterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

@RestController
@RequestMapping(value = "/counter-api")
public class CounterController {

    private static final Logger LOG = LoggerFactory.getLogger(CounterController.class);

    @Autowired
    CounterResponse counterResponse;

    @Autowired
    CounterService counterService;

    /**
     * End point to search the data count from a text file.
     *
     * @param counterData
     * @throws IOException
     */
    @PreAuthorize("hasRole('ROLE')")
    @PostMapping("/search")
    public CounterResponse search(@RequestBody CounterData counterData) {
        LOG.info("counterController::getDataCount::start");
        List<Map<String, Integer>> responseDataList = new ArrayList<Map<String, Integer>>();
        try {
            if (!CollectionUtils.isEmpty(counterData.getCounterDataList())) {
                LOG.info("counterController::getDataCount::searchDataListNotEmpty");

                Map<String, Integer> textCountMap = counterService.getTextCountMap();
                List<String> inputWordList = counterData.getCounterDataList();
                responseDataList = counterService.getSearchDataList(textCountMap, inputWordList);
            }
            counterResponse.setCounts(responseDataList);
        } catch (CounterServiceException e) {
            LOG.error("CounterServiceException occured ", e);
        }
        LOG.info("counterController::getDataCount::end");
        return counterResponse;
    }

    /**
     * End point to fetch top listed text, which has the highest count
     *
     * @param number
     * @param response
     */
    @PreAuthorize("hasRole('ROLE')")
    @GetMapping("/top/{number}")
    public void getTopWordCount(@PathVariable("number") int number, HttpServletResponse response) {
        try {
            writeDataToResponse(number, response);
        } catch (CounterServiceException e) {
            LOG.error("CounterServiceException occurred", e);
        }
    }

    /**
     * Method to write the content in response
     *
     * @param number
     * @param response
     */
    private void writeDataToResponse(int number, HttpServletResponse response) throws CounterServiceException {
        LOG.info("counterController:writeDataToResponse:start");
        try {
            response.setContentType(CounterConstant.HTTP_RESPONSE_CONTENT_TYPE);

            if (number > 0) {
                Map<String, Integer> descSortedMap = CounterUtil.sortByCount(counterService.getTextCountMap());
                response.getWriter().write(counterService.returnTopCountList(descSortedMap, number));
            } else {
                response.getWriter().write(CounterConstant.COUNTER_ERROR_MSG);
                LOG.debug("Invalid input");
            }
            response.getWriter().flush();
        } catch (IOException io) {
            LOG.error("IOException occured in IOException", io);
            throw new CounterServiceException(io.getMessage(), io);
        }
    }

}