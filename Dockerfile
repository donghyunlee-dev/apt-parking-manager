FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY . .
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/build/libs/*.jar apartment.parking.manage.jar

ENTRYPOINT ["java", "-jar", "apartment.parking.manage.jar"]