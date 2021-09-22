/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author CyberLink
 */
public class Usuario {
    private String nomb_usu;
    private String password;
    private String id_empleado;

    public Usuario() {
    }

    public Usuario(String nomb_usu, String password, String id_empleado) {
        this.nomb_usu = nomb_usu;
        this.password = password;
        this.id_empleado = id_empleado;
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

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    
}
