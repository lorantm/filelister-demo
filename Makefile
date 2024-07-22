.PHONY: build
build: remove-app build-app

.PHONY: run
run: create-network start-db start-app

remove-app:
	if podman ps -a --format "{{.Names}}" | grep -q "^app1$$"; then \
		echo "Stopping and removing container app1..."; \
		podman stop app1 && podman rm app1; \
	fi
	if podman ps -a --format "{{.Names}}" | grep -q "^app2$$"; then \
		echo "Stopping and removing container app2..."; \
		podman stop app2 && podman rm app2; \
	fi
	if podman images --format "{{.Repository}}" | grep -q "^localhost/filelister$$"; then \
		echo "Removing image filelister..."; \
		podman rmi -f filelister; \
	fi

build-app:
	echo "Creating Docker image..."
	podman build -t filelister . || echo "Podman build failed."

start-app:
	echo "Starting app containers..."
	podman run -d --network filelister --user user1 --name app1 -p 8081:8080 filelister || echo "Failed to start container 'app1 '."
	podman run -d --network filelister --user user2 --name app2 -p 8082:8080 filelister || echo "Failed to start container 'app2'."

start-db:
	if podman ps -a --format "{{.Names}}" | grep -q "^postgresql$$"; then \
		if podman ps --format "{{.Names}}" | grep -q "^postgresql$$"; then \
			echo "DB container is already running."; \
		else \
			echo "DB container exists but is not running. Starting the container..."; \
			podman start postgresql || echo "Failed to start the DB container."; \
		fi; \
	else \
		echo "DB container does not exist. Creating and starting the container..."; \
		podman run --network filelister -d --name postgresql -p 5432:5432 -e 'POSTGRES_USER=postgres' -e 'POSTGRES_PASSWORD=postgres' docker.io/postgres:latest \
			|| echo "Failed to create and start the DB container."; \
	fi

create-network:
	if podman network ls --format "{{.Name}}" | grep "^filelister$$"; then \
		echo "Podman network 'filelister' already exists."; \
	else \
		echo "Podman network 'filelister' does not exist. Creating the network..."; \
		podman network create filelister; \
	fi
