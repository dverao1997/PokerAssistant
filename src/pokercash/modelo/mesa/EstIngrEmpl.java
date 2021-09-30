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
public class EstIngrEmpl{

    private int id_est_ingr;
    private double ingreso;
    private String id_empleado;
    private String id_mesa;

    public EstIngrEmpl(int id_est_ingr, double ingreso, String id_empleado, String id_mesa) {
        this.id_est_ingr = id_est_ingr;
        this.ingreso = ingreso;
        this.id_empleado = id_empleado;
        this.id_mesa = id_mesa;
    }

    public int getId_est_ingr() {
        return id_est_ingr;
    }

    public void setId_est_ingr(int id_est_ingr) {
        this.id_est_ingr = id_est_ingr;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(String id_mesa) {
        this.id_mesa = id_mesa;
    }
    
    
}
