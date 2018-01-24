package jca.poc.restTimeService.rest;

import com.codahale.metrics.annotation.Timed;
import jca.poc.restTimeService.service.TimeServiceImpl;
import jca.poc.serviceCommons.metrics.RestStatusMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jcarravilla on 26/10/17.
 */

@RestController
public class RestTimeController {
    private final static Logger log = LoggerFactory.getLogger("PocMicroService");

    @Autowired
    private TimeServiceImpl timeService;
    
    @Autowired
    private RestStatusMetric restStatusMetric;

    @Timed(name = "time_method")
    @RequestMapping(value = {"/time"}, method = {RequestMethod.GET})
    public ResponseEntity<String> putTestValue(@RequestParam("millis") Long addedMillis){
        log.info("Request accepted");
        try{
            String resp = timeService.executeService(addedMillis);
            restStatusMetric.handleStatus(HttpStatus.ACCEPTED);
            return new ResponseEntity<String>(resp, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            restStatusMetric.handleStatus(HttpStatus.INTERNAL_SERVER_ERROR, e);
            return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
