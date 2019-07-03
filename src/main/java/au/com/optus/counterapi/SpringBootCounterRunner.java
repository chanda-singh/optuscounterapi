package au.com.optus.counterapi;

import au.com.optus.counterapi.model.CounterResponse;
import au.com.optus.counterapi.service.CounterService;
import au.com.optus.counterapi.service.CounterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;*/
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
/*
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
*/

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

@SpringBootApplication
public class SpringBootCounterRunner {

    private final static Logger LOG = LoggerFactory.getLogger(SpringBootCounterRunner.class);

    public static void main(String[] args) {
        LOG.info("springBootCounterRunner : service : started");
        SpringApplication.run(SpringBootCounterRunner.class, args);
    }

    @Bean
    public CounterResponse counterResponse() {
        return new CounterResponse();
    }

    @Bean
    public CounterService counterService() {
        return new CounterServiceImpl();
    }

}
