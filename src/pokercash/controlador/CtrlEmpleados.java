/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.persona.Empleado;
import pokercash.modelo.persona.ModlEmpleado;
import pokercash.modelo.persona.ModlPersona;
import pokercash.modelo.persona.Persona;
import pokercash.vista.persona.VistaEmpleado;

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
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                CargarLista(v.getTxtBuscar().getText().toUpperCase());
                CargarPersona(v.getTxtBuscarPer().getText().toUpperCase());
            }
        };
        v.getTxtBuscarPer().addKeyListener(kl);
        v.getTxtBuscar().addKeyListener(kl);
        v.getBtnCrear().addActionListener(l -> Persona());
        v.getBtnCancelar().addActionListener(l -> Cancelar());
        v.getBtnCance().addActionListener(l->Cancelar());
        v.getBtnNuevo().addActionListener(l -> CargarDialogo(1));
        v.getBtnEditar().addActionListener((l) -> {
            int seleccion = v.getTablaEmpleado().getSelectedRow();
            if (seleccion != -1) {
                CargarDialogo(2);
            } else {
                JOptionPane.showMessageDialog(v, "Seleccione el empleado a modificar");
            }
        });

        v.getBtnAceptar().addActionListener((ae) -> {
            switch (opcion) {
                case 1:
                    InsertarEmpleado();
                    break;
                case 2:
                    EditarEmpleado();
                    break;
            }
        });
        v.getBtnSeleccionar().addActionListener((l) -> {
            if (v.getTablaPersona().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(v.getDlgPersona(), "Seleccione el empleado que quiere Ingresar");
            } else {

                Seleccionar();
            }

        });

    }

    public void Cancelar() {
        LimpiarCampos();
        v.getDlgEmpleado().setVisible(false);
        v.getDlgPersona().setVisible(false);
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
            case 2:
                v.getDlgEmpleado().setTitle("Editar Empleado");
                int seleccion = v.getTablaEmpleado().getSelectedRow();
                List<Empleado> emp = m.listarEmpleado(v.getTxtBuscar().getText().toUpperCase());
                v.getTxtNombre().setText(emp.get(seleccion).getNombre());
                v.getTxtApellido().setText(emp.get(seleccion).getApellido());
                v.getTxtTelefono().setText(emp.get(seleccion).getTelefono());
                for (int i = 0; i < 2; i++) {
                    if (emp.get(seleccion).getGenero().equals(v.getCbxGenero().getItemAt(i))) {
                        v.getCbxGenero().setSelectedIndex(i);
                        break;
                    }
                }
                for (int i = 0; i < 5; i++) {
                    if (emp.get(seleccion).getRol().equals(v.getCbxRol().getItemAt(i))) {
                        v.getCbxRol().setSelectedIndex(i);
                        break;
                    }
                }
                break;
        }
        
        v.getDlgEmpleado().setLocationRelativeTo(v);
        v.getDlgEmpleado().setSize(320, 320);
        v.getDlgPersona().setVisible(false);
        v.getDlgEmpleado().setModal(true);
        v.getDlgEmpleado().setVisible(true);
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
            JOptionPane.showMessageDialog(v.getDlgEmpleado(), "Empleado Ingresado con Exito");
        } else {
            JOptionPane.showMessageDialog(v.getDlgEmpleado(), "ERROR");
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
            lista = p.ListarpersonaID(num);

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
        v.getTxtBuscar().setText("");
        v.getTxtBuscarPer().setText("");
    }

    public void EditarEmpleado() {
        int seleccion = v.getTablaEmpleado().getSelectedRow();
        List<Empleado> emp = m.listarEmpleado(v.getTxtBuscar().getText().toUpperCase());
        int id_empleado = emp.get(seleccion).getId_empleado();
        int id_persona = emp.get(seleccion).getId_persona();
        ModlEmpleado empl = new ModlEmpleado(
                id_empleado,
                v.getCbxRol().getSelectedItem().toString(),
                id_persona,
                v.getTxtNombre().getText(),
                v.getTxtApellido().getText(),
                v.getTxtTelefono().getText(),
                v.getCbxGenero().getSelectedItem().toString());
        int confirmar = JOptionPane.showConfirmDialog(v.getDlgEmpleado(), "Esta seguro que desea editar el empleado", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
        if (confirmar == 0) {
            if (empl.EditarEmpleado()) {
                v.getDlgEmpleado().setVisible(false);
                JOptionPane.showMessageDialog(v.getDlgEmpleado(), "Empleado Editado con Exito");

            } else {
                JOptionPane.showMessageDialog(v.getDlgEmpleado(), "Error");
            }
        }
        LimpiarCampos();
        CargarLista("");

    }

    public void Persona() {
        CargarPersona("");
        v.getDlgPersona().setLocationRelativeTo(v);
        v.getDlgPersona().setSize(500, 400);
        v.getDlgPersona().setModal(true);
        v.getDlgPersona().setVisible(true);

    }

    public void CargarPersona(String aguja) {
        ModlPersona per = new ModlPersona();
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaPersona().getModel();
        tbmodel.setRowCount(0);
        List<Persona> listaE = per.Listarpersona(aguja);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        listaE.stream().forEach(listaEm -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaPersona().setValueAt(listaEm.getNombre(), i.value, 0);
            v.getTablaPersona().setValueAt(listaEm.getApellido(), i.value, 1);
            v.getTablaPersona().setValueAt(listaEm.getTelefono(), i.value, 2);
            v.getTablaPersona().setValueAt(listaEm.getGenero(), i.value, 3);
            i.value++;
        });
    }

    public void Seleccionar() {
        int selec = v.getTablaPersona().getSelectedRow();
        ModlPersona per = new ModlPersona();
        List<Persona> lp = per.Listarpersona(v.getTxtBuscarPer().getText().toUpperCase());

        String[] roles = {"Administrador", "Deler", "Dueño", "Organizador", "Servicio"};
        String rol = (String) JOptionPane.showInputDialog(v, "Seleccione el Rol", "Poker One Administrator", JOptionPane.DEFAULT_OPTION, null, roles, roles[0]);
        int id_emp = IdEmpleado();
        System.out.println(id_emp);
        int id_persona = lp.get(selec).getId_persona();
        System.out.println(id_persona);
        System.out.println(rol);
        if (rol != null) {
            ModlEmpleado mo = new ModlEmpleado();
            mo.setId_empleado(id_emp);
            mo.setId_persona(id_persona);
            mo.setRol(rol);
            if (mo.InsertEmpleado()) {
                JOptionPane.showMessageDialog(v.getDlgPersona(), "Empleado Ingresado con exito");
            } else {
                JOptionPane.showMessageDialog(v.getDlgPersona(), "ERROR");
            }

            v.getDlgPersona().setVisible(false);
            LimpiarCampos();
            CargarLista("");
        }
    }
}
