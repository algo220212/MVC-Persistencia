package modelo;

import modelo.TipoTrabajador;
import interfaces.IColecciones;

/**
 *
 * @author Administrator
 */
public class TipoTrabajadorArreglo implements IColecciones{
    private int indice, tamanho;
    private TipoTrabajador[] tipoUsuarios ;
    private final String[] cabecera =  {"CODIGO","NOMBRE"};

    public TipoTrabajadorArreglo() {
         this.indice =0;
        this.tamanho =20;
        this.tipoUsuarios = new  TipoTrabajador[this.tamanho];
       
        this.add(new TipoTrabajador("Operador"));      
        this.add(new TipoTrabajador("Supervisor"));    
        this.add(new TipoTrabajador("Administrador"));
        this.add(new TipoTrabajador("Coordinador"));
    }
    @Override    
    public boolean  add(Object tipousuario){
         boolean resultado = false;
        if(!isLleno()){
            this.tipoUsuarios[this.indice]= (TipoTrabajador)tipousuario;
            this.indice++;
            resultado = true;
        }
        return resultado;
    }
    
    @Override
    public void sort() {
        TipoTrabajador temporal;
        for(int i = 0 ;i<indice ;i++){
            for(int j = 0 ;j<indice ;j++){
                if(this.tipoUsuarios[i].getDescripcion().compareTo(
                                this.tipoUsuarios[j].getDescripcion())< 0){
                    temporal = this.tipoUsuarios[i];
                    this.tipoUsuarios[i] = this.tipoUsuarios[j];
                    this.tipoUsuarios[j] = temporal;
                }
            }
        }
    }

    @Override
    public Object[][] getDatos() {
        Object resultado[][]= null;
        return resultado;
    }

    public Object[] getDatosCombo() {
        Object resultado[] = null;
        if(!isVacio()){
            resultado = new Object[this.indice];
            for(int indice=0; indice < this.indice; indice++){
                resultado[indice] = this.tipoUsuarios[indice];
            }
        }
        return resultado;
    }
    
    @Override
    public boolean update(Object tipo) {
        boolean resultado = false;
        int encontrado = this.find(((TipoTrabajador)tipo).getCodigo());
        if(encontrado>= 0){
            this.tipoUsuarios[encontrado].setDescripcion(((TipoTrabajador)tipo).getDescripcion());
            resultado = true;
        }
        return resultado;    
    }

    @Override
    public boolean remove(String id) {
        boolean resultado = false;
        int encontrado = this.find(id);
        if( encontrado >= 0 ) {
            moverElementos(encontrado);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public String[] getCabecera() {
        return this.cabecera;
    }

    @Override
    public int  find(String codigo) {
        int resultado = -1 ;
         for(int indice=0; indice < this.indice; indice++){
            if(this.tipoUsuarios[indice].getDescripcion().equalsIgnoreCase(codigo)){
                resultado = indice;
                break;
            }
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
        TipoTrabajador[] nuevoArreglo = new  TipoTrabajador[tamanho];
        for (int i = 0; i < this.indice; i++){
            nuevoArreglo[i] = this.tipoUsuarios[i];
        }
        this.tipoUsuarios = nuevoArreglo;
    }

    @Override
    public void moverElementos(int posicion) {
        this.indice--;
        for(;posicion<indice ;posicion++){
            this.tipoUsuarios[posicion] = this.tipoUsuarios[posicion+1];
        }
        this.tipoUsuarios[indice]= null;
    }

    @Override
    public boolean isVacio() {
        return this.indice==0;
    }

    
    
}