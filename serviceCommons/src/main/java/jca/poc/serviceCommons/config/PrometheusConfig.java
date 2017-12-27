package jca.poc.serviceCommons.config;

/**
 * Created by jcarravilla on 27/12/17.
 */

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnablePrometheusEndpoint
public class PrometheusConfig {
    private MetricRegistry dropwizardMetricRegistry;

    @Autowired
    public PrometheusConfig(MetricRegistry dropwizardMetricRegistry) {
        this.dropwizardMetricRegistry = dropwizardMetricRegistry;
    }

    @PostConstruct
    public void registerPrometheusCollectors() {
        CollectorRegistry.defaultRegistry.clear();
        new StandardExports().register();
        new MemoryPoolsExports().register();
        new DropwizardExports(dropwizardMetricRegistry).register();
    }
}