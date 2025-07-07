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

//Interfaz para insertar impresora al arbol
public class InsertarImpresoraDialog extends JDialog {
    private final JTextField txtId;
    private final JTextField txtMarca;
    private final JComboBox<String> comboTipo;
    private final JTextField txtPeso;
    private final ArbolBinario arbol;

    //Constructor de la inetrfaz
    public InsertarImpresoraDialog(ArbolBinario arbol) {
        this.arbol = arbol;
        setTitle("Insertar Impresora");
        setSize(300, 300);
        setModal(true);
        setLayout(new GridLayout(5, 2));

        // Campos de entrada
        add(new JLabel("ID:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        add(txtMarca);

        add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Laser", "Inyección de tinta", "Matriz de puntos", "3D"});
        add(comboTipo);

        add(new JLabel("Peso (Kg):"));
        txtPeso = new JTextField();
        add(txtPeso);

        // Botón para insertar
        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarImpresora();
            }
        });
        add(btnInsertar);
    }

    //Metodo para insertar impresora al arbol, otorgada por la clase arbolBinario
    private void insertarImpresora() {
        //Validacion de datos
        try {
            int id = Integer.parseInt(txtId.getText());
            String marca = txtMarca.getText().trim();
            if (marca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La marca no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tipo = (String) comboTipo.getSelectedItem();
            double peso = Double.parseDouble(txtPeso.getText());

            Impresora impresora = new Impresora(id, marca, tipo, peso);
            if (arbol.insertar(impresora)) {
                //JOptionPane.showMessageDialog(this, "Impresora insertada con éxito.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo insertar la impresora. ID duplicado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

