/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import pokercash.modelo.persona.ModlEmpleado;
import pokercash.vista.persona.VistaEmpleado;
import pokercash.vista.principal.VistaPrincipal;

/**
 *
 * @author CyberLink
 */
public class CtrlPrincipal {
    
    private VistaPrincipal v;
    
    public CtrlPrincipal(VistaPrincipal v) {
        this.v = v;
        v.setTitle("Poker Table Assistant");
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }
    
    public void iniciarControl() {
        v.getMnuEmpleados().addActionListener(l->Empleados());
    }
    
    public void Empleados(){
        ModlEmpleado mod=new ModlEmpleado();
        VistaEmpleado vis=new VistaEmpleado();
        v.getDktContenedor().add(vis);
        CtrlEmpleados control=new CtrlEmpleados(mod, vis);
        control.IniciarControl();
    }
}
