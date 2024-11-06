# Use Amazon Corretto JDK for compiling the Java code
FROM amazoncorretto:21-alpine3.17-jdk AS builder

# Copy source code to /app
COPY src/main/java/ /app/

# Set the working directory to /app
WORKDIR /app

# Compile the Java source file
RUN javac PruebaTest.java

# Use a separate stage to run the application
FROM amazoncorretto:21-alpine3.17-jdk AS runner

# Copy compiled class files from the builder stage to the runner stage
COPY --from=builder /app/ /app/

# Set the working directory to /app
WORKDIR /app

# Specify the entrypoint to run the Java program
ENTRYPOINT ["java", "PruebaTest"]
