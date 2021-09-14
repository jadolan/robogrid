# Robot Command Application

A simple Spring Boot webapp to move a robot within a grid.

## Requirements:

- Java 11
- Maven 3.6.0

## Usage

To initially compile the project, cd into the root directory and execute the following command:

`mvn clean install`

To start the application, run the following command:

`mvn spring-boot:run`

The app is available at http://localhost:3050/

## Current Status

### Done

- backend component to calculate robot position based on script
- frontend components make call to backend and display robot in grid
- backend tests

### Next Steps

- use framework to implement frontend (possibly vue)
- input validation on the frontend
- more sophisticated error handling in the backend: don't just ignore invalid commands, return feedback
- create Dockerfile to deploy app easily
