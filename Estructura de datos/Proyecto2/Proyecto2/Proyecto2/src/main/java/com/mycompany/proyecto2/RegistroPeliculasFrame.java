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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//Registro de Peliculas
public class RegistroPeliculasFrame extends JFrame {
    //Atributos donde se manejan productoras y los elementos de interfaz
    private ProductoraManager productoraManager;
    private JTextField textFieldDni;
    private JTextField textFieldNombre;
    private JComboBox<String> comboxboxGenero;
    private JComboBox<String> comboxboxAudiencia;
    private JTable productorasTable;
    private DefaultTableModel tableModel;
    private JTextField textFieldDescripcion;

    //Constructor del registro e inicializacion de la interfaz
    public RegistroPeliculasFrame(ProductoraManager productoraManager) {
        this.productoraManager = productoraManager;
        initializeUI();
    }

    //Interfaz de usuario
    private void initializeUI() {
        setTitle("Registro de Películas");
        setSize(800, 600);
        setLayout(new GridLayout(8, 2, 10, 10));

        //Solicitud DNI de la pelicula
        JLabel labelDni = new JLabel("DNI:");
        textFieldDni = new JTextField();

        //Solicitud Nombre de la pelicula
        JLabel labelNombre = new JLabel("Nombre:");
        textFieldNombre = new JTextField();

        //Solicitud de generos de la Pelicula
        String[] generos = {
                "Acción",
                "Aventura",
                "Catástrofe",
                "Ciencia Ficción",
                "Comedia",
                "Documental",
                "Drama",
                "Fantasía",
                "Musical",
                "Suspenso",
                "Terror"
        };

        JLabel labelGeneros = new JLabel("Género de la película:");
        comboxboxGenero = new JComboBox<>(generos);

        //Solicitud del tipo de audiencia
        String[] audiencias = {
                "Infantiles",
                "Juveniles",
                "Adultos",
                "Familiares"
        };

        JLabel labelAudiencia = new JLabel("Tipo de Audiencia:");
        comboxboxAudiencia = new JComboBox<>(audiencias);

        //Solicitud de la Productora y creacion de la tabla
        JLabel labelProductora = new JLabel("Selecciona una productora:");
        tableModel = new DefaultTableModel(new Object[]{"ID", "Descripción"}, 0);
        productorasTable = new JTable(tableModel);
        productorasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        actualizarListaProductoras();
        JScrollPane scrollPane = new JScrollPane(productorasTable);

        //Accion al seleccionar una productora
        productorasTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = productorasTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int idSeleccionado = (int) tableModel.getValueAt(selectedRow, 0);
                        String descripcion = productoraManager.obtenerDescripcion(idSeleccionado);
                        textFieldDescripcion.setText(descripcion);
                    }
                }
            }
        });

        //Text, para visualizar la productora escogida
        JLabel labelDescripcion = new JLabel("Descripción:");
        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setEditable(false);

        // Botón que hace el registro de la Pelicula
        JButton buttonRegistrarPelicula = new JButton("Registrar Película");
        buttonRegistrarPelicula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Validar si todos los compos estan llenos
                if (validateFields()) {
                    Pelicula pelicula = new Pelicula(
                            textFieldDni.getText(),
                            textFieldNombre.getText(),
                            comboxboxGenero.getSelectedItem().toString(),
                            comboxboxAudiencia.getSelectedItem().toString()
                    );
                    int selectedRow = productorasTable.getSelectedRow();
                    //Revision si se escogio o no una productora
                    if (selectedRow != -1) {
                        int idSeleccionado = (int) tableModel.getValueAt(selectedRow, 0);
                        productoraManager.agregarPeliculaAProductora(idSeleccionado, pelicula);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una productora.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //Insercion de los elementos a la interfaz
        add(labelDni);
        add(textFieldDni);
        add(labelNombre);
        add(textFieldNombre);
        add(labelGeneros);
        add(comboxboxGenero);
        add(labelAudiencia);
        add(comboxboxAudiencia);
        add(labelProductora);
        add(scrollPane);
        add(labelDescripcion);
        add(textFieldDescripcion);
        add(buttonRegistrarPelicula);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Validacion del llenado de los datos
    private boolean validateFields() {
        if (textFieldDni.getText().isEmpty() || textFieldNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void actualizarListaProductoras() {
        tableModel.setRowCount(0); // Limpiar la tabla
        Productora[] productoras = productoraManager.listarProductoras();
        for (Productora productora : productoras) {
            tableModel.addRow(new Object[]{productora.getIdProductora(), productora.getDescripcion()});
        }
    }
}


