# FIZZBUZZ API

REST-API for Fizz Buzz Game

# How to build and run 

1. to build : > mvn clean install
2. to run 	: > mvn spring-boot:run 

The app is configured to run on port 8081, if the local port is occupied, modify the > server.port property in > src/main/resources/application.properties

Use the url to access the logic api, if the app is running in default port 

http://localhost:8081/api?number=100  (query parameter number=100 is the number given to the logic app to generate Fizz Buzz series, add more pairs by 
using unique keys as query parameters)
