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
import pokercash.vista.empleado.VistaEmpleado;

/**
 *
 * @author CyberLink
 */
public class CtrlEmpleados {

    private ModlEmpleado m;
    private VistaEmpleado v;
    private int dialogo=0;

    public CtrlEmpleados(ModlEmpleado m, VistaEmpleado v) {
        this.m = m;
        this.v = v;
        v.setTitle("Empleados");
        v.setVisible(true);
        CargarLista("");
    }

    public void IniciarControl() {
        v.getBtnCrear().addActionListener(l -> cargarDialogo(1));
        v.getBtnAceptar().addActionListener((ae)->{
            switch (dialogo) {
                case 1:
                    CrearEmpleado();
                    break;
            }
        });
    }

    public void CargarLista(String aguja) {
        DefaultTableModel tbModel;
        tbModel = (DefaultTableModel) v.getTablaEmpleado().getModel();
        tbModel.setNumRows(0);
        List<Empleado> listE = m.listarEmpleado(aguja);
        int n = tbModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        listE.stream().forEach(listEm -> {
            tbModel.addRow(new Object[n]);
            v.getTablaEmpleado().setValueAt(listEm.getNombre(), i.value, 0);
            v.getTablaEmpleado().setValueAt(listEm.getApellido(), i.value, 1);
            v.getTablaEmpleado().setValueAt(listEm.getTelefono(), i.value, 2);
            v.getTablaEmpleado().setValueAt(listEm.getGenero(), i.value, 3);
            v.getTablaEmpleado().setValueAt(listEm.getRol(), i.value, 4);
            i.value++;
        });
    }

    public void cargarDialogo(int opc) {
        switch (opc) {
            case 1:
                v.getDlgEmpleado().setTitle("Crear Empleado");
                break;
            case 2:
                v.getDlgEmpleado().setTitle("Editar Empleado");
                break;
        }
        v.getDlgEmpleado().setSize(320, 320);
        v.getDlgEmpleado().setLocationRelativeTo(v);
        this.dialogo=1;
        v.getDlgEmpleado().setVisible(true);
    }

    public void CrearEmpleado() {
        String id_persona = "";
        String id_empleado = "";
        int num = 0;
        List<Empleado> list = m.listarEmpleado("");

        do {
            for (int i = 0; i < 4; i++) {
                num = (int) Math.floor(Math.random() * 9);
                id_persona = id_persona + num + "";
            }
            id_persona = "PER-" + id_persona;
            for (int i = 0; i < list.size(); i++) {
                if (id_persona.equals(list.get(i).getId_persona())) {
                    id_persona = "";
                }
            }
        } while ("".equals(id_persona));
        do {
            for (int i = 0; i < 4; i++) {
                num = (int) Math.floor(Math.random() * 9);
                id_empleado = id_empleado + num + "";
            }
            id_empleado = "EMP-" + id_empleado;
            for (int i = 0; i < list.size(); i++) {
                if (id_empleado.equals(list.get(i).getId_empleado())) {
                    id_empleado = "";
                }
            }
        } while ("".equals(id_empleado));

        ModlEmpleado emp = new ModlEmpleado(
                id_empleado, v.getCbxRol().getSelectedItem().toString(), id_persona,
                v.getTxtNombre().getText(), v.getTxtApellido().getText(), v.getTxtTelefono().getText(),
                v.getCbxGenero().getSelectedItem().toString());
        
        if (emp.Insertar()) {
            JOptionPane.showMessageDialog(v, "Persona creada con exito");
        }else{
            JOptionPane.showMessageDialog(v, "Error al inseratr datos");
        }
        CargarLista("");
        LimpiarCampos();
        v.getDlgEmpleado().setVisible(false);
    }
    
    public void LimpiarCampos(){
        v.getTxtNombre().setText("");
        v.getTxtApellido().setText("");
        v.getTxtTelefono().setText("");
    }
}
