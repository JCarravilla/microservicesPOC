package jca.poc.restAccessService.service;

import jca.poc.serviceCommons.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jcarravilla on 6/11/17.
 */
@Component
public class RestAccessServiceImpl implements IService<String, Long> {

    @Value("${timeEndpoint.host}")
    String timeEndpointHost;

    @Value("${timeEndpoint.port}")
    int timeEndpointPort;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public String executeService(Long param) throws Exception {
        return restTemplate.
                getForObject(String.format("http://%s:%d/time?millis={millis}", timeEndpointHost, timeEndpointPort), String.class, param);
    }
}
