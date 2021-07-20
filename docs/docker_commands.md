# Docker Commands to use in this application

## Build

mvn spring-boot:build-image -Dspring-boot.build-image.imageName=yilmazchef/fooda-backend-product

## Run
We will first need to start MYSQL server to provide a database connection.

To create a network bridge on localhost: 
```
docker network create productnet
```

To run a MYSQL instance and connect it to the network.
```
docker run -d -p 6033:3306 --name fooda-database --network productnet --env MYSQL_ROOT_PASSWORD=toor --env MYSQL_DATABASE=productdb --env MYSQL_USER=fooda --env MYSQL_PASSWORD=fooda mysql:latest
```

if you have user access issues to mysql, then open command line of the mysql container and run these commands:
```
show databases;
use productdb;
create user 'fooda'@'localhost' identified by 'fooda';
grant all on productdb.* to 'fooda'@'localhost';
flush privileges;
```

To run Spring Boot application:

```
docker run -d -p 8080:8080 --name product-api --network productnet --env MYSQL_HOST=fooda-database yilmazchef/fooda-backend-product:latest
```

## Pull from DockerHub
You must be logged in to docker to pull the image to the DockerHub. (docker login)

```
docker pull yilmazchef/fooda-backend-product
```

## Push to DockerHub
You must be logged in to docker to push the image to the DockerHub. (docker login)

```
docker push yilmazchef/fooda-backend-product
```

