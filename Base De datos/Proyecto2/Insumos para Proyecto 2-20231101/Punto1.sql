-- Borrar todos los elementos de las tablas


Delete from HistorialCambios;
Delete from RegistroTiempo;
Delete from Tarea;
Delete from Proyecto;
Delete from Empleado;
Delete from Cargo;
Delete from Departamento;

--Reinicio Autoincremento
DBCC Checkident('HistorialCambios',RESEED,0);
DBCC Checkident('RegistroTiempo',RESEED,0); 
DBCC Checkident('Tarea',RESEED,0);
DBCC Checkident('Proyecto',RESEED,0); 
DBCC Checkident('Cargo',RESEED,0); 
DBCC Checkident('Departamento',RESEED,0); 

--Agrega los elementos a la tabla Cargo
Insert into Cargo (Nombrecargo, Descripcion)
Values
	('Analista de datos',       'Interpretación de los datos y preparación de visualizaciones'),
	('Desarrollador',           'Programador de software'),
	('Administrador BD',        'Gestor de las bases de datos de la empresa'),
	('Gestor de calidad',       'Realiza QA en los proyectos de desarrollo y bases de datos'),
	('Administrador proyecto',  'Gestión de tiempo, costos y alcance'),
	('Gerente Financiero',      'Se encarga de toda la parte de finanzas de la empresa'),
	('Gerente Recursos Humanos','Se encarga de lo relacionado con el talento humano de la empresa');

-- Agregar los elementos a la tabla de Departamento
Insert into Departamento (Nombre, Descripcion)
Values
	('Desarrollo de Software',    'Programación de aplicaciones'),
	('Soporte técnico',           'Soporte a los usuarios internos y externos de la empresa'),
	('Investigación y Desarrollo','Búsqueda de soluciones y nuevas tecnologías'),
	('Recursos administrativos',  'Gestión administrativa de la empresa');

-- Agregar los elementos a la tabla de Empleados	
Insert into Empleado (Cedulaempleado, Codigoempleado, Nombre, Apellido1, Apellido2, IDcargo, IDdepartamento, Fechacontratacion)
Values
	(123456789, 1, 'José',    'Sánchez', 'Rojas',     2, 1, '2020-10-10'),
	(987654321, 2, 'Alonso',  'Castro',  'Vindas',    5, 3, '2018-07-05'),
	(147258369, 3, 'Roberto', 'Salas',   'López',     3, 1, '2022-02-25'),
	(369258147, 4, 'Andrea',  'Rodas',   'Rodríguez', 1, 3, '2019-11-19'),
	(258963741, 5, 'Luisa',   'Calvo',   'Segura',    4, 1, '2020-03-13'),
	(789654123, 6, 'Juan',    'Pérez',   'Linares',   7, 4, '2017-04-15');

-- Agregar los elementos a la tabla Proyectos
Insert into Proyecto (Nombre, Descripcion, Fechainicio, Fechaestimadafin, Estadoproyecto, CedulaLider)
Values
	('Migración BD SportOne',       'Migrar la base de datos a nueva versión de Oracle',     '2023-11-25', '2024-05-24', 'Sin iniciar', 147258369),
	('Actualización sistema pagos', 'Se debe actualizar con nuevos salarios globales',       '2023-06-10', '2023-11-09', 'En proceso',  987654321),
	('Software gimnasio',           'Desarrollar el software para el Gimnasio MultiSpa',     '2023-02-01', '2023-12-15', 'En proceso',  123456789),
	('Reglamento teletrabajo',      'Actualizar el reglamento de Teletrabajo de la empresa', '2023-06-01', '2023-08-08', 'Finalizado',  789654123),
	('Sitio Web Uber CR',           'Nuevo sitio web para la empresa Uber CR',               '2023-08-15', '2023-11-15', 'En proceso',  987654321),
	('Software de ventas',          'Desarrollar el software de ventas para Grupo Kativo',   '2023-03-18', '2024-03-18', 'Cancelada',   258963741);

--Agregar los elementos a la tabla Tarea
Insert into Tarea (Descripcion, FechaInicio, Fechaestimadafin, Estadotarea, Numeroproyecto, Cedulaempleado)
Values
	('Revisar reglamento anterior',                '2023-06-03', '2023-06-18', 'Finalizado',  4, 789654123),
	('Levantar requerimientos',                    '2023-03-02', '2023-06-02', 'Finalizado',  3, 987654321),
	('Crear los casos de uso',                     '2023-06-06', '2023-07-06', 'Finalizado',  3, 123456789),
	('Comprar el hosting y dominio',               '2023-10-20', '2023-10-30', 'En Proceso',  5, 987654321),
	('Respaldar la base de datos',                 '2023-11-27', '2023-11-30', 'Sin Iniciar', 1, 147258369),
	('Instalar nuevo motor de BD',                 '2023-12-01', '2023-12-15', 'Sin Iniciar', 1, 147258369),
	('Ingresar tabla de nuevos salarios globales', '2023-10-25', '2023-11-08', 'En Proceso',  2, 123456789),
	('Levantar requerimientos',                    '2023-03-20', '2023-06-20', 'Cancelado',  6, 258963741);

-- Agregar los elementos a la tabla HistorialCambios
Insert into	HistorialCambios (Fechacambio, Descripcioncambio, Numerotarea)
Values
	('2023-04-20', 'Se cancela tarea',                                                       8),
	('2023-02-15', 'Se aplaza 15 días el inicio del levantamiento de requerimientos',        2),
	('2023-11-26', 'Se cambia el lugar donde se almacenará el respaldo de la base de datos', 5),
	('2023-10-23', 'Se solicita última versión de documento con tabla de pagos globales',    7),
	('2023-07-01', 'Se incluyen nuevos casos de uso no contemplados',                        3);

-- Agregar los elementos al RegistroTiempo
Insert into RegistroTiempo (Horasdedicadas, Numerotarea, CedulaEmpleado)
Values
	(6, 8, 258963741),
	(4, 1, 789654123),
	(8, 2, 987654321),
	(6, 2, 987654321),
	(5, 8, 258963741),
	(4, 7, 123456789),
	(5, 2, 987654321),
	(8, 3, 123456789),
	(7, 4, 789654123),
	(3, 3, 123456789);

/*
Select *
From Proyecto

Select *
From Empleado

Select *
From Cargo

Select *
From Departamento

Select *
From Tarea

Select *
From HistorialCambios

Select *
From RegistroTiempo
*/


