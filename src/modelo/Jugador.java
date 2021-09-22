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
public class Jugador extends Persona{
    private String id_jugador;
    private double ingreso_total;

    public Jugador() {
    }

    public Jugador(String id_jugador, double ingreso_total, String id_persona, String nombre, String apellido, String telefono) {
        super(id_persona, nombre, apellido, telefono);
        this.id_jugador = id_jugador;
        this.ingreso_total = ingreso_total;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    public double getIngreso_total() {
        return ingreso_total;
    }

    public void setIngreso_total(double ingreso_total) {
        this.ingreso_total = ingreso_total;
    }
    
    
}
