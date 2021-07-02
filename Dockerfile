FROM maven:3.5-jdk-8-alpine as maven
ARG MAVEN_OPTS
WORKDIR /app
COPY . /app/
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:8
WORKDIR /app
COPY --from=maven /app/target/outreach_email_service-0.0.1-SNAPSHOT.jar /app/
EXPOSE 3333
CMD ["java","-jar","-Deureka.datacenter=cloud","-Dspring.profiles.active=aws","outreach_email_service-0.0.1-SNAPSHOT.jar"]