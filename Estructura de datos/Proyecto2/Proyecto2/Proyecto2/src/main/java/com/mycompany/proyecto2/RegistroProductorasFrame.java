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

//Libreria qe se van a usar
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//Clase e interfaz para registrar productoras
public class RegistroProductorasFrame extends JFrame {
    //Atributos para manejar las productoras y los elementos de la interfaz
    private ProductoraManager productoraManager;
    private JTextField textFieldId;
    private JTextField textFieldDescripcion;
    private JTable productorasTable;
    private DefaultTableModel tableModel;

    //Constructor y llamado de la interfaz
    public RegistroProductorasFrame(ProductoraManager productoraManager) {
        this.productoraManager = productoraManager;
        initializeUI();
    }

    //Interfaz que se llamara al registrar una productora
    private void initializeUI() {
        setTitle("Registro de Productoras");
        setSize(600, 400);
        setLayout(new GridLayout(6, 2, 10, 10));

        //Indicadion id
        JLabel labelId = new JLabel("ID:");
        textFieldId = new JTextField();
        textFieldId.setEditable(false);
        // Autogenerar ID
        textFieldId.setText(String.valueOf(productoraManager.UltimoIDRegistrado() + 1));

        JLabel labelDescripcion = new JLabel("Descripcion:");
        textFieldDescripcion = new JTextField();

        // Botón que hace el registro de la prductora
        JButton buttonRegistrar = new JButton("Registrar Productora");
        buttonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    Productora productora = new Productora(
                            Integer.valueOf(textFieldId.getText()),
                            textFieldDescripcion.getText()
                    );
                    productoraManager.agregarProductora(productora);
                    actualizarListaProductoras();
                    // Reinicio ID y Descripción después de registrar
                    textFieldId.setText(String.valueOf(productoraManager.UltimoIDRegistrado() + 1));
                    textFieldDescripcion.setText("");
                }
            }
        });

        // Configuración de la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Descripción"}, 0);
        productorasTable = new JTable(tableModel);
        actualizarListaProductoras();
        JScrollPane scrollPane = new JScrollPane(productorasTable);

        //Agregagado de los elementos a la interfaz
        add(labelId);
        add(textFieldId);
        add(labelDescripcion);
        add(textFieldDescripcion);
        add(buttonRegistrar);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Validacion que tenga una descripcion no vacia
    private boolean validateFields() {
        if (textFieldDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Descripcion Vacia", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    //Llenado de la tabla al momento de ingresar productoras
    private void actualizarListaProductoras() {
        tableModel.setRowCount(0); // Limpiar la tabla
        Productora[] productoras = productoraManager.listarProductoras();
        for (Productora productora : productoras) {
            tableModel.addRow(new Object[]{productora.getIdProductora(), productora.getDescripcion()});
        }
    }
}