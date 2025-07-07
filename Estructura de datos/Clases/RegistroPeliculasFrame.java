/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author pabloa1x
 */
public class RegistroPeliculasFrame extends JFrame {
    private ProductoraManager productoraManager;
    private JTextField textFieldDni;
    private JTextField textFieldNombre;
    private JComboBox<String> comboxboxGenero;
    private JComboBox<String> comboxboxAudiencia;
    private JComboBox<Integer> comboxboxProductora;
    private JTextField textFieldDescripcion;
    
    
    public RegistroPeliculasFrame(ProductoraManager productoraManager) {
        this.productoraManager = productoraManager;
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Registro de Productoras");
        setSize(800, 400);
        setLayout(new GridLayout(7, 2, 10, 10));
        
        JLabel labelDni = new JLabel("DNI:");
        textFieldDni = new JTextField();
        
        JLabel labelNombre = new JLabel("Nombre:");
        textFieldNombre = new JTextField();
        
        
        String[] generos ={
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
        
        JLabel labelGeneros = new JLabel("Genero de la pelicula:");
        comboxboxGenero = new JComboBox<>(generos);
        
        String[] audiencias ={
                "Infantiles",
                "Juveniles",
                "Adultos",
                "Familiares"
        };
        
        JLabel labelAudiencia = new JLabel("Tipo de Audiencia:");
        comboxboxAudiencia = new JComboBox<>(audiencias);
        
        JLabel labelProductora = new JLabel("Productora ID:");
        int[] ids = productoraManager.obtenerIds();
        comboxboxProductora = new JComboBox<>();
        for (int id : ids) {
            comboxboxProductora.addItem(id);
        }
        
        comboxboxProductora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idSeleccionado = (int) comboxboxProductora.getSelectedItem();
                String descripcion = productoraManager.obtenerDescripcion(idSeleccionado);
                textFieldDescripcion.setText(descripcion);
            }
        });
        
        JLabel labelDescripcion = new JLabel("Descripcion:");
        textFieldDescripcion = new JTextField();
        textFieldDescripcion.setText(productoraManager.obtenerDescripcion((int) comboxboxProductora.getSelectedItem()));
        textFieldDescripcion.setEditable(false);
        
        //Boton que hace el registro
        JButton buttonRegistrarPelicula = new JButton("Registrar Pelicula");
        buttonRegistrarPelicula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    Pelicula pelicula = new Pelicula(
                            textFieldDni.getText(),
                            textFieldNombre.getText(),
                            comboxboxGenero.getSelectedItem().toString(),
                            comboxboxAudiencia.getSelectedItem().toString()
                    );
                    int idSeleccionado = (int) comboxboxProductora.getSelectedItem();
                    productoraManager.agregarPeliculaAProductora(idSeleccionado , pelicula);
                    dispose();
                }
            }
        });
        
        add(labelDni);
        add(textFieldDni);
        
        add(labelNombre);
        add(textFieldNombre);
        
        add(labelGeneros);
        add(comboxboxGenero);
        
        add(labelAudiencia);
        add(comboxboxAudiencia);
        
        add(labelProductora);
        add(comboxboxProductora);
        
        add(labelDescripcion);
        add(textFieldDescripcion);
        
        add(buttonRegistrarPelicula);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private boolean validateFields(){
        if (textFieldDni.getText().isEmpty() || textFieldNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
