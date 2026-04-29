FROM openjdk:26-ea-26-jdk-slim@sha256:d03b77f66df3ff37be1665c34b32d4d9d19d735aa4e5c0aaec993db699e0e6d4

# Create a custom user with UID 1234 and GID 1234
# https://www.docker.com/blog/understanding-the-docker-user-instruction/
RUN groupadd -g 1234 runner-group && \
    useradd -m -u 1234 -g runner-group runner

# Set the directory for executing future commands
WORKDIR /home/runner

# Extract from .tar and copy the app files from host machine to image filesystem
ADD helidon/build/distributions/helidon-*.tar .
RUN mv helidon-* app && chown -R runner:runner-group app && mkdir data && chown -R runner:runner-group data

# Switch to the custom user
USER runner

# Run the Main class
# https://docs.docker.com/reference/build-checks/json-args-recommended/
ENTRYPOINT ["/home/runner/app/bin/helidon"]

EXPOSE 8080/tcp