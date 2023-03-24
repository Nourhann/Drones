# Drones Musala Task 

## Getting Started
These instructions will get you the project up and running on
your local machine.

### Building

To compile sources, run 
```
mvn clean install
```

### Building without running tests
```
mvn clean install -DskipTests
```

## Running the tests
| Tests run mode             | Maven command              |
| -------------------------- |:--------------------------:|
| run unit tests only        | `mvn clean test`           |



## Database 
Project uses H2 database -In Memory db - when you run the app it will be initialized for you

## REST API
REST API [postmanCollection](/Drone.postman_collection.json)

