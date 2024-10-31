-- Crear un usuario con estos datos

CREATE USER my_user WITH PASSWORD '134679';

-- Conceder permisos al usuario
GRANT ALL PRIVILEGES ON DATABASE prueba_tecnica TO my_user;

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS prueba_tecnica;

CREATE SCHEMA IF NOT EXISTS "MI_ESQUEMA";

-- Creación de la tabla Persona
CREATE TABLE persona (
    persona_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20),
    direccion VARCHAR(100),
    telefono VARCHAR(15)
);

-- Creación de la tabla Cliente, que tiene una relación con Persona
CREATE TABLE cliente (
    cliente_id SERIAL PRIMARY KEY,
    contrasena VARCHAR(20) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    persona_id INT NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES Persona(persona_id) ON DELETE CASCADE
);

-- Datos de prueba para Persona
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES 
    ('Steven Romero', 'Masculino', 30, '1074187602', 'Calle Principal 123', '3123942083'),
    ('Maria Garcia', 'Femenino', 25, '1054267801', 'Avenida 2 Nro 34', '3145942075');

-- Datos de prueba para Cliente
INSERT INTO cliente (contrasena, estado, persona_id)
VALUES 
    ('pass123', TRUE, 6),
    ('securePass', TRUE, 2);

   
 CREATE TABLE cuenta (
    cuenta_id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo_inicial DECIMAL(15, 2) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    cliente_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id) ON DELETE CASCADE
);

-- Creación de la tabla Movimientos, que tiene una relación con Cuenta
CREATE TABLE movimientos (
    movimiento_id SERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_movimiento VARCHAR(20) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    cuenta_id INT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES Cuenta(cuenta_id) ON DELETE CASCADE
);