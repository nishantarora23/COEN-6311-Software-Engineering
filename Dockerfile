FROM tomcat:8.5.91-jdk11 AS tomcat

# Install wget
RUN apt-get update && apt-get install -y wget

# Download MySQL Connector JAR
RUN wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.11.tar.gz && \
    tar -xzf mysql-connector-java-8.0.11.tar.gz && \
    cp mysql-connector-java-8.0.11/mysql-connector-java-8.0.11.jar /usr/local/tomcat/lib/ && \
    rm -rf mysql-connector-java-8.0.11 mysql-connector-java-8.0.11.tar.gz

# Copy the generated war file from the Gradle build
COPY build/libs/jobhive.war /usr/local/tomcat/webapps/jobhive.war

# Expose port for tomcat
EXPOSE 8080

# Command to run the tomcat
CMD ["/bin/bash", "-c", "sleep 30 && catalina.sh run"]
