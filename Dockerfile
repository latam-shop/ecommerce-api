FROM eclipse-temurin:21-jre-ubi9-minimal

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 9090
EXPOSE 9091

ENTRYPOINT ["java", "-jar", "app.jar"]