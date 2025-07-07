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

//Librerias a usar
import javax.swing.*;

//Clase para disenar el arbol binario, el cual va a manejar las constructoras
public class ArbolBinario {
    private Nodo raiz;

    // Constructor el cual va a iniciar en null
    public ArbolBinario() {
        this.raiz = null;
    }
    
    //Metodo para obterner la Raiz del arbol
    public Nodo getRaiz(){
        return raiz;
    }
    

    // Método para insertar una impresora en el árbol
    public boolean insertar(Impresora impresora) {
        if (buscar(impresora.getId())) {
            return false; // ID duplicado, no se inserta
        }
        raiz = insertarRecursivo(raiz, impresora);
        return true; // Inserción exitosa
    }

    //Metodo recursivo de insercion de nodo, donde se valora el ID si es mayor o menor algun nodo ya existente
    private Nodo insertarRecursivo(Nodo nodo, Impresora impresora) {
        if (nodo == null) {
            return new Nodo(impresora);
        }

        if (impresora.getId() < nodo.getImpresora().getId()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), impresora));
        } else if (impresora.getId() > nodo.getImpresora().getId()) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), impresora));
        }

        return nodo;
    }
    
    // Método para eliminar una impresora del árbol por ID
    public boolean eliminar(int id) {
        if (!buscar(id)) {
            // ID no encontrado
            return false; 
        }
        raiz = eliminarRecursivo(raiz, id);
        // Eliminación exitosa
    return true; 
    }

    //Metodo recursivo que realiza la busqueda del ID e intenta eliminarlo
    private Nodo eliminarRecursivo(Nodo nodo, int id) {
        if (nodo == null) {
            // ID no encontrado
            return null; 
        }

        if (id < nodo.getImpresora().getId()) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), id));
        } else if (id > nodo.getImpresora().getId()) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), id));
        } else {
            // Nodo encontrado
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null; // Nodo hoja
            } else if (nodo.getIzquierdo() == null) {
                // Solo un hijo derecho
                return nodo.getDerecho(); 
            } else if (nodo.getDerecho() == null) {
                // Solo un hijo izquierdo
                return nodo.getIzquierdo(); 
            } else {
                // Nodo con dos hijos
                // No se elimina, retorna el nodo original
                JOptionPane.showMessageDialog(null, "No se puede eliminar el nodo porque tiene 2 hijos.", "Error", JOptionPane.ERROR_MESSAGE);
                return nodo; 
            }
        }
    // Retorna el nodo actualizado
    return nodo; 
    }
    
    // Método para buscar una impresora por ID y que retorne booleano, metodo usado tanto para agregar, eliminar y ver si un id ya existe
    private boolean buscar(int id) {
        return buscarRecursivo(raiz, id);
    }

    //Metodo recursivvo de arbol para realizar la busqueda
    private boolean buscarRecursivo(Nodo nodo, int id) {
        if (nodo == null) {
            return false;
        }
        if (nodo.getImpresora().getId() == id) {
             // ID encontrado
            return true;
        }
        return buscarRecursivo(nodo.getIzquierdo(), id) || buscarRecursivo(nodo.getDerecho(), id);
    }
    
    //Método para buscar una impresora por ID y que retorne la impresora pero que retorna la impresora
    public Impresora buscar_impresora(int id) {
        return buscarRecursivo_impresora(raiz, id);
    }

    //Metodo recurivo de busqueda que retorna la impresora
    private Impresora buscarRecursivo_impresora(Nodo nodo, int id) {
        if (nodo == null) {
            return null; // ID no encontrado
        }
        if (nodo.getImpresora().getId() == id) {
            // ID encontrado, retornar la impresora
            return nodo.getImpresora(); 
        } else if (id < nodo.getImpresora().getId()) {
            // Buscar en el subárbol izquierdo
            return buscarRecursivo_impresora(nodo.getIzquierdo(), id); 
        } else {
            // Buscar en el subárbol derecho
            return buscarRecursivo_impresora(nodo.getDerecho(), id); 
        }
    }
}


