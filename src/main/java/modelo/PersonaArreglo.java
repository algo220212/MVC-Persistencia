
package modelo;

import modelo.Persona;
import interfaces.IColecciones;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class PersonaArreglo  implements IColecciones, Serializable{
    private Persona[] personas;
    private int indice, tamanho;
    private final String[] cabecera =  {"CODIGO","PATERNO",
                            "MATERNO","NOMBRES", "NUMERO DOC.",
                            "TIPO USUARIO", "ESTADO"};

    public PersonaArreglo() {
        this.indice =0;
        this.tamanho =2;
        this.personas = new Persona[this.tamanho];
    }
    
    @Override
    public boolean add (Object p){
        boolean resultado = false;
        if(!isLleno()){
            this.personas[this.indice]= (Persona)p;
            this.indice++;
            resultado = true;
        }
        return resultado;
    }
    
    @Override
    public int find(String codigo) {
        int resultado = -1;
         for(int indice=0; indice < this.indice; indice++){
            if( this.personas[indice].getCodigo().equalsIgnoreCase(codigo) ) {
                resultado = indice;
                break;
            }
        }
        return resultado;
    }
    

    public Persona getPersona(String codigo) {
        Persona resultado = null;
         for(int indice=0; indice < this.indice; indice++){
            if( this.personas[indice].getCodigo().equalsIgnoreCase(codigo) ) {
                resultado = this.personas[indice];
                break;
            }
        }
        return resultado;
    }
     
    @Override
    public Object[][] getDatos(){
        Object resultado[][] = new Object[this.indice][7];
        if(!isVacio()){
            for(int indice = 0; indice< this.indice;indice++){
                resultado[indice][0] = this.personas[indice].getCodigo();
                resultado[indice][1] = this.personas[indice].getPaterno();
                resultado[indice][2] = this.personas[indice].getMaterno();
                resultado[indice][3] = this.personas[indice].getNombres();
                resultado[indice][4] = this.personas[indice].getDocumento();
                resultado[indice][5] = this.personas[indice].getTipoUsuario();
                resultado[indice][6] = "ACTIVO";
            }
        }
        return resultado;
    }

    @Override
    public String[] getCabecera() {
        return cabecera;
    }

    @Override
    public boolean remove(String codigoeliminar) {
        boolean resultado = false;
        int encontrado = this.find(codigoeliminar);
        if( encontrado >= 0  ) {
            moverElementos(encontrado);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public boolean update(Object persona) {
        boolean resultado = false;
        int encontrado = this.find(((Persona)persona).getCodigo());
        if(encontrado>= 0){
            this.personas[encontrado].setPaterno(((Persona)persona).getPaterno());
            this.personas[encontrado].setMaterno(((Persona)persona).getMaterno());
            this.personas[encontrado].setNombres(((Persona)persona).getNombres());
            this.personas[encontrado].setDocumento(((Persona)persona).getDocumento());
            this.personas[encontrado].setTipoUsuario(((Persona)persona).getTipoUsuario());
            resultado = true;
        }
        return resultado;
    }

    @Override
    public boolean isLleno() {
        boolean resultado = false;
        if (this.indice == this.tamanho){
            aumentarTamanho();
        }
        return resultado;
    }
    
    @Override
    public void aumentarTamanho() {
        int tamanho = this.tamanho*2;
        Persona[] nuevoArreglo = new  Persona[tamanho];
        for(int i=0; i < this.indice; i++){
            nuevoArreglo[i] = this.personas[i];
        }
        this.personas = nuevoArreglo;
    }

    @Override
    public void moverElementos(int posicion) {
        this.indice--;
        for(;posicion<indice ;posicion++){
            this.personas[posicion] = this.personas[posicion+1];
        }
        this.personas[indice]= null;
    }

    @Override
    public boolean isVacio() {
        return this.indice==0;
    }

    @Override
    public void sort() {
        Persona temporal;
        for(int i = 0 ;i<indice ;i++){
            for(int j = 0 ;j<indice ;j++){
                if(this.personas[i].getCodigo().compareTo(this.personas[j].getCodigo())< 0){
                    temporal = this.personas[i];
                    this.personas[i] = this.personas[j];
                    this.personas[j] = temporal;
                }
            }
        }        
    }

   
    
}
