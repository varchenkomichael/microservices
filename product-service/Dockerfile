FROM openjdk:8-jdk-alpine

WORKDIR /src/main/java/com/varchenko/productservice/ProductServiceApplication.java
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

ENTRYPOINT ["./mvnw", "spring-boot:run"]