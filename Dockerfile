FROM openjdk:18
WORKDIR /app
COPY ./target/tap-adminservice.jar /app
EXPOSE 5373
CMD ["java", "-jar", "tap-adminservice.jar"]
