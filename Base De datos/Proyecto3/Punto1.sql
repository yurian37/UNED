USE InnovateTech
GO

-- Crear el procedimiento almacenado
CREATE PROCEDURE InsertarProyecto
    @Nombre nvarchar(50),
    @Descripcion varchar(200),
    @FechaInicio date,
    @FechaEstimadaFin date,
    @EstadoProyecto nvarchar(50)
AS
BEGIN
    -- Validar que la fecha de inicio sea anterior a la fecha estimada de finalización
    IF @FechaInicio >= @FechaEstimadaFin
    BEGIN
        PRINT 'Error: La fecha de inicio debe ser anterior a la fecha estimada de finalización.'
        RETURN;
    END

    -- Validar que el estado proporcionado sea válido
    IF @EstadoProyecto NOT IN ('Sin Iniciar', 'En Proceso', 'Finalizado', 'Cancelado')
    BEGIN
        PRINT 'Error: El estado proporcionado no es válido.'
        RETURN;
    END

    -- Insertar el nuevo proyecto si las validaciones son exitosas
    INSERT INTO [dbo].[Proyecto] ([Nombre], [Descripcion], [Fechainicio], [Fechaestimadafin], [Estadoproyecto])
    VALUES (@Nombre, @Descripcion, @FechaInicio, @FechaEstimadaFin, @EstadoProyecto)

    PRINT 'Proyecto insertado exitosamente.';
END