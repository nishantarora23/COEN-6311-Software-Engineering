# Build custom Tomcat image with MySQL Connector JAR
FROM tomcat:8.5.91-jdk11 AS tomcat-builder

# Install wget
RUN apt-get update && apt-get install -y wget

# Download MySQL Connector JAR
RUN wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.11.tar.gz && \
    tar -xzf mysql-connector-java-8.0.11.tar.gz && \
    cp mysql-connector-java-8.0.11/mysql-connector-java-8.0.11.jar /usr/local/tomcat/lib/ && \
    rm -rf mysql-connector-java-8.0.11 mysql-connector-java-8.0.11.tar.gz

# Final Tomcat image
FROM tomcat:8.5.91-jdk11

# Copy MySQL Connector JAR from the previous stage
COPY --from=tomcat-builder /usr/local/tomcat/lib/mysql-connector-java-8.0.11.jar /usr/local/tomcat/lib/