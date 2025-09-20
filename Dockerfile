FROM openjdk:25-jdk-slim@sha256:988f9f2e77fc48ef3c81fc429d56110c6f43c35b43493f843f98bb21c6f75333

# Create a custom user with UID 1234 and GID 1234
# https://www.docker.com/blog/understanding-the-docker-user-instruction/
RUN groupadd -g 1234 runner-group && \
    useradd -m -u 1234 -g runner-group runner

# Set the directory for executing future commands
WORKDIR /home/runner

# Extract from .tar and copy the app files from host machine to image filesystem
ADD helidon/build/distributions/helidon-*.tar .
RUN mv helidon-* app && chown -R runner:runner-group app

# Switch to the custom user
USER runner

# Run the Main class
# https://docs.docker.com/reference/build-checks/json-args-recommended/
ENTRYPOINT ["/home/runner/app/bin/helidon"]

EXPOSE 8080/tcp