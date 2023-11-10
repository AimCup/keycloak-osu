#!/bin/bash

CONTAINER_ID=$(docker ps -qf "name=pre_build_stage")

# Copy the JAR file from the pre_build_stage container to the keycloak_stage container
docker cp $CONTAINER_ID:/app.jar keycloak_stage:/opt/keycloak/providers/
