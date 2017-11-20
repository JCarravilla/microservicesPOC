package jca.poc.restAccessService.healthChecker;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcarravilla on 15/11/17.
 */
@Data
@Component
@ConfigurationProperties
public class ChildServiceLst {

    @Data
    public static class ChildService {
        private String name;
        private String host;
        private int port;
        private String healthEndpoint;
    }

    private List<ChildService> childHealthServices = new ArrayList<>();
}
