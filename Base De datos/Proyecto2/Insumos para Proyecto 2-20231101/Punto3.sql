/*Select Estadotarea as TipoEstado, Count(*) as Cantidad
From Tarea
Group by Estadotarea
Order by case Estadotarea
	When 'En Proceso' then 1
	When 'Finalizado' then 2
	When 'Sin Iniciar' then 3
	When 'Cancelado'  then 4
	End

*/

Select valores.valor, coalesce(Count(Tarea.EstadoTarea),0) as Cantidad
From (
	Select 'En Proceso' as valor
	Union all
	Select 'Finalizado' as valor
	Union all
	Select 'Sin Iniciar' as valor
	Union all
	Select 'Cancelado' as valor
	) as valores
Left join Tarea on valores.valor = Tarea.Estadotarea
Group by valores.valor
Order by case valores.valor
	When 'En Proceso' then 1
	When 'Finalizado' then 2
	When 'Sin Iniciar' then 3
	When 'Cancelado'  then 4
	End
