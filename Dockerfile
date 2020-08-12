FROM adoptopenjdk/openjdk14
COPY target/healthconnect-docker.jar healthconnect-docker.jar
VOLUME /HCvolume
CMD ["java", "-jar", "healthconnect-docker.jar"]