
DECLARE @NumeroProyectoBuscado int = 6;

SELECT *
FROM dbo.ObtenerLiderProyecto(@NumeroProyectoBuscado);