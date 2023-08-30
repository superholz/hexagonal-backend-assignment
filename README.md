# Hexagonal Backend Assignment

In this assignment, you will be tasked with building a Spring Boot web application in Kotlin that follows the Hexagonal
Architecture pattern. The application's main purpose is to allow patients to keep track of their self-measurements.
You will need to implement various REST endpoints for managing measurements and patients, as well as a stub
implementation for sharing measurements.

## Requirements:

Your task is to build a Spring Boot web application that adheres to the Hexagonal Architecture pattern. The application
should include the following components:

1. REST Endpoints:
    * Fetch all measurements of a patient: Retrieve all measurements for a given patient.
    * Add a measurement: Implement business rules for adding measurements. For example, you could enforce that
      measurements
      must be within a certain range or follow a specific format.
    * Delete a measurement: Allow users to delete a measurement.
    * Share a measurement: Allow users to share a measurement with an additional comment with the administration
      system (HIS) of
      their healthcare provider. Save the time of sharing and the comment in the database so the user can see which
      measurements are previously shared. You can use the provided stub implementation ([StubHealthcareInformationSystem](src/main/kotlin/nl/topicus/healthcare/hexagonalbackendassignment/infrastructure/his/StubHealthcareInformationSystem.kt)) as       the external system to send the measurement to.

2. Management Endpoints:
    * Add a patient: Enable the addition of new patients.
    * Remove a patient: Allow the removal of patients.
    * List all patients: Retrieve a list of all registered patients.
    
3. Hexagonal Architecture:
   Organize your application using the Hexagonal Architecture pattern. The core domain logic should be isolated from
   external
   dependencies like frameworks and databases.

4. Database:
   Use a database to store patient information and measurements. An in-memory database (H2) is preconfigured in this
   project,
   but you can use any other datastore (SQL / NoSQL) you like.

## Evaluation Criteria:

You will be evaluated based on the following factors:

* Adherence to Hexagonal Architecture: Is the architecture of the application designed according to the Hexagonal
  pattern?
  Are the core domain logic and external dependencies properly separated?
    * Tip: You can verify if there aren't any dependencies between the core domain logic and infrastructure code by
      running
      the [HexagonalArchitectureTest](src/test/kotlin/nl/topicus/healthcare/hexagonalbackendassignment/HexagonalArchitectureTest.kt).
* REST Endpoints Implementation: Are the required REST endpoints correctly implemented with appropriate error handling?
  Do the business rules for adding measurements make sense and are they implemented effectively?
* Management Endpoints: Are the management endpoints for patients implemented correctly? Do they cover adding, removing,
  and listing patients?
* Code Quality: Is the code well-structured, easy to read, and well-documented? Are naming conventions followed?
* Testing: Are there unit tests to verify the functionality of core components? Have you demonstrated good testing
  practices?
* Resilience: Is the system handling a situation where the external healthcare information system is not available
  correctly?

## Submission:

Please provide your solution as a GitHub repository link (you can fork this repository). Include a README file with
the most important design decisions and any additional notes you'd like to share about your implementation.

Remember that this assignment is designed to assess your technical skills, coding practices, and architecture design and
is not intended to produce a production ready system. Feel free to reach out if you have any questions or need
clarifications during the assignment.

Good luck, and we look forward to seeing your implementation!

## Helpfull information

### Database

To get you started, the project already has an embedded H2 database included. The project is configured to use this
database. You can also connect with this database with a SQL database tool of your choice to inspect the data.
To do this, use the url `jdbc:h2:file:./testdb/.testdb` a username and password is not required.

You can also reset all the data in your database by deleting the files in the folder [testdb](./testdb). You can modify
the database schema or add testdata with Liquibase scripts in the folder [resources/db](./src/main/resources/db). See
"Reference Documentation" for more information about Liquibase.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#web)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#data.sql.jdbc)
* [Liquibase for database schema](https://www.baeldung.com/liquibase-refactor-schema-of-java-app)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)


## Implementation notes (Sebastian Holzapfel)


### Usage
The project has the required endpoints implemented. To test them locally the following calls can be tried out after
starting the application in an intelliJ http scratch file:

**Create a patient:**

```json
POST http://localhost:8080/patients
Content-Type: application/json
{
"name": "Sebastian"
}
```

**List all patient:**
```json
GET http://localhost:8080/patients
```

**Create measurement for patient**
```json
###
POST http://localhost:8080/measurements
Content-Type: application/json

{
"patient_id": "5cbda1e6-f582-41b2-a78b-88327d4db5c1",
"type": "BLOOD_SUGAR",
"value": "40",
"unit": "KG",
"measure_time": "2023-08-19T18:30:00Z"
}
```

**List measurement of a patient**
```json
###
GET http://localhost:8080/patients/5cbda1e6-f582-41b2-a78b-88327d4db5c1/measurements
```

**Share a measurement with His**
```json
###
PATCH http://localhost:8080/measurements
Content-Type: application/json

{
  "measurement_id": "98e8a4f8-2824-4ec3-a730-f609475f0dab",
  "comment": "This is a comment for sharing the measurement"
}
```

**Delete a measurement**
```json
###
DELETE http://localhost:8080/measurements/98e8a4f8-2824-4ec3-a730-f609475f0dab>
```

**Delete a patient**
```json
###
DELETE http://localhost:8080/patients/5cbda1e6-f582-41b2-a78b-88327d4db5c1
```



### Hexagonal design, clean code, class and packaging structure
The package structure was mostly used as provided with some changes:
- Package `domain.ports` was added to define interfaces in the domain layer that can be implemented in the adapters of the
infrastructural layer (inversion of dependency)
- The code is organized as such that the use case are as much as possible independent from each other by using one controller 
in infrastructural layer and one service in the domain layer. This makes it easy to remove or adapt functionality because
for one use case responsibility is clearly separated into different classes.
- Each layer and use case has it's own models. Only domain models are defined as separate classes in the `domain` package. 
The models are therefore defined mostly within the classes using them. Often as nested classes. This might be a bit
unconventional compared to approaches I have seen in the past. It gives the advantage of having classes independent.
The nested class approach gives a little less headache on having good names for objects.
- The `application` package is there to hold the business logic for the different use cases. Structuring it as such makes
it very easy to see by the reader what the application is about and which use cases are implemented.
- Universal business logic that adheres mostly to part of the domain is implemented within the domain object, more leaning
to classical thought of OOP (but properties and behaviour in the same class). This originates from the [TellDontAsk](https://martinfowler.com/bliki/TellDontAsk.html)
principle.
- Creation of Ids is done in controllers to let the domain layer clean and separate concerns. As a follow up, id creation 
could be delegated to a low level interface to even better separate.



### API design
- Api design in this service mostly adheres to the [Zalando guidline](https://opensource.zalando.com/restful-api-guidelines/).
- Api naming strategy has snake_case for api properties ad camelCase in the code. This is configured by setting a property
naming strategy in jackson.
- For list endpoints pagination should be still added.
- Defining data types in the controllers should use mostly primitive values which are converted to specific types in the
domain layer. This to keep good separation of concerns. However, for convenience and sake of simplicity
 I did not follow this strictly: `UUID` is used already in controller level.
- For Ids UUIDs are used to have more flexibility and control over their generation.
- Success HTTP codes should be meaningful e.g. getting a 201 back when creating a resource.
- Failing endpoints should give meaningful error codes and should result into meaningful logging. E.g. when measurement
can not be created because the measurement type is not known it should result in `400 Bad request` and log 
a specific exception. Setttin up a proper logger gave me headaches which left me with println for the moment.
- It is debatable if details about the exceptions should be included in the problem detail.
- Depending on company strategy it maybe useful to have spec first approach and in any case generate an OpenAPI specification.

  
### Testing strategy

I have the following target in mind:
- Having unit test as the basis of the testing pyramid. Using them for the layers of the application as slice test. Crossing 
a boundary (e.g. from Application to Repository) results in having the boundary method mocked.
- Having integration test to test wiring of different layers **within** the application. Ideally one happy case and one unhappy
case. External services would be mocked.
- Test behaviour, so not necessarily testing all correct wiring and mapping in each test.
- Testing behaviour also means in not testing private methods but testing their functionality through the use case.
- Test one thing per test.
- One business logic change, ideally should leads only to one changing test.
- Make use of auto-generated fixtures (kotlinFixture package) to have test less dependent on code changes and focus on
 changes that do matter for the test case.
- Redundant test should be deleted.
- I used kotest matchers for assertions, because of the good readability.

Clearly, not all of this was implemented in this exercise.

### Persistence layer
- In this project I kept the H2 database for convenience. Replacing with e.g. an pgSQL database would be
straightforward.
- Naming convention
    - get-methods should return the entity or throw an exception
    - find method should return with entity or null
- I'm not too happy about my current approach to implement the persistence layer. Especially 
mixing the `CrudRepository` interface with more low level JDBC api calls. I think it would be 
better to not use the CrudRepository and use plain SQL with JDBC for all cases. Due to time constraint
I did not make this improvement. Due to the hexagonal design this ugliness can be dealt without effecting other parts
of the code base.
- Currently, the entities in the database have no foreign keys defined. In this first iteration I decided
to keep it simple and handle it *manually in the code*. E.g. could be a good thing to have
the patientId as a foreign key for the measurements in order to have the one-to-many relationship also coverd in the 
database.

  
### Some business logic decisions
- When a patient gets deleted also all his measurements get deleted. Sharing logs stay.
- When a measurement is shared a log is created about the sharing. There can be multiple logs per measurement.
- When a measurement is shared once it gets a status of `SHARED`.


### Other thoughts for discussion
- It could be idea to make constructor of Measurement private to not accidentally create a measurement in the domain layer that
does not undergo data validation. But this makes it un-flexible when converting to it from database. I assume with this approach
that only validated data makes it into the database. So being more strict on creation than at retrieval.
- I really like the use of immutability. In the code base I recently worked with a pattern is to have domain objects mutable
in order to prevent ugly calls to copy or static functions. 
- Use of `value class` can be used to make the code safer to use and implement validation for properties. Not used here yet.
- Authorization should be added to the service to limit access to the service. I was happy not to go down
this rabbit whole for this exercise ;-).
