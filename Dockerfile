FROM openjdk:21-jdk-slim as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM openjdk:21-jdk-slim
WORKDIR /apartment.parking.manage
COPY --from=build /app/build/libs/*.jar apartment.parking.manage.jar
ENTRYPOINT ["java","-jar","apartment.parking.manage.jar"]