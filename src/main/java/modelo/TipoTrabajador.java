package modelo;

import java.io.Serializable;
/**
 *
 * @author Administrator
 */
public class TipoTrabajador implements Serializable{
    private String  codigo;
    private String descripcion;
    public static int id;

    

    public TipoTrabajador(String descripcion) {
        this.descripcion = descripcion;
        this.codigo = String.valueOf(id);
        id++;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return  descripcion ;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
