/*
Select Nombre,Fechaestimadafin
From Proyecto
*/
-- Modo para aumentar fecha 1 mes, es retroalimentada por lo que si se usa sin cuidado el aumento puede ser mucho mas
UPDATE Proyecto
SET Fechaestimadafin = DATEADD(MONTH, 1, Fechaestimadafin)

/*
Select Nombre,Fechaestimadafin
From Proyecto
*/
