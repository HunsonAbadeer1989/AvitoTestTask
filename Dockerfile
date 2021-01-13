FROM openjdk:11
ADD target/avito-test-task.jar avito-test-task.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "avito-test-task.jar"]
