# w2m-heroes
App Backend API de Heroes

# Tecnologías y versiones:
- Java 17
- Maven 3.8.7
- Spring Boot 2.7.3
- DB H2
- Junit 5
- Mockito 2.23.4
- Swagger 3.0.2
- Docker 20.10.12
- Spring Security

# Docker - comandos útiles:
- Build:
  - docker build -t "w2m-heroes-docker" .
- Run:
  - docker run --name w2m-heroes-docker -8080:8080 w2m-heroes-docker:latest

# Swagger: 
 - http://localhost:8080/swagger-ui/index.html
  
# TODO: 
 - Desplegar en EC2
