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
public class Jugador extends Persona{
    private String id_jugador;
    private String fecha_ingreso;

    public Jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    
    public Jugador(String id_jugador, String fecha_ingreso, String id_persona, String nombre, String apellido, String telefono, String genero) {
        super(id_persona, nombre, apellido, telefono, genero);
        this.id_jugador = id_jugador;
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    
    
}
