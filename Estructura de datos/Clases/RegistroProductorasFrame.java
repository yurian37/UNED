/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author pabloa1x
 */
public class RegistroProductorasFrame extends JFrame {
    private ProductoraManager productoraManager;
    private JTextField textFieldId;
    private JTextField textFieldDescripcion;
    
    public RegistroProductorasFrame(ProductoraManager productoraManager) {
        this.productoraManager = productoraManager;
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Registro de Productoras");
        setSize(400, 400);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel labelId = new JLabel("ID:");
        textFieldId = new JTextField();
        textFieldId.setEditable(false);
        // Autogenerar ID
        textFieldId.setText(String.valueOf(productoraManager.listarProductoras().length + 1));
        
        JLabel labelDescripcion = new JLabel("Descripcion:");
        textFieldDescripcion = new JTextField();
        
        //Boton que hace el registro
        JButton buttonRegistrar = new JButton("Registrar Pedido");
        buttonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    Productora productora = new Productora(
                            Integer.valueOf(textFieldId.getText()),
                            textFieldDescripcion.getText()
                    );
                    productoraManager.agregarProductora(productora);
                    dispose();
                }
            }
        });
        
        add(labelId);
        add(textFieldId);
        add(labelDescripcion);
        add(textFieldDescripcion);
        add(buttonRegistrar);
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private boolean validateFields(){
        if (textFieldDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Descripcion Vacia", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
