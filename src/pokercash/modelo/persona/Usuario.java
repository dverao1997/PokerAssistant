/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.persona;

/**
 *
 * @author CyberLink
 */
public class Usuario extends Empleado{
    private String nomb_usu;
    private String password;

    public Usuario() {
    }

    public Usuario(String nomb_usu, String password, int id_empleado) {
        super(id_empleado);
        this.nomb_usu = nomb_usu;
        this.password = password;
    }

  
    
    
    public String getNomb_usu() {
        return nomb_usu;
    }

    public void setNomb_usu(String nomb_usu) {
        this.nomb_usu = nomb_usu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
