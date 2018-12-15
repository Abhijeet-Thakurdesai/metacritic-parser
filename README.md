# Metacritic-parser

A small RESTful webservice which returns top playstation games listed on metacritic website.

# Features:
  - Custom exception handling and error messages.
  - Good code quality and code coverage.
  - Restful architecture.

### Installation

Metacritic-parser requires [Java1.8](https://www.oracle.com/technetwork/java/javase/overview/index.html) to run. To build and run unit tests on the source code [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac) is required

To run unit tests:
```sh
$ mvn test
```

To build from source code:
```sh
$ mvn package
```
After build is successfull navigate to target folder to run the build.
To run the build execute following command:
```sh
$ java -jar metacritic-parser-0.0.1-SNAPSHOT.jar
```

Now the Restful server will start at localhost:8080, now the games API can be accesed by url http://localhost:8080/games.

### Tools for API testing:
| Plugin | Download |
| ------ | ------ |
| Postman | https://www.getpostman.com/apps |

### Configuration
Configuration of entire webservice is present in application.properties file.
**Have a great day!**


