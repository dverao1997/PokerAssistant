/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.mesa;

import java.time.LocalDate;

/**
 *
 * @author CyberLink
 */
public class Mesa {
    private int id_mesa;
    private double casilla;
    private LocalDate fecha;
    private double gastos;

    public Mesa() {
    }

    public Mesa(int id_mesa, double casilla, LocalDate fecha, double gastos) {
        this.id_mesa = id_mesa;
        this.casilla = casilla;
        this.fecha = fecha;
        this.gastos = gastos;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public double getCasilla() {
        return casilla;
    }

    public void setCasilla(double casilla) {
        this.casilla = casilla;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

  
    
    
}
