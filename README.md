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
 
It's also possible to map a folder from host to a new running container:
 * `sudo docker run -v /home/filer/homebank:/repository/xml -p 8080:8080 -i -t mpa-server:0.0.1-SNAPSHOT`

Thus [HomeBank](http://homebank.free.fr) data file can be stored on host and used by [mpa-server](https://github.com/cyosp/mpa-server).

### Debug

Last, this command line can be used to access to a running container in interactive mode:
 * `sudo docker exec -i -t <mpa-server running container name> /bin/sh`