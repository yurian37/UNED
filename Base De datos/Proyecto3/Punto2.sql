USE InnovateTech
GO

-- Crear la función
CREATE FUNCTION ObtenerLiderProyecto
    (@NumeroProyecto int)
RETURNS TABLE
AS
RETURN
(
    SELECT E.Cedulaempleado, E.Nombre, E.Apellido1
    FROM [dbo].[Proyecto] P
    INNER JOIN [dbo].[Empleado] E ON P.CedulaLider = E.Cedulaempleado
    WHERE P.Numeroproyecto = @NumeroProyecto
);