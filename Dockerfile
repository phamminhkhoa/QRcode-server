FROM openjdk:8-jre-alpine3.9
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT [ "java","-jar","/app.jar" ]
