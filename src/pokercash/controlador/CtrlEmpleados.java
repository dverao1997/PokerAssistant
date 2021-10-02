/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import pokercash.modelo.persona.ModlEmpleado;
import pokercash.vista.empleado.VistaEmpleado;

/**
 *
 * @author CyberLink
 */
public class CtrlEmpleados {
    private ModlEmpleado m;
    private VistaEmpleado v;

    public CtrlEmpleados(ModlEmpleado m, VistaEmpleado v) {
        this.m = m;
        this.v = v;
        v.setTitle("Empleados");
        v.setVisible(true);
        
    }
    
    public void IniciarControl(){
        
    }
}
