/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.persona;

import java.time.LocalDate;

/**
 *
 * @author CyberLink
 */
public class Jugador extends Persona{
    private int id_jugador;
    private LocalDate fecha_ingreso;
    private int estado;

    public Jugador() {
    }

    public Jugador(int id_jugador, LocalDate fecha_ingreso, int estado, int id_persona, String nombre, String apellido, String telefono, String genero) {
        super(id_persona, nombre, apellido, telefono, genero);
        this.id_jugador = id_jugador;
        this.fecha_ingreso = fecha_ingreso;
        this.estado = estado;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

 
}
