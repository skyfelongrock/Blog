FROM adoptopenjdk/openjdk11
ARG JAR_FILE=Blog-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} blog.jar
ENTRYPOINT [ "java","-jar","/blog.jar" ]