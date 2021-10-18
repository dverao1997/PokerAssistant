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
public class Propinas {
    private int id_propina;
    private int id_mesa;
    private double propina_servi;
    private double propina_deler;

    public Propinas(int id_propina, int id_mesa, double propina_servi, double propina_deler) {
        this.id_propina = id_propina;
        this.id_mesa = id_mesa;
        this.propina_servi = propina_servi;
        this.propina_deler = propina_deler;
    }

    public Propinas() {
    }

    public int getId_propina() {
        return id_propina;
    }

    public void setId_propina(int id_propina) {
        this.id_propina = id_propina;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public double getPropina_servi() {
        return propina_servi;
    }

    public void setPropina_servi(double propina_servi) {
        this.propina_servi = propina_servi;
    }

    public double getPropina_deler() {
        return propina_deler;
    }

    public void setPropina_deler(double propina_deler) {
        this.propina_deler = propina_deler;
    }
    
}
