ALTER TABLE topicos
ADD COLUMN temp_id BIGSERIAL NOT NULL;

UPDATE topicos
SET temp_id = id;

ALTER TABLE topicos
DROP COLUMN id;

ALTER TABLE topicos
RENAME COLUMN temp_id TO id;

ALTER TABLE topicos
ADD PRIMARY KEY (id);
