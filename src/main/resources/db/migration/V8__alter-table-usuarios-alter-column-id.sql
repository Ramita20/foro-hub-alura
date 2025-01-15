ALTER TABLE usuarios
ADD COLUMN temp_id BIGSERIAL NOT NULL;

UPDATE usuarios
SET temp_id = id;

ALTER TABLE usuarios
DROP COLUMN id;

ALTER TABLE usuarios
RENAME COLUMN temp_id TO id;

ALTER TABLE usuarios
ADD PRIMARY KEY (id);
