FROM java:8-jdk-alpine
COPY ./target/spring-boot-postgres.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch spring-boot-postgres.jar'
ENTRYPOINT ["java","-jar","spring-boot-postgres.jar"]
