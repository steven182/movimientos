
# Microservicio Cuenta-Movimientos

Este microservicio gestiona las entidades **Cuenta** y **Movimientos**. Proporciona APIs para manejar cuentas y registrar movimientos financieros. Recibe eventos de creación y modificación de cuenta desde el microservicio de `cuenta-Persona` a través de RabbitMQ.

## Requisitos

- **Java 17** o superior
- **Spring Boot 3.x**
- **PostgreSQL**
- **RabbitMQ** (para comunicación asíncrona)

## Configuración

### Base de Datos
```markdown
Se adjunta script para base de datos en (src/main/resources)
```
Este microservicio utiliza PostgreSQL. Configura las credenciales en el archivo `application.properties` o `application.yml`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mi_basedatos
spring.datasource.username=my_user
spring.datasource.password=134679
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
### RabbitMQ
Para habilitar la comunicación de eventos entre microservicios, configura RabbitMQ
```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```
### Comunicación de Eventos
Este microservicio produce eventos en RabbitMQ cada vez que se reglistra un movimieento.

### cuenta API Cuentas - Se adjunta coleccion postman (src/main/resources)
```properties
POST /cuenta/save-cuenta: Crear una nueva cuenta.
GET /cuenta/get-cuentas: Obtener todos las cuenta.
GET /cuenta/reportes/{cuentaId}: Reporte para obtener movimientos.
PUT /cuenta/update-cuenta/{cuentaId}: Actualizar una cuenta por ID.
DELETE /cuenta/delete-cuenta/{cuentaId}: Eliminar un cuenta por ID.
```

### cuenta API Movimientos - Se adjunta coleccion postman (src/main/resources)
```properties
POST /movimientos/save-movimiento: Crear un nuevo movimiento.
GET /movimientos/get-movimientos: Obtener todos los movimiento.
PUT /movimientos/update-movimiento/{movimientoId}: Actualizar un movimiento por ID.
DELETE /movimientos/delete-movimiento/{movimientoId}: Eliminar un movimiento por ID.
```
