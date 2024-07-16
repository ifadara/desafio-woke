CREATE TABLE usuario (
  id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   telephone VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   birth_date date NOT NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE usuario ADD CONSTRAINT uc_usuario_email UNIQUE (email);