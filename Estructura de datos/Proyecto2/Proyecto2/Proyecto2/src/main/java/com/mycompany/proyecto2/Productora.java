/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;


/* UNED II Cuatrimestre 2024
 * * Proyecto02: Pilas y Colas
 * * Estudiante: Pablo Valenciano 115720043
 * * Fecha 05/07/2024
 * 
 * */
//Clase Productora
public class Productora {
    //Atributos de Productora
    int idProductora;
    String descripcion;
    //Arreglo Primitivo de tipo Pelicula
    Pelicula[] peliculas;
    
    //Constructor de la clase productora
    public Productora(int idProductora, String descripcion){
        this.idProductora = idProductora;
        this.descripcion = descripcion;
        this.peliculas = new Pelicula[0];
    }
    
    //Funciones para manipular elementos de productor
    public int getIdProductora(){
        return idProductora;
    }
    
    public void setIdProductora(int idProductora){
        this.idProductora = idProductora;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public Pelicula[] getPeliculas(){
        return peliculas;
    }
    
    public void setPeliculas(Pelicula[] peliculas){
        this.peliculas = peliculas;
    }
    
    //Funcion para agregar peliculas
    public void agregarPelicula(Pelicula pelicula) {
        Pelicula[] nuevasPeliculas = new Pelicula[peliculas.length + 1];
        System.arraycopy(peliculas, 0, nuevasPeliculas, 0, peliculas.length);
        nuevasPeliculas[peliculas.length] = pelicula;
        this.peliculas = nuevasPeliculas;
    }
}
