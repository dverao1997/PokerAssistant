/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import pokercash.controlador.persona.CtrlUsuario;
import pokercash.controlador.inventario.CtrlFichas;
import pokercash.controlador.inventario.CtrlProductos;
import pokercash.controlador.persona.CtrlEmpleados;
import pokercash.modelo.inventario.ModlFichas;
import pokercash.modelo.inventario.ModlProducto;
import pokercash.modelo.persona.ModlEmpleado;
import pokercash.modelo.persona.ModlUsuario;
import pokercash.vista.inventario.VistaFichas;
import pokercash.vista.persona.VistaEmpleado;
import pokercash.vista.principal.VistaPrincipal;
import pokercash.vista.inventario.VistaProducto;
import pokercash.vista.persona.VistaUsuario;

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
        v.getMnuFichas().addActionListener(l->Fichas());
        v.getMnuUsuario().addActionListener(l->Usuario());
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
    
    public void Fichas(){
        ModlFichas mod=new ModlFichas();
        VistaFichas fic=new VistaFichas();
        v.getDktContenedor().add(fic);
        CtrlFichas control=new CtrlFichas(mod, fic);
        control.IniciarControl();
    }
    public void Usuario(){
        ModlUsuario mod=new ModlUsuario();
        VistaUsuario vis=new VistaUsuario();
        v.getDktContenedor().add(vis);
        CtrlUsuario control=new CtrlUsuario(vis, mod);
        control.IniciarControl();
    }
}
