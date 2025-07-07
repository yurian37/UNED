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

//Clase dada por el profesor, para la impresora
public class Impresora {
    private int id;
    private String marca;
    private String tipo;
    private double peso;

    // Constructor
    public Impresora(int id, String marca, String tipo, double peso) {
        this.id = id;
        this.marca = marca;
        this.tipo = tipo;
        this.peso = peso;
    }

    // Métodos getter y setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Métodos getter y setter para marca
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Métodos getter y setter para tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Métodos getter y setter para peso
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}

