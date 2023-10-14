# Linux
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/todolist-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

# Windows
#FROM mcr.microsoft.com/java/jdk:17 AS build

#WORKDIR /app

#COPY . .

#RUN mvn clean install

#FROM mcr.microsoft.com/java/jdk:17-slim

#WORKDIR /app

#EXPOSE 8080

#COPY --from=build /app/target/todolist-1.0.0.jar app.jar

#ENTRYPOINT ["java", "-jar", "app.jar"]