/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto3;

/* UNED II Cuatrimestre 2024
 * * Proyecto03: Aplicacion Arbol Binario
 * * Estudiante: Pablo Valenciano 115720043
 * * Fecha 07/08/2024
 * 
 * */

//Clase nodo, el cual se construye el arbolBinario
public class Nodo {
    private Impresora impresora;
    private Nodo izquierdo;
    private Nodo derecho;

    // Constructor
    public Nodo(Impresora impresora) {
        this.impresora = impresora;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Métodos getter y setter para impresora
    public Impresora getImpresora() {
        return impresora;
    }

    public void setImpresora(Impresora impresora) {
        this.impresora = impresora;
    }

    // Métodos getter y setter para izquierdo
    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    // Métodos getter y setter para derecho
    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
