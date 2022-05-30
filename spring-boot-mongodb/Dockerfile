FROM java:8-jdk-alpine
COPY ./target/spring-boot-mongodb.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch spring-boot-mongodb.jar'
ENTRYPOINT ["java","-jar","spring-boot-mongodb.jar"]
