package jca.poc.restTimeService.service;

import jca.poc.serviceCommons.service.IService;
import org.springframework.stereotype.Component;

/**
 * Created by jcarravilla on 6/11/17.
 */
@Component
public class TimeServiceImpl implements IService<String, Long> {
    @Override
    public String executeService(Long param) throws Exception {
        if (param.compareTo(1000000L) > 0){
            throw new Exception("For test purposes, you can't add more of 1.000.000 milliseconds");
        }

        return new Long(System.currentTimeMillis() + param).toString();
    }
}
