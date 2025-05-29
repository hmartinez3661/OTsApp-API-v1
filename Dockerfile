
FROM eclipse-temurin:21-jdk AS build

# RUN ./mvnw.cmd clean package -DskipTests

WORKDIR /app

COPY target/OTsApp-API-v1-0.0.1.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]


# IMAGEN NODELO:
# FROM eclipse-temurin:21-jdk AS builder

# DEFINIR DIRECTORIO RAIZ DEL CONTENEDOR:
# WORKDIR /root

# COPIAR EL CODIGO DE LA APP a la ruta del proyecto
# COPY . .

# CONSTRUIR LA APP
# RUN ./mvnw clean package -DskipTests

# CORRE LA APP
# FROM eclipse-temurin:21-jre

# DEFINIR DIRECTORIO DE TRABAJO
# WORKDIR /root

# COPIA EL JAR FILE FROM THE BUILDER STAGE
# COPY --from=builder /root/target/*.jar app-name.jar

# PUERTO DONDE LA APP CORRERA
# EXPOSE 8095

# COMANDO PARA CORRER LA APP
# ENTRYPOINT ["java", "-jar", "/root/target/app-name.jar"]




# ------------------------------------------------
# ARG JAR_FILE=target/otsapp-api-v1-0.0.1.jar
# COPY ${JAR_FILE} otsapp-api-v1.jar
# EXPOSE 8095
# ENTRYPOINT ["java", "-jar", "otsapp-api-v1.jar"]