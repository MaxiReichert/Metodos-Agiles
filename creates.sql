-- primero ejecutar el create database luego ejecutar el resto de los creates
create database metodos_agiles;

create table titular(
tipo_doc VARCHAR(3) NOT NULL,
numero_doc INTEGER NOT NULL,
apellido VARCHAR(20) NOT NULL,
nombre VARCHAR(20) NOT NULL,
fecha_nac DATE NOT NULL,
direccion VARCHAR(50) NOT NULL,
tipo_lic_solicitada VARCHAR(1) NOT NULL,
grupo_sanguineo VARCHAR(2) NOT NULL,
factor VARCHAR(2) NOT NULL,
donante BOOLEAN NOT NULL,
PRIMARY KEY(numero_doc));

create table usuario(
numero_legajo INTEGER NOT NULL,
tipo_doc VARCHAR(3) NOT NULL,
numero_doc INTEGER NOT NULL,
apellido VARCHAR(20) NOT NULL,
nombre VARCHAR(20) NOT NULL,
fecha_nac DATE NOT NULL,
direccion VARCHAR(50) NOT NULL,
PRIMARY KEY(numero_legajo));

create table tramite(
id SERIAL NOT NULL,
fecha_reali DATE NOT NULL,
usuario INTEGER NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(usuario) REFERENCES usuario(numero_legajo));

create table licencia(
id SERIAL NOT NULL,
titular INTEGER NOT NULL,
tipo VARCHAR(1) NOT NULL,
fecha_otor DATE NOT NULL,
fecha_venc DATE NOT NULL,
costo INTEGER NOT NULL,
observaciones VARCHAR(200),
tramite INTEGER NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(titular) REFERENCES titular(numero_doc),
FOREIGN KEY(tramite) REFERENCES tramite(id));