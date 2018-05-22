FROM openjdk
MAINTAINER Manoel Medeiros <manoel.medeiros@gmail.com>
ADD target/discovery-service.jar service.jar
ENTRYPOINT ["java", "-jar", "/service.jar"]
EXPOSE 8700