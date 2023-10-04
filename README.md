# MoonPark

This project was created to complete the practical test.

Its structure is based on Java 17, Spring Boot, Spring JPA, PostgreSQL, Lombok, Mockito, and JUnit.
This project features the main function "calculateParkingCost," where a design pattern factory is being used to create different calculations based on the estimated zone, divided into M1, M2, M3.

To complete the project, it was developed with TDD (Test-Driven Development), starting with Unit Tests and covering every necessary scenario for its completion.

## Build
To build the tests, run the following command:
``mvn clean install``

This will compile the project and generate test coverage using the Jacoco plugin included in the project.

## Database
If you wish to build it on your local machine, make sure that the database has the correct name as defined in the file:
[application.yaml](src/main/resources)

The required schema name is:
```
moonpark
```

# Task 5

I have some ideas to discuss it, I will put forward some scenarios that I would use to reach a solution.

1 - I put some monitoring and logging to keep track of client requests, rate limit violations and service performance,
as a New Relic, log as SL4J.

2 - Verify the auto-scaling of the services, how many memories the server has, 
consider scaling the service horizontally and using load balance to distribute incoming requests across multiple services instances.

3 - Maybe add Kafka on the process to consume some requests and distribute in different consumers.