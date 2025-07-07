/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

/**
 *
 * @author pabloa1x
 */
public class Proyecto2 extends JFrame {
    private ProductoraManager productoraManager;

    //Inicio de la clase
    public Proyecto2() {
        productoraManager = new ProductoraManager();
        initializeUI();
    }

    //Interfaz del Menu Principal
    private void initializeUI() {
        setTitle("Gestión de Pedidos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create buttons
        JButton buttonRegistro = new JButton("Registro de Productoras");
        JButton buttonListado = new JButton("Registro de Peliculas");
        JButton buttonBusquedaEdicion = new JButton("Eliminacion de Peliculas");
        JButton buttonComparacion = new JButton("Traslado de Peliculas");
        JButton buttonOrdenamiento = new JButton("Eliminacion de Productoras");

        // Set button colors
        buttonRegistro.setBackground(Color.GREEN);
        buttonListado.setBackground(Color.CYAN);
        buttonBusquedaEdicion.setBackground(Color.ORANGE);
        buttonComparacion.setBackground(Color.MAGENTA);
        buttonOrdenamiento.setBackground(Color.YELLOW);

        // Set button font
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        buttonRegistro.setFont(buttonFont);
        buttonListado.setFont(buttonFont);
        buttonBusquedaEdicion.setFont(buttonFont);
        buttonComparacion.setFont(buttonFont);
        buttonOrdenamiento.setFont(buttonFont);

        // Add buttons to the layout with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(buttonRegistro, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(buttonListado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonBusquedaEdicion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonComparacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonOrdenamiento, gbc);

        setLocationRelativeTo(null);
        
        //Funcionalidad de Botones
        buttonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegistroProductorasFrame(productoraManager).setVisible(true);
            }
        });
        
        buttonListado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (productoraManager.listarProductoras().length !=0){
                    new RegistroPeliculasFrame(productoraManager).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                "No hay Productoras para asignar",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        /*
        buttonBusquedaEdicion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EliminacionPeliculasFrame(productoraManager).setVisible(true);
            }
        });
        
        buttonComparacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TrasladoPeliculasFrame(productoraManager).setVisible(true);
            }
        });
        
        buttonOrdenamiento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EliminacionProductorasFrame(productoraManager).setVisible(true);
            }
        });*/
    }

    //Llamada que inicia todo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Proyecto2().setVisible(true);
            }
        });
    }
}
