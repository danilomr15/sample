FROM java:8-jdk-alpine
COPY ./target/spring-boot-kafka.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch spring-boot-kafka.jar'
ENTRYPOINT ["java","-jar","spring-boot-kafka.jar"]
