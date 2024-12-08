Question : I want to create a container with image mysql and want to create a database called "cdk", then I want to create some table and insert some data as well. Whole thing needs to be a docker image, so whenever I run this container, i get the data.

Answer: To achieve this, you can create a Docker image that initializes a MySQL database with a specific schema and data. Here's a step-by-step guide:

---

### 1. **Prepare the SQL Script**
Create a SQL script (`init.sql`) to set up the database, tables, and initial data.

```sql
-- init.sql

CREATE DATABASE IF NOT EXISTS cdk;

USE cdk;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO users (name, email) VALUES
('Alice', 'alice@example.com'),
('Bob', 'bob@example.com');
```

---

### 2. **Create a Dockerfile**
Create a `Dockerfile` to build the MySQL container with the SQL initialization script.

```dockerfile
# Use the official MySQL image as a base
FROM mysql:8.0

# Environment variables for MySQL root user
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=cdk

# Copy the SQL script to the Docker image
COPY init.sql /docker-entrypoint-initdb.d/

# Expose MySQL port
EXPOSE 3306
```

---

### 3. **Build the Docker Image**
Build the Docker image with the following command:

```bash
docker build -t custom-mysql .
```

---

### 4. **Run the Container**
Run the container using the image you built:

```bash
docker run -d --name custom-mysql -p 3306:3306 custom-mysql
```

---

### 5. **Verify the Data**
Connect to the running MySQL container and verify the database and tables:

```bash
docker exec -it custom-mysql mysql -uroot -proot -e "USE cdk; SELECT * FROM users;"
```

You should see the inserted data.

---

### Notes:
1. The SQL script in the `docker-entrypoint-initdb.d` directory is executed automatically when the MySQL container is first initialized.
2. Data will persist as part of the Docker image, so every time you run the container, the database will be pre-initialized with the specified data.
3. If you want to make further updates, modify the `init.sql` and rebuild the image.

---

Question 2: how do i stop this running container and how do i restart.

Answer: Here are the commands to stop and restart the running container:

---

### **1. Stop the Running Container**
To stop a running container, use the `docker stop` command followed by the container name or ID.

```bash
docker stop custom-mysql
```

---

### **2. Restart the Container**
To restart the container, you can use the `docker start` command:

```bash
docker start custom-mysql
```

This will start the container with the existing data preserved.

---

### **3. Remove the Container (Optional)**
If you want to remove the container completely (e.g., to create a fresh instance), stop the container first, then use the `docker rm` command:

```bash
docker stop custom-mysql
docker rm custom-mysql
```

> ⚠️ Removing the container deletes its state. However, the data will remain intact if it's baked into the Docker image or stored in a volume.

---

### **4. Restart a New Instance**
If you’ve removed the container but want to recreate it, you can run the container again using the `docker run` command:

```bash
docker run -d --name custom-mysql -p 3306:3306 custom-mysql
```

---

### **5. Check Container Status**
Use the following command to check the status of your container:

```bash
docker ps        # Shows running containers
docker ps -a     # Shows all containers, including stopped ones
```

You'll see the `custom-mysql` container listed along with its status.