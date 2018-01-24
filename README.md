# Microservice-POC

Microservice POC coded with Java 8 and Spring boot. Its main goals are test the following microservice capabilities:

  1. CorrelationId
  2. Add service metrics
  3. Management endpoint security
  4. Transitive health status
  5. Maven and docker integration

# Sumary

  There are two microservices. One of then will be our system access point and will call the second one.

# Modules

  - **restAccessService:** It is the system entry point. It is an ApiRest that exposes and endpoint "/time". This endpoint
  accepts a long as "addmillis" parameter. It returns the current timestamp plus / minus the number of millis provided.

  - **restTimeService:** It is a rest service that gets a long, adds it to the current time millis and returns the result.

  - **serviceCommons**

# 1. CorrelationId

  In order to have a correlationID we've choose Spring-cloud-sleuth. We only need:
   - Add it as a dependency in both projects
   - Set the spring.application.name property in both application.yml
   - The communication between both services must be through any system that propagates the correlationId. We've choose
    RestTemplate. Just add a @Bean as did in **RestAccessServiceImpl**

  **restAccessService log example:**
  2017-11-13 15:16:52.755  INFO [rest_access_service,**ea75420f4a3cc8cc**,ea75420f4a3cc8cc,false] 24161 --- [nio-8090-exec-1] PocMicroService: Request accepted

  **restTimeService log example:**
  2017-11-13 15:17:09.928  INFO [rest_time_service,**ea75420f4a3cc8cc**,4dc07b5f2897087e,false] 24115 --- [nio-8091-exec-1] PocMicroService: Request accepted

# 2. Metrics

  To archive metric capabilities we've choose Spring-cloud-actuator and Dropwizard Metrics. This way we will have both
  metric information as health checks as a bunch useful service endpoints.
   - Add it as a dependency in both projects
   - Add also metrics-spring as dependency to improve the amount of metrics (https://github.com/ryantenney/metrics-spring & https://instil.co/2017/03/31/spring-boot-with-dropwizard-metrics/)
   - Set @EnableMetrics in a @Configuration class as well as @Timed(name="Access_to_time_service") in the methods to
   measure to have all the information of a method
   - Ensure the spring security is configured in management endpoints to be able to access it (see *3. Management endpoint security*)

  Taking advantage of the dropwizard capabilities, I've add two new metrics that counts the number of http 200 and http 500.
  This code is in RestStatusMetric.java

  We can test it by calling to the endpoint http://localhost:9999/metrics with the user and password configured in SERVICE_MANAGEMENT_USER

  Furthermore we've can plug  with **prometheus** through the endpoint /prometheus. We've archive this with the io.prometheus
  dependencies as we can read in https://reflectoring.io/monitoring-spring-boot-with-prometheus/

# 4. Transitive health status

  To be able to control if a Service who is used by our main service is healthy, I've build a ChildServiceHealtIndicator
  that is scanned in the App class as @ComponentScan(basePackages = {"jca.poc"}).

  We just need to inform at application.yml which services we need to control in childHealthServices.

# 5. Maven and docker integration

  In order to build, tag and push the docker images to a remote repository I use the Spotify's dockerfile dependency.
  This way we can build the docker image as a goal of maven package phase and tag it in maven deploy.

  To archive this I only had to define the plugin in the parent pom and redefine its specific properties in both microservices.
  Also I skip the deploy goal (maven-deploy-plugin plugin) in both pom to avoid maven try to find a mvn repository where
  store the resultant jars.

  To deploy an image I only need to do **mvn deploy**. In this POC it will try to deploy it in localhost:5000.