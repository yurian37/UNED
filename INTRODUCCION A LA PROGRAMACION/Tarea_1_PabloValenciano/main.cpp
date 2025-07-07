//Librerias para funciones del programa
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

//Funcion obtenida Web para evitar problemas con el CIN que loopea al ingresar letras, obtenida de:  https://stackoverflow.com/questions/5864540/infinite-loop-with-cin-when-typing-string-while-a-number-is-expected
void Check_Cin_failed(){
    if (cin.fail())
        {
            cin.clear();
            cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        }
}

//Funcion creada por mi de Menu, para estandarizar menu, se ingresa un titulo y un arreglo con su extension con las opciones.
int Create_Menu(string title, string Options[], int len_array){
    //Muestra el titulo de forma Ordenada
    cout<<"****"<<title<<"****"<< endl;
    //Inicializar un arreglo que inserte los numeros de las opciones
    int counter_array[len_array];
    // Loop que muestra las opciones con su respectiva opcion.
    for (int i = 1; i <= len_array; i++){
            // String que toma el valor descrito del arreglo que contiene las opciones ingresadas.
            string OpcionActual = Options[i-1];
            // Salida en pantalla de las opciones 1-<Nombre de la Opcion y asi hasta completar.
            cout << i << "-" << OpcionActual << endl;
            // Insercion de los posible numeros.
            counter_array[i-1]=i;
    }
    // Seleccion del menu
    // Seleccion de captura en formato string
    string Option_Choosen_s;
    // Seleccion de captura en formato flotanto
    float Option_Choosen;
    // error para capturar valores con "," que genera arreglos.
    bool Error;
    // Loop hasta obtener un valor valido
    while (true){
            // No hay error
            Error=false;
            // Inicializa la opcion en un valor no valido
            Option_Choosen=0;
            //Salida de seleccionar una opcion.
            cout << "Selecciona una opcion: ";
            //Captura el valor en formato string.
            cin >> Option_Choosen_s;
            // Funcion que evita el error de error por captura de un caracter
            Check_Cin_failed();
            // Invalid Argument exception handled, dado por valores que tienen una letra
            try {
                // Conversion de String a Flotanto
                Option_Choosen = stof(Option_Choosen_s);
            }
            catch (std::invalid_argument& e){
                Error=true;
            }
            // Revisa si elemento capturado esta en la lista de opciones del menu, funcion obtenida de web: https://www.techiedelight.com/check-if-an-element-exists-in-an-array-cpp/
            bool exists = std::find(counter_array, counter_array + len_array, Option_Choosen) != counter_array + len_array;
            // Revision de condiciones de fallo, si no hay fallo, hace break y dicha opcion es valida
            if (exists && !(Option_Choosen_s.find(",") != std::string::npos) && !Error){
                break;
            }
            // De ser algun fallo, se indica que no se pudo procesar y que inserte un valor perteneciente del menu
            else{
                //Salto de linea
                cout << endl;
                // Salida indicando un error
                cout << "Por favor ingresar un numero perteneciente al menu"<<endl;
            }
    }
    // Retorna el valor de la opcion escogida
    return Option_Choosen;
}

// Funcion usada para la opcion 1 del menu, donde se ingresan datos de forma individual
int Ingreso_numeros(){
    //Inicializacion de variables, en orden captura del valor insertado formato string y flotante, Resta del valor capturado y el valor truncado en la unidad y error en caso de ingreso de una ","
    string Input_Number_s;
    float Input_Number;
    double res;
    int Integet_Value;
    bool Error;
    // Loop que sale hasta que se obtenga un valor valido
    do {
            // Inicializa para cada valor en false, el error.
            Error=false;
            // Salida de terminal de pedir un valor.
            cout << "Ingresa un numero: ";
            // Ingreso por un usuario de un valor.
            cin >> Input_Number_s;
            // Revisa si el valor si es un dato numero y repara error de cin en caso de ser un string
            Check_Cin_failed();
            // Captura de errot en caso de ingresar una alguna letra
            try{
                // Conversion de String a flotante
                Input_Number = stof(Input_Number_s);
                // Conversion de flotante a entero
                Integet_Value = static_cast<int>(Input_Number);
                // Resta que valida si el numero ingresado es flotante o entero.
                res=Input_Number-Integet_Value;
            }
            // Error dado por la insercion de una ","
            catch (std::invalid_argument& e){
                // Conversion de la linea de error
                Error=true;
            }
            // Revisa los multiples errores si sale todo bien, hace break y sino muestra un mensaje al usuario, volviendo a iniciar el ciclo.
            // Se revisa sea positivo (No sea 0 ni menor a 0), se revisa sea con flotante con decimales, se revisa no sea caracter y tampoco posea ","
            if (Input_Number<=0 or res>0 or Input_Number_s.find(",") != std::string::npos or Error){
                cout << "Error habeis ingresado un valor incorrecto"<<endl;
                cin.clear();
                cin.ignore(123,'\n');
            }
            else{
                break;
            }
    }
    while(true);
    // Retorna el valor y lo atrapa para agregarlo a la lista de numero
    return Input_Number;
}

//Funcion disenada por mi, que generaliza el caso para obtener numeros pares(res_mod=0) e impares (res_mod=1)
void BuscarNumeros(int list_int[], int res_mod){
    // Inicializador de variable que cuenta la cantidad de numeros que sean impares o pares
    int contador=0;
    // Atrapa los numeros que cumplan condicion
    int numeros_encontrados[10];
    // String que segun sea el modo se cambia a impar o par
    string Is_Odd="";
    // Cmabio del string segun sea el modo
    if (res_mod==1){
        Is_Odd="impares";
    }
    else{
        Is_Odd="pares";
    }
    // Loop que captura valores que cumplan condicion
    for(int i=0; i<10; i++){
        if(list_int[i]!=0 && list_int[i] % 2 ==res_mod){
            // Inserccion de valores que cumplan condicion
            numeros_encontrados[contador]=list_int[i];
            // Aumento de valor en caso de cumplir condicion
            contador++;
        }
    }
    // En caso de que no se haya dado ningun caso, un mensaje especial
    if(contador==0){
        cout<<"No hay numeros "<<Is_Odd<<". ";
    }
    // En caso de tener al menos un caso, otro mensaje.
    else {
            // Indicar si estamos en caso par o impar
            cout<<"Ingreso "<<contador<<" numeros "<<Is_Odd<<" los cuales son:";
            // Loop para mostrar todos los casos que cumplan condicion.
            for(int o=0; o<contador; o++){
                // Caso de ser el ultimo dato capturado
                if (o==contador-1) {
                    cout<<" "<<numeros_encontrados[o]<<". ";
                }
                // Para el resto de casos desde el primero hasta el penultimo
                else {
                    cout<<" "<<numeros_encontrados[o]<<", ";
                }
            }
    }
}

// Imprimir los valores Impares
void Imprimir_numeros_impares(int list_int[]){
   BuscarNumeros(list_int,1);
}

//Imprimir valores pares
void Imprimir_numeros_pares(int list_int[]){
    BuscarNumeros(list_int,0);
}


//Funcion principal
int main()
{
    // Opciones del menu
    string Opciones[3]={"Inclusion de numeros","Analisis de numeros","Salir del Programa"};
    // Inicializar el numero de opciones
    int Cant_Opciones;
    // Inicializar valor de Opcion escogida por el usuario
    int Opcion;
    // Inicializar la lista de 10 numeros a 0
    int lista_numeros[10]={0,0,0,0,0,0,0,0,0,0};
    // Tamano de la lista
    int TAM=10;
    // Asignar el valor de la cantidad de opciones del menu.
    Cant_Opciones=sizeof(Opciones)/sizeof(Opciones[0]);
    // Bool que permite salir del menu.
    bool Continuar=true;
    // Loop del menu principal
    do {
            // Captura del valor del menu del usuario, siendo validado en la funcion
            Opcion=Create_Menu("Menu Principal", Opciones, Cant_Opciones);
            // Se le indica al usuario la opcion seleccionada.
            cout << "Has seleccionado la opcion "<<Opcion<< " la cual es: " << Opciones[Opcion-1]<<endl;
            // Intente hacerlo con switch y case pero dio muchos problemas y por ser solo dos opciones,salia mas rentable el if y else
            if(Opcion==1){
                    // Reinicio de la lista a 0s
                    fill_n(lista_numeros,10,0);
                    // Loops para ingresar 10 datos
                    for (int i=0;i<TAM;i++){
                        // Ingreso de los datos 1 por 1 y validando cada dato
                        lista_numeros[i]=Ingreso_numeros();
                        // Validando la condicion del numero 13
                        if (lista_numeros[i]==13){
                            // Muestra solo numeros pares
                            Imprimir_numeros_pares(lista_numeros);
                            // Rompe el while
                            Continuar=false;
                            //Rompe el for
                            break;
                        }
                    }
            }
            //Seleccion del menu opcion 2
            else{
                    // Muestra numeros impares.
                    Imprimir_numeros_impares(lista_numeros);
                    // Muestra numeros pares.
                    Imprimir_numeros_pares(lista_numeros);
                    // Salto de linea.
                    cout<<endl;
            }
    }
    // Opcion 3 o que se haya dado condicion de salida numero 13
    while(Opcion!=3 && Continuar);
    // fin del programa.
    return 0;
}
