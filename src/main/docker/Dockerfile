FROM eclipse-temurin:11-jre-alpine
ENV TARGET_DIR=/komoot/challenge

# Set the working directory to TARGET_DIR
WORKDIR ${TARGET_DIR}
COPY . ${TARGET_DIR}

# Make ports available to the world outside this container
EXPOSE 8090

ENTRYPOINT ["./komoot_challenge_entrypoint.sh"]
