#include <iostream>
#include <string>
#include <vector>
#include "Funciones.h"
#include <iostream>
#include <stdio.h>
#include <windows.h>
#include <fstream>
#include <algorithm>
#include <ctime>
#include <iomanip>

using namespace std;

//Variables y Contantes del ingreso de Usuario
bool Access_Allowed=false;
const unsigned int max_attempts_login=3;
const string Usuarios="Usuarios.txt";
vector <string> Users;
vector <string> Pwds;
//Constantes de los menus
const string Titulo_principal="Menu Principal";
const vector <string> Opciones_Menu_Principal={"Departamentos","Activos","Salir"};
const string Titulo_Depa="Departamentos";
const vector <string> Opciones_Depa={"Registros", "Consultar"};
const string Titulo_Acti="Activos";
const vector <string> Opciones_Acti={"Registros","Consultar","Reporte General"};
const string Titulo_Cons="Consultar Activos";
const vector <string> Opciones_Cons={"Por Persona", "Por departamento"};

//Si desea continuar
int Yes_No(){
    bool valid=false;
    char Option[10];
    while(!valid){
        gotoxy(0,18);
        cout << "Deseas continuar S/N: ";
        cin.getline(Option,10);
        if(strcmp(Option,"S")){
            valid=true;
            return 0;
        }
        else if(strcmp(Option,"N")){
            valid=true;
            return 1;
        }
        else{
            gotoxy(0,19);
            cout << "Dato no valido, vuelva a ingresar";
            gotoxy(21,18);
            cout << "          ";
            valid=false;
        }
    }
}

//Constante de los archivos a usar
const string Departamentos = "dptos.txt";
const string Activos = "activos.txt";
// Usado para ordenar la matriz segun codigo
bool compareVectors(const std::vector<string>& v1, const std::vector<string>& v2) {
    return stoi(v1[0]) < stoi(v2[0]);
}

void Registrar_Dpto() {
    // Inicializacion de Variables y Matrices
    vector<vector<string>> matriz_Dpto;
    ifstream infile;
    int cod_dpto;
    string cod, dsc, dsc_dpto;
    char cod_c[10], dsc_c[30];
    bool isnumeric=false;
    int num_filas = 0;
    int actual;
    // Se abre el archivo
    infile.open(Departamentos);
    if (!(infile.is_open())) {
        gotoxy(0, 8);
        cout << "El archivo no abre";
    }
    // Insercion del archivo en una matriz
    while (infile >> cod >> dsc) {
        num_filas++;
        matriz_Dpto.resize(num_filas);
        matriz_Dpto[num_filas-1].resize(2);
        matriz_Dpto[num_filas-1][0] = cod;
        matriz_Dpto[num_filas-1][1] = dsc;
    }
    //Se cierra el archivo
    infile.close();
    gotoxy(0, 3);
    try {
        // Impresion en Pantalla
        gotoxy(0, 3);
        cout << "Ingrese el codigo: ";
        gotoxy(19, 3);
        cout << "                        ";
        gotoxy(0, 4);
        cout << "Ingrese la descripcion: ";
        gotoxy(24, 4);
        cout << "                        ";
        //Codigo
        while(!esSoloDigitos(cod_c) || !isnumeric){
            gotoxy(19, 3);
            cin.getline(cod_c,10);
            isnumeric=true;
            if(!esSoloDigitos(cod_c)){
                isnumeric=false;
                gotoxy(0,8);
                cout << "Debes ingresar un dato numerico entero positivo. Vuelva a intentar";
            }
        }
        gotoxy(0,8);
        cout << "                                                                          ";
        //Descripcion del departamento
        gotoxy(24, 4);
        cin.getline(dsc_c, 30);
        gotoxy(0, 8);
        cout << "                                                 " << endl;
        cout << "                                                 " << endl;
        // Variables de datos
        cod_dpto = stoi(cod_c);
        dsc_dpto = dsc_c;
        //Fallos de que ya exista
        for (int i=0; i < num_filas; i++) {
            actual = i;
            gotoxy(0, 6);
            // Si el codigo existe no se puede agregar
            if (cod_dpto == stoi(matriz_Dpto[i][0])) {
                //gotoxy(0, 8);
                //cout << "El codigo ya pertenece a " << matriz_Dpto[actual][1];
                throw 1;
            }
            //Si el dsc_dpto ya existe no se puede agregar
            else if (dsc_dpto.compare(matriz_Dpto[i][1])==0) {
                //gotoxy(0, 8);
                //cout << "El departamento ya pertenece al codigo " << matriz_Dpto[actual][0];
                throw 2;
            }
        }
        //Agregar en Matriz
        matriz_Dpto.resize(num_filas+1);
        matriz_Dpto[num_filas].resize(2);
        matriz_Dpto[num_filas][0] = to_string(cod_dpto);
        matriz_Dpto[num_filas][1] = dsc_dpto;
        //Ordenar Matriz
        sort(matriz_Dpto.begin(), matriz_Dpto.end(), compareVectors);
        // Rescribir el archivo
        ofstream outfile;
        outfile.open(Departamentos.c_str(), fstream::out);
        for (int a = 0; a <= num_filas; a++) {
            outfile << matriz_Dpto[a][0] << " " << matriz_Dpto[a][1] << endl;
        }
        outfile.close();
    }
    catch(int ex){
        gotoxy(0,8);
        //Se dio un error
        cout << "Codigo de error: " << ex << endl;
        if (ex == 1) {
            //El codigo ya pertenece a otro
            cout << "El codigo ya pertenece a " << matriz_Dpto[actual][1];
        }
        if (ex == 2) {
            // La dsc_dpto ya lo tiene otro
            cout << "El departamento ya pertenece al codigo " << matriz_Dpto[actual][0];
        }
    }
}
//Funcion que Activa Registrar Dpto
void Registrar_Depa(){
    int continuar=1;
    while(continuar==1){
        system("CLS");
        Titulo("Registrar Departamento");
        Registrar_Dpto();
        continuar=Yes_No();
    }
}

//Funcion que consulta el archivo y busca por Departamentos
void Consultar_Dpto() {
    // Inicializacion de Variables y Matrices
    vector<vector<string>> matriz_Dpto;
    ifstream infile;
    int cod_dpto;
    string  cod, dsc, dsc_dpto;
    char cod_c[10], dsc_c[30];
    int num_filas = 0;
    int actual;
    // Abrir archivos
    infile.open(Departamentos);
    if (!(infile.is_open())) {
        gotoxy(0, 8);
        cout << "El archivo no abre";
    }
    //Insercion del archivo en la matriz
    while (infile >> cod >> dsc) {
        num_filas++;
        matriz_Dpto.resize(num_filas);
        matriz_Dpto[num_filas - 1].resize(2);
        matriz_Dpto[num_filas - 1][0] = cod;
        matriz_Dpto[num_filas - 1][1] = dsc;
    }
    //se cierra el archivo
    infile.close();
    gotoxy(0, 4);
    //Variables de menu
    unsigned int Opcion_Busqueda;
    bool Salir=false;
    Opcion_Busqueda=menu("Seleccion de busqueda",{"Codigo","Nombre Departamento","Ninguna"});
    if (Opcion_Busqueda==3){
        Salir=true;
    }
    while(!Salir){
        // Buscar por Codigo
        if(Opcion_Busqueda==1){
            bool isNumeric=false;
            //Impresion en pantalla
            gotoxy(0, 10);
            cout << "Has seleccciona busqueda por Codigo";
            gotoxy(0, 12);
            cout << "Ingrese el codigo: ";
            while(!esSoloDigitos(cod_c) || !isNumeric){
                //Captura de datos
                gotoxy(19, 12);
                cin.getline(cod_c,10);
                isNumeric=true;
                if(!esSoloDigitos(cod_c)){
                    isNumeric=false;
                    gotoxy(0,16);
                    cout << "Debes ingresar un dato numerico entero positivo. Vuelva a intentar";
                }
            }
            gotoxy(0,16);
            cout << "                                                                          ";
            cod_dpto=stoi(cod_c);
            //Variables de busqueda
            bool found_Code=false;
            //Busqueda
            for (int i=0; i < num_filas; i++) {
                actual = i;
                if (cod_dpto == stoi(matriz_Dpto[i][0])) {
                    gotoxy(0, 14);
                    cout << "El codigo pertenece a " << matriz_Dpto[actual][1];
                    found_Code=true;
                }
            }
            if(!found_Code){
                gotoxy(0, 14);
                cout << "No se encontro tal codigo";
            }
            Salir=true;
        }
        // Busqueda por Departamento
        else if (Opcion_Busqueda==2){
            //Impresion en pantalla
            gotoxy(0, 10);
            cout << "Has seleccciona busqueda por nombre, respeta el uso de mayusculas";
            gotoxy(0, 12);
            cout << "Ingrese el nombre de Departamento: ";
            gotoxy(36, 12);
            //Captura de datos
            cin.getline(dsc_c, 30);
            dsc_dpto=dsc_c;
            //Variable de Busqueda
            bool found_dsc=false;
            //Busqueda
            for (int i=0; i < num_filas; i++) {
                actual = i;
                if (dsc_dpto.compare(matriz_Dpto[i][1])==0) {
                    gotoxy(0, 14);
                    cout << "El departamento ya pertenece al codigo " << matriz_Dpto[actual][0];
                    found_dsc=true;
                }
            }
            if(!found_dsc){
                gotoxy(0, 14);
                cout << "No se encontro tal nombre";
            }
        }
        else {
            gotoxy(0, 8);
            cout << "Algo salio mal";
        }
        Salir=true;
    }
    // Registro General de Dptos, no se pide pero lo dejo, en cualquier caso
    //gotoxy(5,4)
    //cout << "Codigo";
    //gotoxy(25, 4);
    //cout << "Departamento";
    //for (int i = 0; i < num_filas; i++) {
        //gotoxy(5, 5+i);
        //cout << matriz_Dpto[i][0];
        //gotoxy(25, 5 + i);
        //cout << matriz_Dpto[i][1];
    //}
}
// Funcion que activa Consultar por Departamento
void Consultar_Depa(){
    int continuar=1;
    while(continuar==1){
        system("CLS");
        Titulo("Consultar Departamento");
        Consultar_Dpto();
        continuar=Yes_No();
    }
}

void Registrar_Activo(){
    //Incializacion de variables y matrices
    vector<vector<string>> matriz_Activos;
    ifstream infile;
    int cod_dpto;
    string dsc_activo;
    string ubicacion;
    struct tm fecha;
    double valor;
    string asignado;
    string cod_s, dsc_s, ubi_s, fecha_s, valor_s, asignado_s;
    char input[10];
    bool isnumeric=false;
    int num_filas = 0;
    //Se abre el archivo
    infile.open(Activos);
    if (!(infile.is_open())) {
        gotoxy(0, 8);
        cout << "El archivo no abre";
    }
    // Insercion del archivo en la matriz
    while (infile >> cod_s >> dsc_s >> ubi_s >> fecha_s >> valor_s >> asignado_s ) {
        num_filas++;
        matriz_Activos.resize(num_filas);
        matriz_Activos[num_filas-1].resize(6);
        matriz_Activos[num_filas-1][0] = cod_s;
        matriz_Activos[num_filas-1][1] = dsc_s;
        matriz_Activos[num_filas-1][2] = ubi_s;
        matriz_Activos[num_filas-1][3] = fecha_s;
        matriz_Activos[num_filas-1][4] = valor_s;
        matriz_Activos[num_filas-1][5] = asignado_s;
    }
    //Se cierra el archivo
    infile.close();
    // Impresion de solicitud
    gotoxy(0, 3);
    cout << "Ingrese el codigo: ";
    gotoxy(19, 3);
    cout << "                        ";
    gotoxy(0, 4);
    cout << "Ingrese la descripcion: ";
    gotoxy(24, 4);
    cout << "                        ";
    gotoxy(0, 5);
    cout << "Ingrese la ubicacion: ";
    gotoxy(22, 5);
    cout << "                        ";
    gotoxy(0, 6);
    cout << "Ingrese el valor: ";
    gotoxy(18, 6);
    cout << "                        ";
    gotoxy(0, 7);
    cout << "Ingrese a quien se asigna: ";
    gotoxy(27, 7);
    cout << "                        ";
    while(!esSoloDigitos(input) || !isnumeric){
        gotoxy(19, 3);
        cin.getline(input,30);
        isnumeric=true;
        if(!esSoloDigitos(input)){
            isnumeric=false;
            gotoxy(19, 3);
            cout << "                 ";
            gotoxy(0,10);
            cout << "Debes ingresar un dato numerico entero positivo. Vuelva a intentar";
        }
    }
    isnumeric=false;
    cod_dpto=stoi(input);
    gotoxy(0,10);
    cout << "                                                                          ";
    // Descriptor Activo
    gotoxy(24, 4);
    cin.getline(input, 30);
    dsc_activo = input;
    // Ubicacion
    gotoxy(22, 5);
    cin.getline(input, 30);
    ubicacion = input;
    // Fecha
    time_t now;
    now = time(NULL);
    fecha = *localtime(&now);
    // Valor
    while(!esSoloDigitos(input) || !isnumeric){
        gotoxy(18, 6);
        cin.getline(input,30);
        isnumeric=true;
        if(!esSoloDigitos(input)){
            isnumeric=false;
            gotoxy(18, 6);
            cout << "                 ";
            gotoxy(0,10);
            cout << "Debes ingresar un dato numerico entero positivo. Vuelva a intentar";
        }
    }
    isnumeric=false;
    valor=stoi(input);
    gotoxy(0,10);
    cout << "                                                                          ";
    //Asignado
    gotoxy(27, 7);
    cin.getline(input, 30);
    asignado = input;
    //Agregar en Matriz
    matriz_Activos.resize(num_filas+1);
    matriz_Activos[num_filas].resize(6);
    matriz_Activos[num_filas][0] = to_string(cod_dpto);
    matriz_Activos[num_filas][1] = dsc_activo;
    matriz_Activos[num_filas][2] = ubicacion;
    //Poner Fecha
    matriz_Activos[num_filas][3] = timeToString(fecha);
    // Poner dos decimal
    matriz_Activos[num_filas][4] = doubleToString(valor);
    matriz_Activos[num_filas][5] = asignado;
    //Ordenar Matriz
    sort(matriz_Activos.begin(), matriz_Activos.end(), compareVectors);
    // Rescribir el archivo
    ofstream outfile;
    outfile.open(Activos.c_str(), fstream::out);
    for (int a = 0; a <= num_filas; a++) {
        outfile << matriz_Activos[a][0] << " " << matriz_Activos[a][1] << " " << matriz_Activos[a][2] << " " << matriz_Activos[a][3] << " " << matriz_Activos[a][4] << " " << matriz_Activos[a][5] <<  endl;
    }
    outfile.close();
}
//Funcion que activa para registrar un activo
void Registrar_Acti(){
    int continuar=1;
    while(continuar==1){
        system("CLS");
        Titulo("Registar Activo");
        Registrar_Activo();
        continuar=Yes_No();
    }
}

void Consultar_Activos_por_Persona(){
    // Inicializacion de Variables y Matrices
    vector<vector<string>> matriz_Activos;
    string cod, dsc, ubicacion, fecha, valor, asignado;
    ifstream infile;
    string asignado_chosen;
    char asignado_c[30];
    int num_filas = 0;
    //Se abre el archivo
    infile.open(Activos);
    if (!(infile.is_open())) {
        gotoxy(0, 8);
        cout << "El archivo no abre";
    }
    // Insercion del archivo en la matriz
    while (infile >> cod >> dsc >> ubicacion >> fecha >> valor >> asignado) {
        num_filas++;
        matriz_Activos.resize(num_filas);
        matriz_Activos[num_filas - 1].resize(6);
        matriz_Activos[num_filas - 1][0] = cod;
        matriz_Activos[num_filas - 1][1] = dsc;
        matriz_Activos[num_filas - 1][2] = ubicacion;
        matriz_Activos[num_filas - 1][3] = fecha;
        matriz_Activos[num_filas - 1][4] = valor;
        matriz_Activos[num_filas - 1][5] = asignado;
    }
    infile.close();
    // Mostrar en pantalla
    gotoxy(0, 5);
    cout << "Has seleccciona busqueda por nombre, respeta el uso de mayusculas";
    gotoxy(0, 7);
    cout << "Ingrese el nombre de la persona: ";
    //Captura de datos
    gotoxy(33, 7);
    cin.getline(asignado_c, 30);
    asignado_chosen=asignado_c;
    //Variables de Busqueda
    bool found_dsc=false;
    vector <int> filas_found={};
    int len_vector=0;
    // Busqueda
    for (int i=0; i < num_filas; i++) {
        if (asignado_chosen.compare(matriz_Activos[i][5])==0) {
            len_vector++;
            filas_found.resize(len_vector);
            filas_found[len_vector-1]=i;
            found_dsc=true;
        }
    }
    // Si el archivo se encontro 1 elemento como minimo
    if(found_dsc){
        // Impresion de encabezados
        int a=0;
        gotoxy(0,8);
        cout << "Cod_activo";
        gotoxy(15, 8);
        cout << "dsc_activo";
        gotoxy(35, 8);
        cout << "ubicacion";
        gotoxy(50, 8);
        cout << "fecha";
        gotoxy(65, 8);
        cout << "Valor";
        gotoxy(80, 8);
        cout << "asignado a";
        // Mostrar filas que coincidan con la busqueda
        for (auto const& it : filas_found){
            a++;
            gotoxy(0,8+a);
            cout << matriz_Activos[it][0];
            gotoxy(15, 8+a);
            cout << matriz_Activos[it][1];
            gotoxy(35, 8+a);
            cout << matriz_Activos[it][2];
            gotoxy(50, 8+a);
            cout << matriz_Activos[it][3];
            gotoxy(65, 8+a);
            cout << setprecision(2) << fixed << stod(matriz_Activos[it][4]);
            gotoxy(80, 8+a);
            cout << matriz_Activos[it][5];
        }
    }
    // Si no se encontro ningun elemento de busqueda
    else{
        gotoxy(0,16);
        cout << "No se encontro el activo";
    }
}

//Funcion de Consultar Activos por Perpona
void Consultar_Acti_por_Persona(){
    int continuar=1;
    while(continuar==1){
        system("CLS");
        Titulo("Consultar Activos por persona");
        Consultar_Activos_por_Persona();
        continuar=Yes_No();
    }
}

//Funcion de Busqueda de Activos por departamento
void Consultar_Activos_por_Departamento(){
    // Inicializacion de Matriz y Variable
    vector<vector<string>> matriz_Activos;
    string cod, dsc, ubicacion, fecha, valor, asignado;
    ifstream infile;
    string dep_chosen;
    char dep_c[30];
    int num_filas = 0;
    //Abrir archivos
    infile.open(Activos);
    if (!(infile.is_open())) {
        gotoxy(0, 8);
        cout << "El archivo no abre";
    }
    //Insercion de archivo en la matriz
    while (infile >> cod >> dsc >> ubicacion >> fecha >> valor >> asignado) {
        num_filas++;
        matriz_Activos.resize(num_filas);
        matriz_Activos[num_filas - 1].resize(6);
        matriz_Activos[num_filas - 1][0] = cod;
        matriz_Activos[num_filas - 1][1] = dsc;
        matriz_Activos[num_filas - 1][2] = ubicacion;
        matriz_Activos[num_filas - 1][3] = fecha;
        matriz_Activos[num_filas - 1][4] = valor;
        matriz_Activos[num_filas - 1][5] = asignado;
    }
    //Se cierra el archivo
    infile.close();
    // Mostrar en pantalla
    gotoxy(0, 5);
    cout << "Has seleccciona busqueda por nombre, respeta el uso de mayusculas";
    gotoxy(0, 7);
    cout << "Ingrese el nombre de la persona: ";
    //Captura de Datos
    gotoxy(33, 7);
    cin.getline(dep_c, 30);
    dep_chosen=dep_c;
    //Variables de busqueda
    bool found_dsc=false;
    vector <int> filas_found={};
    int len_vector=0;
    // Busqueda
    for (int i=0; i < num_filas; i++) {
        if (dep_chosen.compare(matriz_Activos[i][2])==0) {
            len_vector++;
            filas_found.resize(len_vector);
            filas_found[len_vector-1]=i;
            found_dsc=true;
        }
    }
    if(found_dsc){
        //Fila de encabezados
        int a=0;
        gotoxy(0,8);
        cout << "Cod_activo";
        gotoxy(15, 8);
        cout << "dsc_activo";
        gotoxy(35, 8);
        cout << "ubicacion";
        gotoxy(50, 8);
        cout << "fecha";
        gotoxy(65, 8);
        cout << "Valor";
        gotoxy(80, 8);
        cout << "asignado a";
        // Mostrar resultados
        for (auto const& it : filas_found){
            a++;
            gotoxy(0,8+a);
            cout << matriz_Activos[it][0];
            gotoxy(15, 8+a);
            cout << matriz_Activos[it][1];
            gotoxy(35, 8+a);
            cout << matriz_Activos[it][2];
            gotoxy(50, 8+a);
            cout << matriz_Activos[it][3];
            gotoxy(65, 8+a);
            cout << setprecision(2) << fixed << stod(matriz_Activos[it][4]);
            gotoxy(80, 8+a);
            cout << matriz_Activos[it][5];
        }
    }
    //Si no encontro ningun elemento que se de la busqueda
    else{
        gotoxy(0,16);
        cout << "No se encontro el activo";
    }
}

//Funcion que activa Consultar Activos por Departamento
void Consultar_Acti_por_Depa(){
    int continuar=1;
    while(continuar==1){
        system("CLS");
        Titulo("Consultar Activos por departamento");
        Consultar_Activos_por_Departamento();
        continuar=Yes_No();
    }
}

//Funcion Reporte General
void Reporte_Activos(){
    vector<vector<string>> matriz_Activos;
    ifstream infile;
    string cod, dsc, ubicacion, fecha, valor, asignado;
    int num_filas = 0;
    infile.open(Activos);
    if (!(infile.is_open())) {
        gotoxy(0, 8);
        cout << "El archivo no abre";
    }
    while (infile >> cod >> dsc >> ubicacion >> fecha >> valor >> asignado) {
        num_filas++;
        matriz_Activos.resize(num_filas);
        matriz_Activos[num_filas - 1].resize(6);
        matriz_Activos[num_filas - 1][0] = cod;
        matriz_Activos[num_filas - 1][1] = dsc;
        matriz_Activos[num_filas - 1][2] = ubicacion;
        matriz_Activos[num_filas - 1][3] = fecha;
        matriz_Activos[num_filas - 1][4] = valor;
        matriz_Activos[num_filas - 1][5] = asignado;
    }
    infile.close();
    gotoxy(0,4);
    cout << "Cod_activo";
    gotoxy(15, 4);
    cout << "dsc_activo";
    gotoxy(35, 4);
    cout << "ubicacion";
    gotoxy(50, 4);
    cout << "fecha";
    gotoxy(65, 4);
    cout << "Valor";
    gotoxy(80, 4);
    cout << "asignado a";
    for (int i = 0; i < num_filas; i++) {
        gotoxy(0, 5+i);
        cout << matriz_Activos[i][0];
        gotoxy(15, 5 + i);
        cout << matriz_Activos[i][1];
        gotoxy(35, 5+i);
        cout << matriz_Activos[i][2];
        gotoxy(50, 5 + i);
        cout << matriz_Activos[i][3];
        gotoxy(65, 5+i);
        cout << setprecision(2) << fixed << stod(matriz_Activos[i][4]);
        gotoxy(80, 5 + i);
        cout << matriz_Activos[i][5];
    }
}

//Funcion que activa el reporte General
void Reporte_General_Acti(){
    int continuar=1;
    while(continuar==1){
        system("CLS");
        Titulo("Reporte General");
        Reporte_Activos();
        continuar=Yes_No();
    }
}

int ConverttxtFile_to_Matriz_2(string filename){
    ifstream infile;
    infile.open(filename);
    unsigned int fila;
    string a,b;
    if (!infile.is_open()) {
        std::cout << "No se pudo abrir el archivo" << endl;
        return 1;
    }
    else{
        string line;
        vector<string> data;
        while (infile >> a >> b){
            fila++;
            Users.resize(fila);
            Pwds.resize(fila);
            Users[fila-1]=a;
            Pwds[fila-1]=b;
        }
    }
    infile.close();
    return 0;
}

int main()
{
    //Entradas y Control del Menu Principal
    bool Salir=false;
    int Option_Choosen;
    //Ingreso_Usuario 3 Intentos
    ConverttxtFile_to_Matriz_2(Usuarios);
    Access_Allowed=Login_Access(max_attempts_login,Users,Pwds);
    //Condicion de Ingreso
    if (Access_Allowed){
        while(!Salir){
            // Menu Principal
            Option_Choosen=0;
            system("CLS");
            Option_Choosen=menu(Titulo_principal,Opciones_Menu_Principal);
            switch(Option_Choosen){
            case 1:
                //Departamentos
                Option_Choosen=0;
                system("CLS");
                // Menu Departamentos
                Option_Choosen=menu(Titulo_Depa,Opciones_Depa);
                switch(Option_Choosen){
                case 1:
                    //Registrar
                    Registrar_Depa();
                    break;
                case 2:
                    //Consultar
                    Consultar_Depa();
                    break;
                }
                break;
            case 2:
                //Activos
                Option_Choosen=0;
                system("CLS");
                Option_Choosen=menu(Titulo_Acti,Opciones_Acti);
                // Menu Activos
                switch(Option_Choosen){
                case 1:
                    //Registrar
                    Registrar_Acti();
                    break;
                case 2:
                    //Consultar
                    Option_Choosen=0;
                    system("CLS");
                    // Menu de Sellecion de Consulta de Activos
                    Option_Choosen=menu(Titulo_Cons,Opciones_Cons);
                    switch(Option_Choosen){
                    case 1:
                        //Por Personal
                        Consultar_Acti_por_Persona();
                        break;
                    case 2:
                        //Por departamento
                        Consultar_Acti_por_Depa();
                        break;
                    }
                    break;
                case 3:
                    //Reporte Genenar
                    Reporte_General_Acti();
                    break;
                }
                break;
            case 3:
                Salir=true;
                break;
            }
        }
    }
}
