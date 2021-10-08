/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import pokercash.controlador.inventario.CtrlProductos;
import pokercash.controlador.persona.CtrlEmpleados;
import pokercash.modelo.inventario.ModlProducto;
import pokercash.modelo.persona.ModlEmpleado;
import pokercash.vista.persona.VistaEmpleado;
import pokercash.vista.principal.VistaPrincipal;
import pokercash.vista.inventario.VistaProducto;

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
        v.getMnuProducto().addActionListener(l->Productos());
    }
    
    public void Empleados(){
        ModlEmpleado mod=new ModlEmpleado();
        VistaEmpleado vis=new VistaEmpleado();
        v.getDktContenedor().add(vis);
        CtrlEmpleados control=new CtrlEmpleados(mod, vis);
        control.IniciarControl();
    }
    
    public void Productos(){
        ModlProducto mod=new ModlProducto();
        VistaProducto pro=new VistaProducto();
        v.getDktContenedor().add(pro);
        CtrlProductos control=new CtrlProductos(mod, pro);
        control.IniciarControl();
    }
}
