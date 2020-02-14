# Education Application

Application based on micro-service architecture.
Project Benefits:
- Java Web Application based on Spring Boot (using MVC, Data, AOP, Security);
- Uninterrupted communication between micro-services using Apache Kafka;
- Improved project security using OAuth2 from a separate service;
- Support Docker containers;
- Running with docker-compose via single command;
- Swagger documentation (public accessible);
- Actuator support (public accessible);
- Ansible playbooks.

***
### How to run application with docker:
1. Download, install and start Docker (with Docker Compose)
2. Clone this repository and run the following command inside: `mvn clean install -Pdocker`

##### Notes
  + Maven plugins will automatically created Docker images and executed `docker-compose up -d` command
  + To **stop** all application, need to execute `docker-compose down` manually
  + [Swagger UI local](http://localhost:8080/app/swagger-ui.html)
  + [Swagger Docs local](http://localhost:8080/app/v2/api-docs)
  + [Actuator local](http://localhost:8080/app/actuator)

***
### How to run application for local debugging:

#### Zookeeper and Kafka
1. Download, install and start Zookeper
2. Download, install and start Kafka
3. Create the next topics:
   - education-finish-lesson-event
   - education-start-course-event
   
#### PostgreSQL
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
- Make sure that references in application.yml set up correctly.

#### Last steps
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
   
Also, for working with users, you need to pass `/second-step-register` endpoint.

***
### TODO list:
1. Add ElasticSearch support
2. Create Ansible role to install java, docker to run docker-compose as result
3. Make a flexible solution for public endpoints (swagger/actuator WITH RoleSecurityFilter)
