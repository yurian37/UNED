/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;


/**
 *
 * @author pabloa1x
 */
import java.util.Arrays;

public class ProductoraManager {
    private Productora[] productoras;
    
    public ProductoraManager(){
        this.productoras = new Productora[0];
    }
    
    public Productora[] listarProductoras() {
        if (productoras == null){
            return new Productora[0];
        }
        Productora[] copiaProductoras = new Productora[productoras.length];
        System.arraycopy(productoras,0, copiaProductoras,0,productoras.length);
        return copiaProductoras;
    }
    
    public void agregarProductora(Productora productora) {
        Productora[] nuevasProductoras = Arrays.copyOf(productoras, productoras.length + 1);
        nuevasProductoras[productoras.length] = productora;
        this.productoras = nuevasProductoras;
    }
    
    public int[] obtenerIds() {
        int[] ids = new int[productoras.length];
        for (int i = 0; i < productoras.length; i++) {
            ids[i] = productoras[i].getIdProductora();
        }
        return ids;
    }
    
    public String obtenerDescripcion(int idSeleccionado) {
        for (int i = 0; i < productoras.length; i++) {
            if (productoras[i].getIdProductora() == idSeleccionado) {
                return productoras[i].getDescripcion();
            }
        }
        return "";
    }
    
    public void agregarPeliculaAProductora(int idProductora, Pelicula pelicula) {
        for (int i = 0; i < productoras.length; i++) {
            if (productoras[i].getIdProductora() == idProductora) {
                productoras[i].agregarPelicula(pelicula);
                break;
            }
        }
    }
}
