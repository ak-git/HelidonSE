FROM openjdk:24-jdk-slim@sha256:df6c3a966c372fa314ec0dc428bf27f8aa28316a173d3d6e99035343dee7fc2c

# Create a custom user with UID 1234 and GID 1234
# https://www.docker.com/blog/understanding-the-docker-user-instruction/
RUN groupadd -g 1234 app-group && \
    useradd -m -u 1234 -g app-group app-user

# Set the directory for executing future commands
WORKDIR /home/app-user

# Extract from .tar and copy the app files from host machine to image filesystem
ADD helidon/build/distributions/helidon-*.tar .
RUN mv helidon-* app && chown -R app-user:app-group app

# Switch to the custom user
USER app-user

# Run the Main class
# https://docs.docker.com/reference/build-checks/json-args-recommended/
ENTRYPOINT ["/home/app-user/app/bin/helidon"]

EXPOSE 8080/tcp