CREATE DATABASE agenda_mvc;

USE agenda_mvc;

CREATE TABLE contactos( 
    id_contacto integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    telefono int(10) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO contactos (nombre, email) VALUES 
('Dejah Thoris','dejah@barson.ma', 7757054446), 
('Jhon Carter','jhon@barson.ma',7757472412),
('Carthoris Carter','carthoris@barson.ma',55772212);


SELECT * FROM contactos;

CREATE USER 'MarioNL'@'localhost' IDENTIFIED BY 'MVC122018';
GRANT ALL PRIVILEGES ON agenda_mvc.* TO 'MarioNL'@'localhost';
FLUSH PRIVILEGES;
