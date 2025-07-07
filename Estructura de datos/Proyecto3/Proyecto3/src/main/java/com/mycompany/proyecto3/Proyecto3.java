/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto3;

/* UNED II Cuatrimestre 2024
 * * Proyecto03: Aplicacion Arbol Binario
 * * Estudiante: Pablo Valenciano 115720043
 * * Fecha 07/08/2024
 * 
 * */

//Librerias a usar
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;


//Clase inicial del proyecto 3
public class Proyecto3 extends JFrame {
    //Clase que arbolBinario que maneja las impresoras
    private final ArbolBinario arbol;

    //Inicio del constructor
    public Proyecto3() {
        arbol = new ArbolBinario();
        //productoraManager = new ProductoraManager();
        initializeUI();
    }

    //Interfaz del Menu Principal
    private void initializeUI() {
        // Configuración de la ventana principal
        setTitle("Menú Principal");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel para la sección 1
        JPanel section1 = new JPanel(new GridLayout(4, 1, 10, 10));
        section1.setBorder(BorderFactory.createTitledBorder("Gestión de Impresoras"));
        section1.setBackground(new Color(224, 255, 255));

        JButton btnInsertarImpresora = new JButton("1.1 Insertar Impresora");
        JButton btnEliminarImpresora = new JButton("1.2 Eliminar Impresora");
        JButton btnBuscarImpresora = new JButton("1.3 Buscar Impresora por ID");
        JButton btnGraficarArbol = new JButton("1.4 Graficar árbol binario");
        
        // Agregar ActionListener al botón para insertar impresora
        btnInsertarImpresora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInsertarImpresora();
            }
        });
        
        // Agregar ActionListener al botón para eliminar impresora
        btnEliminarImpresora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEliminarImpresora();
            }
        });
        // Agregar ActionListener al botón para buscar impresora
        btnBuscarImpresora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarBuscarImpresora();
            }
        });
        
        // Agregar ActionListener al botón para graficar el árbol
        btnGraficarArbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGraficaArbol();
            }
        });

        section1.add(btnInsertarImpresora);
        section1.add(btnEliminarImpresora);
        section1.add(btnBuscarImpresora);
        section1.add(btnGraficarArbol);

        // Panel para la sección 2
        JPanel section2 = new JPanel(new GridLayout(3, 1, 10, 10));
        section2.setBorder(BorderFactory.createTitledBorder("Recorridos del Árbol"));
        section2.setBackground(new Color(255, 228, 225));

        JButton btnRecorridoPreOrden = new JButton("2.1 Recorrido PRE-ORDEN");
        JButton btnRecorridoPostOrden = new JButton("2.2 Recorrido POST-ORDEN");
        JButton btnRecorridoInOrden = new JButton("2.3 Recorrido IN-ORDEN");
        
        // Agregar ActionListener al botón para realizar el recorrido Pre-orden
        btnRecorridoPreOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecorridoPreOrden();
            }
        });
        
        // Agregar ActionListener al botón para realizar el recorrido Post-Orden
        btnRecorridoPostOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecorridoPostOrden();
            }
        });
        
        // Agregar ActionListener al botón para realizar el recorrido In-orden
        btnRecorridoInOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecorridoInOrden();
            }
        });

        section2.add(btnRecorridoPreOrden);
        section2.add(btnRecorridoPostOrden);
        section2.add(btnRecorridoInOrden);
        
        // Configuración de GridBagConstraints para el posicionamiento
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(section1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(section2, gbc);
    }
    
    //Llamada de la inetrfaz segun sea el boton que se presione
    
    private void mostrarInsertarImpresora() {
        InsertarImpresoraDialog dialog = new InsertarImpresoraDialog(arbol);
        dialog.setVisible(true);
    }
    
    private void mostrarEliminarImpresora() {
        EliminarImpresoraDialog dialog = new EliminarImpresoraDialog(arbol);
        dialog.setVisible(true);
    }
    
    private void mostrarBuscarImpresora() {
        BuscarImpresoraDialog dialog = new BuscarImpresoraDialog(arbol);
        dialog.setVisible(true);
    }
    
    private void mostrarGraficaArbol() {
        JFrame graficaFrame = new JFrame("Gráfica del Árbol Binario");
        ArbolBinarioPanel panel = new ArbolBinarioPanel(arbol);
        graficaFrame.add(panel);
        graficaFrame.setSize(800, 600);
        graficaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        graficaFrame.setVisible(true);
    }
    
    private void mostrarRecorridoPreOrden() {
        RecorridoPreOrdenDialog dialog = new RecorridoPreOrdenDialog(arbol);
        dialog.setVisible(true);
    }
    
    private void mostrarRecorridoPostOrden() {
        RecorridoPostOrdenDialog dialog = new RecorridoPostOrdenDialog(arbol);
        dialog.setVisible(true);
    }
    
    private void mostrarRecorridoInOrden() {
        RecorridoInOrdenDialog dialog = new RecorridoInOrdenDialog(arbol);
        dialog.setVisible(true);
    }

    //Llamada que inicia todo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Proyecto3().setVisible(true);
            }
        });
    }
}

