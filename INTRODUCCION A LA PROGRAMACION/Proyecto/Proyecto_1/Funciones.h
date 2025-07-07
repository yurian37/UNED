#ifndef FUNCIONES_H_INCLUDED
#define FUNCIONES_H_INCLUDED

#include <string>
#include <vector>
#include "windows.h"
#include <fstream>
#include <ctime>

//Funciones usadas en main.cpp provenientes de Funciones.cpp
int menu(std::string title, std::vector<std::string> options);
void Titulo(std::string title);
void gotoxy(int x, int y);
bool esSoloDigitos(std::string str);
size_t splitCount(std::string str, char delimiter);
int index(std::vector<std::string>& vec, std::string x);
void Check_Cin_failed();
std::string removeNonNumeric(std::string input);
bool Login_Access(unsigned int max_attempts, std::vector <std::string> Users, std::vector <std::string> Pwds);
std::string doubleToString(double value);
std::string timeToString(tm time);

#endif // FUNCIONES_H_INCLUDED
