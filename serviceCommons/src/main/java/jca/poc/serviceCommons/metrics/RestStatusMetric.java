package jca.poc.serviceCommons.metrics;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by jcarravilla on 24/01/18.
 */
@Component
public class RestStatusMetric {
    private final static Logger log = LoggerFactory.getLogger("PocMicroService");

    private MetricRegistry metricRegistry;

    @Autowired
    public RestStatusMetric(MetricRegistry metricRegistry){
        this.metricRegistry = metricRegistry;
    }

    public void handleStatus(HttpStatus status){
        countHttpStatus(status);
    }

    public void handleStatus(HttpStatus status, Exception ex) {
        handleStatus(status);
        log.error(String.format("Returned HTTP Status %d due to the following exception:", status.value()), ex);
    }

    private void countHttpStatus(HttpStatus status){
        Meter meter = metricRegistry.meter(String.format("http.status.%d", status.value()));
        meter.mark();
    }
}
