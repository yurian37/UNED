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

//Librerias a usar
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//Funcion que va a permitir eliminar productoras
public class EliminacionProductorasFrame extends JFrame {
    //Inicializacion de arreglos y elementos a mostrar
    private ProductoraManager productoraManager;
    private JTable productorasTable;
    private DefaultTableModel tableModel;
    private JButton eliminarProductoraButton;

    //Constructor de la clase e inicializacion de la interfaz
    public EliminacionProductorasFrame(ProductoraManager productoraManager) {
        this.productoraManager = productoraManager;
        initializeUI();
    }

    //Interfaz
    private void initializeUI() {
        setTitle("Eliminar Productora");
        setSize(500, 400);
        setLayout(new GridLayout(2, 1, 10, 10));

        // Configuración de la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Descripción"}, 0);
        productorasTable = new JTable(tableModel);
        actualizarListaProductoras();
        JScrollPane scrollPane = new JScrollPane(productorasTable);

        // Botón para eliminar productora
        eliminarProductoraButton = new JButton("Eliminar última productora");
        eliminarProductoraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUltimaProductora();
            }
        });

        //Adicion de elementos a la interfaz
        add(scrollPane);
        add(eliminarProductoraButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Funcion que actualiza la tabla
    private void actualizarListaProductoras() {
        //Limpiar la tabla
        tableModel.setRowCount(0);
        Productora[] productoras = productoraManager.listarProductoras();
        for (Productora productora : productoras) {
            tableModel.addRow(new Object[]{productora.getIdProductora(), productora.getDescripcion()});
        }
    }

    //Funcion que elimina la ultima productora tipo pila LIFO
    private void eliminarUltimaProductora() {
        Productora[] productoras = productoraManager.listarProductoras();
        //Condicion si por algura razon no hay productoras
        if (productoras.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay productoras para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Seleccion de ultima productora
        Productora ultimaProductora = productoras[productoras.length - 1];
        //Revision que la productora este vacia
        if (ultimaProductora.getPeliculas().length > 0) {
            JOptionPane.showMessageDialog(this, "No se puede eliminar la productora porque tiene películas registradas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Manejo luego de la eliminacion de la productora
        Productora[] nuevasProductoras = new Productora[productoras.length - 1];
        System.arraycopy(productoras, 0, nuevasProductoras, 0, productoras.length - 1);
        productoraManager.setProductoras(nuevasProductoras);

        actualizarListaProductoras();
    }
}