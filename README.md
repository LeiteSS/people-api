# Person Manager :bust_in_silhouette:

An **API** **Rest** using ***Spring Boot*** to register and manager people of an organization. 

| Features                            |
| ----------------------------------- |
| Register a person                   |
| Delete a person using the id number |
| List all people                     |
| Find a person using the id number   |

## Running

To run the application use the command:

```bas
./mvnw spring-boot:run
```

To run the tests:

```bash
# PersonControllerTest 
./mvnw -Dtest=PersonControllerTest test

# PersonServiceTest
./mvnw -Dtest=PersonServiceTest test

# PersonMapperTest
./mvnw -Dtest=PersonMapperTest test

```

## In The Air

If you do not have **Postman** installed in your computer [click here](https://coding-people-api.herokuapp.com/swagger-ui.html); the Swagger UI allows to "play" with the **API** . Otherwise, download the [collection](https://documenter.getpostman.com/view/14979428/TzJsfdF5) to run in **Postman**. 

## Dependencies

This project uses [**Lombok**](https://projectlombok.org/), [**Mapstruct**](https://mapstruct.org/),  [**Springdoc OpenAPI UI**](https://www.baeldung.com/spring-rest-openapi-documentation), [**H2 Database**](https://www.h2database.com/html/main.html), **Spring Boot Devtools**, **Spring Boot JPA**, **Spring Boot Actuator**.  

## Version 1.0

- The deploy and build was successfully in **Heroku**;
- The features was tested in Postman, and everything passed;
- This version does not have a front-end;
- The tests in `PersonControllerTest` passed;
- The tests in `PersonServiceTest` failed;
- The tests in `PersonMapperTest` failed.