FROM openjdk:8
ADD target/auth-module.jar /opt/auth-module.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/auth-module.jar"]