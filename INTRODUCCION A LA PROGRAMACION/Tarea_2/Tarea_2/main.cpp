#include "Funciones.h"
#include <iostream>
#include <string>
#include <vector>
#include "windows.h"
#include <iomanip>
#include <stdio.h>
#include <cstdio>
#include <iostream>
#include <algorithm>


using namespace std;

//Tamano de la matriz de ingreso de datos
const int tam_Y=5;
const int tam_X=4;
// Constante de Interes
const double interes=11.28;
// Revision que la opcion haya sido ingresada para dar permiso a la opcion
bool option2_accessed=false;
// Contador del menu 4, que aumenta para completar las cuotas
unsigned int contador{1};

// Plazo de meses
string mes;
// Revision de si el credito se aprobo para ver si se puede ingresar al menu 4
bool Credit_approved{false};
// Variable que indica el credito que se solicita
double credito_d{0.00};

//datos para el menu 4
float Amort{0};
float Monto_total{0};
float Cuota{0};

//Matriz de datos ingresado por menu 2, e importantes para el menu 3
string matriz1[tam_X][tam_Y];

//Estructura de revision del menu 2, para revisar que los datos son validos o no, y espera hasta que da informacion valida
typedef struct{
    string dato_s;
    string Check_Integer(char dato[30], int Coord_X, int Coord_Y){
        dato_s=dato;
        if(!(esSoloDigitos(dato)) || dato_s.empty()){
            gotoxy(0,18);
            cout << "Dato no valido, ingrese otro";
            gotoxy(Coord_X, Coord_Y);
            cout << "                             ";
            gotoxy(Coord_X, Coord_Y);
            cin.getline(dato,30);
            dato_s=dato;
            Check_Integer(dato, Coord_X, Coord_Y);
        }
        return dato_s;
    }
    void Check_Name(char dato[30], int Coord_X, int Coord_Y){
        if(!(splitCount(dato,' ')==2)){
            gotoxy(0,18);
            cout << "Dato no valido, ingrese otro";
            gotoxy(Coord_X, Coord_Y);
            cout << "                             ";
            gotoxy(Coord_X, Coord_Y);
            cin.getline(dato,30);
            Check_Name(dato, Coord_X, Coord_Y);
            }
    }
    string Check_Salary(char dato[30], int Coord_X, int Coord_Y){
        dato_s=dato;
        if(!(esSoloDigitos(dato)) || dato_s.empty()){
            gotoxy(0,18);
            cout << "Dato no valido, ingrese otro";
            gotoxy(Coord_X, Coord_Y);
            cout << "                             ";
            gotoxy(Coord_X, Coord_Y);
            cin.getline(dato,30);
            dato_s=dato;
            Check_Salary(dato, Coord_X, Coord_Y);
        }
        return dato_s;
    }
    void Check_Value(char dato[30], int Coord_X, int Coord_Y){
        if(!(strcmp(dato,"S")==0 || strcmp(dato,"N")==0)){
            gotoxy(0,18);
            cout << "Dato no valido, ingrese otro";
            gotoxy(Coord_X, Coord_Y);
            cout << "                             ";
            gotoxy(Coord_X, Coord_Y);
            cin.getline(dato,30);
            Check_Value(dato, Coord_X, Coord_Y);
            }
    }
} Datos_Solicitante;

//Funcion que abre un PDF si el usuario lo desea y retorna al Menu 1
void Menu_1();
void PDF_open(){
    string PDF_Option;
    cout << "Deseas abrir un pdf con esta informacion [S/N]:";
    cin >> PDF_Option;
    if (PDF_Option=="S" || PDF_Option=="s"){
        ShellExecute(NULL, "open", "Requisitos.pdf", NULL, NULL, SW_SHOW);
    }
    else if (!(PDF_Option=="N" || PDF_Option=="n") ){
        system("CLS");
        Menu_1();
    }
}

//Funcion que muestra la matriz incluida del menu 2
void Mostrar_Matriz(){
    const unsigned int Orig_Coord_X=5;
    const unsigned int Orig_Coord_Y=8;
    unsigned int New_Coord_X=0;
    unsigned int New_Coord_Y=0;
    gotoxy(40,5);
    cout << "Datos Incluidos"<< endl;
    gotoxy(5,7);
    cout << "Cedula";
    gotoxy(35,7);
    cout << "Nombre Completo";
    gotoxy(65,7);
    cout << "Salario Mensual";
    gotoxy(95,7);
    cout << "Orden Patronal";
    for(int y=0; y<5; y++){
        for (int x=0; x<4; x++){
            New_Coord_X=Orig_Coord_X+x*30;
            New_Coord_Y=Orig_Coord_Y+y;
            gotoxy(New_Coord_X,New_Coord_Y);
            switch(x){
            case 0:
                cout << stoi(matriz1[x][y]);
                break;
            case 1:
                cout << matriz1[x][y];
                break;
            case 2:
                cout << fixed << setprecision(2) << stof(matriz1[x][y]);
                break;
            case 3:
                cout << matriz1[x][y];
                break;
            }
        }
    }
    gotoxy(0,20);
    system("Pause");
}

//Revision de que el dato ingresado de cedula dentro de las opciones
int Check_Cedula(char cedula[30]){
    const int j = 0; // índice de la columna Cedula
    const int k = 1; // índice de la columna Nombre
    vector<string> Cedula;
    string cedula_choosen;
    int Pos_Cedula{-1};
    for (int i = 0; i < tam_Y; i++) {
        Cedula.push_back(matriz1[j][i]);
    }
    cedula_choosen=cedula;
    try{
        Pos_Cedula=index(Cedula, cedula_choosen);
        //cout << Pos_Cedula;
        if (Pos_Cedula==-1){
            gotoxy(0,18);
            throw "No es una ID elegible";
        }
        else{
            gotoxy(0,6);
            cout << "Nombre: " << matriz1[k][Pos_Cedula];
        }
    }
    catch(const char* error){
        gotoxy(0,18);
        cout << "                                          ";
        gotoxy(0,18);
        cout << "No es una ID Elegible";
    }
    return Pos_Cedula;
}

//Revision de que el credito es un numero.
double Check_Credito(char credito[30]){
    double credito_d{0.00};
    if(esSoloDigitos(credito)){
        credito_d=stod(credito);
    }
    else{
        gotoxy(0,18);
        cout << "                                          ";
        gotoxy(0,18);
        cout << "Dato no valido";
        gotoxy(0,9);
        cout << "                                          ";
        gotoxy(0,9);
        cin.getline(credito,30);
        Check_Credito(credito);

    }

    return credito_d;
}

//Menu para escoger el plazo
string Choose_plazo(){
    unsigned int CoordY{13};
    float choice;
    char choice_s[30];
    bool valid_input= false;
    const vector <string> options={"3 meses", "6 meses", "9 meses", "12 meses"};
    const string title="Seleccione un plazo";
    string counter_array[options.size()];
    while (!valid_input) {
        gotoxy(0,CoordY);
        cout << title << endl;
        for (unsigned int i = 1; i <= options.size(); i++) {
            gotoxy(0,CoordY+i);
            cout << i << ") " << options[i-1] << endl;
            counter_array[i-1] = to_string(i);
        }
        gotoxy(0,CoordY+5);
        cout << "Por favor selecciona una opcion: ";
        gotoxy(33,CoordY+5);
        cout << "                                 ";
        gotoxy(33,CoordY+5);
        //cin >> choice_s;
        cin.getline(choice_s,30);
        //Check_Cin_failed();
        // Revisa si elemento capturado esta en la lista de opciones del menu, funcion obtenida de web: https://www.techiedelight.com/check-if-an-element-exists-in-an-array-cpp/
        bool exists = find(counter_array, counter_array + options.size(), choice_s) != counter_array + options.size();
        if (exists && esSoloDigitos(choice_s)) {
            choice = stof(choice_s);
            valid_input = true;
            break;
        } else {
            gotoxy(0,CoordY+6);
            cout << "Opcion invalida, vuelva a intentarlo." << endl;
        }
    }
    return options[choice-1];
}

//Funcion que convierte la palabra 12 meses a solo el numero 12. Y asi para otros plazos
int Convert_Month(string month){
    unsigned int Mes_i{0};
    string Mes_s=removeNonNumeric(month);
    Mes_i=stoi(Mes_s);
    return Mes_i;
}

//Ventana 1 del menu, Informacion General del credito
void Window_1(){
    const int CoordX=0;
    const int CoordY=6;
    int mes_i=Convert_Month(mes);
    vector <string> Lista={"Monto del Prestamo","Tasa de Interes", "Plazo en meses", "Cuota Mensual"};
    gotoxy(0,3);
    Titulo("Tabla de prestamo general");
    for(int i=0; i<4; i++){
        for(int j=1; j<=2; j++){
            gotoxy(CoordX+20*i,CoordY+j);
            if(j==1){
                cout << Lista[i];
            }
            else{
                if(i==0){
                    cout << credito_d*1000;
                }
                else if(i==1){
                    cout << interes;
                }
                else if(i==2){
                    cout << mes_i;
                }
                else if(i==3){
                    Amort=(credito_d*interes*10)/mes_i;
                    Cuota=(credito_d*1000)/mes_i+Amort;
                    Monto_total=Cuota*mes_i;
                    cout << fixed << setprecision(2) << Cuota;
                }
            }
        }
    }
}

// Funcion que recursiva que da los cuotas a mes por pagar y el saldo
void New_Mount(float monto_total, float cuota, int CoordY, unsigned int Count_mes){
    if (contador<=Count_mes){
            monto_total=monto_total-cuota;
            gotoxy(0, CoordY+contador);
            cout << "Mes " << contador;
            gotoxy(20, CoordY+contador);
            cout << cuota;
            gotoxy(40, CoordY+contador);
            cout << Amort;
            gotoxy(60, CoordY+contador);
            if (contador==Count_mes){
                cout << "0.00";
            }
            else{
                cout << monto_total;
            }
            contador++;
            New_Mount(monto_total, cuota, CoordY, Count_mes);
    }
    contador=1;
}

//Ventana 2 del menu 4, Informacion detallada de las cuotas
void Window_2(){
    system("CLS");
    // Variables de control
    unsigned int mes_i=Convert_Month(mes);
    unsigned int const CoordY=8;
    //Titulos de Columnas
    gotoxy(0,3);
    Titulo("Tabla de prestamo desglosada");
    gotoxy(0,5);
    Titulo("Monto del credito " + to_string(Monto_total)+" Colones");
    gotoxy(20,8);
    cout << "Cuota";
    gotoxy(40,8);
    cout << "Amortizacion";
    gotoxy(60,8);
    cout << "Saldo";
    // Funcion que da el detalle de las cuotas
    New_Mount(Monto_total,Cuota, CoordY, mes_i);
    cout <<endl;
    system("Pause");
}



// Opcion 1 del Menu
void Menu_1(){
    system("CLS");
    // Informacion de montos
    Titulo("Requisitos");
    cout << setw(50) << "REQUISITOS PARA CREDITO" << endl << endl;
    cout << "Para optar por un credito debe cumplir con lo siguiente:" << endl << endl << endl;
    cout << setw(40) << "Monto a solicitar en colones" << setw(40) << "Ingreso Mensual en colones" <<endl;
    cout << setw(35) << "10,000 - 100,000" << setw(35) << "400,000" <<endl;
    cout << setw(35) << "101,000 - 300,000" << setw(35) << "500,000" <<endl;
    cout << setw(35) << "301,000 - 500,000" << setw(35) << "750,000" <<endl;
    cout << setw(35) << "501,000 - 700,000" << setw(35) << "900,000" <<endl;
    cout << setw(35) << "701,000 - 1,000,000" << setw(35) << "1,200,000" <<endl;

    cout << endl << endl;
    // Informacion de Requisitos
    cout<< "Presentar:" <<endl;
    cout<< "I. Cedula de Identidad" <<endl;
    cout<< "II. Orden Patronal al día" <<endl;
    cout<< "III. Constancia Salarial" <<endl;

    cout << endl << endl;
    // Se pregunta si desea esta informacion de forma accesible en un PDF
    PDF_open();
    cout << endl << endl;
    system("Pause");
}

//Funcion 2 Ingreso de Datos
void Menu_2(){
    system("CLS");
    //Datos que se usaran en esta opcion;
    char dato[30];
    const unsigned int Orig_Coord_X=5;
    const unsigned int Orig_Coord_Y=8;
    unsigned int New_Coord_X=0;
    unsigned int New_Coord_Y=0;
    //Estructura para validacion de datos
    Datos_Solicitante Persona;
    Titulo("Incluir datos del Solicitante");
    // Titulos de Columnas
    gotoxy(40,5);
    cout << "Datos del solicitante"<< endl;
    gotoxy(5,7);
    cout << "Cedula";
    gotoxy(35,7);
    cout << "Nombre Completo";
    gotoxy(65,7);
    cout << "Salario Mensual";
    gotoxy(95,7);
    cout << "Orden Patronal";
    //Matriz de Insercion
    for(int y=0; y<5; y++){
        for (int x=0; x<4; x++){
            gotoxy(0,18);
            cout << "                             ";
            //Variables de control de cursor
            New_Coord_X=Orig_Coord_X+x*30;
            New_Coord_Y=Orig_Coord_Y+y;
            //Switch segun la columna
            switch (x){
            case 0:
                //Insercion Cedula
                gotoxy(0,19);
                cout << "                                      ";
                gotoxy(0,19);
                cout << "En caso de no tener digite 0";
                gotoxy(New_Coord_X,New_Coord_Y);
                cin.getline(dato,30);
                //Revision, Consulta e Insercion
                matriz1[x][y]=Persona.Check_Integer(dato,New_Coord_X,New_Coord_Y);
                break;
            case 1:
                //Insercion Nombre
                gotoxy(0,19);
                cout << "                                      ";
                gotoxy(0,19);
                cout << "En caso de no tener digite Na Na Na";
                gotoxy(New_Coord_X,New_Coord_Y);
                cin.getline(dato,30);
                //Revision, Consulta e Insercion
                Persona.Check_Name(dato,New_Coord_X,New_Coord_Y);
                matriz1[x][y]=dato;
                break;
            case 2:
                //Insercion Salario
                gotoxy(0,19);
                cout << "                                      ";
                gotoxy(0,19);
                cout << "En caso de no tener digite 0";
                gotoxy(New_Coord_X,New_Coord_Y);
                cin.getline(dato,30);
                //Revision, Consulta e Insercion
                matriz1[x][y]=Persona.Check_Salary(dato,New_Coord_X,New_Coord_Y);;
                break;
            case 3:
                //Insercion Orden Patronal
                gotoxy(0,19);
                cout << "                                      ";
                gotoxy(0,19);
                cout << "Solo se acepta S/N";
                gotoxy(New_Coord_X,New_Coord_Y);
                cin.getline(dato,30);
                //Revision, Consulta
                Persona.Check_Value(dato,New_Coord_X,New_Coord_Y);
                //Insercion
                matriz1[x][y]=dato;
                break;
            }
        }
    }
    gotoxy(0,20);
    system("Pause");
    system("CLS");
    // Se muestra lo que se inserto
    Mostrar_Matriz();
    option2_accessed=true;
}

//Menu 3, Formulario del prestamo
void Menu_3(){
    system("CLS");
    gotoxy(0,0);
    Titulo("Formulario del prestamo");
    // Variables Matriz
    int cedula;
    string nombre;
    char ordenPat;
    double salario {0.00};
    // Variables de la opcion.
    char input_cedula[30];
    char credito[30];
    static int filaM2;
    Credit_approved=false;
    //Inicio de Impresion
    gotoxy(0,5);
    //Insercion de Cedula
    cout << "Ingrese la cedula: ";
    cin.getline(input_cedula,30);
    //Se busca si la opcion ingresada esta en la lista de terminos encontados dada la opcion 2 del menu
    filaM2=Check_Cedula(input_cedula);
    if (filaM2!=-1){
        //Se encontro la cedula
        //Se asigna cada termino de su respectiva columna
        cedula=stoi(matriz1[0][filaM2]);
        nombre=matriz1[1][filaM2];
        salario=stod(matriz1[2][filaM2]);
        ordenPat=matriz1[3][filaM2][0];
        //try{ Intente hacer try pero no me salio los cout deberian ser throw

            if(cedula==0){ //Si la cedula es 0 no cumple
                gotoxy(0,18);
                cout<< "                                ";
                gotoxy(0,18);
                cout << "NO CUMPLE CON LOS REQUISITOS”";
            }
            else if(nombre=="Na Na Na"){ //Si el nombre es Na Na Na no cumple
                gotoxy(0,18);
                cout<< "                                ";
                gotoxy(0,18);
                cout << "NO CUMPLE CON LOS REQUISITOS”";
            }
            else if(salario==0){ //Si el salario es 0 no cumple
                gotoxy(0,18);
                cout<< "                                ";
                gotoxy(0,18);
                cout << "NO CUMPLE CON LOS REQUISITOS”";
            }
            else if(ordenPat=='N'){ //Si la orden Patronal no se entraga no cumple
                gotoxy(0,18);
                cout<< "                                ";
                gotoxy(0,18);
                cout << "NO CUMPLE CON LOS REQUISITOS”";
            }
            else{
                //Todos los datos son correctos, se pide un monto para revisar
                gotoxy(0,8);
                //Por la tabla se simplifica que los creditos se piden en multiplos en 1000, si digitas 1000, seian 1000 mil es decir un millon.
                cout << "Digitar el monto del credito a pedir de 10,000 a 1,000,000, en terminos de mil si digitas 10 seran 10,000:";
                gotoxy(0,9);
                cin.getline(credito,30);
                credito_d=Check_Credito(credito);
                gotoxy(0,10);
                //Se da la informacion multiplicada por mil
                cout << "Has solicitado un credito por " << credito_d * 1000 <<endl;
                //Se revisa contra la tabla de que si el credito es valido contra el Salario
                if (credito_d>=10 && credito_d<=100){
                    if(salario>=400000){
                        gotoxy(0,12);
                        cout << "                                ";
                        gotoxy(0,12);
                        cout << "El credito ha sido aprobado";
                        Credit_approved=true;
                    }
                    else{
                        gotoxy(0,12);
                        cout << "                                ";
                        gotoxy(0,12);
                        cout << "NO CUMPLE CON LOS REQUISITOS";
                    }
                }
                else if (credito_d>=101 && credito_d<=300){
                    if(salario>=500000){
                        gotoxy(0,12);
                        cout << "                                ";
                        gotoxy(0,12);
                        cout<< "El credito ha sido aprobado";
                        Credit_approved=true;
                    }
                    else{
                        gotoxy(0,12);
                        cout << "                                ";
                        gotoxy(0,12);
                        cout << "NO CUMPLE CON LOS REQUISITOS";
                    }
                }
                else if (credito_d>=301 && credito_d<=500){
                    if(salario>=750000){
                        gotoxy(0,12);
                        cout << "                                ";
                        gotoxy(0,12);
                        cout<< "El credito ha sido aprobado";
                        Credit_approved=true;
                    }
                    else{
                        gotoxy(0,12);
                        cout << "                                ";
                        gotoxy(0,12);
                        cout<< "NO CUMPLE CON LOS REQUISITOS";
                    }
                }
                else if (credito_d>=501 && credito_d<=700){
                    if(salario>=900000){
                        gotoxy(0,11);
                        cout << "                                ";
                        gotoxy(0,11);
                        cout<< "El credito ha sido aprobado";
                        Credit_approved=true;
                    }
                    else{
                        gotoxy(0,11);
                        cout << "                                ";
                        gotoxy(0,11);
                        cout<< "NO CUMPLE CON LOS REQUISITOS";
                    }
                }
                else if (credito_d>=701 && credito_d<=1000){
                    if(salario>=1200000){
                        gotoxy(0,11);
                        cout << "                                ";
                        gotoxy(0,11);
                        cout << "El credito ha sido aprobado";
                        Credit_approved=true;
                    }
                    else{
                        gotoxy(0,11);
                        cout << "                                ";
                        gotoxy(0,11);
                        cout << "NO CUMPLE CON LOS REQUISITOS";
                    }
                }
                //Si ingresas un dato fuera de los limites entre 10 mil y 1000 mil, se indica que no cumple
                else{
                    gotoxy(0,11);
                    cout << "                                ";
                    gotoxy(0,11);
                    cout << "NO CUMPLE CON LOS REQUISITOS";
                }
            }
        //} Intente hacer catch no funciono
        //catch(){
            //gotoxy(0,11);
            //cout << "                                          ";
            //gotoxy(0,11);
            //cout << "NO CUMPLE CON LOS REQUISITOS_7";
        //}
    }
    //En caso de si cumplir, se pide mas datos
    if (Credit_approved){
        //Se consulta por los meses a Ingresar entre 3,6, 9 y 12 meses.
        mes=Choose_plazo();
    }
    gotoxy(0,20);
    system("Pause");
}

// Menu 4, visualizacion de informacion del ultimo credito aprobado
void Menu_4(){
    contador=1;
    system("CLS");
    Titulo("Hoja de Calculo del prestamo");
    //Primera Ventana, informacion general del credito
    Window_1();
    gotoxy(0,20);
    system("Pause");
    system("CLS");
    //Segunda Ventana, informacion detallada del credito
    Titulo("Hoja de Calculo del prestamo");
    Window_2();
}

int main() {
    // Titulo del menu principal
    string title = "Menu Principal";
    // opciones del menu Principal
    vector<string> options = {"Requisitos", "Incluir datos del Solicitante", "Formulario de prestamo", "Hoja de Calculo del prestamo", "Salir"};
    // Decision del usuario inicializada en 0
    unsigned int choice{0};
    //Control y 5 de Salida
    while (choice!=5){
            //Funcion de Funciones.h que crea un menu validado y solo permite entradas del 1 a la cantidad de Opciones
            choice = menu(title, options);
            //Se indica que se selecciono
            cout << "Has seleccionado la opcion " << choice << " la cual es: " << options[choice-1] << "."<< endl;
            //Switch de casos para opcion del menu.
            switch (choice){
            case 1:
                Sleep(2000);
                //Ingreso a la funcion Menu 1
                Menu_1();
                system("CLS");
                break;
            case 2:
                Sleep(2000);
                //Ingreso a la funcion Menu 2
                Menu_2();
                system("CLS");
                break;
            case 3:
                Sleep(2000);
                //Revision de que ya se accedio al Menu 2 e ingreso datos
                if (option2_accessed){
                        //Ingreso a la funcion Menu 3
                        Menu_3();
                }
                else{
                    //En caso de no haber entrado am Menu se da este menu y se retorna al menu principal
                    cout << "Previo a entrar a esta opcion, ingresar datos en la Opcion 2 del menu" << endl;
                    cout << "Regresando al menu principal automatiocamente";
                    Sleep(3000);
                }
                system("CLS");
                break;
            case 4:
                Sleep(2000);
                //Revision si hay un credito a revisar los plazos, se debe ingresar al menu 3 antes, y que un credito se haya aprobado
                if (Credit_approved){
                        //Ingreso a la funcion Menu 4
                        Menu_4();
                }
                else{
                    //Si no se ha aprobado el anterior credito se regresa al menu principal
                    cout << "Previo a entrar a esta opcion, ingresar un plazo en la Opcion 3 del menu" << endl;
                    cout << "Regresando al menu principal automatiocamente";
                    Sleep(3000);
                }
                system("CLS");
                break;
            }
    }
    cout << "Ejecuccion finalizada con exito se ha seleccionado la opcion 5 la cual es Salir" << endl;
    return 0;
}
