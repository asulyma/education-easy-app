# Education Application

Application based on microservice architecture.
Project Benefits:
- uninterrupted communication between microservices using Apache Kafka;
- improved project security using OAuth2 from a separate service;
- Support Docker containers;

### How to run application for local debugging:
1. Install and start Zookeper and Kafka
2. Create next topics:
   - education-finish-lesson-event
   - education-start-course-event
3. Install, start Postgres 10+ and create next databases (owner - postgres):
   - education_app
   - education_auth
3.1 Make sure that references in application.yml set up correctly.
4. Run `mvn clean install`
5. Start **AuthApplication** firstly, and the second one - **EducationApplication**
6. Try accessing for generating token with _client_credentials_ as grant type:
```
POST    - http://localhost:8081/auth/oauth/token?grant_type=client_credentials
Headers - Authorization: Basic d2ViLWNsaWVudDp3ZWItY2xpZW50LXNlY3JldA==
```
7. Try accessing for generating token with _password_ as grant type:
```
GET     - http://localhost:8081/auth/oauth/token?grant_type=password&username=john&password=john
Headers - Authorization: Basic d2ViLWNsaWVudDp3ZWItY2xpZW50LXNlY3JldA==
```
8. Once the token is obtained you can access the resource using:
```
CRUD    - 
Headers -
```

#### Technical Note:
In OAuth2 service by default, there are next information:
   - clientId: _web-client_, clientSecret: _web-client-secret_
   - username: _john_, password: _john_, role: _ROLE_USER_
   - username: _admin_, password: _admin_, role: _ROLE_ADMIN_

### How to run application with docker:
1. Run `mvn clean install`
2. Run `docker-compose up -d`

### TODO list:
1. Remove working with user from OAuth module.
2. Add to token UUID of user
3. Find user by UUID inside App module.
4. Working with user data only inside App module.


1. Build Docker image from maven plugin
2. Fix for Docker compose to start correctly

## Education endpoints:

Method | URI | Description
------------ | ------------- | -------------
GET | /course | Get list of courses (by criteria too)
GET | /course/{id} | Get single course by id
POST | /course/{id} | Start course by id (send kafka event to OAuth2 service)
POST | /course | Create a course **FAO**
PUT | /course/{id} | Update existing course by id **FAO**
DELETE | /course/{id} | Delete course by id **FAO**
  |   |  
GET | /lesson?courseId={id} | Get list of lessons by course
GET | /lesson/{id} | Get single lesson by id
PUT | /lesson/finish/{id} | Finish lesson and add progress (send kafka event to OAuth2 service)
POST | /lesson | Create a lesson **FAO**
PUT | /lesson{id} | Update existing lesson by id **FAO**
DELETE | /lesson{id} | Delete lesson by id **FAO**
  |   |  
GET | /comment?{lessonId} | Get list of comments
POST | /comment | Create a new comment
DELETE | /comment/{id} | Delete comment by id

* FAO - For Admin Only


#### Body templates
```json5
{
  "title": "Course Name",
  "cost": 100,
  "beginDate": 1564617600000,
  "endDate": 1565617600000
}
```

```json5
{
  "title": "Lesson Name",
  "description": "optional",
  "courseId": 1
}
```
