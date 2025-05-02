# Usa una imagen base con OpenJDK
FROM eclipse-temurin:21-jdk-alpine

# Crea un directorio de trabajo en el contenedor
WORKDIR /app

# Copia la carpeta classes con el Main.class al contenedor
COPY target/classes /app/classes

# Ejecuta el archivo Main.class
CMD ["java", "-cp", "/app/classes", "mx.itson.testcase.ui.Main"]
