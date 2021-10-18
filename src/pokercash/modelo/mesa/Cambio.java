/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.mesa;

/**
 *
 * @author CyberLink
 */
public class Cambio extends EstadJugador{
    int id_cambio;
    double valor;

    public Cambio(int id_cambio, double valor, int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas_en_contra, double deudas_a_favor) {
        super(id_est_jug, ingreso_total, id_jugador, id_mesa, perdidas, ganancias, deudas_en_contra, deudas_a_favor);
        this.id_cambio = id_cambio;
        this.valor = valor;
    }

    public Cambio() {
    }

    public int getId_cambio() {
        return id_cambio;
    }

    public void setId_cambio(int id_cambio) {
        this.id_cambio = id_cambio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
