FROM java:8-jdk-alpine
COPY ./target/spring-boot-redis.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch spring-boot-redis.jar'
ENTRYPOINT ["java","-jar","spring-boot-redis.jar"]
