# Use the official MySQL image as a base
FROM mysql:8.0

# Environment variables for MySQL root user
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=cdk

# Copy the SQL script to the Docker image
COPY init.sql /docker-entrypoint-initdb.d/

# Expose MySQL port
EXPOSE 3306
