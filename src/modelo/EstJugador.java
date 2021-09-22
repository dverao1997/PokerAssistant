/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author CyberLink
 */
public class EstJugador {
    private int id_esta;
    private double est_ingresos;
    private double deuda;
    private LocalDate fecha;
    private String id_jugador;

    public EstJugador() {
    }

    public EstJugador(int id_esta, double est_ingresos, double deuda, LocalDate fecha, String id_jugador) {
        this.id_esta = id_esta;
        this.est_ingresos = est_ingresos;
        this.deuda = deuda;
        this.fecha = fecha;
        this.id_jugador = id_jugador;
    }

    public int getId_esta() {
        return id_esta;
    }

    public void setId_esta(int id_esta) {
        this.id_esta = id_esta;
    }

    public double getEst_ingresos() {
        return est_ingresos;
    }

    public void setEst_ingresos(double est_ingresos) {
        this.est_ingresos = est_ingresos;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }
    
    
}
