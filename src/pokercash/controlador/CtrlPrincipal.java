/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.controlador.persona.CtrlUsuario;
import pokercash.controlador.inventario.CtrlFichas;
import pokercash.controlador.mesa.CtrlDialogoMesa;
import pokercash.controlador.inventario.CtrlProductos;
import pokercash.controlador.mesa.CtrlMesa;
import pokercash.controlador.persona.CtrlEmpleados;
import pokercash.modelo.inventario.ModlFichas;
import pokercash.modelo.inventario.ModlProducto;
import pokercash.modelo.mesa.Mesa;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.modelo.persona.ModlEmpleado;
import pokercash.modelo.persona.ModlUsuario;
import pokercash.vista.inventario.VistaFichas;
import pokercash.vista.persona.VistaEmpleado;
import pokercash.vista.principal.VistaPrincipal;
import pokercash.vista.inventario.VistaProducto;
import pokercash.vista.mesa.VistaDialogoMesa;
import pokercash.vista.mesa.VistaMesa;
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
        v.setExtendedState(MAXIMIZED_BOTH);
        v.setLocationRelativeTo(null);

    }

    public void iniciarControl() {
        v.getMnuEmpleados().addActionListener(l -> Empleados());
        v.getMnuProducto().addActionListener(l -> Productos());
        v.getMnuFichas().addActionListener(l -> Fichas());
        v.getMnuUsuario().addActionListener(l -> Usuario());
        v.getMnuMesa().addActionListener(l -> CrearMesa());
        v.getMnuAbrirMesa().addActionListener(l -> CargarDialogo());
        v.getBtnAceptar().addActionListener(l -> {
            int s = v.getTablaMesa().getSelectedRow();
            ModlMesa ms = new ModlMesa();
            List<Mesa> lp = ms.Listar();
            if (s != -1) {
                if (lp.get(s).getEstado() != 0) {
                    
                    AbrirMesa(lp.get(s).getId_mesa());
                } else {
                    JOptionPane.showMessageDialog(v, "Mesa ya finalizada");
                }

            } else {
                JOptionPane.showMessageDialog(v, "Seleccionar mesa para continuar");
            }
        });
    }

    public void Empleados() {
        ModlEmpleado mod = new ModlEmpleado();
        VistaEmpleado vis = new VistaEmpleado();
        v.getDktContenedor().add(vis);
        CtrlEmpleados control = new CtrlEmpleados(mod, vis);
        control.IniciarControl();
    }

    public void Productos() {
        ModlProducto mod = new ModlProducto();
        VistaProducto pro = new VistaProducto();
        v.getDktContenedor().add(pro);
        CtrlProductos control = new CtrlProductos(mod, pro);
        control.IniciarControl();
    }

    public void Fichas() {
        ModlFichas mod = new ModlFichas();
        VistaFichas fic = new VistaFichas();
        v.getDktContenedor().add(fic);
        CtrlFichas control = new CtrlFichas(mod, fic);
        control.IniciarControl();
    }

    public void Usuario() {
        ModlUsuario mod = new ModlUsuario();
        VistaUsuario vis = new VistaUsuario();
        v.getDktContenedor().add(vis);
        CtrlUsuario control = new CtrlUsuario(vis, mod);
        control.IniciarControl();
    }

    public void CrearMesa() {
        VistaDialogoMesa d = new VistaDialogoMesa(v, true);
        CtrlDialogoMesa c = new CtrlDialogoMesa(d, v);
        c.IniciarControl();
    }

    public void CargarDialogo() {

        ModlMesa ms = new ModlMesa();

        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaMesa().getModel();
        tbmodel.setRowCount(0);
        List<Mesa> lp = ms.Listar();
        int n = tbmodel.getColumnCount();
        Holder<Integer> i=new Holder<>(0); 

        lp.stream().forEach(l -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaMesa().setValueAt(l.getId_mesa(), i.value, 0);
            v.getTablaMesa().setValueAt(l.getFecha(), i.value, 1);
            if (l.getEstado() == 0) {
                v.getTablaMesa().setValueAt("Finalizada", i.value, 2);
            } else {
                v.getTablaMesa().setValueAt("En ejecución", i.value, 2);
            }
            i.value++;
        });

        v.getDlgMesa().setSize(340, 385);
        v.getDlgMesa().setLocationRelativeTo(v);
        v.getDlgMesa().setModal(true);
        v.getDlgMesa().setVisible(true);
    }

    public void AbrirMesa(int id) {
        VistaMesa vm = new VistaMesa();
        ModlMesa d = new ModlMesa();
        v.getDktContenedor().add(vm);
        CtrlMesa c = new CtrlMesa(vm, d, id);
        c.IniciarControl();
        v.getDlgMesa().setVisible(false);
    }
}
