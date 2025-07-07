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
import java.util.Arrays;

//Clase que maneja el arregla de Productoras, e implicitamente las peliculas
public class ProductoraManager {
    //Manejo de productoras
    private Productora[] productoras;
    private int contador;
    
    //Constructor, inicializa productoras en 0
    public ProductoraManager(){
        contador=0;
        this.productoras = new Productora[0];
    }
    
    //Funcion que permite listas productoras y si es vacia no de error
    public Productora[] listarProductoras() {
        if (productoras == null){
            return new Productora[0];
        }
        Productora[] copiaProductoras = new Productora[productoras.length];
        System.arraycopy(productoras,0, copiaProductoras,0,productoras.length);
        return copiaProductoras;
    }
    
    //Funcion para agregar productoras
    public void agregarProductora(Productora productora) {
        Productora[] nuevasProductoras = Arrays.copyOf(productoras, productoras.length + 1);
        nuevasProductoras[productoras.length] = productora;
        this.productoras = nuevasProductoras;
        contador++;
    }
    
    //Funcion para evitar problemas de registro con mismos ids
    public int UltimoIDRegistrado(){
        return contador;
    }
    
    //Funcion para obtener la lista de ids
    public int[] obtenerIds() {
        int[] ids = new int[productoras.length];
        for (int i = 0; i < productoras.length; i++) {
            ids[i] = productoras[i].getIdProductora();
        }
        return ids;
    }
    
    //Funcion dada al tener un id, se obtenga la descripcion de la productora
    public String obtenerDescripcion(int idSeleccionado) {
        for (int i = 0; i < productoras.length; i++) {
            if (productoras[i].getIdProductora() == idSeleccionado) {
                return productoras[i].getDescripcion();
            }
        }
        return "";
    }
    
    //Funcion que relaciona la pelicula a su productora
    public void agregarPeliculaAProductora(int idProductora, Pelicula pelicula) {
        for (int i = 0; i < productoras.length; i++) {
            if (productoras[i].getIdProductora() == idProductora) {
                productoras[i].agregarPelicula(pelicula);
                break;
            }
        }
    }
    
    //Funcion para manipular productoras
    public void setProductoras(Productora[] productoras) {
        this.productoras = productoras;
    }
}
