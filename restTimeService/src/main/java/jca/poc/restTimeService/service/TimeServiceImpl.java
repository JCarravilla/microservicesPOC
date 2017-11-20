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
        return new Long(System.currentTimeMillis() + param).toString();
    }
}
