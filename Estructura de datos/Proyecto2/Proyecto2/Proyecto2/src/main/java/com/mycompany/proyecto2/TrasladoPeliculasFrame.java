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
import javax.swing.JTextField;

//Clase que se va a llamar si desea trasladar una cola de peliculas a otra
public class TrasladoPeliculasFrame extends JFrame {
    //Manejo del arreglo de Productoras e inicializacion de los elementos de la interfaz
    private ProductoraManager productoraManager;
    private JComboBox<Integer> origenComboBox;
    private JComboBox<Integer> destinoComboBox;
    private JTextField origenDescripcionField;
    private JTextField destinoDescripcionField;
    private JButton trasladarPeliculasButton;

    //Constructor e inicio de interfaz
    public TrasladoPeliculasFrame(ProductoraManager productoraManager) {
        this.productoraManager = productoraManager;
        initializeUI();
    }

    //Diseno de la interfaz
    private void initializeUI() {
        setTitle("Trasladar Películas");
        setSize(600, 400);
        setLayout(new GridLayout(7, 1, 10, 10));

        // Asegurarse de que haya al menos 2 productoras
        if (productoraManager.listarProductoras().length < 2) {
            JOptionPane.showMessageDialog(this, "Debe haber al menos dos productoras para trasladar películas.");
            dispose();
            return;
        }

        //Solicitud de productora origen segun ID
        JLabel origenLabel = new JLabel("Selecciona productora origen:");
        origenComboBox = new JComboBox<>();
        for (int id : productoraManager.obtenerIds()) {
            origenComboBox.addItem(id);
        }
        origenComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDescripcionOrigen();
            }
        });
        
        origenDescripcionField = new JTextField();
        origenDescripcionField.setEditable(false);

        //Solicitud de productora destino segun ID
        JLabel destinoLabel = new JLabel("Selecciona productora destino:");
        destinoComboBox = new JComboBox<>();
        for (int id : productoraManager.obtenerIds()) {
            destinoComboBox.addItem(id);
        }
        destinoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDescripcionDestino();
            }
        });

        destinoDescripcionField = new JTextField();
        destinoDescripcionField.setEditable(false);

        //Boton Pelicula y funcionalidad de traslado de peliculas
        trasladarPeliculasButton = new JButton("Trasladar Películas");
        trasladarPeliculasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trasladarPeliculas();
            }
        });

        //Insercion de los elementos a la interfaz
        add(origenLabel);
        add(origenComboBox);
        add(new JLabel("Descripción Origen:"));
        add(origenDescripcionField);
        add(destinoLabel);
        add(destinoComboBox);
        add(new JLabel("Descripción Destino:"));
        add(destinoDescripcionField);
        add(trasladarPeliculasButton);

        actualizarDescripcionOrigen();
        actualizarDescripcionDestino();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Funciones que actualizan la descripcion
    private void actualizarDescripcionOrigen() {
        int idSeleccionado = (int) origenComboBox.getSelectedItem();
        origenDescripcionField.setText(productoraManager.obtenerDescripcion(idSeleccionado));
    }

    private void actualizarDescripcionDestino() {
        int idSeleccionado = (int) destinoComboBox.getSelectedItem();
        destinoDescripcionField.setText(productoraManager.obtenerDescripcion(idSeleccionado));
    }

    //Funciona que realiza el traslado
    private void trasladarPeliculas() {
        int origenId = (int) origenComboBox.getSelectedItem();
        int destinoId = (int) destinoComboBox.getSelectedItem();

        //No permite el traslado si la misma productora origen, es la misma que la del destino
        if (origenId == destinoId) {
            JOptionPane.showMessageDialog(this, "La productora origen y destino no pueden ser la misma.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Elementos pivot de cada productora
        Productora origenProductora = null;
        Productora destinoProductora = null;

      
        for (Productora productora : productoraManager.listarProductoras()) {
            if (productora.getIdProductora() == origenId) {
                origenProductora = productora;
            } else if (productora.getIdProductora() == destinoId) {
                destinoProductora = productora;
            }
        }

        //Error a que la productora no posea elementos
        if (origenProductora == null || destinoProductora == null) {
            JOptionPane.showMessageDialog(this, "Error al encontrar las productoras seleccionadas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Error si la productora origen no posee peliculas para trasladar al destinto
        if (origenProductora.getPeliculas().length == 0) {
            JOptionPane.showMessageDialog(this, "La productora origen no tiene películas para trasladar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Tralado de las peliculas por medio de uso de cola
        Pelicula[] origenPeliculas = origenProductora.getPeliculas();
        Pelicula[] destinoPeliculas = destinoProductora.getPeliculas();

        Pelicula[] nuevasPeliculas = new Pelicula[origenPeliculas.length + destinoPeliculas.length];
        System.arraycopy(destinoPeliculas, 0, nuevasPeliculas, 0, destinoPeliculas.length);
        System.arraycopy(origenPeliculas, 0, nuevasPeliculas, destinoPeliculas.length, origenPeliculas.length);

        destinoProductora.setPeliculas(nuevasPeliculas);
        origenProductora.setPeliculas(new Pelicula[0]);

        dispose();
    }
}
