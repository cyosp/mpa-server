# mpa-server

My Personal Accounts server is a web application which allows to read and modify a persistent file of a personal accounting software.

Main aim is to support [HomeBank](http://homebank.free.fr) in persistence version 1.2.

## Docker

### Building image

The following command allows to build the Docker image:
 * `sudo ./gradlew distDocker`

### Running

The following command allows to run the Docker image in a new container:
 * `sudo docker run -p 8080:8080 -i -t mpa-server:0.0.1-SNAPSHOT`