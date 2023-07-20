FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
<<<<<<< HEAD
EXPOSE 8080
=======
EXPOSE 63007
>>>>>>> master
