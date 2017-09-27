# README #

Accompanying source code for blog entry at http://tech.asimio.net/2016/04/05/Microservices-using-Spring-Boot-Jersey-Swagger-and-Docker.html

### Requirements ###

* Java 8
* Maven 3.3.x
* Docker host or Docker machine

### Building and executing the application from command line ###

```
mvn clean package
java -jar target/springboot-jersey-swagger-docker.jar
or
java -DappPort=8701 -jar target/springboot-jersey-swagger-docker.jar
```

Open http://localhost:8000 in a browser

### How do I get set up using Docker? ###

```
sudo docker pull asimio/springboot-jersey-swagger-docker
sudo docker run -idt -p 8701:8701 -e appPort=8701 chadbutz/pai-docker:latest
```

Open http://localhost:8701 in a browser
