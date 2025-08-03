FROM openjdk:24-jdk-slim@sha256:df6c3a966c372fa314ec0dc428bf27f8aa28316a173d3d6e99035343dee7fc2c

# Create a custom user with UID 1234 and GID 1234
# https://www.docker.com/blog/understanding-the-docker-user-instruction/
RUN groupadd -g 1234 customgroup && \
    useradd -m -u 1234 -g customgroup customuser

# Set the directory for executing future commands
WORKDIR /home/customuser

# Extract from .tar and copy the app files from host machine to image filesystem
ADD helidon/build/distributions/helidon-*.tar .
RUN mv helidon-* app && chown -R customuser:customgroup app

# Switch to the custom user
USER customuser

# Run the Main class
# https://docs.docker.com/reference/build-checks/json-args-recommended/
ENTRYPOINT ["/home/customuser/app/bin/helidon"]

EXPOSE 8080/tcp