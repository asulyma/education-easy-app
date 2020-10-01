# Education Application

Application based on micro-service architecture.
Project Benefits:
- Java Web Application based on Spring Boot (using MVC, Data, AOP, Security);
- Systematized storage and access to teaching materials of academic disciplines. Under the materials should be considered concepts
  "course". Each course, in turn, is divided into "lectures";
- Improved search engine for various criteria;
- Caching engine for faster course search;
- AOP functionality to send email notification;
- Interactive creation of comments by users to each lecture;
- Uninterrupted communication between micro-services using Apache Kafka;
- Improved project security using OAuth2 from a separate service;
- Local Deploying via single Docker command;
- Support Docker containers;
- Swagger documentation;
- Actuator support;
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
5. Once the token is obtained you can access to the EducationApplication (resource server) using:
```
CRUD    - http://localhost:8080/app/course
Headers - Authorization: Bearer *your_token*
```

</details>

***
#### Technical Note:
When OAuth2 server rises for the first time, a default client and users will be created  
1. Client:
   - clientId: _education-web-client_
   - clientSecret: _education-web-client-secret_
   - role: [_ROLE_USER_, _ROLE_CLIENT_]
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
   
* After the first start of the application, in order to be able to use the main functionality of the application, you need to complete the user registration. To do this, you need to execute a POST request for `http://localhost:8080/app/system/second-step-register`
   
* Currently, there is no possibility to create new users or clients.

***
##### Helpful links
  + [Swagger UI local](http://localhost:8080/app/swagger-ui.html)
  + [Swagger Docs local](http://localhost:8080/app/v2/api-docs)
  + [Actuator local](http://localhost:8080/app/actuator)

***
### TODO list:
1. Add ElasticSearch support
2. Implement a Jenkins integration with the following pipelines:
   - 2.1. ~~Build and push image pipeline~~
   - 2.2. ~~Deploy to AWS pipeline~~
   - 2.3. Remove all remote images except the last.
3. Update Jenkins readme file for deploy pipeline
4. Add monitoring functionality for teacher
5. Add feedback form for student after each lection
6. Check comments functionality
7. Check Kafka functionality
