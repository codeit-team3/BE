FROM amazoncorretto:17

COPY build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

VOLUME ["/data"]

CMD ["java", "-jar", "/app/application.jar"]