#!/bin/bash

mysql -uroot -proot -e "CREATE DATABASE user"


#entrypoint: ./mysql-entrypoint.sh








  user-service:
    image: manorfm/user-service:latest
    container_name: user-service
    build:
      context: ./users-service/
      dockerfile: Dockerfile
    ports:
      - 3001:3001
    depends_on:
      - mysql
  clicks-service:
    image: manorfm/clicks-service:latest
    container_name: clicks-service
    build:
      context: ./clicks-service/ 
      dockerfile: Dockerfile
    ports:
      - 3000:3000
    depends_on:
      - user-service
  gateway-service:
    image: manorfm/gateway-service:latest
    container_name: gateway-service
    build:
      context: ./gateway-service/ 
      dockerfile: Dockerfile
    ports:
      - 80:80
    depends_on:
      - discovery-service
      - clicks-service
  discovery-service:
    image: manorfm/discovery-service:latest
    container_name: discovery-service
    build:
      context: ./discovery-service/ 
      dockerfile: Dockerfile
    ports:
      - 8700:8700
  zipkin-service:
    image: manorfm/zipkin-service:latest
    container_name: zipkin-service
    build:
      context: ./zipkin-service/ 
      dockerfile: Dockerfile
    ports:
      - 8701:8701
    depends_on:
      - logstash
  elasticsearch:
    image: elasticsearch
    container_name: elasticsearch
    ports:
      - 9200:9200
      - 9300:9300
  kibana:
    image: kibana
    container_name: kibana
    ports:
      - 5601:5601
    links:
      - elasticsearch
    depends_on:
      - elasticsearch
  logstash:
    image: logstash
    container_name: logstash
    ports:
      - 5000:5000
    environment:
      - 'input { tcp { port => 5000 codec => "json" } } output { elasticsearch { hosts => ["172.17.0.3"] index => "micro-%{serviceName}"} }'
    depends_on:
      - kibana