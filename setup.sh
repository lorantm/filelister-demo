#!/bin/bash

function error_exit {
    echo "$1" 1>&2
    exit 1
}

# TODO find out how to get inter-container networking to work rootlessly
if sudo podman ps -a --format "{{.Names}}" | grep -q "postgresql$"
then
    if sudo podman ps --format "{{.Names}}" | grep -q "^postgresql$"
    then
        echo "DB container is already running."
    else
        echo "DB container exists but is not running. Starting the container..."
        sudo podman start postgresql || error_exit "Failed to start the DB container."
    fi
else
    echo "DB container does not exist. Creating and starting the container..."
    sudo podman run -d --name postgresql -p 5432:5432 -e 'POSTGRES_USER=postgres' -e 'POSTGRES_PASSWORD=postgres' docker.io/postgres:latest \
        || error_exit "Failed to create and start the DB container."
fi

echo "Removing app containers and image..."
sudo podman rm -f app1 app2
sudo podman rmi filelister

echo "Building application..."
mvn clean package -DskipTests || error_exit "Maven build failed."
echo "Creating Docker image..."
sudo podman build -t filelister . || error_exit "Podman build failed."

echo "Starting app containers..."
sudo podman run -d --name app1 -p 8081:8080 filelister || error_exit "Failed to start container 'app1'."
sudo podman run -d --name app2 -p 8082:8080 filelister || error_exit "Failed to start container 'app2'."

echo "Script execution completed."