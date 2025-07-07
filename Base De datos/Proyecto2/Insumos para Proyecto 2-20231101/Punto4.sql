--Lista de columnas a mostrar
SELECT
    E.Cedulaempleado AS CedulaEmpleado,
    E.Nombre AS NombreEmpleado,
    E.Apellido1 AS ApellidoEmpleado,
	--Calculo de la cantidad de horas trabajadas
    COALESCE(SUM(R.Horasdedicadas), 0) AS CantidadHorasTrabajadas,
	--Transformacion de las horas a planilla y ingreso de la unidad $
    Concat('$', COALESCE(SUM(R.Horasdedicadas), 0) * 50) AS SalarioCobrar
--Tablas a usar
FROM Empleado AS E
LEFT JOIN RegistroTiempo AS R ON E.Cedulaempleado = R.CedulaEmpleado
--Agrupamiento para utilizacion de la suma.
GROUP BY
    E.Cedulaempleado,
    E.Nombre,
    E.Apellido1