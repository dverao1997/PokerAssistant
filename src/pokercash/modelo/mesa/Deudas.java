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
public class Deudas extends EstadJugador{
    private int id_deudas;
    private double valor;
    private LocalDate fecha;
    private String tipo;

    public Deudas(int id_deudas, double valor, LocalDate fecha, String tipo, int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas) {
        super(id_est_jug, ingreso_total, id_jugador, id_mesa, perdidas, ganancias, deudas);
        this.id_deudas = id_deudas;
        this.valor = valor;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public Deudas() {
    }


    public int getId_deudas() {
        return id_deudas;
    }

    public void setId_deudas(int id_deudas) {
        this.id_deudas = id_deudas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
