USE [master]
GO
/****** Object: Database [InnovateTech] Script Date: 21/10/2023 13:00:47 ******/
CREATE DATABASE [InnovateTech]
go
USE [InnovateTech]
GO
/****** Object: Table [dbo].[Cargo] Script Date: 21/10/2023 13:00:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cargo](
[IDcargo] [int] IDENTITY(1,1) NOT NULL,
[Nombrecargo] [nvarchar](50) NOT NULL,
[Descripcion] [nvarchar](200) NULL,
CONSTRAINT [PK_Cargo] PRIMARY KEY CLUSTERED
(
[IDcargo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object: Table [dbo].[Departamento] Script Date: 21/10/2023 13:00:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Departamento](
[IDdepartamento] [int] IDENTITY(1,1) NOT NULL,
[Nombre] [nvarchar](50) NOT NULL,
[Descripcion] [nvarchar](200) NULL,
CONSTRAINT [PK_Departamento] PRIMARY KEY CLUSTERED
(
[IDdepartamento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object: Table [dbo].[Empleado] Script Date: 21/10/2023 13:00:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empleado](
[Cedulaempleado] [int] NOT NULL,
[Codigoempleado] [int] NOT NULL,
[Nombre] [nvarchar](50) NOT NULL,
[Apellido1] [nvarchar](50) NOT NULL,
[Apellido2] [nvarchar](50) NULL,
[IDcargo] [int] NOT NULL,
[IDdepartamento] [int] NOT NULL,
[Fechacontratacion] [date] NULL,
CONSTRAINT [PK_Empleado] PRIMARY KEY CLUSTERED
(
[Cedulaempleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object: Table [dbo].[HistorialCambios] Script Date: 21/10/2023 13:00:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HistorialCambios](
[IDhistorial] [int] IDENTITY(1,1) NOT NULL,
[Fechacambio] [date] NOT NULL,
[Descripcioncambio] [nvarchar](200) NOT NULL,
[Numerotarea] [int] NOT NULL,
CONSTRAINT [PK_HistorialCambios] PRIMARY KEY CLUSTERED
(
[IDhistorial] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object: Table [dbo].[Proyecto] Script Date: 21/10/2023 13:00:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proyecto](
[Numeroproyecto] [int] IDENTITY(1,1) NOT NULL,
[Nombre] [nvarchar](50) NOT NULL,
[Descripcion] [varchar](200) NULL,
[Fechainicio] [date] NOT NULL,
[Fechaestimadafin] [date] NOT NULL,
[Estadoproyecto] [nvarchar](50) NOT NULL,
[CedulaLider] [int] NOT NULL,
CONSTRAINT [PK_Proyecto] PRIMARY KEY CLUSTERED
(
[Numeroproyecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object: Table [dbo].[RegistroTiempo] Script Date: 21/10/2023 13:00:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RegistroTiempo](
[IDregistro] [int] IDENTITY(1,1) NOT NULL,
[Horasdedicadas] [int] NOT NULL,
[Numerotarea] [int] NOT NULL,
[CedulaEmpleado] [int] NOT NULL,
CONSTRAINT [PK_RegistroTiempo] PRIMARY KEY CLUSTERED
(
[IDregistro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object: Table [dbo].[Tarea] Script Date: 21/10/2023 13:00:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tarea](
[Numerotarea] [int] IDENTITY(1,1) NOT NULL,
[Descripcion] [nchar](200) NOT NULL,
[FechaInicio] [date] NOT NULL,
[Fechaestimadafin] [date] NULL,
[Estadotarea] [nvarchar](50) NOT NULL,
[Numeroproyecto] [int] NOT NULL,
[Cedulaempleado] [int] NOT NULL,
CONSTRAINT [PK_Tarea] PRIMARY KEY CLUSTERED
(
[Numerotarea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Empleado] WITH CHECK ADD CONSTRAINT [FK_Empleado_Cargo] FOREIGN KEY([IDcargo])
REFERENCES [dbo].[Cargo] ([IDcargo])
GO
ALTER TABLE [dbo].[Empleado] CHECK CONSTRAINT [FK_Empleado_Cargo]
GO
ALTER TABLE [dbo].[Empleado] WITH CHECK ADD CONSTRAINT [FK_Empleado_Departamento] FOREIGN KEY([IDdepartamento])
REFERENCES [dbo].[Departamento] ([IDdepartamento])
GO
ALTER TABLE [dbo].[Empleado] CHECK CONSTRAINT [FK_Empleado_Departamento]
GO
ALTER TABLE [dbo].[HistorialCambios] WITH CHECK ADD CONSTRAINT [FK_HistorialCambios_Tarea] FOREIGN KEY([Numerotarea])
REFERENCES [dbo].[Tarea] ([Numerotarea])
GO
ALTER TABLE [dbo].[HistorialCambios] CHECK CONSTRAINT [FK_HistorialCambios_Tarea]
GO
ALTER TABLE [dbo].[Proyecto] WITH CHECK ADD CONSTRAINT [FK_Proyecto_Empleado] FOREIGN KEY([CedulaLider])
REFERENCES [dbo].[Empleado] ([Cedulaempleado])
GO
ALTER TABLE [dbo].[Proyecto] CHECK CONSTRAINT [FK_Proyecto_Empleado]
GO
ALTER TABLE [dbo].[RegistroTiempo] WITH CHECK ADD CONSTRAINT [FK_RegistroTiempo_Empleado] FOREIGN KEY([CedulaEmpleado])
REFERENCES [dbo].[Empleado] ([Cedulaempleado])
GO
ALTER TABLE [dbo].[RegistroTiempo] CHECK CONSTRAINT [FK_RegistroTiempo_Empleado]
GO
ALTER TABLE [dbo].[RegistroTiempo] WITH CHECK ADD CONSTRAINT [FK_RegistroTiempo_Tarea] FOREIGN KEY([Numerotarea])
REFERENCES [dbo].[Tarea] ([Numerotarea])
GO
ALTER TABLE [dbo].[RegistroTiempo] CHECK CONSTRAINT [FK_RegistroTiempo_Tarea]
GO
ALTER TABLE [dbo].[Tarea] WITH CHECK ADD CONSTRAINT [FK_Tarea_Empleado] FOREIGN KEY([Cedulaempleado])
REFERENCES [dbo].[Empleado] ([Cedulaempleado])
GO
ALTER TABLE [dbo].[Tarea] CHECK CONSTRAINT [FK_Tarea_Empleado]
GO
ALTER TABLE [dbo].[Tarea] WITH CHECK ADD CONSTRAINT [FK_Tarea_Proyecto] FOREIGN KEY([Numeroproyecto])
REFERENCES [dbo].[Proyecto] ([Numeroproyecto])
GO
ALTER TABLE [dbo].[Tarea] CHECK CONSTRAINT [FK_Tarea_Proyecto]
GO
USE [master]
GO
ALTER DATABASE [InnovateTech] SET READ_WRITE
GO