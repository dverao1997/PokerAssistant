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
public class IngrJugMesa extends EstadJugador{
    private int id_ingr_jug;
    private double ingreso;
    private String hora;
    private String tipo;

    public IngrJugMesa() {
    }

    public IngrJugMesa(int id_ingr_jug, double ingreso, String hora, String tipo, int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas) {
        super(id_est_jug, ingreso_total, id_jugador, id_mesa, perdidas, ganancias, deudas);
        this.id_ingr_jug = id_ingr_jug;
        this.ingreso = ingreso;
        this.hora = hora;
        this.tipo = tipo;
    }

   

    public int getId_ingr_jug() {
        return id_ingr_jug;
    }

    public void setId_ingr_jug(int id_ingr_jug) {
        this.id_ingr_jug = id_ingr_jug;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    
    
}
