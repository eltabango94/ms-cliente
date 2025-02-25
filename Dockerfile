# Imagen base con JDK 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por Maven al contenedor
COPY target/ms-cliente-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que correrá la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
