-- =====================================================================
-- JFA Distribuciones (DS2023-TFI-G17) - Esquema de base de datos
-- Motor: MySQL 8.x / MariaDB
-- Uso:   mysql -u root -p < schema.sql
-- =====================================================================

CREATE DATABASE IF NOT EXISTS cursojava
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cursojava;

-- Tabla mapeada por la entidad Usuario (Models/Usuario.java)
CREATE TABLE IF NOT EXISTS usuarios (
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    nombre    VARCHAR(100),
    apellido  VARCHAR(100),
    email     VARCHAR(150) NOT NULL,
    password  VARCHAR(255) NOT NULL,   -- hash Argon2, nunca la contraseña en claro
    telefono  VARCHAR(30),
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuarios_email (email)
) ENGINE=InnoDB;
