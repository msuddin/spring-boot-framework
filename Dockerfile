# Alpine Linux with OpenJDK JRE
FROM adoptopenjdk/openjdk11

# Copy jar file
COPY build/libs/spring-boot-framework-0.0.1-SNAPSHOT.jar /spring-boot-framework.jar

# Run command
CMD ["java", "-jar", "/spring-boot-framework.jar"]