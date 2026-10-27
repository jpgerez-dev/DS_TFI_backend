-- Para la base en la nube (Aiven): se ejecuta sobre la base "defaultdb".
CREATE TABLE IF NOT EXISTS usuarios (
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    nombre    VARCHAR(100),
    apellido  VARCHAR(100),
    email     VARCHAR(150) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    telefono  VARCHAR(30),
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuarios_email (email)
) ENGINE=InnoDB;
