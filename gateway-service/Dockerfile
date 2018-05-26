FROM openjdk
MAINTAINER Manoel Medeiros <manoel.medeiros@gmail.com>
ADD target/gateway-service.jar service.jar
ADD docker/wait-for-it.sh /wait-for-it.sh
RUN ["chmod", "+x", "/wait-for-it.sh"]
#ENTRYPOINT ["java", "-jar", "/service.jar"]
EXPOSE 80
