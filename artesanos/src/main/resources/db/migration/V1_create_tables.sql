CREATE TABLE artesano (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    ubicacion VARCHAR(255),
    tipo_artesania VARCHAR(255),
    descripcion TEXT
);

CREATE TABLE producto (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio NUMERIC(10,2),
    cantidad INTEGER,
    artesano_id BIGINT REFERENCES artesano(id)
);
