CREATE ROLE vacunacion_user WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'vacunacion_user';
	
	
CREATE SCHEMA vacunacion
    AUTHORIZATION vacunacion_user;

COMMENT ON SCHEMA vacunacion
    IS 'esquema de vacunacion';
    
CREATE SEQUENCE vacunacion.sq_empleado_id
INCREMENT 1
START 2;

ALTER SEQUENCE vacunacion.sq_empleado_id
OWNER TO vacunacion_user;
    
CREATE SEQUENCE vacunacion.sq_usuario_id
    INCREMENT 1
    START 2;

ALTER SEQUENCE vacunacion.sq_usuario_id
    OWNER TO vacunacion_user;
    

CREATE SEQUENCE vacunacion.sq_vacuna_id
    INCREMENT 1
    START 1
    MINVALUE 1;

ALTER SEQUENCE vacunacion.sq_vacuna_id
    OWNER TO vacunacion_user;    
 
CREATE TABLE vacunacion.empleado
(
    id numeric NOT NULL,
    ci character varying(13) NOT NULL,
    nombres character varying(250),
    apellidos character varying(250),
    correo character varying(250),
    direccion character varying(500),
    fechanacimiento date,
    telfonomovil character varying(13),
    estadovacunado integer,
    numerodosis integer,
    estado integer,    
    insertadopor character varying(50),
    actualizadopor character varying(50),
    fechainserta timestamp with time zone,
    fechaactualiza timestamp with time zone,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS vacunacion.empleado
    OWNER to vacunacion_user;
    
    
CREATE TABLE vacunacion.vacuna
(
    id numeric NOT NULL,
    tipo character varying(250),
    numerodosis numeric(1),
    fecha date,
    estado numeric(1),
    insertadopor character varying(50),
    actualizadopor character varying(50),
    fechainserta timestamp with time zone,
    fechaactualiza timestamp with time zone,
    empleado_id numeric NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_empleado_vacuna FOREIGN KEY (empleado_id)
        REFERENCES vacunacion.empleado (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS vacunacion.vacuna
    OWNER to vacunacion_user;
    
    
CREATE TABLE vacunacion.usuario
(
    id numeric NOT NULL,
    usuario character varying(50) NOT NULL,
    contrasena character varying(500) NOT NULL,
    rol character varying(20),
    estado numeric,
    insertadopor character varying(50),
    actualizadopor character varying(50),
    fechainserta timestamp with time zone,
    fechaactualiza timestamp with time zone,
    empleado_id numeric NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_empleado_usuario FOREIGN KEY (empleado_id)
        REFERENCES vacunacion.empleado (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS vacunacion.usuario
    OWNER to vacunacion_user;
    
INSERT INTO vacunacion.empleado(
	id, ci, nombres, apellidos, correo, estado, insertadopor, fechainserta)
	VALUES (1, '01038979555', 'klever vinicio', 'lascano sumbana', 'kleverlascano@gmail.com', 1, 'admin', current_timestamp);
	
	
INSERT INTO vacunacion.usuario(
	id, usuario, contrasena, rol, estado, insertadopor, fechainserta, empleado_id)
	VALUES (1, '0103897955', '$2a$10$x.9FexRh15dPE0jXd8yNWeR5wSd.8hZ/Urst4RNKiBdtAO0N0zl42', 'admin', 1, 'admin', current_timestamp, 1);	    
    
 
