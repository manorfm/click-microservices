FROM openjdk
MAINTAINER Manoel Medeiros <manoel.medeiros@gmail.com>
ADD target/zipkin-service-1.0.0.jar service.jar
ENTRYPOINT ["java", "-jar", "/service.jar"]
EXPOSE 8701