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
      measurements are previously shared. You can use the provided stub implementation ([StubHealthcareInformationSystem](src/main/kotlin/nl/topicus/healthcare/hexagonalbackendassignment/infrastructure/his/StubHealthcareInformationSystem.kt)) as       the external system to send the measurement to the external system.

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

