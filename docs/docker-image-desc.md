# Short Description

The Value Set Service (VSS) is used as Value Set Management System in Consent2Share. 

# Full Description

# Supported Source Code Tags and Current `Dockerfile` Link

[`0.5.0 (latest)`](https://github.com/bhits-dev/vss/releases/tag/0.5.0), [`0.4.0`](https://github.com/bhits-dev/vss/releases/tag/0.4.0), [`0.3.0`](https://github.com/bhits-dev/vss/releases/tag/0.2.0), [`0.2.0`](https://github.com/bhits-dev/vss/releases/tag/0.2.0)


[`Current Dockerfile`](../vss/src/main/docker/Dockerfile)

For more information about this image, the source code, and its history, please see the [GitHub repository](https://github.com/bhits-dev/vss).

# What is VSS?

The Value Set Service (VSS) is responsible for Managing sensitive categories, code systems, value sets and coded concepts. The VSS also provides a RESTful service to map coded concepts to respective sensitive categories and provide the list of all sensitive categories available in the system.

The value sets, downloaded from [VSAC](https://vsac.nlm.nih.gov/), are a set of concept codes mapped to various *sensitive categories* that are intended for use by organizations exchanging personally identifiable protected health information to perform data segmentation based on the patient’s privacy preferences in his or her consent.

For more information and related downloads for Consent2Share, please visit [Consent2Share](https://bhits-dev.github.io/consent2share/).

# How to Use This Image

## Start a VSS Instance

Be sure to familiarize yourself with the repository's [README.md](https://github.com/bhits-dev/vss) file before starting the instance.

`docker run  --name vss -d bhitsdev/vss:latest <additional program arguments>`

*NOTE: In order for this project to fully function as a microservice in the Consent2Share application, it is required to setup the dependency microservices and the support level infrastructure. Please refer to the Consent2Share Deployment Guide in the corresponding Consent2Share release (see [Consent2Share Releases Page](https://github.com/bhits-dev/consent2share/releases)) for instructions to setup the Consent2Share infrastructure.*
 
## Configure

The Spring profiles `application-default` and `docker` are activated by default when building images.

This project can run with the default configuration which is from three places: `bootstrap.yml`, `application.yml`, and the data which the [`Configuration Server`](https://github.com/bhits-dev/config-server) reads from the `Configuration Data Git Repository`. Both `bootstrap.yml` and `application.yml` files are located in the class path of the running application.

We **recommend** overriding the configuration as needed in the `Configuration Data Git Repository`, which is used by the `Configuration Server`.

Also, [Spring Boot](https://projects.spring.io/spring-boot/) supports other ways to override the default configuration to configure the project for a certain deployment environment. 

The following is an example to override the default database password:

`docker run -d bhitsdev/vss:latest --spring.datasource.password=strongpassword`

## Environment Variables

When you start the VSS image, you can edit the configuration of the VSS instance by passing one or more environment variables on the command line. 

### JAR_FILE

This environment variable is used to setup which jar file will run. You need to mount the jar file to the root of container.

`docker run --name vss -e JAR_FILE="vss-latest.jar" -v "/path/on/dockerhost/vss-latest.jar:/vss-latest.jar" -d bhitsdev/vss:latest`

### JAVA_OPTS 

This environment variable is used to setup a JVM argument, such as memory configuration.

`docker run --name vss -e "JAVA_OPTS=-Xms512m -Xmx700m -Xss1m" -d bhitsdev/vss:latest`

### DEFAULT_PROGRAM_ARGS 

This environment variable is used to setup an application argument. The default value is: "--spring.profiles.active=application-default, docker".

`docker run --name vss -e DEFAULT_PROGRAM_ARGS="--spring.profiles.active=application-default,ssl,docker" -d bhitsdev/vss:latest`

# Supported Docker Versions

This image is officially supported on Docker version 1.13.0.

Support for older versions (down to 1.6) is provided on a best-effort basis.

Please see the [Docker installation documentation](https://docs.docker.com/engine/installation/) for details on how to upgrade your Docker daemon.

# License

View [license](https://github.com/bhits-dev/vss/blob/master/LICENSE) information for the software contained in this image.

# User Feedback

## Documentation
 
Documentation for this image is stored in the [bhitsdev/vss](https://github.com/bhits-dev/vss) GitHub repository. Be sure to familiarize yourself with the repository's README.md file before attempting a pull request.

## Issues

If you have any problems with or questions about this image, please contact us through a [GitHub issue](https://github.com/bhits-dev/vss/issues).

