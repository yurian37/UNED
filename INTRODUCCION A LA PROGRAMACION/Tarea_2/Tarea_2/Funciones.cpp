#include <iostream>
#include <string>
#include <algorithm>
#include <string>
#include <vector>
#include "windows.h"
#include <cstdio>
#include <stdio.h>

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

//Menu principal de usuario que toma de entrada el titulo y el arreglo de Opciones.
int menu(string title, vector<string> options) {
    float choice;
    char choice_s[30];
    bool valid_input= false;
    string counter_array[options.size()];
    while (!valid_input) {
        cout << "---------------------------" << endl;
        cout << title << endl;
        cout << "---------------------------" << endl;
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

//Funcion para posicionar el cursor
COORD coord={0,0};
void gotoxy(int x, int y){
    coord.X=x;
    coord.Y=y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),coord);
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
