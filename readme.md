# Education Application

Application based on micro-service architecture.
Project Benefits:
- uninterrupted communication between micro-services using Apache Kafka;
- improved project security using OAuth2 from a separate service;
- Support Docker containers;
- Running with docker-compose via `mvn clean install`
- Swagger documentation (public accessible)
- Actuator support (public accessible)

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
2. Set up the local profile for Spring Boot applications 
3. Start **AuthApplication** firstly, and the second one - **EducationApplication**
4. Try accessing to AuthApplication for generating token with _client_credentials_ or _password_ as grant type:
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

#### Technical Note:
When OAuth2 server rises for the first time, a default client and users will be created  
   - clientId: _education-web-client_, clientSecret: _education-web-client-secret_
   - username: _john_, password: _john_, role: _ROLE_USER_
   - username: _admin_, password: _admin_, role: _ROLE_ADMIN_

### How to run application with docker:
1. Download, install and start Docker (with Docker Compose)
2. Go to `education` folder and run `mvn clean install`
  - Maven plugins will automatically created Docker images and executed `docker-compose up -d` command
  - To **stop** all application, need to execute `docker-compose down`

### TODO list:
0. Move versions from pom.xml to properties
1. Add ElasticSearch support
2. Update endpoints descriptions for swagger and remove `Education endpoints` block below

http://localhost:8080/app/v2/api-docs
http://localhost:8080/app/swagger-ui.html

## Education endpoints:

Method | URI | Description
------------ | ------------- | -------------
GET | /course | Get list of courses (by criteria too)
GET | /course/{id} | Get shared course by id
POST | /course/start/{id} | Start course by id (send kafka event to OAuth2 service)
POST | /course | Create a course **FAO**
PUT | /course/{id} | Update existing course by id **FAO**
DELETE | /course/{id} | Delete course by id **FAO**
  |   |  
GET | /lesson?courseId={id} | Get list of lessons by course
GET | /lesson/{id}?courseId={id} | Get shared lesson by id
POST | /lesson/finish/{id}?courseId={id} | Finish lesson and add progress (send kafka event to OAuth2 service)
POST | /lesson | Create a lesson **FAO**
PUT | /lesson{id} | Update existing lesson by id **FAO**
DELETE | /lesson{id} | Delete lesson by id **FAO**
  |   |  
GET | /comment?lessonId={id} | Get list of comments
POST | /comment | Create a new comment
DELETE | /comment/{id} | Delete comment by id
  |   |  
POST | /system/second-step-register | Register user after successful authentication from OAuth2 module

* FAO - For Admin Only


#### Body templates
```json5
{
  "title": "Body for course creation",
  "cost": 100,
  "beginDate": 1564617600000,
  "endDate": 1565617600000
}
```

```json5
{
  "title": "Body for lesson creation",
  "description": "optional",
  "courseId": 1
}
```

```json5
{
  "lessonId": 1,
  "courseId": 1,
  "content": "Body for comment creation"
}
```

```json5
{
  "username": "john",
  "email": "test@ukr.net",
  "rank": "MIDDLE"
}
```
