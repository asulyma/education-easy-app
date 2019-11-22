FROM openjdk:8
ADD target/app-module.jar /opt/app-module.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app-module.jar"]