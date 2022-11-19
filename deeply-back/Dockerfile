#FROM adoptopenjdk:11-jdk-hotspot AS builder
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew
#RUN ./gradlew bootJar
#
#FROM adoptopenjdk:11-jdk-hotspot
#COPY --from=builder build/libs/deeply-0.0.1-SNAPSHOT.jar.jar app.jar
#
#
## 변수 받기
#ARG ENVIRONMENT
#ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
#
#EXPOSE 9000
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM adoptopenjdk/openjdk11
COPY build/libs/deeply-0.0.1-SNAPSHOT.jar dockerservice.jar
ENTRYPOINT ["java","-jar","/dockerservice.jar"]