
package modelo;

public class Usuario {
    private String usuario;
    private String contrasenha;
    private boolean activo;

    public Usuario(String usuario, String contrasenha) {
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.activo = false;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public boolean ingresar(String usuario, String contrasenha){
        boolean result= false;
        if(this.usuario.equalsIgnoreCase(usuario) && 
                this.contrasenha.equals(contrasenha) &&
                !this.activo){
            this.activo = true;
            result= true;
        }
        return result;
    }
    
    public boolean salir(){
        boolean result = false;
        if(this.activo){
            this.activo = false;
            result = true;
        }
        return result;
    }
    
    
    
    
}
