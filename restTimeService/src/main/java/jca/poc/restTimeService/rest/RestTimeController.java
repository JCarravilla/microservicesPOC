package jca.poc.restTimeService.rest;

import com.codahale.metrics.annotation.Timed;
import jca.poc.restTimeService.service.TimeServiceImpl;
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

    @Timed(name = "time_method")
    @RequestMapping(value = {"/time"}, method = {RequestMethod.GET})
    public ResponseEntity<String> putTestValue(@RequestParam("millis") Long addedMillis){
        log.info("Request accepted");
        try{
            return new ResponseEntity<String>(timeService.executeService(addedMillis), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
