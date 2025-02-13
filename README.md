# GESTOR DE TARAS 
- El desarrollo se hizo usando Spring boot 3.4.2 y Java 17

## Modulos
- Tareas
- Auth


## Conexion sin Docker
- En el archivo application.properties configura tu username y password de tu BD
- Para levantar el proyecto crear bd gestor_tareas en Mysql
    `CREATE DATABASE gestor_tareas`


## Conexion con Docker
- configurar tu username y password en el archivo "application-docker.properties"
- ejecutar el siguiente comando `docker-compose up --build`




#Nota : 
- El servicio se hizo usando swagger pero por motivos de compatibilidad de librería esta cayendo en error
- Si levanta pero no muestra los endpoints
- Es por eso que se uso postman 

- POST - http://localhost:8080/api/auth/register
`
{
    "id": 0,
    "nombre": "Wilson",
    "primer_apellido": "Vasquez",
    "segundo_apellido": "Coronado",
    "email": "corvas19@gmail.com",
    "password": "wilson12345",
    "telefono": "960235562",
    "fechaNac": "1990-10-18T10:00:00Z"
}
`

- POST - http://localhost:8080/api/auth/login
`
{
  "email": "corvas19@gmail.com",
  "password": "wilson12345"
}
`
- POST - http://localhost:8080/api/tareas
`
{
  "id": 0,
  "createDate": "2025-02-13T02:50:57.096Z",
  "updateDate": "2025-02-13T02:50:57.096Z",
  "titulo": "Mi segunda tarea",
  "descripcion": "Mi segunda tara de prueba",
  "estado": "POR_HACER",
  "user_id": 2
}
`


