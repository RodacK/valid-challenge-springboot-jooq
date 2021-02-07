-- eliminar si existe el esquema
DROP SCHEMA IF EXISTS USERS CASCADE;

--crear el esquema
CREATE SCHEMA users AUTHORIZATION rodack;

--ubicarse en el esquema
SET SCHEMA users;

--crear la tabla con la que se va a trabajar
CREATE TABLE IF NOT EXISTS USER(
                         id INT auto_increment NOT NULL,
                         name VARCHAR(45) NOT NULL,
                         last_name VARCHAR(45) NOT NULL,
                         process BOOLEAN default false,
                         PRIMARY KEY (id));