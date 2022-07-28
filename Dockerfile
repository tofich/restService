FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

ARG JAR_FILE=target/testApp-0.0.1-SNAPSHOT.jar
ARG CONF_FILE=application.yml

COPY ${JAR_FILE} app.jar

COPY ${CONF_FILE} ${CONF_FILE}

ENTRYPOINT ["java","-jar","app.jar"]