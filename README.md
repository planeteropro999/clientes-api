# clientes-api

API REST desarrollada con Spring Boot y Oracle Database para la gestión y control de clientes, validaciones de negocio y pruebas unitarias, además de integración

### Configuración y Ejecución
### 1. Prerrequisitos
* Tener instalado **Java 17** o superior.
* Tener instalado **Maven**.
* Acceso a una instancia de **Oracle Database**.

### 2. Configuración de la Base de Datos
Abre el archivo `src/main/resources/application.properties` y ajusta las credenciales de conexión a tu base de datos Oracle:

### 3. src/main/resources/application.properties
properties
server.port=8080
spring.datasource.url=jdbc:oracle:thin:@192.168.37.147:1521/AXISP
spring.datasource.username=porta
spring.datasource.password=a1x7i8s9

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.show-sql=true

Api/Docs: http://localhost:8080/v3/api-docs
Swagger: http://localhost:8080/swagger-ui/index.html#/

### Ejecucion de las pruebas
Ejecutar ambas de las pruebas: mvn clean test
### Ejecutar con Docker
Ejecutar el siguiente comando en la terminal
docker build -t clientes-api .
Una vez construida la imagen, puedes levantar el contenedor mapeando el puerto 8080:
docker run -p 8080:8080 clientes-api
