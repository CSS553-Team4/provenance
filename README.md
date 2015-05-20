# CSS-553, Assignment 3: Provenance Implementation

This project is built using the Java 1.7 SDK.

## Dependencies
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
