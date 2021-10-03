/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.persona.Empleado;
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
        CargarLista("");
    }
    
    public void IniciarControl(){
        
    }
    
    public void CargarLista(String aguja){
        DefaultTableModel tbmodel;
        tbmodel=(DefaultTableModel) v.getTablaEmpleado().getModel();
        tbmodel.setRowCount(0);
        List<Empleado> listaE=m.listarEmpleado(aguja);
        int n=tbmodel.getColumnCount();
        Holder<Integer> i=new Holder<>(0);
        listaE.stream().forEach(listaEm->{
            tbmodel.addRow(new Object[n]);
            v.getTablaEmpleado().setValueAt(listaEm.getNombre(), i.value, 0);
            v.getTablaEmpleado().setValueAt(listaEm.getApellido(), i.value, 1);
            v.getTablaEmpleado().setValueAt(listaEm.getTelefono(), i.value, 2);
            v.getTablaEmpleado().setValueAt(listaEm.getGenero(), i.value, 3);
            v.getTablaEmpleado().setValueAt(listaEm.getRol(), i.value, 4);
            i.value++;
        });
    }
}
