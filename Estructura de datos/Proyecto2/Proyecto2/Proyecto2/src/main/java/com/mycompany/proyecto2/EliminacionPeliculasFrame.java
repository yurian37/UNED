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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//Clase de la interfaz para eliminar peliculas
public class EliminacionPeliculasFrame extends JFrame {
    //Inicializacion de los arreglos y elementos a usar en la interfaz
    private ProductoraManager productoraManager;
    private JComboBox<Integer> productoraComboBox;
    private JTextField descripcionField;
    private JTable peliculasTable;
    private DefaultTableModel tableModel;
    private JButton eliminarPeliculaButton;
    
    //Llamada y constructor al ser llamado
    public EliminacionPeliculasFrame(ProductoraManager productoraManager) {
        this.productoraManager = productoraManager;
        initializeUI();
    }

    //Interfaz Grafica
    private void initializeUI() {
        setTitle("Eliminar Película");
        setSize(600, 400);
        setLayout(new GridLayout(6, 1, 10, 10));

        //Indicacion de seleccion de productora
        JLabel productoraLabel = new JLabel("Selecciona una productora:");
        int[] ids = productoraManager.obtenerIds();
        productoraComboBox = new JComboBox<>();
        for (int id : ids) {
            productoraComboBox.addItem(id);
        }
        productoraComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInformacionProductora();
            }
        });

        //Texto, para saber cual producta es segun Id
        descripcionField = new JTextField();
        descripcionField.setEditable(false);

        // Crear la tabla y el modelo de tabla
        tableModel = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Género", "Tipo de Audiencia"}, 0);
        peliculasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(peliculasTable);

        //Boton para eliminar pelicula
        eliminarPeliculaButton = new JButton("Eliminar película");
        //Accionamiento del boton
        eliminarPeliculaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPrimeraPelicula();
            }
        });

        //Ingreso de los elementos a la interfaz
        add(productoraLabel);
        add(productoraComboBox);
        add(new JLabel("Descripción:"));
        add(descripcionField);
        add(scrollPane);
        add(eliminarPeliculaButton);

        actualizarInformacionProductora();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Se actualiza las peliculas, sea cuando se cambie una productora o se elimine alguna pelicula
    private void actualizarInformacionProductora() {
        int idSeleccionado = (int) productoraComboBox.getSelectedItem();
        for (Productora productora : productoraManager.listarProductoras()) {
            if (productora.getIdProductora() == idSeleccionado) {
                descripcionField.setText(productora.getDescripcion());
                actualizarListaPeliculas(productora.getPeliculas());
                break;
            }
        }
    }

    //Funciones que actualiza las peliculas en el arreglo de Peliculas de cada productora
    private void actualizarListaPeliculas(Pelicula[] peliculas) {
        tableModel.setRowCount(0); // Limpiar la tabla antes de agregar los datos
        for (Pelicula pelicula : peliculas) {
            tableModel.addRow(new Object[]{pelicula.getDNI(), pelicula.getNombre(), pelicula.getGenero(), pelicula.getTipoAudiencia()});
        }
    }

    //Algoritmo para realizar el procedimiento de cola Fifo
    private void eliminarPrimeraPelicula() {
        int idSeleccionado = (int) productoraComboBox.getSelectedItem();
        for (Productora productora : productoraManager.listarProductoras()) {
            if (productora.getIdProductora() == idSeleccionado) {
                Pelicula[] peliculas = productora.getPeliculas();
                if (peliculas.length > 0) {
                    Pelicula[] nuevasPeliculas = new Pelicula[peliculas.length - 1];
                    System.arraycopy(peliculas, 1, nuevasPeliculas, 0, peliculas.length - 1);
                    productora.setPeliculas(nuevasPeliculas);
                    actualizarListaPeliculas(nuevasPeliculas);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay películas para eliminar.");
                }
                break;
            }
        }
    }
}


