/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

/**
 *
 * @author pabloa1x
 */
public class Pelicula {
    private String dni;
    private String nombre;
    private String genero;
    private String tipoAudiencia;
    
    public Pelicula(String dni, String nombre, String genero, String tipoAudiencia){
        this.dni = dni;
        this.nombre = nombre;
        this.genero = genero;
        this.tipoAudiencia = tipoAudiencia;
    }
    
    public String getDNI(){
        return dni;
    }
    
    public void setDNI(String dni){
        this.dni = dni;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
    
    public String getTipoAudiencia(){
        return tipoAudiencia;
    }
    
    public void setTipoAudiencia(String tipoAudiencia){
        this.tipoAudiencia = tipoAudiencia;
    }
}
