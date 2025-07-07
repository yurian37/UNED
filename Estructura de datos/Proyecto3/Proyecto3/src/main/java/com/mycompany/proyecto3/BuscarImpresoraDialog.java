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

//Interfaz de la busqueda de impresora de ID
public class BuscarImpresoraDialog extends JDialog {
    private final JTextField txtId;
    private final JLabel lblResultado;
    private final ArbolBinario arbol;

    //Constructor de la inetrfaz
    public BuscarImpresoraDialog(ArbolBinario arbol) {
        this.arbol = arbol;
        setTitle("Buscar Impresora");
        setSize(300, 200);
        setModal(true);
        setLayout(new GridLayout(3, 2));

        // Campo de entrada para el ID
        JPanel panelId = new JPanel(new FlowLayout());
        panelId.add(new JLabel("ID de la impresora a buscar:"));
        txtId = new JTextField(10);
        panelId.add(txtId);
        add(panelId);

        // Label para mostrar el resultado
        lblResultado = new JLabel(" ");
        add(lblResultado);

        // Botón para buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarImpresora();
            }
        });
        add(btnBuscar);
    }

    //Metodo que usa las caracteristicas del arbolBinario para encontrar y mostrar en caso de encontrar o no la impresora
    private void buscarImpresora() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Impresora impresora = arbol.buscar_impresora(id);
            if (impresora != null) {
                lblResultado.setText("ID: " + impresora.getId() + ", Marca: " + impresora.getMarca() +
                        ", Tipo: " + impresora.getTipo() + ", Peso: " + impresora.getPeso() + " Kg");
            } else {
                lblResultado.setText("Impresora no encontrada.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

