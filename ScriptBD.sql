CREATE DATABASE IF NOT EXISTS alumnos;

USE alumnos;

CREATE TABLE IF NOT EXISTS alumnos (
idAlumno int not null auto_increment,
nombres varchar(30) not null,
apellidoPaterno varchar(20) not null,
apellidoMaterno varchar(20) default null,
eliminado bit(1) not null default b'1',
activo bit(1) not null default b'0',
PRIMARY KEY (idAlumno)
);