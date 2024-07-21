# Uniquely Named File Lister Web App Demo

This web app is designed to recursively list uniquely named files and their occurrences within a specified directory, log query history to a PostgreSQL database, utilize Swagger UI for endpoint testing. It runs in two instances, all containerized via Podman, including the database.

## Features

- **Recursively List Unique Files**: Lists the names and occurrences of uniquely named files within a specified directory and with a specified file extension.
- **Query History Logging**: Logs all query history to a PostgreSQL database, capturing the details of each query.
- **Swagger UI Integration**: Provides a default Swagger UI for endpoint testing.
- **Containerized Deployment**: Runs in 3 containers: two instances and a PostgreSQL database.

## Endpoints

- **Swagger UI**: Access the API documentation at the root endpoint.
  - URL: `/`

- **Javadoc**: Access the Javadoc of the application.
  - URL: `/doc`

- **Get Unique Files**: Retrieves uniquely named files and their occurrences based on specified parameters.
  - Endpoint: `/get_unique_files`
  - Parameters:
    - `dir`: Optional parameter specifying the directory path (defaults to `/tmp` if not provided).
    - `ext`: Optional parameter specifying the file extension (defaults to `.txt` if not provided).
  - Example: `/get_unique_files?dir=/path/to/directory&ext=.pdf`
  - Returns: A map of `[filename, occurrences]` for uniquely named files within the specified directory and extension.

- **Query History**: Retrieves a list of query history records.
  - Endpoint: `/history`
  - Returns: A list of query history records containing `[epoch, Linux username, queried directory path, queried file extension]`.

## Build and Run

1. Use the following commands to build and run the application:
   ```bash
   make build
   make run
   ```
2. Access the application in your web browser:
   - Swagger UI: http://localhost:8081
   - Javadoc: http://localhost:8081/doc