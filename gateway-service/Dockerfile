FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY gateway-service/pom.xml gateway-service/
COPY gateway-service/src gateway-service/src
RUN mvn clean package -DskipTests -f gateway-service/pom.xml

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/gateway-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

