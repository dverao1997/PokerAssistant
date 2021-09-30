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
public class Empleado extends Persona{
    private String id_empleado;
    private String rol;

    public Empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Empleado(String id_empleado, String rol, String id_persona, String nombre, String apellido, String telefono, String genero) {
        super(id_persona, nombre, apellido, telefono, genero);
        this.id_empleado = id_empleado;
        this.rol = rol;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
