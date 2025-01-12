CREATE TABLE topicos (
    id BIGINT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(100) NOT NULL,
    fechaCreacion TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    curso VARCHAR(250) NOT NULL,
    id_usuario INT NOT NULL,

    PRIMARY KEY(id)
)