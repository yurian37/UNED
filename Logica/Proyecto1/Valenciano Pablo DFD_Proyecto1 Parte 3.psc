
Funcion Resumen_Diario (Detalles_Vuelo,Tiquete_Dia)
	Definir TotalTiquetes, TotalEfectivo, TotalDescuento Como Real
	Definir TotalCaridad como Real
	Imprimir 'Tiquetes: ',Tiquete_Dia
	TotalTiquetes<-TotalTiquetes+Tiquete_Dia
	TotalEfectivo<-TotalEfectivo+Tiquete_Dia*ConvertirANumero(Detalles_Vuelo[2])
	TotalDescuento<-TotalDescuento+Tiquete_Dia*ConvertirANumero(Detalles_Vuelo(2))*ConvertirANumero(Detalles_Vuelo[4])
	TotalCaridad<-0
	Si Detalles_Vuelo(0)='5' entonces
		Si Tiquetes*ConvertirANumero(Detalles_Vuelo[2]) >= 1250 Entonces
			TotalCaridad<-Tiquete_Dia*ConvertirANumero(Detalles_Vuelo[2])*0.03
		FinSi
		Imprimir 'Reporte Diario'
		Imprimir '===============PIKA-VUELO============='
		Imprimir 'El monto total de tiquetes vendidos---->',TotalTiquetes
		Imprimir 'El monto total de pagos en dolares----->',TotalEfectivo
		Imprimir 'El monto total de descuentos aplicados->',TotalDescuento
		Imprimir 'El monto total de dinero para caridad-->',TotalCaridad
		Imprimir '===============PIKA-VUELO============='
		Wait_Insert()
	FinSi
Fin Funcion


Funcion Tiquetes_Totales<-Resumen_Vuelo ( Detalles_Vuelo, Tiquetes_Vendidos )
	Imprimir '===============PIKA-VUELO============='
	Imprimir 'Reporte para vuelo AV-',Detalles_Vuelo(0)
	Imprimir 'Billetes Vendidos----------->', Tiquetes_Vendidos
	Imprimir 'Puntos Aplicados------------>', Tiquetes_Vendidos * ConvertirANumero(Detalles_Vuelo(2))
	Imprimir 'Monton Pagados-------------->', Tiquetes_Vendidos * ConvertirANumero(Detalles_Vuelo(3))
	Imprimir 'Descuentos Aplicados-------->', Tiquetes_Vendidos * ConvertirANumero(Detalles_Vuelo(4))
	Imprimir '===============PIKA-VUELO============='
	Tiquetes_Totales<-Tiquetes_Vendidos
	Wait_Insert()
Fin Funcion

Funcion Reporte_Venta ( Tiquetes_Comprar, Detalles_Vuelo, Tiquetes_Vendidos)
	Puntos<- ConvertirANumero(Detalles_Vuelo(2))
	Precio<- ConvertirANumero(Detalles_Vuelo(3))
	Descuento<- ConvertirANumero(Detalles_Vuelo(4))
	Imprimir '===============PIKA-VUELO============='
	Imprimir 'Puntos_Requeridos-------------->',Tiquetes_Comprar * Puntos
	Imprimir 'El monto a pagar en dolares---->',Tiquetes_Comprar * Precio
	Imprimir 'Se aplica un descuento de------>',Tiquetes_Comprar * Descuento * Precio
	Imprimir '===============PIKA-VUELO============='
	Wait_Insert()
	Puntos_Requeridos<- Tiquetes_Comprar * Puntos
	Imprimir 'Digite su cantidad de puntos en multiplos de mil'
	Leer Puntos_Aplicar
	Si Puntos_Aplicar mod 1000 <> 0
		Imprimir 'Cantidad de puntos no es multiplo de mil, intente nuevamente'
		Wait_Insert()
		Reporte_Venta(Tiquetes_Comprar, Detalles_Vuelo, Tiquetes_Vendidos)
	FinSi
	Si Puntos_Aplicar<Puntos_Requeridos
		Imprimir 'Cantidad de Puntos no es suficiente'
		Wait_Insert()
		Vuelo(Detalles_Vuelo,Falso,Tiquetes_Vendidos)
	SiNo
		Imprimir 'Canje de puntos realizado exitosamente, gracias por la compra'
		Imprimir 'Pase a depositar ',Tiquetes_Comprar * Precio,' dolares'
		Tiquetes_Vendidos <- Tiquetes_Vendidos+Tiquetes_Comprar
		Imprimir 'Tiquetes actuales vendidos para AV-',Detalles_Vuelo(0),' es ',Tiquetes_Vendidos
		Wait_Insert()
		Vuelo(Detalles_Vuelo,Falso, Tiquetes_Vendidos)
	FinSi
Fin Funcion

Funcion Bienvenida ( Num_Vuelo, Destino )
	Imprimir '===============PIKA-VUELO============='
	Imprimir 'Bienvenido al vuelo AV-',Num_Vuelo,' con destino a ',Destino
	Imprimir '===============PIKA-VUELO============='
Fin Funcion

Funcion Compra (Detalles_Vuelo, Tiquetes_Vendidos)
	Imprimir 'Ingrese el codigo del propietario de los puntos'
	Leer Codigo_Usuario
	Imprimir 'Ingrese la cantidad de tiquetes a adquirir'
	Leer Tiquetes_Comprar
	Si Tiquetes_Comprar<=0
		Imprimir 'Dato no valido, volviendo a preguntar'
		Wait_Insert()
		Compra(Detalles_Vuelo, Tiquetes_Vendidos)
	FinSi
	Reporte_Venta(Tiquetes_Comprar, Detalles_Vuelo, Tiquetes_Vendidos)
Fin Funcion

Funcion Wait_Insert ()
	Imprimir 'Digite cualquier tecla para continuar'
	Esperar Tecla
	Limpiar Pantalla
Fin Funcion

Funcion Vuelo (Detalles_Vuelo, Entrada, Tiquetes_Vendidos)
	Si Entrada = Verdadero Entonces
		Imprimir '===============PIKA-VUELO============='
		Imprimir 'Desea canjear puntos para el vuelo AV-',Detalles_Vuelo(0),' S/N'
		Leer Decision
		Imprimir '===============PIKA-VUELO============='
	SiNo
		Imprimir '===============PIKA-VUELO============='
		Imprimir 'Desea procesar otro pasajero para este vuelo. S=Si, N=No?'
		Leer Decision
		Imprimir '===============PIKA-VUELO============='
		Limpiar Pantalla
	FinSi
	Si Decision='S' Entonces
		Bienvenida(Detalles_Vuelo(0),Detalles_Vuelo(1))
		Compra(Detalles_Vuelo, Tiquetes_Vendidos)
	Sino Si Decision <> 'N' Entonces
			Imprimir 'Dato no valido, volviendo a preguntar'
			Wait_Insert()
			Vuelo(Detalles_Vuelo, Falso,Tiquetes_Vendidos)
		Sino Si Tiquetes_Vendidos>0 Entonces
				Tiquete_Dia<-Resumen_Vuelo(Detalles_Vuelo,Tiquetes_Vendidos)
				Resumen_Diario(Detalles_Vuelo,Tiquete_Dia)
			FinSi
		FinSi
	FinSi
Fin Funcion

Algoritmo Main
	Imprimir 'Bienvenido al control de canje de puntos para la empresa PIKA-VUELO'
	Imprimir '(-_-)'
	Wait_Insert()
	Dimension Detalles_Vuelo[5]
	Detalles_Vuelo(0)<-'1'
	Detalles_Vuelo(1)<-'Bogota'
	Detalles_Vuelo(2)<-'4000'
	Detalles_Vuelo(3)<-'150'
	Detalles_Vuelo(4)<-'0'
	Vuelo(Detalles_Vuelo,Verdadero,0)
	Detalles_Vuelo(0)<-'2'
	Detalles_Vuelo(1)<-'Cali'
	Detalles_Vuelo(2)<-'4000'
	Detalles_Vuelo(3)<-'150'
	Detalles_Vuelo(4)<-'0'
	Vuelo(Detalles_Vuelo,Verdadero,0)
	Detalles_Vuelo(0)<-'3'
	Detalles_Vuelo(1)<-'Lima'
	Detalles_Vuelo(2)<-'5000'
	Detalles_Vuelo(3)<-'200'
	Detalles_Vuelo(4)<-'0'
	Vuelo(Detalles_Vuelo,Verdadero,0)
	Detalles_Vuelo(0)<-'4'
	Detalles_Vuelo(1)<-'Quito'
	Detalles_Vuelo(2)<-'4000'
	Detalles_Vuelo(3)<-'200'
	Detalles_Vuelo(4)<-'0'
	Vuelo(Detalles_Vuelo,Verdadero,0)
	Detalles_Vuelo(0)<-'5'
	Detalles_Vuelo(1)<-'Brasilia'
	Detalles_Vuelo(2)<-'7000'
	Detalles_Vuelo(3)<-'350'
	Detalles_Vuelo(4)<-'0.3'
	Vuelo(Detalles_Vuelo,Verdadero,0)
	Resumen_Diario(Detalles_Vuelo,0)
FinAlgoritmo
