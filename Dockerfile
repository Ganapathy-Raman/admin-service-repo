FROM openjdk:18
WORKDIR /app
COPY ./target/tap-api.jar /app
EXPOSE 5373
CMD ["java", "-jar", "tap-api.jar"]
