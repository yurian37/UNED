#include <iostream>
#include <string>
#include <algorithm>
#include <string>
#include <vector>
#include "windows.h"
#include <cstdio>
#include <stdio.h>
#include <conio.h>
#include <fstream>
#include <ctime>

using namespace std;

//Funcion obtenida Web para evitar problemas con el CIN que loopea al ingresar letras, obtenida de:  https://stackoverflow.com/questions/5864540/infinite-loop-with-cin-when-typing-string-while-a-number-is-expected
void Check_Cin_failed(){
    if (cin.fail())
        {
            cin.clear();
            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        }
}

//Funcion que retorna un true si todos los valores de un string son numericos
bool esSoloDigitos(string str) {
    for (char c : str) {
            if (!isdigit(c) or c==',') {
                return false;
            }
    }
    return true;
}
//Funcion para posicionar el cursor
COORD coord={0,0};
void gotoxy(int x, int y){
    coord.X=x;
    coord.Y=y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),coord);
}

//Menu principal de usuario que toma de entrada el titulo y el arreglo de Opciones.
int menu(string title, vector<string> options) {
    float choice;
    char choice_s[30];
    bool valid_input= false;
    string counter_array[options.size()];
    while (!valid_input) {
        gotoxy(10,0);
        cout << "---------------------------" << endl;
        gotoxy(10,1);
        cout << title << endl;
        gotoxy(10,2);
        cout << "---------------------------" << endl;
        gotoxy(0,5);
        for (unsigned int i = 1; i <= options.size(); i++) {
            cout << i << ") " << options[i-1] << endl;
            counter_array[i-1] = to_string(i);
        }
        cout << "---------------------------" << endl;
        cout << "Por favor selecciona una opcion: ";
        //cin >> choice_s;
        cin.getline(choice_s,30);
        Check_Cin_failed();

        // Revisa si elemento capturado esta en la lista de opciones del menu, funcion obtenida de web: https://www.techiedelight.com/check-if-an-element-exists-in-an-array-cpp/
        bool exists = find(counter_array, counter_array + options.size(), choice_s) != counter_array + options.size();
        if (exists && esSoloDigitos(choice_s)) {
            choice = stof(choice_s);
            valid_input = true;
        } else {
            gotoxy(0,20);
            cout << "Opcion invalida, vuelva a intentarlo." << endl;
            Sleep(2000);
            system("CLS");
        }
    }
    return choice;
}

//Funcion para dar un titulo
void Titulo(string title){
    cout << "---------------------------" << endl;
    cout << title << endl;
    cout << "---------------------------" << endl;
}



//Funcion para contar espacios en un string
size_t splitCount(string str, char delimiter) {
    vector<string> tokens;
    size_t start = 0, end = 0;

    while ((end = str.find(delimiter, start)) != string::npos) {
        tokens.push_back(str.substr(start, end - start));
        start = end + 1;
    }

    tokens.push_back(str.substr(start));
    return tokens.size() - 1;
}

//Funcion index de Python para encontrar la posicion de un elementor de un arreglo de strings
int index(vector<string>& vec, string x) {
    for (unsigned int i = 0; i < vec.size(); i++) {
        if (vec[i] == x) {
            return i;
        }
    }
    return -1;
}

//Funcion usada para eliminar datos no numericos y solo dejar los numericos
string removeNonNumeric(string input) {
    string output = "";
    for (unsigned int i = 0; i < input.length(); i++) {
        if (isdigit(input[i])) {
            output += input[i];
        }
    }
    return output;
}

//Menu de Ingreso de contrasena
bool Login_Access(unsigned int max_attemps, vector <string> Users, vector <string> Pwds){
    char nombreUsuario[30];
    char contrasena[30];
    char caracter;
    bool Login_Approved=false;
    unsigned int j;
    for (unsigned int i=0; i<max_attemps;i++){
            gotoxy(10,0);
            cout << "Login de Usuario";
            gotoxy(10,1);
            cout << "----------------";
            gotoxy(6,4);
            cout << "Usuario:                                                       ";
            gotoxy(6,5);
            cout << "Contrasena:                                                    ";
            gotoxy(15,4);
            cin.getline(nombreUsuario,30);

            gotoxy(18,5);
            int a = 0;
            while (a < 30) {
                    caracter = getch();
                    if (caracter == '\r') break; // Salir del bucle si se presiona Enter
                    putchar('*');
                    contrasena[a] = caracter;
                    a++;
            }
            contrasena[a] = '\0'; // Agregar carácter nulo al final de la cadena

            // Comprobar si las credenciales son válidas.
            j=0;
            gotoxy(0,20);
            while (j<Users.size()){
                if (strcmp(nombreUsuario, Users[j].c_str()) == 0 && strcmp(contrasena, Pwds[j].c_str()) == 0) {
                        gotoxy(6,6);
                        cout << "Inicio de sesion exitoso. Bienvenido, " << nombreUsuario << "." << endl;
                        Login_Approved=true;
                        break;
                }
                j++;
            }
            if(!Login_Approved){
                gotoxy(6,6);
                cout << "Incorrecto. Intento numero " << i+1 << " le quedan " << max_attemps-(i+1) << endl;
            }
            else{
                break;
            }
     }
     return Login_Approved;
}

size_t split(const string &txt, vector<string> &strs, char ch)
{
    size_t pos = txt.find( ch );
    size_t initialPos = 0;
    strs.clear();

    // Decompose statement
    while( pos != std::string::npos ) {
        strs.push_back( txt.substr( initialPos, pos - initialPos ) );
        initialPos = pos + 1;

        pos = txt.find( ch, initialPos );
    }

    // Add the last one
    strs.push_back( txt.substr( initialPos, std::min( pos, txt.size() ) - initialPos + 1 ) );

    return strs.size();
}
//Conver un double a String
string doubleToString(double value) {
    string valueStr = to_string(value);
    // Establecer precisión de dos decimales
    valueStr = to_string(stod(valueStr.substr(0, valueStr.find("."))));
    return valueStr;
}

//Convertir uns estructura de tiempo a string a time
string timeToString(tm time) {
    char buffer[11];
    std::strftime(buffer, 11, "%d/%m/%Y", &time);
    return buffer;
}

