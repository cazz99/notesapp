FROM openjdk:17-slim


WORKDIR /app

# Copy the JAR file
COPY target/notes-1.jar /app/notes-1.jar

# Expose the port the app will run on
EXPOSE 8080

# Set the entry point to run both Java
ENTRYPOINT ["sh", "-c", "java -jar /app/notes-1.jar"]
