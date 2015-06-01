# CSS-553, Assignment 3: Provenance Implementation

This project is built using the Java 1.7 SDK, 1.8 SDK is preferred.

## Dependencies
- [Java Persistence API 2.0.0.Beta1](http://ebr.springsource.com/repository/app/bundle/version/detail?name=com.springsource.javax.persistence&version=2.0.0.Beta1) is required for extended javax functionalities
- [hibernate-core-4.3.10.Final.jar](http://mvnrepository.com/artifact/org.hibernate/hibernate-core/4.3.10.Final) is required for ORM
- [commons-lang3-3.4-javadoc.jar](https://commons.apache.org/proper/commons-lang/download_lang.cgi) is used for ease of overriding core java object methods
- [groovy-all-2.4.3-indy.jar](http://repo1.maven.org/maven2/org/codehaus/groovy/groovy-all/2.4.3/) is required for GroovyAction.java
- junit-4.11.jar is required for the junit tests
- hamcrest-core-1.3.jar is required for the junit tests

## Project Structure
The general structure of the project
```
src
  ├── main
  |   ├── config
  |   └── java
  |       └── edu.uw.css553
  |           ├── backend
  |           |   ├── entities
  |           |   ├── logger
  |           |   ├── manager
  |           |   └── runner
  |           └── frontend
  └── test
      └── java
```
