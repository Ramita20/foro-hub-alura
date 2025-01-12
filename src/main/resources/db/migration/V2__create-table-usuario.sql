CREATE TABLE usuarios(
    id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    correoElectronico VARCHAR(100) NOT NULL,
    contrasena VARCHAR(300) NOT NULL,

    PRIMARY KEY(id)
)