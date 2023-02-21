FROM openjdk:17-jdk-alpine
COPY "./target/app-w2m-heores-0.0.1-SNAPSHOT.jar" "app-w2m-heroes.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app-w2m-heroes.jar"]