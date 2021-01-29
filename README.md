# DBNerd-2 & Microservices developer 1
## DBNerd-2
Crea una aplicación restful que procese toda tu combinación de File Creator (rest, webscrapping, importación de datos por fichero) a través de un microservicio con una base de datos documental.
## Microservices developer 1
Crea una aplicación restful que procese tu combinación de File Creator (rest, webscrapping, importación de datos por fichero) a través de un microservicio con una base de datos orientada a grafos o implementa tu proyecto para que se realice con Quarkus en lugar de Spring.
## Base de datos (MongoDB)
Se puede utilizar el archivo `mongo/docker-compose.yml` para iniciar un contenedor de MongoDB con Docker.

Para la configuración de este contenedor se utilizan dos variables de entorno:
- DBNERD_ROOT_PASSWORD
- DBNERD_ROOT_USERNAME

Estas variables de entorno se puede configurar con un archivo `mongo/.env`. El proyecto de Quarkus también utiliza variables de entorno para realizar la conexión con la base de datos (`application.properties`).