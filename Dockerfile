FROM openjdk:24

# Create a custom user with UID 1234 and GID 1234
# https://www.docker.com/blog/understanding-the-docker-user-instruction/
RUN groupadd -g 1234 customgroup && \
    useradd -m -u 1234 -g customgroup customuser

# Switch to the custom user
USER customuser

# Copy the app files from host machine to image filesystem
COPY --link helidon/build/libs/helidon-all.jar /home/customuser/helidon-all.jar

# Set the directory for executing future commands
WORKDIR /home/customuser

# Run the Main class
# https://docs.docker.com/reference/build-checks/json-args-recommended/
ENTRYPOINT ["java", "-jar", "/home/customuser/helidon-all.jar"]

EXPOSE 8080