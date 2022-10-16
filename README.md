# About
This project was created for solve a project challenge in Québec Java Digital bootcamp.

The purpose is implements part of the bank domain.

## Technologies
Project was created with:

- Java 17;
- JUnit 5;
- Maven

## In this project:
- I use the domain concepts
- I use enum, service and repository patterns
- I separate entities by domains
- I use the TDD method to create tests with JUnit
- I create custom exceptions
- I use Simples responsibility Principle, Open-Closed Principle and Dependency Inversion Principle of the SOLID pattern
    - Dependency Inversion Principle: The class TransferService depends on interface and not on concretions.
    - Simple Responsibility Principle: Each class have one and only one responsibility.
    - Open-Closed Principle: As the class TransferService depends on interface TransferRepositoryInterface, so we can change TransferRepository implementation without alter the class TransferService. In other words, open to inclusion and closed to alter.


