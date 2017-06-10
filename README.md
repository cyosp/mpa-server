# mpa-server
My Personal Accounts application server

## Docker

### Building image

The following command allows to build the Docker image:
 * `sudo ./gradlew distDocker`

### Running

The following command allows to run the Docker image in a new container:
 * `sudo docker run -p 8080:8080 -i -t mpa-server:0.0.1-SNAPSHOT`