package controlador;

import datos.Repositorio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.PersonaArreglo;
import modelo.TipoTrabajadorArreglo;
import vista.frmIngreso;
import vista.frmPersonaMantenimiento;

public class IngresoControlador {
    Usuario usuario;
    frmIngreso fIngreso;

    public IngresoControlador(Usuario usuario, frmIngreso fIngreso) {
        this.usuario = usuario;
        this.fIngreso = fIngreso;
        
        this.fIngreso.btnSalir.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.fIngreso.btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( usuario.ingresar(fIngreso.txtUsuario.getText(), fIngreso.txtContrasenha.getText()) ) {
                    
                    String mensaje = "<b>Señor(a):"+ fIngreso.txtUsuario.getText() +"</b><br>";
                        mensaje += "<font color=red>Usted inicio sesion en la app de Algorítmica II</font><br>";
                    
                    librerias.Email email =  new librerias.Email("jzavaletac@unmsm.edu.pe" , "Inicio de sesión Exitoso", mensaje);
                    Thread enviar = new Thread(email);
                    enviar.start();

                        
                    fIngreso.dispose();
                    frmPersonaMantenimiento vista = new frmPersonaMantenimiento();
                    Repositorio.modelopersonas =  (PersonaArreglo)SerializadoraGen.deserializar("datosPersona.txt");
                    PersonaMantenimientoController controlador = new PersonaMantenimientoController(vista,Repositorio.modelopersonas);
                    controlador.iniciar_vista();
                    vista.setVisible(true);        
                }
                else{
                    JOptionPane.showMessageDialog(fIngreso, "Error");
                }
            }
        } );

        
    }
    
    public void iniciar(){
        this.fIngreso.setLocationRelativeTo(null);
        this.fIngreso.setVisible(true);
    }
    
}
