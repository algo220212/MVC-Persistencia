/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import datos.Repositorio;
import modelo.PersonaArreglo;
import modelo.TipoTrabajadorArreglo;
import modelo.Persona;
import modelo.TipoTrabajador;
import vista.frmPersonaMantenimiento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.frmPersonaEditar;

/**
 *
 * @author Administrator
 */
public class PersonaMantenimientoController   {
    frmPersonaMantenimiento vista;
    PersonaArreglo  modelo;
    
    
    
    public PersonaMantenimientoController(frmPersonaMantenimiento vista, PersonaArreglo modelo ) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(validar()){
                    if(modelo.find(vista.txtCodigo.getText())== -1 ){
                        String codigo = vista.txtCodigo.getText().toUpperCase();
                        String nombres = vista.txtNombres.getText().toUpperCase();
                        String paterno = vista.txtPaterno.getText().toUpperCase();
                        String materno = vista.txtMaterno.getText().toUpperCase();
                        TipoTrabajador tipoUsuario = (TipoTrabajador)vista.cboTipoUsuario.getSelectedItem();
                        String numeroDocumento = vista.txtNumeroDocumento.getText().toUpperCase();
                        modelo.add( new Persona(codigo, nombres, paterno, materno, numeroDocumento, tipoUsuario));
                        JOptionPane.showMessageDialog(vista, "Registro exitoso" , "Agregar Persona" , JOptionPane.INFORMATION_MESSAGE);
                        limpiarcontroles();
                    }else{
                        JOptionPane.showMessageDialog(vista, "Cóodigo ya esta registrado" , "Agregar Persona" , JOptionPane.ERROR_MESSAGE);
                    }

                }else{
                    JOptionPane.showMessageDialog(vista,"Debe ingresar valores en todos los campos ", "Agregar Persona" , JOptionPane.ERROR_MESSAGE  );
                }
            }
        });
                
        this.vista.btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int fila = vista.tblPersonas.getSelectedRow();
                int columna = vista.tblPersonas.getSelectedColumn();
                if( fila >= 0 ){
                    if( vista.btnEditar.getText().equalsIgnoreCase("Actualizar")){
                        String codigoeliminar = vista.tblPersonas.getValueAt(fila, 0).toString();
                        modelo.remove(codigoeliminar);
                        limpiarcontroles();
                        JOptionPane.showMessageDialog(vista,"Registro eliminado", "Elimiar Persona" , JOptionPane.INFORMATION_MESSAGE );
                    }
                    else{
                        JOptionPane.showMessageDialog(vista, "Estas actualizando un registro de persona", "Elimiar Persona" , JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    if(modelo.isVacio()){
                        JOptionPane.showMessageDialog(vista,"No existen registros para eliminar" , "Elimiar Persona" , JOptionPane.WARNING_MESSAGE );
                    }else{
                        JOptionPane.showMessageDialog(vista,"Selecciones una fila de la tabla ", "Elimiar Persona" , JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
        });
        
        this.vista.btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int fila = vista.tblPersonas.getSelectedRow();
                int columna = vista.tblPersonas.getSelectedColumn();
                if( fila >= 0  &&  vista.btnEditar.getText().equalsIgnoreCase("Actualizar")){
                    Persona p = modelo.getPersona(vista.tblPersonas.getValueAt(fila, 0).toString());
                    vista.txtCodigo.setText(p.getCodigo());
                    vista.txtPaterno.setText(p.getPaterno());
                    vista.txtMaterno.setText(p.getMaterno());
                    vista.txtNombres.setText(p.getNombres());
                    vista.txtNumeroDocumento.setText(p.getDocumento());
                    int indice_tmp = Repositorio.arreglotipousuario.find(p.getTipoUsuario().getDescripcion());
                    vista.cboTipoUsuario.setSelectedIndex(indice_tmp);
                    habilitarControlesEdicion(true);
                    
                }else if( vista.btnEditar.getText().equalsIgnoreCase("Grabar")) {
                    if(validar()){
                        String codigo = vista.txtCodigo.getText().toUpperCase();
                        String nombres = vista.txtNombres.getText().toUpperCase();
                        String paterno = vista.txtPaterno.getText().toUpperCase();
                        String materno = vista.txtMaterno.getText().toUpperCase();
                        TipoTrabajador tipoUsuario = (TipoTrabajador)vista.cboTipoUsuario.getSelectedItem();
                        String numeroDocumento = vista.txtNumeroDocumento.getText().toUpperCase();
                        Persona p = new Persona(codigo, nombres, paterno, materno, numeroDocumento, tipoUsuario);
                        modelo.update(p);
                        
                        limpiarcontroles();
                        habilitarControlesEdicion(false);
                        JOptionPane.showMessageDialog(vista,"Actualizacion exitosa", "Actualizar Persona" , JOptionPane.INFORMATION_MESSAGE );
                    }else {
                        JOptionPane.showMessageDialog(vista, "Debe ingresar valores en todos los campos" , "Actualizar Persona" , JOptionPane.WARNING_MESSAGE );
                    }
                
                }
                else  {
                    if(modelo.isVacio()){
                        JOptionPane.showMessageDialog(vista,"No existen registros para actualizar" , "Actualizar Persona" , JOptionPane.WARNING_MESSAGE );
                    }else{
                        JOptionPane.showMessageDialog(vista,"Selecciones una fila de la tabla para Actualizar: ", "Actualizar Persona" , JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
        });
        
        this.vista.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if( vista.btnEditar.getText().equalsIgnoreCase("Grabar")){
                    limpiarcontroles();
                    habilitarControlesEdicion(false);
                    
                }
            }
        });
        
        this.vista.btnEditarNuevaVentana.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = vista.tblPersonas.getSelectedRow();
                if( fila >= 0) {
                    frmPersonaEditar vistapersona = new frmPersonaEditar();               
                    
                    Persona  modelopersona =  modelo.getPersona(vista.tblPersonas.getValueAt(fila, 0).toString());
                    PersonaEditarController controlador = new PersonaEditarController(vistapersona,modelopersona);
                    controlador.iniciar_vista();
                    vistapersona.setVisible(true);   
                    vista.dispose();
                }else {
                    JOptionPane.showMessageDialog(vista,"Selecciones una fila de la tabla para Editar: ", "Editar Persona" , JOptionPane.ERROR_MESSAGE );                    
                }    
            }
        });  
        
        
        this.vista.btnSalir.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SerializadoraGen.serializar("datosPersona.txt", modelo);
                System.exit(0);
            }
        });
        
        this.vista.tblPersonas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String codigo=null;
                int fila = vista.tblPersonas.rowAtPoint(e.getPoint());
                int columna = vista.tblPersonas.columnAtPoint(e.getPoint());
                if (fila > -1 ){
                        System.out.println(vista.tblPersonas.getValueAt(fila,1));
                }
            }
        });
                
                
    }

    public void iniciar_vista(){
        vista.setTitle( "Registro de Persona" );
        vista.setLocationRelativeTo(null);      
        
        this.vista.tblPersonas.setAutoCreateRowSorter(true);
        Repositorio.arreglotipousuario.sort();        
        DefaultComboBoxModel modelocombobox = new DefaultComboBoxModel();
        for( Object o : Repositorio.arreglotipousuario.getDatosCombo()){
            modelocombobox.addElement(o);
        }
        this.vista.cboTipoUsuario.setModel(modelocombobox);
        limpiarcontroles();
        habilitarControlesEdicion(false);
        
    }


    private void limpiarcontroles() {
        vista.txtCodigo.setText("");
        vista.txtNombres.setText("");
        vista.txtPaterno.setText("");
        vista.txtMaterno.setText("");
        vista.txtNumeroDocumento.setText("");
        vista.cboTipoUsuario.setSelectedIndex(-1);
        DefaultTableModel modelotabla = new DefaultTableModel(this.modelo.getDatos(),this.modelo.getCabecera());
        this.vista.tblPersonas.setModel(modelotabla);
        this.vista.txtCodigo.requestFocus();
        this.vista.txtCodigo.selectAll();

        
    }
    private void habilitarControlesEdicion(boolean editar){
        if (editar){
            vista.btnAgregar.setEnabled(false);
            vista.btnEliminar.setEnabled(false);
            vista.btnEditar.setEnabled(true);
            vista.btnCancelar.setEnabled(true);
            vista.btnEditar.setText("Grabar");
            vista.txtCodigo.setEnabled(false);
            this.vista.txtNombres.requestFocus();
            this.vista.txtNombres.selectAll();
        }
        else {
            vista.btnAgregar.setEnabled(true);
            vista.btnEliminar.setEnabled(true);
            vista.btnEditar.setEnabled(true);
            vista.btnCancelar.setEnabled(false);
            vista.btnEditar.setText("Actualizar");
            vista.txtCodigo.setEnabled(true);
            this.vista.txtCodigo.requestFocus();
            this.vista.txtCodigo.selectAll();
        }
    }

    private boolean validar() {
        boolean resultado = false;
        if (this.vista.txtCodigo.getText().length()!= 0 &&
                this.vista.txtNombres.getText().length()!= 0 &&
                this.vista.txtPaterno.getText().length()!= 0 &&
                this.vista.txtMaterno.getText().length()!= 0 &&
                this.vista.txtNumeroDocumento.getText().length()!= 0 &&
                this.vista.cboTipoUsuario.getSelectedIndex() != -1
                ){
            resultado = true;
        }
        return resultado;
    }
    
}
