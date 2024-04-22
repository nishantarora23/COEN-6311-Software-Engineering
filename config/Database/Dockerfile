# Use the official MySQL image as the base image
FROM mysql:8.0

# Copy initialization scripts into the container
COPY initial_RFU.sql /docker-entrypoint-initdb.d/

# Expose the MySQL port
EXPOSE 3306

# Set the default authentication plugin to caching_sha2_password
CMD ["--default-authentication-plugin=caching_sha2_password"]

# Set the root password for MySQL
ENV MYSQL_ROOT_PASSWORD=admin123