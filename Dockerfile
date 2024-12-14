FROM amazoncorretto:17

COPY app.jar /app/application.jar

VOLUME ["/data"]

CMD ["java", "-jar", "/app/application.jar"]