FROM openjdk
MAINTAINER Manoel Medeiros <manoel.medeiros@gmail.com>
ADD target/gateway-service.jar service.jar
ENTRYPOINT ["java", "-jar", "/service.jar"]
EXPOSE 80