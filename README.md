# Backend Coding Challenge

We appreciate you taking the time to participate and submit a coding challenge. In the next step we would like you to
create/extend a backend REST API for a simple note-taking app. Below you will find a list of tasks and limitations
required for completing the challenge. Select your tech stack from the table below and fork the repository. If you don't see you tech stack no worries, you will then need to create your repository from scratch.

### Application:

* Users can add, delete and modify their notes
* Users can see a list of all their notes
* Users can filter their notes via tags
* Users must be logged in, in order to view/add/delete/etc. their notes

### The notes are plain text and should contain:

* Title
* Body
* Tags

### Optional Features 🚀

* [ ] Search contents of notes with keywords
* [ ] Notes can be either public or private
    * Public notes can be viewed without authentication, however they cannot be modified
* [ ] User management API to create new users

### Limitations:

* test accordingly

### What if I don't finish?

Try to produce something that is at least minimally functional. Part of the exercise is to see what you prioritize first when you have a limited amount of time. For any unfinished tasks, please do add `TODO` comments to your code with a short explanation. You will be given an opportunity later to go into more detail and explain how you would go about finishing those tasks.

## Repositories

| Tech Stack | CI Integration | Challenge |
|--|--|--|
| Python & Django | Yes | [Repository →](https://github.com/Thermondo/backend-coding-challenge-django) 
| Kotlin & Ktor | Yes | [Repository →](https://github.com/Thermondo/backend-coding-challenge-ktor)





## Features

### Below features available in this service

1. User Management API to register new users.
2. Authenticate users using JWT.
3. User login session management.
4. Create Notes API for the users.
5. Update Notes API for the users.
6. Delete Notes API for the users.
7. Get Notes API for the users.
8. Get Notes API based on the tags for the users.
9. Get All notes for the users without authentication.


### Building and Running Instructions

Solution is implemented in Java. Hence, to build and run it one need to have Java8 installed. Optionally, you can have Maven installed. Maven Wrapper is checked in into repo, if you don't have maven you can use it.

In memory DB - H2 database is used to persist the data. 

Before running app, ensure that port 8080 is free. One can use server.port system property to override default 8080 port

Go into repo root and run:

./mvnw spring-boot:run

1. All the apis will be authenticated using JWT token and user session token.
2. Authenticate API has to be called before calling any API to get the JWT token
3. Once the Authenticate API call is successful, pass the JWT token as part of API header to access other APIs.
4. User registration will be done using registration API call.
5. Post registration call is successful , User login API to be called which generated generates session token, this session token has to be passed as part of usercontext to access subsequent APIs. This ensures that, user has to be logged in to access other API's.
6. getAllNotes API is excluded from authentication and user session token validation as mentioned in the requirement.



### Below are the different API end points created and purpose.

1. /authenticate - to generate JWT token
2. /user/register - to register the user
3. /user/login - to login the user
4. /api/notes/all - to find all the notes in the system without authentication and user session.
5. /notes - Create a new note for the user
6. /notes - Get all the notes for the user.
7. /notes - Update notes belong to the user.
8. /notes - Delete notes belong to the user.
9. /notes/tag/{tag} - to retrieve all the notes for the given tag.
10. /notes/keyword/{keyword} - search all the notes for the given keyword.
