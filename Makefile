.PHONY: build
build: remove-app build-app

.PHONY: run
run: start-db start-app

remove-app:
	if sudo podman ps -a --format "{{.Names}}" | grep -q "^app1$$"; then \
		echo "Stopping and removing container app1..."; \
		sudo podman stop app1 && sudo podman rm app1; \
	fi
	if sudo podman ps -a --format "{{.Names}}" | grep -q "^app2$$"; then \
		echo "Stopping and removing container app2..."; \
		sudo podman stop app2 && sudo podman rm app2; \
	fi
	if sudo podman images --format "{{.Repository}}" | grep -q "^localhost/filelister$$"; then \
		echo "Removing image filelister..."; \
		sudo podman rmi -f filelister; \
	fi

build-app:
	echo "Building application..."
	mvn clean package -DskipTests || echo "Maven build failed."
	echo "Creating Docker image..."
	sudo podman build -t filelister . || echo "Podman build failed."

start-app:
	echo "Starting app containers..."
	sudo podman run -d --name app1 -p 8081:8080 filelister || echo "Failed to start container 'app1'."
	sudo podman run -d --name app2 -p 8082:8080 filelister || echo "Failed to start container 'app2'."

start-db:
	if sudo podman ps -a --format "{{.Names}}" | grep -q "^postgresql$$"; then \
		if sudo podman ps --format "{{.Names}}" | grep -q "^postgresql$$"; then \
			echo "DB container is already running."; \
		else \
			echo "DB container exists but is not running. Starting the container..."; \
			sudo podman start postgresql || echo "Failed to start the DB container."; \
		fi; \
	else \
		echo "DB container does not exist. Creating and starting the container..."; \
		sudo podman run -d --name postgresql -p 5432:5432 -e 'POSTGRES_USER=postgres' -e 'POSTGRES_PASSWORD=postgres' docker.io/postgres:latest \
			|| echo "Failed to create and start the DB container."; \
	fi
