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


//Interfaz para eliminar impresoras
public class EliminarImpresoraDialog extends JDialog {
    private final JTextField txtId;
    private final ArbolBinario arbol;

    //Constructor de la Ineterfaz para eliminar impresora segun ID
    public EliminarImpresoraDialog(ArbolBinario arbol) {
        this.arbol = arbol;
        setTitle("Eliminar Impresora");
        setSize(300, 150);
        setModal(true);
        setLayout(new GridLayout(2, 2));

        // Campo de entrada para el ID
        add(new JLabel("ID de la impresora a eliminar:"));
        txtId = new JTextField();
        add(txtId);

        // Botón para eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarImpresora();
            }
        });
        add(btnEliminar);
    }

    //Funciones que realiza el proceso o no de la eliminacion de impresora
    private void eliminarImpresora() {
        try {
            int id = Integer.parseInt(txtId.getText());
            if (arbol.eliminar(id)) {
                //JOptionPane.showMessageDialog(this, "Impresora eliminada con éxito.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la impresora. ID no encontrado o tiene 2 hijos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
