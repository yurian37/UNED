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
import java.awt.*;

//Clase para mostrar el arbol de forma grafico
public class ArbolBinarioPanel extends JPanel {
    private final ArbolBinario arbol;

    //Consutructor del Panel
    public ArbolBinarioPanel(ArbolBinario arbol) {
        this.arbol = arbol;
        setBackground(Color.WHITE);
    }

    //Escritura del Panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol.getRaiz() != null) {
            dibujarNodo(g, arbol.getRaiz(), getWidth() / 2, 30, 100);
        }
    }

    private void dibujarNodo(Graphics g, Nodo nodo, int x, int y, int espacio) {
        if (nodo != null) {
            g.drawString(String.valueOf(nodo.getImpresora().getId()), x, y);
            if (nodo.getIzquierdo() != null) {
                g.drawLine(x, y + 5, x - espacio, y + 50);
                dibujarNodo(g, nodo.getIzquierdo(), x - espacio, y + 50, espacio / 2);
            }
            if (nodo.getDerecho() != null) {
                g.drawLine(x, y + 5, x + espacio, y + 50);
                dibujarNodo(g, nodo.getDerecho(), x + espacio, y + 50, espacio / 2);
            }
        }
    }
}
