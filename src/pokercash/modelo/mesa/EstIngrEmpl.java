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
    private int id_empleado;
    private int id_mesa;

    public EstIngrEmpl() {
    }

    public EstIngrEmpl(int id_est_ingr, double ingreso, int id_empleado, int id_mesa) {
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

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

  
    
}
