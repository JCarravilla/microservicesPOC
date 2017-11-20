# Microservice-POC

Microservice POC coded with Java 8 and Spring boot. Its main goals are test the following microservice capabilities:

  1. CorrelationId
  2. Add service metrics
  3. Management endpoint security
  4. Transitive health status

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

  To archive metric capabilities we've choose Spring-cloud-actuator and Dropwizard Metrics. This way we will have both metric information as health
  checks as a bunch useful service endpoints.
   - Add it as a dependency in both projects
   - Add also metrics-spring as dependency to improve the amount of metrics (https://github.com/ryantenney/metrics-spring & https://instil.co/2017/03/31/spring-boot-with-dropwizard-metrics/)
   - Set @EnableMetrics in a @Configuration class as well as @Timed(name="Access_to_time_service") in the methods to measure to have all the information of a method
   - Ensure the spring security is configured in management endpoints to be able to access it (see *3. Management endpoint security*)

  We can test it by calling to the endpoint http://localhost:9999/metrics with the user and password configured in SERVICE_MANAGEMENT_USER