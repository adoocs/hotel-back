# Hotel Van Gogh - Backend

Este es el backend para el sistema de gestión de reservas del Hotel Van Gogh, construido con Spring Boot.

## Tecnologías

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security** (autenticación JWT)
- **Spring Data JPA**
- **PostgreSQL**
- **MapStruct**
- **Lombok**
- **Springdoc OpenAPI** (Swagger UI)

## Instalación

1. Clona el repositorio:

    ```bash
    git clone https://github.com/adoocs/hotel-back.git
    cd hotel-back
    ```

2. Configura la base de datos en `application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

3. Ejecuta el proyecto:

    ```bash
    mvn spring-boot:run
    ```

4. Accede a la aplicación en [http://localhost:8080](http://localhost:8080).

## Entidades

- Empleado
- Habitacion
- Huesped
- Pago
- Reserva
- Role
- Usuario
- TipoHabitacion

## Autenticación

El sistema usa **JWT** para autenticar usuarios. Tras autenticarse en `/auth/login`, se devuelve un token que debe enviarse en el encabezado `Authorization` como:

```text
Bearer <token>
