/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.persona.Empleado;
import pokercash.modelo.persona.ModlEmpleado;
import pokercash.modelo.persona.ModlPersona;
import pokercash.modelo.persona.Persona;
import pokercash.vista.empleado.VistaEmpleado;

/**
 *
 * @author CyberLink
 */
public class CtrlEmpleados {

    private ModlEmpleado m;
    private VistaEmpleado v;
    private int opcion;

    public CtrlEmpleados(ModlEmpleado m, VistaEmpleado v) {
        this.m = m;
        this.v = v;
        v.setTitle("Empleados");
        v.setVisible(true);
        CargarLista("");
    }

    public void IniciarControl() {
        v.getBtnCrear().addActionListener(l -> CargarDialogo(1));
        v.getBtnAceptar().addActionListener((ae) -> {
            switch (opcion) {
                case 1:
                    InsertarEmpleado();
                    break;
            }
        });
    }

    public void CargarLista(String aguja) {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaEmpleado().getModel();
        tbmodel.setRowCount(0);
        List<Empleado> listaE = m.listarEmpleado(aguja);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        listaE.stream().forEach(listaEm -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaEmpleado().setValueAt(listaEm.getNombre(), i.value, 0);
            v.getTablaEmpleado().setValueAt(listaEm.getApellido(), i.value, 1);
            v.getTablaEmpleado().setValueAt(listaEm.getTelefono(), i.value, 2);
            v.getTablaEmpleado().setValueAt(listaEm.getGenero(), i.value, 3);
            v.getTablaEmpleado().setValueAt(listaEm.getRol(), i.value, 4);
            i.value++;
        });
    }

    public void CargarDialogo(int opc) {
        this.opcion = opc;
        switch (opc) {
            case 1:
                v.getDlgEmpleado().setTitle("Ingresar Empleado");
                break;
        }
        v.getDlgEmpleado().setVisible(true);
        v.getDlgEmpleado().setLocationRelativeTo(v);
        v.getDlgEmpleado().setSize(320, 320);
    }

    public void InsertarEmpleado() {
        int id_persona = IdPersona();
        int id_empleado = IdEmpleado();

        ModlEmpleado emp = new ModlEmpleado(
                id_empleado,
                v.getCbxRol().getSelectedItem().toString(),
                id_persona,
                v.getTxtNombre().getText(),
                v.getTxtApellido().getText(),
                v.getTxtTelefono().getText(),
                v.getCbxGenero().getSelectedItem().toString());
                if (emp.InsertarEmpleado()) {
            JOptionPane.showMessageDialog(v, "Empleado Ingresado con Exito");
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }
        v.getDlgEmpleado().setVisible(false);
        LimpiarCampos();
        CargarLista("");
    }

    public int IdEmpleado() {
        int num;
        List<Empleado> lista = m.listarEmpleado("");
        num = lista.size() + 1;
        do {
            lista = m.listarEmpleado(num);

            if (lista.size() == 1) {
                num++;
            } else {
                break;
            }
        } while (true);
        return num;
    }

    public int IdPersona() {
        ModlPersona p = new ModlPersona();
        int num;
        List<Persona> lista = p.Listarpersona();
        num = lista.size() + 1;
        
        do {
            lista = p.Listarpersona(num);

            if (lista.size() == 1) {
                num++;
            } else {
                break;
            }
        } while (true);
        return num;
    }

    public void LimpiarCampos() {
        v.getTxtNombre().setText("");
        v.getTxtApellido().setText("");
        v.getTxtTelefono().setText("");
    }
}
