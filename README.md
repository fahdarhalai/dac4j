# About DAC4J
The Data Access Control for Java (DAC4J) serves as a simple but efficient generic extension over your data layer which provides users controlled access on sensitive data records to perform read or read-write operations.

DAC4J aims to be data store agnostic by providing implementations for various datastore apis (JPA, JDBC, Elasticsearch, ...).

## DAC4J for Spring Data JPA
[spring-boot-starter-data-jpa-dac4j](https://github.com/fahdarhalai/dac4j/tree/master/spring-boot-starter-data-jpa-dac4j) is a maven dependency that you can include in your Spring Boot project to activate _Data Access Control_ on your JPA entities via DAC4J annotations.

[spring-boot-starter-data-jpa-dac4j-demo](https://github.com/fahdarhalai/dac4j/tree/master/spring-boot-starter-data-jpa-dac4j-demo) is a demo Spring Boot application that makes use of DAC4J to implement access control over entities.
