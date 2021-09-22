/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author CyberLink
 */
public class DetallEmpleado {
    private int id_deta_empl;
    private double ingreso;
    private LocalDate fecha;
    private String id_empleado;
    private int id_mesa;

    public DetallEmpleado() {
    }

    public DetallEmpleado(int id_deta_empl, double ingreso, LocalDate fecha, String id_empleado, int id_mesa) {
        this.id_deta_empl = id_deta_empl;
        this.ingreso = ingreso;
        this.fecha = fecha;
        this.id_empleado = id_empleado;
        this.id_mesa = id_mesa;
    }

    public int getId_deta_empl() {
        return id_deta_empl;
    }

    public void setId_deta_empl(int id_deta_empl) {
        this.id_deta_empl = id_deta_empl;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }
    
    
}
