# Usar una imagen con Maven y Java 17
FROM maven:3.9-eclipse-temurin-17

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos del proyecto
COPY . .

# Compilar y empaquetar la aplicación
RUN mvn package -DskipTests

# Exponer el puerto
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "target/tareas-api-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
