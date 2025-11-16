FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY bénéficiaire-service/pom.xml bénéficiaire-service/
COPY bénéficiaire-service/src bénéficiaire-service/src
RUN mvn clean package -DskipTests -f bénéficiaire-service/pom.xml

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/bénéficiaire-service/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

