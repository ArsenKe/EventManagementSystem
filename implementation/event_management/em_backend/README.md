## RabbitMQ server login:
- username: guest
- password: guest

## Run docker compose file form root directory
- $ `docker compose up`
## adding ms instractions:
- to add your microservice implementation, create a new folder in the `em_backend` folder. The folder name should be the name of your microservice.
- copy Docker file form other microservices and add a section in root docker-compose.yaml file for your microservice.
- make sure you use the same naming convention as in the `em_backend` folder
- before run docker-compose.yaml make sure every microservice has .jar file in target folder.

## how to create .v