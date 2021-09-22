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
public class Empleado extends Persona{
    private String id_empleado;
    private double ingreso_total;
    private int id_rol;

    public Empleado() {
    }

    public Empleado(String id_empleado, double ingreso_total, int id_rol, String id_persona, String nombre, String apellido, String telefono) {
        super(id_persona, nombre, apellido, telefono);
        this.id_empleado = id_empleado;
        this.ingreso_total = ingreso_total;
        this.id_rol = id_rol;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public double getIngreso_total() {
        return ingreso_total;
    }

    public void setIngreso_total(double ingreso_total) {
        this.ingreso_total = ingreso_total;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }


}
