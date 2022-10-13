FROM eclipse-temurin:17
WORKDIR app
COPY target/*.jar todo-app.jar
ENTRYPOINT ["java", "-jar", "todo-app.jar"]