FROM java:8-jdk-alpine
COPY ./target/spring-boot-rabbitmq.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch spring-boot-rabbitmq.jar'
ENTRYPOINT ["java","-jar","spring-boot-rabbitmq.jar"]

