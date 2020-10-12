# Education Application

Application based on micro-service architecture.
Project Benefits:
- Java Web Application based on Spring Boot (using MVC, Data, AOP, Security);
- Systematized storage and access to teaching materials of academic disciplines. Under the materials should be considered concepts
  "course". Each course is divide into "lectures";
- Improved search engine by difference criteria;
- Caching engine for faster course search;
- AOP functionality to send email notification;
- Interactive comments creation by users to each lecture;
- Uninterrupted communication between micro-services using Apache Kafka;
- Improved project security using OAuth2 from a separate service;
- Support Docker containers;
- Swagger documentation;
- Access for the client to actuator information;
- Local Deploying with the Docker technology via single MAVEN command;
- Good coverage with integration tests and running them in single MAVEN command;
- Ansible's playbooks to build and deploy application;
- Jenkins' job for build and push docker image to DockerHub;
- Jenkins' job for deploy application to AWS (Support RedHat and Debian OS).

***
### How to run application locally via the Docker:
1. Download and install Docker (with Docker Compose)
2. Clone this repository and run : `mvn clean install -Pdocker`

##### Notes
  + Maven plugins will automatically created Docker images and executed `docker-compose up -d` command;
  + To **stop** all application, need to execute `docker-compose down` manually.
  + To run all integration tests need to execute `mvn verify -PIT` manually.
  + To recreate only APP container, use following steps:
    + Stop and remove existing container and image;
    + Run `cd app && mvn clean install` and `docker build -f Dockerfile -t education_app:latest.20.9-SNAPSHOT .`
    + Run `docker-compose up -d --build`
  
***
### How to run application remotely via the Jenkins:
1. Download and install Docker (with Docker Compose)
2. Download **jenkins/start_jenkins** and run  `docker-compose up --build -d`
3. Open [this tutorial](jenkins/start_jenkins/readme.md)

***
### How to run application locally with detailed debugging:

<details><summary><b>Zookeeper and Kafka</b></summary>

1. Download, install and start Zookeeper
2. Download, install and start Kafka
3. Create the next topics:
   - education-finish-lesson-event
   - education-start-course-event

</details>

<details><summary><b>PostgreSQL</b></summary>

1. Download, install, start Postgres 10+
2. Create the next databases and roles:
```sql
CREATE USER "flyway" WITH PASSWORD 'flyway';
CREATE USER "education-app" WITH PASSWORD 'education-app';
CREATE USER "education-auth" WITH PASSWORD 'education-auth';

CREATE DATABASE education_app WITH OWNER = "education-app";
CREATE DATABASE education_auth WITH OWNER = "education-auth";

ALTER USER "flyway" WITH SUPERUSER;
ALTER USER "education-app" WITH SUPERUSER;
ALTER USER "education-auth" WITH SUPERUSER;
```
- Make sure references in application.yml set up correctly.

</details>

<details><summary><b>Last steps</b></summary>

1. Go to `education` folder and run `mvn clean install`
2. Start **AuthApplication** firstly, and the second one - **EducationApplication**
3. Try accessing to AuthApplication for generating token with _client_credentials_ or _password_ as grant type:
```
POST    - http://localhost:8081/auth/oauth/token?grant_type=client_credentials
Headers - Authorization: Basic ZWR1Y2F0aW9uLXdlYi1jbGllbnQ6ZWR1Y2F0aW9uLXdlYi1jbGllbnQtc2VjcmV0
```
```
GET     - http://localhost:8081/auth/oauth/token?grant_type=password&username=john&password=john
Headers - Authorization: Basic ZWR1Y2F0aW9uLXdlYi1jbGllbnQ6ZWR1Y2F0aW9uLXdlYi1jbGllbnQtc2VjcmV0
```
4. Once the token is obtained you can access to the EducationApplication (resource server) using:
```
CRUD    - http://localhost:8080/app/course
Headers - Authorization: Bearer *your_token*
```

</details>

***
<details><summary><b>Technical Note</b></summary>

When OAuth2 server rises for the first time, a default client and users will be created:
1. Client:
   - clientId: _education-web-client_
   - clientSecret: _education-web-client-secret_
   - role: _ROLE_CLIENT_
   - scope: standard-scope
2. First User:
   - username: _john_
   - password: _john_
   - role: _ROLE_USER_
   - scope: standard-scope user-scope
3. Second User:
   - username: _admin_
   - password: _admin_
   - role: _ROLE_ADMIN_
   - scope: standard-scope user-scope
   
* To start use the main functionality, need to complete the User registration: execute a POST request for `http://localhost:8080/app/system/second-step-register`
* The Client has minimum available functionality.
* Currently, there is no possibility to create new users or clients.

</details>

***
##### Helpful links
  + [Swagger APP UI local](http://localhost:8080/app/swagger-ui.html)
  + [Swagger APP Docs local](http://localhost:8080/app/v2/api-docs)
  + [Swagger AUTH UI local](http://localhost:8081/auth/swagger-ui.html)
  + [Swagger AUTH Docs local](http://localhost:8081/auth/v2/api-docs)
  + [Actuator local](http://localhost:8080/app/actuator)
  + You can use `ModHeader` extension in Google Chrome to add an Authorization header to open requests above.

***
### TODO list:
1. Implement a Jenkins integration with the following pipelines:
   - 1.1. Run all integration tests;
2. Add monitoring functionality for teacher
3. Add feedback form for student after each lection
4. Migrate to Gradle
