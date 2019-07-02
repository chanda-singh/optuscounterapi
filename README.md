# optuscounterapi

Application is built on Spring boot on port 9090, it can run in any system without doing any env setup. Prerequisite is mentioned below;

It has two Rest service

1. http://localhost:9090/counter-api/search (Post service)

Sample Request
> curl http://host/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -
d’{“searchText”:[“Duis”, “Sed”, “Donec”, “Augue”, “Pellentesque”, “123”]}’ -H"Content-
Type: application/json" –X POST

Result in JSON:
> {"counts": [{"Duis": 11}, {"Sed": 16}, {"Donec": 8}, {"Augue": 7}, {"Pellentesque": 6},
{"123": 0}]}

2. http://localhost:9090/counter-api/top/{number} (Get Service)

Sample Request : 
curl http://host/counter-api/top/20 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -
H”Accept: text/csv”


To run the application

Pre-requisite;
1. Java 8 in classpath
2. Maven in classpath

To build and test
1. git clone <current repository>
2. cd <to cloned repository directory>
3. mvn clean install
4. java -jar target/optus-counter-api-1.0-SNAPSHOT.jar
5. Access through curl as mentioned above

OR run from your editor eclipse/intelliJ

1. Run SpringBootCounterRunner.java (main Program)
2. Access through curl as mentioned above
