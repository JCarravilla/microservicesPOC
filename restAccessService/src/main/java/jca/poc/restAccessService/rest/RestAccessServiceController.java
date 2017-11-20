package jca.poc.restAccessService.rest;

import com.codahale.metrics.annotation.Timed;
import jca.poc.restAccessService.service.RestAccessServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jcarravilla on 26/10/17.
 */

@RestController
public class RestAccessServiceController {
    private final static Logger log = LoggerFactory.getLogger("PocMicroService");

    @Autowired
    private RestAccessServiceImpl restAccessService;

    @Timed(name="time_service")
    @RequestMapping(value = {"/time"}, method = {RequestMethod.GET})
    public ResponseEntity<String> putTestValue(@RequestParam("addmillis") Long addmillis){
        log.info("Request accepted");
        log.debug(String.format("Current time millis: %d", System.currentTimeMillis()));

        try {
            String result = restAccessService.executeService(addmillis);
            log.debug(String.format("Result after next service call: %s", result));
            return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
