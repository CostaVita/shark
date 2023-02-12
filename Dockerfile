#FROM openjdk:17-slim AS TEMP_BUILD_IMAGE
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY build.gradle.kts settings.gradle.kts gradlew $APP_HOME
#COPY gradle $APP_HOME/gradle
#RUN ./gradlew build || return 0
#COPY . .
#RUN ./gradlew build

FROM openjdk:17-slim
RUN apt-get update && \
        apt-get -y install curl
RUN mkdir /app
COPY build/libs/*.jar /app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]