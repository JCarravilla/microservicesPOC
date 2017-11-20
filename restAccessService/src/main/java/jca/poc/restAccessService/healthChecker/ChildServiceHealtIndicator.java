package jca.poc.restAccessService.healthChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jcarravilla on 14/11/17.
 */

@Component
public class ChildServiceHealtIndicator extends AbstractHealthIndicator {

    @Autowired
    private ChildServiceLst childServiceLst;

    @Override
    public void doHealthCheck(Health.Builder bldr){
        String errorInfo = "";

        for (ChildServiceLst.ChildService chServ : childServiceLst.getChildHealthServices()) {
            int errorCode = checkClidService(chServ);
            if (errorCode != 200) {
                errorInfo = errorInfo.concat(String.format("[Service %s -> errorCode %d]", chServ.getName(), errorCode));
            }
        }

        if(errorInfo.isEmpty()){
            bldr.up();
        }else {
            bldr.down().withDetail("info", errorInfo).build();
        }


    }

    private int checkClidService(ChildServiceLst.ChildService chServ) {
        return restTemplate.getForEntity(getUrl(chServ), String.class).getStatusCodeValue();
    }

    private String getUrl(ChildServiceLst.ChildService chServ){
        return String.format("http://%s:%d/%s", chServ.getHost(), chServ.getPort(), chServ.getHealthEndpoint());
    }

    @Autowired
    private RestTemplate restTemplate;

}
