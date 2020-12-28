CREATE DATABASE myblogbd;

CREATE TABLE Usuario
(
IdUsuario int primary key not null,
Nombre varchar (45) not null,
Apellido varchar (45) not null,
Edad int not null, 
Email varchar (45) not null,
Ciudad varchar (45) not null,
Provincia varchar (45) not null,
Pais varchar (50)
); 
