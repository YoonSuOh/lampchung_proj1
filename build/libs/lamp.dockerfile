FROM openjdk:11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} lamp1.jar
ENTRYPOINT ["java", "-jar", "lamp1.jar"]