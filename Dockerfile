FROM openjdk:11-slim
EXPOSE 8080
ADD ./dotaStats-0.0.1-SNAPSHOT.jar dotaStats.jar
ENTRYPOINT [ "java", "-jar", "dotaStats.jar" ]
