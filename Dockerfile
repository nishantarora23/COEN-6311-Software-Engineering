# Stage 1: Build the project with Gradle
FROM gradle:jdk11 AS gradle-builder

WORKDIR /home/gradle/project

# Copy the build files
COPY build.gradle ./
COPY src ./src/

# Build the project
RUN gradle clean build -x test

# Stage 2: Build custom Tomcat image with MySQL Connector JAR and jobhive.war
FROM tomcat:8.5.91-jdk11 AS tomcat-builder

# Install wget
RUN apt-get update && apt-get install -y wget

# Download MySQL Connector JAR
RUN wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.11.tar.gz && \
    tar -xzf mysql-connector-java-8.0.11.tar.gz && \
    cp mysql-connector-java-8.0.11/mysql-connector-java-8.0.11.jar /usr/local/tomcat/lib/ && \
    rm -rf mysql-connector-java-8.0.11 mysql-connector-java-8.0.11.tar.gz

# Copy the generated war file from the Gradle build
COPY --from=gradle-builder /home/gradle/project/build/libs/jobhive.war /usr/local/tomcat/webapps/jobhive.war

# Stage 3: Final Tomcat image
FROM tomcat:8.5.91-jdk11

# Copy MySQL Connector JAR and jobhive.war from the previous stage
COPY --from=tomcat-builder /usr/local/tomcat/lib/mysql-connector-java-8.0.11.jar /usr/local/tomcat/lib/
COPY --from=tomcat-builder /usr/local/tomcat/webapps/jobhive.war /usr/local/tomcat/webapps/
