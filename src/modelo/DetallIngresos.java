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
public class DetallIngresos {
    private int id_deta_ing;
    private double ingreso;
    private String hora;
    private String id_jugador;
    private int id_mesa;

    public DetallIngresos() {
    }

    public DetallIngresos(int id_deta_ing, double ingreso, String hora, String id_jugador, int id_mesa) {
        this.id_deta_ing = id_deta_ing;
        this.ingreso = ingreso;
        this.hora = hora;
        this.id_jugador = id_jugador;
        this.id_mesa = id_mesa;
    }

    public int getId_deta_ing() {
        return id_deta_ing;
    }

    public void setId_deta_ing(int id_deta_ing) {
        this.id_deta_ing = id_deta_ing;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }
    
}
