FROM openjdk:8
ADD target/auth-module.jar auth-module.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "auth-module.jar"]