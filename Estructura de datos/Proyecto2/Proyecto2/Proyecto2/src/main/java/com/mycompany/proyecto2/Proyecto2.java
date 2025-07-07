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

//Librerias a utilizar
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

//Clase inicial del proyecto 2
public class Proyecto2 extends JFrame {
    //Clase que maneja las productoras
    private ProductoraManager productoraManager;

    //Inicio del constructor
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

        // Crear botones
        JButton buttonRegistroProductora = new JButton("Registro de Productoras");
        JButton buttonRegistroPelicula = new JButton("Registro de Peliculas");
        JButton buttonEliminacionPelicula = new JButton("Eliminacion de Peliculas");
        JButton buttonTraslado = new JButton("Traslado de Peliculas");
        JButton buttonEliminacionProductora = new JButton("Eliminacion de Productoras");

        // Ponerle color a los botones
        buttonRegistroProductora.setBackground(Color.GREEN);
        buttonRegistroPelicula.setBackground(Color.CYAN);
        buttonEliminacionPelicula.setBackground(Color.ORANGE);
        buttonTraslado.setBackground(Color.MAGENTA);
        buttonEliminacionProductora.setBackground(Color.YELLOW);

        // Ponerle una unica fuente estilizada a cada boton
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        buttonRegistroProductora.setFont(buttonFont);
        buttonRegistroPelicula.setFont(buttonFont);
        buttonEliminacionPelicula.setFont(buttonFont);
        buttonTraslado.setFont(buttonFont);
        buttonEliminacionProductora.setFont(buttonFont);

        // Colocar los botones en la interfaz
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(buttonRegistroProductora, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(buttonRegistroPelicula, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonEliminacionPelicula, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonTraslado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonEliminacionProductora, gbc);

        setLocationRelativeTo(null);
        
        //Funcionalidad de Botones
        //Boton para registrar productoras
        buttonRegistroProductora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegistroProductorasFrame(productoraManager).setVisible(true);
            }
        });
        
        //Boton para registrar Pelicula, solo permite ingresar si hay al menos 1 productora
        buttonRegistroPelicula.addActionListener(new ActionListener() {
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
        
        //Boton para eliminar una pelicula de una productora en cola, solo permite ingresar si hay al menos 1 productora
        buttonEliminacionPelicula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (productoraManager.listarProductoras().length !=0){
                    new EliminacionPeliculasFrame(productoraManager).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                "No hay Productoras para asignar",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        //Boton para trasladar de una cola de peliculas hacia otra productora, solo permite ingresar si hay al menos 2 productoras
        buttonTraslado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (productoraManager.listarProductoras().length >1){
                    new TrasladoPeliculasFrame(productoraManager).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                "Debe haber como minimo 2 productoras",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        //Boton para eliminar productoras, solo permite ingresar si hay al menos 1 productora
        buttonEliminacionProductora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (productoraManager.listarProductoras().length !=0){
                    new EliminacionProductorasFrame(productoraManager).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                "No hay Productoras para borrar",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
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
