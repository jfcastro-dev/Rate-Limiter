# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add the application's jar to the container
ADD target/ratelimiter-0.0.1-SNAPSHOT.jar ratelimiter.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/ratelimiter.jar"]