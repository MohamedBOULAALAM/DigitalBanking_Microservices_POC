FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY chatbot-service/pom.xml chatbot-service/
COPY chatbot-service/src chatbot-service/src
RUN mvn clean package -DskipTests -f chatbot-service/pom.xml

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/chatbot-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]

