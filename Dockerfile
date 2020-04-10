FROM adoptopenjdk/openjdk8:alpine-jre
EXPOSE 8080
COPY build/libs/reddit-lite-snapshot.jar /opt/reddit-lite.jar
CMD ["java", "-jar", "/opt/reddit-lite.jar"]