FROM adoptopenjdk/openjdk13
COPY target/healthconnect-docker.jar healthconnect-docker.jar
CMD ["java", "-jar", "healthconnect-docker.jar"]