/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ControllerAgenda {

    public ModelAgenda modelAgenda;
    public ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_insertar) {
                jbtn_insertar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_modificar){
                jbtn_modificar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_cancelar) {
                jbtn_cancelar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_guardar) {
                jbtn_guardar_actionPerformed();
            }

        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
        cerrar(false);
        botones(true);
        this.viewAgenda.jbtn_insertar.setVisible(false);
        this.viewAgenda.jbtn_guardar.setVisible(false);
        this.viewAgenda.jbtn_cancelar.setVisible(false);
    }

//    /**
//     * Metodo para inicializar la ViewAgenda
//     */
//    public void initComponents() {
//        viewAgenda.setLocationRelativeTo(null);
//        viewAgenda.setTitle("Agenda MVC");
//        viewAgenda.setVisible(true);
//    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    private void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbtn_nuevo.addActionListener(actionListener);
        viewAgenda.jbtn_insertar.addActionListener(actionListener);
        viewAgenda.jbtn_modificar.addActionListener(actionListener);
        viewAgenda.jbtn_guardar.addActionListener(actionListener);
        viewAgenda.jbtn_eliminar.addActionListener(actionListener);
        viewAgenda.jbtn_cancelar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
         modelAgenda.moverPrimerRegistro();
        setValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();
        setValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();
        setValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();
        setValues();
    }

    /**
     * Muestra el nombre y email almacenados en el modelAgenda en el viewAgenda.
     */
    private void setValues() {
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }
    /**
     * Metodo que activa los botones para un nuevo registro.
     */
    private void jbtn_nuevo_actionPerformed() {
        System.out.println("Action del boton jbtn_nuevo");
        cerrar(true);
        this.viewAgenda.jtf_email.setText(null);
        this.viewAgenda.jtf_nombre.setText(null);
        this.viewAgenda.jtf_telefono.setText(null);
        botones(false);
        this.viewAgenda.jbtn_insertar.setVisible(true);
        this.viewAgenda.jbtn_cancelar.setVisible(true);
    }
    /**
     * Metodo que inserta un nuevo registro en la base de datos y verifica que este completo los campos.
     */
    private void jbtn_insertar_actionPerformed() {
        System.out.println("Action del boton jbtn_insertar");
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setEmail(viewAgenda.jtf_email.getText());
        modelAgenda.setTelefono(viewAgenda.jtf_telefono.getText());
        if (modelAgenda.getNombre().isEmpty() ||modelAgenda.getEmail().isEmpty()||modelAgenda.getTelefono().isEmpty())
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        else{
            modelAgenda.Insertar();
            cerrar(false);
            botones(true);
            this.viewAgenda.jbtn_insertar.setVisible(false);
            this.viewAgenda.jbtn_cancelar.setVisible(false);
        }
    }
    /**
     * Metodo que modifica un registro actual
     */
    private void jbtn_modificar_actionPerformed() {
        System.out.println("Action del boton jbtn_modificar");
        cerrar(true);
        botones(false);
        this.viewAgenda.jbtn_guardar.setVisible(true);
        this.viewAgenda.jbtn_cancelar.setVisible(true);
    }
    /**
     * Metodo que guarda cambios en un registro actualizado.
     */
    private void jbtn_guardar_actionPerformed() {
        System.out.println("Action del boton jbtn_guardar");
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setEmail(viewAgenda.jtf_email.getText());
        modelAgenda.setTelefono(viewAgenda.jtf_telefono.getText());
        modelAgenda.Modificar();
        cerrar(false);
        botones(true);
        this.viewAgenda.jbtn_cancelar.setVisible(false);
        this.viewAgenda.jbtn_guardar.setVisible(false);
    }
    /**
     * Metodo que cancela cualquier movimiento.
     */
    private void jbtn_cancelar_actionPerformed() {
        System.out.println("Action del boton jbtn_cancelar");
        cerrar(false);
        botones(true);
        jbtn_anterior_actionPerformed();
        this.viewAgenda.jbtn_cancelar.setVisible(false);
        this.viewAgenda.jbtn_guardar.setVisible(false);
        this.viewAgenda.jbtn_insertar.setVisible(false);
    }
    /**
     * Metodo que elimina un registro.
     */
    private void jbtn_eliminar_actionPerformed() {
        System.out.println("Action del boton jbtn_eliminar");
        modelAgenda.Borrrar();
    }
    /**
     * Medo que activara o desactivara las cajas de texto.
     * @param a variable bolleana para activar o desactivar datos.
     */
    private void cerrar(boolean a){
        this.viewAgenda.jtf_email.setEditable(a);
        this.viewAgenda.jtf_nombre.setEditable(a);
        this.viewAgenda.jtf_telefono.setEditable(a);
    }
    private void botones(boolean a){
        this.viewAgenda.jbtn_anterior.setVisible(a);
        this.viewAgenda.jbtn_siguiente.setVisible(a);
        this.viewAgenda.jbtn_primero.setVisible(a);
        this.viewAgenda.jbtn_ultimo.setVisible(a);
        this.viewAgenda.jbtn_nuevo.setVisible(a);
        this.viewAgenda.jbtn_modificar.setVisible(a);
        this.viewAgenda.jbtn_eliminar.setVisible(a);
    }
}
