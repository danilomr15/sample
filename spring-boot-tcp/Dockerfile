FROM java:8-jdk-alpine
COPY ./target/spring-boot-tcp.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch spring-boot-tcp.jar'
ENTRYPOINT ["java","-jar","spring-boot-tcp.jar"]
