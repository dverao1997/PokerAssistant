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
public class Persona {
    private int id_persona;
    private String nombre;
    private String apellido;
    private String telefono;
    private String genero;

    public Persona() {
    }

    public Persona(int id_persona, String nombre, String apellido, String telefono, String genero) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.genero = genero;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

  
}
