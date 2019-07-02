# optuscounterapi

Application is built on Spring boot, it can run in any system without doing any env setup. Prerequisite is mentioned below;

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
Java 8 in classpath
Maven in classpath

To build and test
git clone <current repository>
cd <to cloned repository directory>
mvn clean install
java -jar target/optus-counter-api-1.0-SNAPSHOT.jar
