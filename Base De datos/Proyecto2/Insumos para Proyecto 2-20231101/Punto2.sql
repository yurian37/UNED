--Columnas a mostrar con un nombre de columna unci
SELECT
    P.Numeroproyecto AS NumeroProyecto,
    P.Descripcion AS DescripcionProyecto,
    P.Fechainicio AS FechaInicio,
    P.Fechaestimadafin AS FechaEstimadaFin,
    P.Estadoproyecto AS EstadoProyecto,
    P.CedulaLider AS CedulaLiderProyecto,
    CONCAT(E.Nombre, ' ', E.Apellido1, ' ', ISNull(E.Apellido2, '')) AS NombreLiderProyecto,
    D.Nombre AS DepartamentoLider,
    COUNT(DISTINCT T.Cedulaempleado) AS CantidadEmpleadosAsignados,
    COUNT(T.Numerotarea) AS TotalTareas
-- tablas a usar y las combinaciones que se deben seguir por las llaves foranes y primarias
FROM Proyecto AS P
LEFT JOIN Empleado AS E ON P.CedulaLider = E.Cedulaempleado
LEFT JOIN Departamento AS D ON E.IDdepartamento = D.IDdepartamento
LEFT JOIN Tarea AS T ON P.Numeroproyecto = T.Numeroproyecto
-- Agrupacion de terminos similares de no usar uno se cometera un error de tipo logico
GROUP BY
    P.Numeroproyecto,
    P.Descripcion,
    P.Fechainicio,
    P.Fechaestimadafin,
    P.Estadoproyecto,
    P.CedulaLider,
    E.Nombre,
    E.Apellido1,
    E.Apellido2,
    D.Nombre;