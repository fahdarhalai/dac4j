# [Demo] DAC4J for Spring Data JPA

## Launching the demo
### Publish DAC4J dependency to local maven repository
1. Build [spring-boot-starter-data-jpa-dac4j](https://github.com/fahdarhalai/dac4j/tree/master/spring-boot-starter-data-jpa-dac4j): 

   `gradlew clean build`
2. Publish to local maven repository: 

   `gradlew publishMavenPublicationToMavenLocal`

### Run the demo app
1. Launch the container:

   `docker compose -f docker-compose-db.yml up -d`

2. Import the demo SQL dump file:

   `cat ./domain/src/main/resources/dac4j-demo-db_dump.sql | docker exec -i dac4j-demo-db mysql -uroot -proot`

3. Run _BootApplication.java_ with your IDE or with commands:

   `gradlew clean build`
   
   then:
   
   `java -jar ./boot/build/libs/boot-0.0.1-SNAPSHOT.jar`