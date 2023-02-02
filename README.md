# heroes-challenge
========================
### Run it with Docker

 * Have [docker](https://www.docker.com/) installed
 * Execute ```gradle clean build docker```
 * Execute ```cd docker```
 * Execute ```docker-compose up```
 * Go to http://localhost:8080/swagger-ui/index.html to see swagger documentation
 * Execute ```gradle test jacocoTestReport``` Go to /build/jacoco/index.html
  
 **NOTE:** to log in, authenticate the username and password is "**admin**" in both
