/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Administrator
 */
public class Persona implements Serializable{
    
    private final String codigo;
    private String nombres;
    private String paterno;
    private String materno;
    private String documento;
    // private boolean activo;
    private TipoTrabajador tipo;

    public Persona(String codigo, String nombres, String paterno, 
                    String materno, String documento, 
                    TipoTrabajador tipo) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.documento = documento;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo + ", nombres=" + nombres + 
               ", apellidoPaterno=" + paterno + 
               ", apellidoMaterno=" + materno ;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String apellidoPaterno) {
        this.paterno = apellidoPaterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String apellidoMaterno) {
        this.materno = apellidoMaterno;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoTrabajador getTipoUsuario() {
        return tipo;
    }

    public void setTipoUsuario (TipoTrabajador tipo) {
        this.tipo = tipo;
    }

    public String  getCodigo() {
        return this.codigo;
    }

}
