FROM maven:3.6.3-openjdk-15 as build

WORKDIR /app
COPY pom.xml /app

VOLUME maven-repo:/root/.m2

RUN mvn dependency:resolve

COPY . /app

RUN mvn package -Dmaven.test.skip=true

FROM tomcat:latest as runner

COPY --from=build /app/target/raise.war /usr/local/tomcat/webapps/
RUN mv /usr/local/tomcat/webapps/raise.war /usr/local/tomcat/webapps/ROOT.war

