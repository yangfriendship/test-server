FROM adoptopenjdk/openjdk11
COPY ./build/libs/ubuntu-test-server-0.0.1-SNAPSHOT.jar app
ENTRYPOINT ["java", "-jar", "/app", "*.jar"]