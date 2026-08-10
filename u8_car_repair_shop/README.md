# Sistema de gestión de taller mecánico

Esta aplicación es un backend REST para administrar un taller de servicio automotriz.

El sistema permite:
- Registrar clientes con nombre, cédula, teléfono y correo.
- Registrar vehículos asociados a clientes: placa, marca, modelo, año.
- Registrar órdenes de servicio: descripción, costo, fecha de entrada, estado y vehículo.
- Consultar clientes, vehículos y órdenes.
- Validaciones de datos de entrada y manejo global de errores.

## Contexto del cliente

El cliente tiene un taller en Zipaquirá y hoy lleva el control en cuaderno. Necesita digitalizar:
- Clientes con cédula y teléfono.
- Vehículos con placa, marca, modelo y año.
- Órdenes de servicio con lo que se hará al carro, fecha de entrada, estado y costo.
- Un cliente puede traer varios carros.
- Un carro puede entrar al taller muchas veces.

## Tecnologías

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Springdoc OpenAPI

## Cómo correr la aplicación

### Requisitos

- Java 21
- Maven
- PostgreSQL en `localhost:5432`

### Configuración

Actualiza `src/main/resources/application.properties` con los datos de tu base de datos:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/car_repair_shop
spring.datasource.username=admin_user
spring.datasource.password=
```

### Ejecutar

Desde la carpeta del proyecto:

```bash
./mvnw spring-boot:run
```

O compilar y ejecutar el JAR:

```bash
./mvnw clean package
java -jar target/u8_car_repair_shop-0.0.1-SNAPSHOT.jar
```

## Endpoints

Base URL: `http://localhost:8080/api`

### Clientes

- `POST /api/customers`
  - Crear cliente.
  - Body JSON: `name`, `documentNumber`, `cell`, `email`.
- `GET /api/customers`
  - Listar todos los clientes.
- `GET /api/customers/{id}`
  - Buscar cliente por id.
- `PUT /api/customers/{id}`
  - Actualizar cliente.
- `DELETE /api/customers/{id}`
  - Eliminar cliente.

### Vehículos

- `POST /api/vehicles`
  - Crear vehículo.
  - Body JSON: `licensePlate`, `make`, `model`, `year`, `customerId`.
- `GET /api/vehicles`
  - Listar todos los vehículos.
- `GET /api/vehicles/{id}`
  - Buscar vehículo por id.
- `GET /api/vehicles/customer/{customerId}`
  - Listar vehículos de un cliente.
- `DELETE /api/vehicles/{id}`
  - Eliminar vehículo.

### Órdenes de servicio

- `POST /api/orders`
  - Crear orden.
  - Body JSON: `description`, `cost`, `vehicleId`.
- `GET /api/orders`
  - Listar todas las órdenes.
- `GET /api/orders/{id}`
  - Buscar orden por id.
- `PATCH /api/orders/{id}/status?orderStatus={estado}`
  - Actualizar estado de la orden.
  - `orderStatus` puede ser `RECIBIDO`, `EN_PROCESO`, `TERMINADO`, `ENTREGADO`, etc.
- `GET /api/orders/status/{orderStatus}`
  - Listar órdenes por estado.

## Validaciones y errores

- Se validan campos obligatorios, formatos y rangos.
- Cuando ocurre un error de negocio o validación, la API devuelve respuesta JSON con código HTTP adecuado.
- Ejemplos de respuestas:
  - 400 Bad Request para datos inválidos.
  - 404 Not Found para recursos no encontrados.
  - 409 Conflict para duplicados.
  - 403 Forbidden para operaciones no permitidas.

## UI de documentación

Si la aplicación está corriendo, la documentación OpenAPI Swagger está disponible en:

- `http://localhost:8080/swagger-ui.html`

## Notas

- `spring.jpa.hibernate.ddl-auto=update` actualiza automáticamente el esquema de la base de datos.
- Ajusta `server.port` en `application.properties` si necesitas otro puerto.
