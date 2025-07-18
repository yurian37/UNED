Funcion Pago_Normal <- Cobro_Normal ( Horas, Pago )
	Definir Pago_Normal Como Real;
	Pago_Normal=Horas*Pago;
FinFuncion

Funcion Pago_Extra <- Cobro_Extra ( Horas, Pago )
	Definir Pago_Extra Como Real;
	Pago_Extra=Horas*Pago*1.5;
FinFuncion

Funcion Pago_Doble <- Cobro_Doble ( Horas, Pago )
	Definir Pago_Doble Como Real;
	Pago_Doble=Horas*Pago*2;
FinFuncion

SubProceso LlenarMatriz(pMatriz,pN)
	Definir fil,col Como Entero;
	Escribir "Reporte de horas laboradas por los empleados";
	Escribir "-----------------------------------------------";
	Escribir "             Lun Mar Mie Jue Vie";
	Para fil=0 hasta pN-1 Hacer
		Escribir "Empleado ", fil+1, "   " Sin Saltar;
		Para col=0 hasta 4 Hacer
			pMatriz(fil,col)=Aleatorio(0,12);
			Si pMatriz(fil,col) > 9 Entonces
				Escribir pMatriz(fil,col), "  " sin Saltar;
			Sino
				Escribir " ", pMatriz(fil,col), "  " sin Saltar;
			FinSi
		FinPara
		Escribir " ";
	FinPara
	Escribir "-----------------------------------------------";
	Escribir "Digite <Enter> para continuar";
	Esperar Tecla;
	Limpiar Pantalla;
FinSubProceso 

SubProceso MostrarMatriz(pMatriz,pN)
	Definir fil,col Como entero;
	Escribir "Reporte de horas laboradas por los empleados";
	Escribir "-----------------------------------------------";
	Escribir "             Lun Mar Mie Jue Vie";
	Para fil=0 hasta pN-1 Hacer
		Escribir "Empleado ", fil+1, "   " Sin Saltar;
		Para col=0 hasta 4 Hacer
			Si pMatriz(fil,col) > 9 Entonces
				Escribir pMatriz(fil,col), "  " sin Saltar;
			Sino
				Escribir " ", pMatriz(fil,col), "  " sin Saltar;
			FinSi
		FinPara
		Escribir " ";
	FinPara
	Escribir "-----------------------------------------------";
	Escribir "Digite <Enter> para continuar";
	Esperar Tecla;
FinSubProceso 

SubProceso Empleado_Categoria(pVect,pN)
	Definir i Como Entero;
	Escribir "Reporte de categor�as asignadas a los Empleados";
	Escribir "-----------------------------------------------";
	Para i=0 hasta pN-1 Hacer
		pVect[i]=Aleatorio(1,4);
		Escribir "El empleado ", i+1," tiene asignada la categor�a ", pVect(i);
	FinPara
	Escribir "-----------------------------------------------";
	Escribir "Digite <Enter> para continuar";
	Esperar Tecla;
	Limpiar Pantalla;
FinSubProceso

SubProceso Aleatorio_Pago_Categoria(pVect)
	Definir i Como Entero;
	Escribir "Se digitara de forma aleatoria";
	Escribir "Procesando." Sin Saltar;
	Esperar 2 Segundos;
	Escribir "." Sin Saltar;
	Esperar 2 Segundos;
	Escribir ".";
	Esperar 2 Segundos;
	Para i=0 hasta 3 Hacer
		pVect[i]=Aleatorio(3000,6000);
	FinPara
	Escribir "Terminado de procesar";
	Escribir "Digite <Enter> para continuar";
	Esperar Tecla;
	Limpiar Pantalla;
FinSubProceso

SubProceso Ingresado_Pago_Categoria(pVect)
	Definir i Como Entero;
	Definir Valor_1 Como Entero;
	Valor_1=0;
	Escribir "Se digitara de forma manual";
	Para i=0 hasta 3 Hacer
		Valor_1=0;
		Mientras Valor_1<3000 o Valor_1>6000 Hacer
			Escribir "Digite el monto de la categor�a ", i+1, ":>" Sin Saltar;
			Leer Valor_1;
			Si Valor_1<3000 o Valor_1>6000 Entonces
				Escribir "Opcion Invalida. Digite <Enter> para continuar";
				Esperar Tecla;
			FinSi
		FinMientras
		pVect[i]=Valor_1;
	FinPara
	Escribir "Terminado de procesar";
	Escribir "Digite <Enter> para continuar";
	Esperar Tecla;
	Limpiar Pantalla;
FinSubProceso

SubProceso Mostrar_Pago_Categoria(pVect)
	Definir i Como Entero;
	Escribir "Reporte de salarios por categor�a";
	Escribir "===========================================";
	Para i=0 hasta 3 Hacer
		Escribir "Categor�a ", i+1, "  Se le asign� un valor por hora de ", pVect[i];
	FinPara
	Escribir "Digite <Enter> para continuar";
	Esperar Tecla;
	Limpiar Pantalla;
FinSubProceso

SubProceso Reporte_Salarios(pMatriz, pVect_1, pVect_2, pN)
	Definir fil, col, k Como Entero;
	Definir Pago_Normal, Pago_Extra, Pago_Doble Como real;
	Definir Total_Dia, Total_Empleado, Total_Completo Como real;
	Total_Dia=0;
	Total_Empleado=0;
	Total_Completo=0;
	Pago_Normal=0;
	Pago_Extra=0;
	Pago_Doble=0;
	Escribir "Reporte de salarios semanales";
	Escribir "===================================================================";
	Escribir "Empleado SalxHora   Lun      Mar    Mier    Juev    Vier     Total";
	Escribir "===================================================================";
	Para fil=0 hasta pN-1 Hacer
		Total_Empleado=0;
		Escribir fil+1, "        " Sin Saltar;
		Escribir pVect_2[pVect_1[fil]-1], "   " Sin Saltar;
		Para col=0 hasta 4 Hacer
			Total_Dia=0;
			Pago_Normal=0;
			Pago_Extra=0;
			Pago_Doble=0;
			Si col=4 Entonces
				Pago_Doble=Cobro_Doble(pMatriz(fil,col),pVect_2[pVect_1[fil]-1]);
			SiNo
				Si pMatriz(fil,col)>8 Entonces
					Pago_Normal=Cobro_Normal(8,pVect_2[pVect_1[fil]-1]);
					Pago_Extra=Cobro_Extra(pMatriz(fil,col)-8,pVect_2[pVect_1[fil]-1]);
				SiNo
					Pago_Normal=Cobro_Normal(pMatriz(fil,col),pVect_2[pVect_1[fil]-1]);
				FinSi
			FinSi
			Total_Dia=Pago_Normal+Pago_Extra+Pago_Doble;
			Para k<-0 Hasta 7-longitud(ConvertirATexto(Total_Dia)) Con Paso 1 Hacer
				Escribir " " Sin Saltar;
			FinPara
			Escribir Total_Dia Sin Saltar;
			Total_Empleado=Total_Empleado+Total_Dia;
		FinPara
		Para k<-0 Hasta 9-longitud(ConvertirATexto(Total_Empleado)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Total_Empleado;
		Total_Completo=Total_Completo+Total_Empleado;
	FinPara
	Escribir "===================================================================";
	Escribir "Monto Total de Salarios                                    ", Total_Completo;
	Escribir "===================================================================";
FinSubProceso

SubProceso Reporte_Empleado(pMatriz, pVect_1, pVect_2, pN)
	Definir fil, Empleado,col, k Como Entero;
	Definir Horas_Normal, Horas_Extra, Horas_Doble Como Entero;
	Definir Pago_Normal, Pago_Extra, Pago_Doble Como Real;
	Definir Total_Dia, Total_Empleado Como Real;
	Total_Dia=0;
	Total_Empleado=0;
	Horas_Normal=0;
	Horas_Extra=0;
	Horas_Doble=0;
	Pago_Normal=0;
	Pago_Extra=0;
	Pago_Doble=0;
	Empleado=0;
	Mientras Empleado<=0 o Empleado>pN Hacer
		Escribir "Por favor digitar el numero de empleado, se consta de ",pN, " empleados";
		Leer Empleado;
		Si Empleado<=0 o Empleado>pN Entonces
			Escribir "Opcion Invalida. Digite <Enter> para continuar";
			Esperar Tecla;
		FinSi
	FinMientras
	fil=Empleado-1;
	Escribir "Reporte de salarios semanal";
	Escribir "=======================================================================";
	Escribir "Numero de empleado: ", Empleado;
	Escribir "Salario por hora:   ", pVect_2[pVect_1[fil]-1];
	Escribir "=======================================================================";
	Escribir "Dia     HorasReg MontoHR HorasEx MontoHE HorasDob MontoHD Salario_Diario";
	Escribir "=======================================================================";
	Para col=0 hasta 4 Hacer
		Total_Dia=0;
		Horas_Normal=0;
		Horas_Extra=0;
		Horas_Doble=0;
		Pago_Normal=0;
		Pago_Extra=0;
		Pago_Doble=0;
		Segun col Hacer
			0: Escribir "Lunes       " Sin Saltar;
			1: Escribir "Martes      " Sin Saltar;
			2: Escribir "Miercoles   " Sin Saltar;
			3: Escribir "Jueves      " Sin Saltar;
			4: Escribir "Viernes     " Sin	Saltar;
		FinSegun
		Si col=4 Entonces
			Horas_Doble=pMatriz(fil,col);
			Pago_Doble=Cobro_Doble(pMatriz(fil,col),pVect_2[pVect_1[fil]-1]);
		SiNo
			Si pMatriz(fil,col)>8 Entonces
				Horas_Normal=8;
				Pago_Normal=Cobro_Normal(8,pVect_2[pVect_1[fil]-1]);
				Horas_Extra=pMatriz(fil,col)-8;
				Pago_Extra=Cobro_Extra(pMatriz(fil,col)-8,pVect_2[pVect_1[fil]-1]);
			SiNo
				Horas_Normal=pMatriz(fil,col);
				Pago_Normal=Cobro_Normal(pMatriz(fil,col),pVect_2[pVect_1[fil]-1]);
			FinSi
		FinSi
		Para k<-0 Hasta 3-longitud(ConvertirATexto(Horas_Normal)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Horas_Normal Sin Saltar;
		Para k<-0 Hasta 6-longitud(ConvertirATexto(Pago_Normal)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Pago_Normal Sin Saltar;
		Para k<-0 Hasta 7-longitud(ConvertirATexto(Horas_Extra)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Horas_Extra Sin Saltar;
		Para k<-0 Hasta 8-longitud(ConvertirATexto(Pago_Extra)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Pago_Extra Sin Saltar;
		Para k<-0 Hasta 8-longitud(ConvertirATexto(Horas_Doble)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Horas_Doble Sin Saltar;
		Para k<-0 Hasta 6-longitud(ConvertirATexto(Pago_Doble)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Pago_Doble Sin Saltar;
		Total_Dia=Pago_Normal+Pago_Extra+Pago_Doble;
		Para k<-0 Hasta 12-longitud(ConvertirATexto(Total_Dia)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Total_Dia;
		Total_Empleado=Total_Empleado+Total_Dia;
	FinPara
	Escribir "======================================================================";
	Escribir "Monto Total de Salarios                                         ", Total_Empleado;
	Escribir "======================================================================";
FinSubProceso

SubProceso Reporte_General(pMatriz, pVect_1, pVect_2, pN)
	Definir fil, col, k Como Entero;
	Definir Pago_Normal, Pago_Extra, Pago_Doble Como real;
	Definir Total_Dia, Total_Empleado, Total_Completo Como real;
	Definir Pago_CCSS, Pago_Asoc, Pago_BP como Real;
	Total_Dia=0;
	Total_Empleado=0;
	Total_Completo=0;
	Pago_Normal=0;
	Pago_Extra=0;
	Pago_Doble=0;
	Escribir "        Reporte de General de salarios semanales    ";
	Escribir "===========================================";
	Escribir "Empleado    Salario Semanal";
	Escribir "===========================================";
	Para fil=0 hasta pN-1 Hacer
		Escribir fil+1 Sin Saltar;
		Total_Empleado=0;
		Para col=0 hasta 4 Hacer
			Total_Dia=0;
			Pago_Normal=0;
			Pago_Extra=0;
			Pago_Doble=0;
			Si col=4 Entonces
				Pago_Doble=Cobro_Doble(pMatriz(fil,col),pVect_2[pVect_1[fil]-1]);
			SiNo
				Si pMatriz(fil,col)>8 Entonces
					Pago_Normal=Cobro_Normal(8,pVect_2[pVect_1[fil]-1]);
					Pago_Extra=Cobro_Extra(pMatriz(fil,col)-8,pVect_2[pVect_1[fil]-1]);
				SiNo
					Pago_Normal=Cobro_Normal(pMatriz(fil,col),pVect_2[pVect_1[fil]-1]);
				FinSi
			FinSi
			Total_Dia=Pago_Normal+Pago_Extra+Pago_Doble;
			Total_Empleado=Total_Empleado+Total_Dia;
		FinPara
		Para k<-0 Hasta 15-longitud(ConvertirATexto(Total_Empleado)) Con Paso 1 Hacer
			Escribir " " Sin Saltar;
		FinPara
		Escribir Total_Empleado;
		Total_Completo=Total_Completo+Total_Empleado;
	FinPara
	Pago_CCSS=trunc(Total_Completo*30)/100;
	Pago_Asoc=trunc(Total_Completo*3)/100;
	Pago_BP=trunc(Total_Completo*1)/100;
	Escribir "===========================================";
	Escribir "Total Semanal" Sin Saltar;
	Para k<-0 Hasta 15-longitud(ConvertirATexto(Total_Completo)) Con Paso 1 Hacer
		Escribir " " Sin Saltar;
	FinPara
	Escribir Total_Completo;
	Escribir "CCSS" Sin Saltar;
	Para k<-0 Hasta 24-longitud(ConvertirATexto(Pago_CCSS)) Con Paso 1 Hacer
		Escribir " " Sin Saltar;
	FinPara
	Escribir Pago_CCSS;
	Escribir "Asoc solidarista" Sin Saltar;
	Para k<-0 Hasta 12-longitud(ConvertirATexto(Pago_Asoc)) Con Paso 1 Hacer
		Escribir " " Sin Saltar;
	FinPara
	Escribir Pago_Asoc;
	Escribir "Banco Popular" Sin Saltar;
	Para k<-0 Hasta 15-longitud(ConvertirATexto(Pago_BP)) Con Paso 1 Hacer
		Escribir " " Sin Saltar;
	FinPara
	Escribir Pago_BP;
	Escribir "===========================================";
FinSubProceso

Algoritmo Main
	//Declaracion
	//Variables de Control Globables
	Definir Step_1, Step_2, Step_3, Salir Como Logico;
	Definir Decision, Decision_2 Como Entero;
	//Constantes
	Definir Dias, Num_Categorias Como Entero;
	//Variable numero empleados
	Definir N Como Entero;
	//Datos de salida
	Definir Matriz_Horas_Laboradas, Vector_Categorias, Vector_Pagoxhora  Como Entero;
	//Inicializacion de Variables
	Step_1=Falso;
	Step_2=Falso;
	Step_3=Falso;
	Salir=Falso;
	Decision=0;
	Decision_2=0;
	N=0;
	//Constantes
	Dias=5;
	Num_Categorias=4;
	//Mensaje de Bienvenida
	Escribir "===========================================================================";
	Escribir "****** Bienvenido al programa de reporte de pagos para la empresa YYYY*****";
	Escribir "===========================================================================";
	Escribir "Digite cualquier tecla para continuar";
	Esperar Tecla;
	Limpiar Pantalla;
	Mientras !(N>0) Hacer
		Escribir "Por favor digitar el numero de empleados";
		Leer N;
		Si N<=0 Entonces
			Escribir "Opcion Invalida. Digite <Enter> para continuar";
			Esperar Tecla;
		FinSi
	FinMientras
	//Creacion de matrices y vector 
	Dimension Matriz_Horas_Laboradas(N+1,Dias+1);
	Dimension Vector_Categorias[N];
	Dimension Vector_Pagoxhora[Num_Categorias];
	Limpiar Pantalla;
	//Menu
	Mientras Salir=Falso Hacer
		//Menu
		Escribir "Men� principal";
		Escribir "1. Inicializaci�n de matriz de horas laboradas";
		Escribir "2. Inicializaci�n de categor�as por empleado";
		Escribir "3. Inicializaci�n de precio por hora de cada categor�a";
		Escribir "4. Reporte de salarios por empleado";
		Escribir "5. Reporte de salarios de un empleado especifico";
		Escribir "6. Reporte general de salarios";
		Escribir "7.Salir del men� ";
		Leer Decision;
		Limpiar Pantalla;
		Segun Decision Hacer
			1,2,3:
				Si Decision=1 Entonces
					//Decision 1
					LlenarMatriz(Matriz_Horas_Laboradas,N);
					Step_1=Verdadero;
				SiNo
					Si Decision=2 Entonces
						//Decision 2
						Empleado_Categoria(Vector_Categorias,N);
						Step_2=Verdadero;
					SiNo
						//Decision 3
						Decision_2=0;
						Mientras !(Decision_2=1 o Decision_2=2) Hacer
							Escribir "Ingreso del pago por Categoria, digite el numero";
							Escribir "1-De forma aleatoria";
							Escribir "2-De forma manual";
							Leer Decision_2;
							Si !(Decision_2=1 o Decision_2=2) Entonces
								Escribir "Opcion Invalida. Digite <Enter> para continuar";
								Esperar Tecla;
								Limpiar Pantalla;
							FinSi
						FinMientras
						Si Decision_2=1 Entonces
							Aleatorio_Pago_Categoria(Vector_Pagoxhora);
						SiNo
							Ingresado_Pago_Categoria(Vector_Pagoxhora);
						FinSi
						Mostrar_Pago_Categoria(Vector_Pagoxhora);
						Step_3=Verdadero;
					FinSi
				FinSi
			4,5,6:
				Si Step_1=Falso o Step_2=Falso o Step_3=Falso Entonces
					Si Step_1=Falso Entonces
						Escribir "Falta de aplicar la opcion 1 previo a ejecutar esta opcion";
					FinSi
					Si Step_2=Falso Entonces
						Escribir "Falta de aplicar la opcion 2 previo a ejecutar esta opcion";
					FinSi
					Si Step_3=Falso Entonces
						Escribir "Falta de aplicar la opcion 3 previo a ejecutar esta opcion";
					FinSi
					Escribir "Digite <Enter> para continuar";
					Esperar Tecla;
					Limpiar Pantalla;
				SiNo
					Si Decision=4 Entonces
						//Decision 4
						MostrarMatriz(Matriz_Horas_Laboradas,N);
						Reporte_Salarios(Matriz_Horas_Laboradas,Vector_Categorias,Vector_Pagoxhora, N);
						Escribir "Digite <Enter> para continuar";
						Esperar Tecla;
						Limpiar Pantalla;
					SiNo
						Si Decision=5 Entonces
							//Decision 5
							Reporte_Empleado(Matriz_Horas_Laboradas,Vector_Categorias,Vector_Pagoxhora, N);
							Escribir "Digite <Enter> para continuar";
							Esperar Tecla;
							Limpiar Pantalla;
						SiNo
							Escribir "--------------------------------";
							//Decision 6
							Reporte_General(Matriz_Horas_Laboradas,Vector_Categorias,Vector_Pagoxhora,N);
							Escribir "Digite <Enter> para continuar";
							Esperar Tecla;
							Limpiar Pantalla;
						FinSi
					FinSi
				FinSi	
			7: Salir=Verdadero;
			De Otro Modo:
				Escribir "Opcion Invalida. Digite <Enter> para continuar";
				Esperar Tecla;
				Limpiar Pantalla;
		FinSegun
	FinMientras
FinAlgoritmo
