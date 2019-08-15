FROM openjdk:8
ADD target/app-module.jar app-module.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-module.jar"]