FROM openjdk:8-alpine

WORKDIR /opt/workspace

ENV APPLICATION_PORT ${PORT:-8091}

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=secure"]