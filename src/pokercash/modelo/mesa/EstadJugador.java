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
public class EstadJugador {

    private int id_est_jug;
    private double ingreso_total;
    private int id_jugador;
    private int id_mesa;
    private double perdidas;
    private double ganancias;
    private double deudas_en_contra;
    private double deudas_a_favor;

    public EstadJugador(int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas_en_contra, double deudas_a_favor) {
        this.id_est_jug = id_est_jug;
        this.ingreso_total = ingreso_total;
        this.id_jugador = id_jugador;
        this.id_mesa = id_mesa;
        this.perdidas = perdidas;
        this.ganancias = ganancias;
        this.deudas_en_contra = deudas_en_contra;
        this.deudas_a_favor = deudas_a_favor;
    }

    public EstadJugador() {
    }

    public int getId_est_jug() {
        return id_est_jug;
    }

    public void setId_est_jug(int id_est_jug) {
        this.id_est_jug = id_est_jug;
    }

    public double getIngreso_total() {
        return ingreso_total;
    }

    public void setIngreso_total(double ingreso_total) {
        this.ingreso_total = ingreso_total;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public double getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(double perdidas) {
        this.perdidas = perdidas;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

    public double getDeudas_en_contra() {
        return deudas_en_contra;
    }

    public void setDeudas_en_contra(double deudas_en_contra) {
        this.deudas_en_contra = deudas_en_contra;
    }

    public double getDeudas_a_favor() {
        return deudas_a_favor;
    }

    public void setDeudas_a_favor(double deudas_a_favor) {
        this.deudas_a_favor = deudas_a_favor;
    }

}
