-- Database: CursosActualizacion

-- DROP DATABASE "CursosActualizacion";

CREATE DATABASE "CursosActualizacion"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	

CREATE TABLE PROFESOR(
	Id_profesor int primary key not null generated always as identity
	(start with 1 increment by 1),
	Nombre varchar(20) not null,
	Ap_paterno varchar(15) not null,
	Ap_materno varchar(15) not null,
	Cedula int not null,
	Nivel_max varchar(15) not null,
	Carrera varchar(40) not null,
	Area varchar(20) not null,
	correo varchar(40) not null,
	constraint ck_cedula check (Cedula > 0)
);

CREATE TABLE CURSO(
	Id_curso int primary key not null generated always as identity
	(start with 1 increment by 1),
	NombreCurso varchar(35) not null,
	FechaInicio date not null,
	FechaFin date not null,
	Id_profRespon int not null,
	Aula varchar(2) not null,
	HoraInicio time not null,
	HoraFin time not null,
	estatus boolean default false,
	Constraint ck_idprofres check (Id_profRespon > 0),
	Constraint fk_idProdRes foreign key (Id_profRespon)
	references Profesor(id_profesor)
);

CREATE TABLE Profesor_Curso(
	Id_profesor int not null,
	Id_curso int not null,
	Constraint fk_idProfr foreign key (Id_profesor)
	references Profesor(id_profesor),
	Constraint fk_idCurso foreign key (Id_curso)
	references Curso(id_curso)
);

insert into profesor ("nombre", "ap_paterno", "ap_materno", "cedula", 
					 "nivel_max", "carrera", "area", "correo") 
					 values ('Juan', 'Perez', 'Hernandez', 9876544, 
							 'Licenciatura', 'Ing. Sistemas', 'Computacion', 
							 'erikjr_12@hotmail.com');
insert into profesor ("nombre", "ap_paterno", "ap_materno", "cedula", 
					 "nivel_max", "carrera", "area", "correo") 
					 values ('Anayansi', 'Hernandez', 'Abrego', 1763653, 
							 'Maestria', 'Lic. en computacion', 'Computacion',
							'erikjr_12@hotmail.com');

insert into curso ("nombrecurso", "fechainicio", "fechafin", "id_profrespon",
				  "aula", "horainicio", "horafin") values (
				  'Induccion a las nuevas tecnologias', '2019/07/01', '2019/08/02',
				  1, 'A3', '08:00:00', '11:00:00');
insert into curso ("nombrecurso", "fechainicio", "fechafin", "id_profrespon",
				  "aula", "horainicio", "horafin") values (
				  'Desarrrollo Laboral', '2019/07/01', '2019/08/02',
				  2, 'I9', '09:30:00', '11:30:00');
				  
select * from curso;
select * from profesor;

insert into profesor_curso ("id_profesor", "id_curso") values (2, 2);
select * from profesor_curso;
 
select count(*) from profesor_curso where id_curso = 1;

update curso set estatus = false where id_curso = 2;

delete from profesor_curso where id_profesor = 6;
select * from profesor_curso;
