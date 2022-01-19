
package principal;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
    
    public static void main(String args[]){
        System.out.println("Hola!!!");
         
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        vista.frmIngreso flogin = new vista.frmIngreso();
        controlador.IngresoControlador  controlador = new controlador.IngresoControlador( datos.Repositorio.admin , flogin);
        controlador.iniciar();
        
    }
}
