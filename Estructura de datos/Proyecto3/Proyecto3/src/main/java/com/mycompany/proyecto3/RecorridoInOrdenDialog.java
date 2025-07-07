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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Interfaz del recorrido IN-Orden
public class RecorridoInOrdenDialog extends JDialog {
    private final JTextArea textAreaImpresoras;
    private final ArbolBinario arbol;

    public RecorridoInOrdenDialog(ArbolBinario arbol) {
        this.arbol = arbol;
        setTitle("Recorrido IN-ORDEN");
        setSize(300, 200);
        setModal(true);
        setLayout(new BorderLayout());

        // Crear el JTextArea para mostrar los IDs
        textAreaImpresoras = new JTextArea();
        textAreaImpresoras.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaImpresoras);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para cargar el recorrido
        JButton btnCargar = new JButton("Cargar Recorrido");
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarRecorridoInOrden();
            }
        });
        add(btnCargar, BorderLayout.SOUTH);
    }

    private void cargarRecorridoInOrden() {
        StringBuilder ids = new StringBuilder();
        // Iniciar el recorrido en inorden
        recorridoInOrden(arbol.getRaiz(), ids);

        // Mostrar los IDs en el JTextArea
        textAreaImpresoras.setText(ids.toString());
    }

    //Definicion del In-Orden
    private void recorridoInOrden(Nodo nodo, StringBuilder ids) {
        if (nodo != null) {
            recorridoInOrden(nodo.getIzquierdo(), ids);
            ids.append(nodo.getImpresora().getId()).append(" - ");
            recorridoInOrden(nodo.getDerecho(), ids);
        }
    }
}

