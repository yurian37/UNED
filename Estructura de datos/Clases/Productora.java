/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

/**
 *
 * @author pabloa1x
 */
public class Productora {
    int idProductora;
    String descripcion;
    Pelicula[] peliculas;
    
    public Productora(int idProductora, String descripcion){
        this.idProductora = idProductora;
        this.descripcion = descripcion;
        this.peliculas = new Pelicula[0];
    }
    
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
    
    public void agregarPelicula(Pelicula pelicula) {
        peliculas[peliculas.length] = pelicula;
    }
}
