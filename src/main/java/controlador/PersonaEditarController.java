
package controlador;

import datos.Repositorio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.Persona;
import modelo.TipoTrabajador;
import vista.frmPersonaEditar;
import vista.frmPersonaMantenimiento;

public class PersonaEditarController {
    frmPersonaEditar vista;
    Persona modelo;

    public PersonaEditarController(frmPersonaEditar vista, Persona persona) {
        this.vista =  vista;
        this.modelo = persona;

        this.vista.btnGrabar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                modelo.setNombres(vista.txtNombres.getText().toUpperCase());
                modelo.setPaterno(vista.txtPaterno.getText().toUpperCase());
                modelo.setMaterno(vista.txtMaterno.getText().toUpperCase());
                regrasar();
            }
        });
        
        this.vista.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                regrasar();
            }
        });

        
    }
 
    private void regrasar(){
        frmPersonaMantenimiento vista = new frmPersonaMantenimiento();
        PersonaMantenimientoController controlador = new PersonaMantenimientoController(vista,Repositorio.modelopersonas);
        controlador.iniciar_vista();
        vista.setVisible(true);        
        this.vista.dispose();
    }
    public void iniciar_vista(){
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        
        vista.txtCodigo.setText(modelo.getCodigo());
        vista.txtPaterno.setText(modelo.getPaterno());
        vista.txtMaterno.setText(modelo.getMaterno());
        vista.txtNombres.setText(modelo.getNombres());
        vista.txtNumeroDocumento.setText(modelo.getDocumento());

        DefaultComboBoxModel modelocombobox = new DefaultComboBoxModel();
        for( Object o : Repositorio.arreglotipousuario.getDatosCombo()){
            modelocombobox.addElement(o);
        }
        this.vista.cboTipoUsuario.setModel(modelocombobox);
        
        int indice_tmp = Repositorio.arreglotipousuario.find(modelo.getTipoUsuario().getDescripcion());
        vista.cboTipoUsuario.setSelectedIndex(indice_tmp);
                    
        

    }

}
