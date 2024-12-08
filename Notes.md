To containerize your Spring Boot API and link it to the MySQL container, follow these steps:

---

### **1. Prepare the Spring Boot Project**
Ensure your Spring Boot application is properly configured to connect to the MySQL database. Update the `application.properties` or `application.yml` file:

```properties
# application.properties
spring.datasource.url=jdbc:mysql://custom-mysql:3306/cdk
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=none
```

Here, `custom-mysql` is the hostname of the MySQL container (will be resolved using Docker networking).

---

### **2. Create a Dockerfile for Spring Boot**
Create a `Dockerfile` in your Spring Boot project root directory:

```dockerfile
# Use an official OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/your-app-name.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Replace `your-app-name.jar` with the actual name of your built JAR file.

---

### **3. Build the Spring Boot Docker Image**
Ensure your Spring Boot application is built using Maven or Gradle, and the JAR file is available in the `target` directory.

```bash
# Build the Spring Boot app JAR
mvn clean package

# Build the Docker image
docker build -t dealer-locator-api .
```

---

### **4. Run Both Containers Together**

#### **Option 1: Using Docker Network**
Create a Docker network to allow communication between containers:

```bash
docker network create my-network
```

Run the MySQL container on this network:

```bash
docker run -d --name custom-mysql --network my-network -p 3306:3306 custom-mysql
```

Run the Spring Boot container on the same network:

```bash
docker run --name dealer-locator-api --network my-network -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://custom-mysql:3306/cdk -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=root dealer-locator-api
```

---

# Running software using docker compose

#### **Option 2: Using Docker Compose**
Create a `docker-compose.yml` file to manage both containers:

```yaml
version: '3.8'
services:
  mysql:
    image: custom-mysql
    container_name: custom-mysql
    ports:
      - "3306:3306"
    networks:
      - app-network
    environment:
      MYSQL_ROOT_PASSWORD: "root"
      MYSQL_DATABASE: "cdk"
    healthcheck:
      test: [ "CMD", "mysqladmin", "ping", "-h", "localhost" ]
      interval: 10s
      timeout: 5s
      retries: 5

  dealer-locator-api:
    image: dealer-locator-api
    container_name: dealer-locator-api
    depends_on:
      mysql:
        condition: service_healthy
    ports:
      - "8080:8080"
    networks:
      - app-network
    environment:
      SPRING_DATASOURCE_URL: "jdbc:mysql://mysql:3306/cdk?allowPublicKeyRetrieval=true&useSSL=false"
      SPRING_DATASOURCE_USERNAME: "root"
      SPRING_DATASOURCE_PASSWORD: "root"

networks:
  app-network:
    driver: bridge
```

Run the containers using Docker Compose:

```bash
docker-compose up -d
```

or

```bash
docker-compose up --build
```
