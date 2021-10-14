/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.persona;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.persona.ModlUsuario;
import pokercash.modelo.persona.Usuario;
import pokercash.vista.persona.VistaUsuario;

/**
 *
 * @author CyberLink
 */
public class CtrlUsuario {

    private VistaUsuario v;
    private ModlUsuario m;
    private int idEmp, opc;
    String n;

    public CtrlUsuario(VistaUsuario v, ModlUsuario m) {
        this.v = v;
        this.m = m;
        v.setTitle("Usuario");
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
                CargarEmpleado(v.getTxtBuscarPer().getText().toUpperCase());
            }
        };
        v.getTxtBuscar().addKeyListener(kl);
        v.getTxtBuscarPer().addKeyListener(kl);
        v.getBtnAgregar().addActionListener(l -> Escoger());
        v.getBtnCance().addActionListener(l -> Cancelar());
        v.getBtnCancelar().addActionListener(l -> Cancelar());
        v.getBtnCerrar().addActionListener(l->cerrar());
        v.getBtnEditar().addActionListener(l -> {
            int s = v.getTablaUsuario().getSelectedRow();
            if (s != -1) {
                List<Usuario> u = m.ListarUsuario(v.getTxtBuscar().getText().toUpperCase());
                this.n = u.get(s).getNomb_usu();
                CargarDialogue(2);

            } else {
                JOptionPane.showMessageDialog(v.getDlgEmpleado(), "Seleccione un usuario primero");
            }
        });
        v.getBtnEliminar().addActionListener(l -> {
            int s = v.getTablaUsuario().getSelectedRow();
            if (s != -1) {
                Delete();
            } else {
                JOptionPane.showMessageDialog(v.getDlgEmpleado(), "Seleccione un usuario primero");
            }
        });
        v.getBtnSeleccionar().addActionListener(l -> {
            int s = v.getTablaPersona().getSelectedRow();
            if (s != -1) {
                List<Usuario> u = m.ListarEmpleado(v.getTxtBuscarPer().getText().toUpperCase());
                this.idEmp = u.get(s).getId_empleado();

                v.getDlgEmpleado().setVisible(false);
                CargarDialogue(1);
            } else {
                JOptionPane.showMessageDialog(v.getDlgEmpleado(), "Seleccione un empleado primero");
            }
        });
        v.getBtnAceptar().addActionListener(l -> {
            switch (opc) {
                case 1:
                    Ingresar();
                    break;
                case 2:
                    Editar();
                    break;
            }
        });
    }

    public void CargarLista(String aguja) {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaUsuario().getModel();
        tbmodel.setRowCount(0);
        List<Usuario> u = m.ListarUsuario(aguja);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        u.stream().forEach(us -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaUsuario().setValueAt(us.getNomb_usu(), i.value, 0);
            v.getTablaUsuario().setValueAt(us.getPassword(), i.value, 1);
            i.value++;
        });
    }

    public void CargarDialogue(int opc) {
        this.opc = opc;
        int s = v.getTablaUsuario().getSelectedRow();
        switch (opc) {
            case 1:
                v.getDlgUsuario().setTitle("Ingresar usuario");
                break;
            case 2:
                v.getDlgUsuario().setTitle("Editar usuario");
                List<Usuario> u = m.ListarUsuario(v.getTxtBuscar().getText().toUpperCase());
                v.getTxtNombre().setText(u.get(s).getNomb_usu());
                v.getTxtPassword().setText("");
                v.getTxtRepPassword().setText("");
                this.idEmp = u.get(s).getId_empleado();
                break;
        }
        v.getDlgUsuario().setSize(320, 320);
        v.getDlgUsuario().setLocationRelativeTo(v);
        v.getDlgUsuario().setModal(true);
        v.getDlgUsuario().setVisible(true);
    }

    public void Cancelar() {
        v.getDlgEmpleado().setVisible(false);
        v.getDlgUsuario().setVisible(false);
    }

    public void Ingresar() {

        String p = String.valueOf(v.getTxtPassword().getPassword());
        String rp = String.valueOf(v.getTxtRepPassword().getPassword());
        ModlUsuario u = new ModlUsuario();
        if ("".equals(p) && "".equals(rp)) {
            JOptionPane.showMessageDialog(v.getDlgUsuario(), "Llenar todos los Campos");
        } else {
            if (p.equals(rp)) {

                u.setNomb_usu(v.getTxtNombre().getText());
                u.setPassword(p);
                u.setId_empleado(this.idEmp);
                List<Usuario> ls = m.IniciarSesion(u.getNomb_usu());
                if (ls.size() == 0) {
                    if (u.Insertar()) {
                        JOptionPane.showMessageDialog(v.getDlgUsuario(), "Usuario ingresado con exito");
                    } else {
                        JOptionPane.showMessageDialog(v.getDlgUsuario(), "ERROR");
                    }
                    LimpiarCampos();
                    v.getDlgUsuario().setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(v.getDlgUsuario(), "Nombre de usuario ya existe");
                }

            } else {
                JOptionPane.showMessageDialog(v.getDlgUsuario(), "Las no contraseñas deben ser iguales");
            }
        }

    }

    public void Editar() {
        String p = String.valueOf(v.getTxtPassword().getPassword());
        String rp = String.valueOf(v.getTxtRepPassword().getPassword());
        ModlUsuario u = new ModlUsuario();
        if ("".equals(p) && "".equals(rp)) {
            JOptionPane.showMessageDialog(v.getDlgUsuario(), "Llenar todos los Campos");
        } else {
            if (p.equals(rp)) {
                u.setNomb_usu(v.getTxtNombre().getText());
                u.setPassword(p);
                u.setId_empleado(this.idEmp);
                List<Usuario> ls = m.IniciarSesion(u.getNomb_usu());
                if (ls.size() == 0) {
                    if (u.Editar()) {
                        JOptionPane.showMessageDialog(v.getDlgUsuario(), "Usuario editado con exito");
                    } else {
                        JOptionPane.showMessageDialog(v.getDlgUsuario(), "ERROR");
                    }
                    LimpiarCampos();
                    v.getDlgUsuario().setVisible(false);
                } else {
                    if (ls.get(0).getNomb_usu().equals(n)) {
                        if (u.Editar()) {
                            JOptionPane.showMessageDialog(v.getDlgUsuario(), "Usuario editado con exito");
                        } else {
                            JOptionPane.showMessageDialog(v.getDlgUsuario(), "ERROR");
                        }
                        LimpiarCampos();
                        v.getDlgUsuario().setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(v.getDlgUsuario(), "Nombre de usuario ya existe");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(v.getDlgUsuario(), "Las no contraseñas deben ser iguales");
            }
        }
    }

    public void Escoger() {
        LimpiarCampos();
        CargarEmpleado("");
        v.getDlgEmpleado().setSize(420, 320);
        v.getDlgEmpleado().setLocationRelativeTo(v);
        v.getDlgEmpleado().setModal(true);
        v.getDlgEmpleado().setVisible(true);

    }

    public void CargarEmpleado(String aguja) {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaPersona().getModel();
        tbmodel.setRowCount(0);
        List<Usuario> u = m.ListarEmpleado(aguja);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        u.stream().forEach(us -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaPersona().setValueAt(us.getNombre(), i.value, 0);
            v.getTablaPersona().setValueAt(us.getApellido(), i.value, 1);
            v.getTablaPersona().setValueAt(us.getTelefono(), i.value, 2);
            v.getTablaPersona().setValueAt(us.getGenero(), i.value, 3);
            i.value++;
        });
    }

    public void LimpiarCampos() {
        v.getTxtNombre().setText("");
        v.getTxtBuscar().setText("");
        v.getTxtBuscarPer().setText("");
        v.getTxtPassword().setText("");
        v.getTxtRepPassword().setText("");
        CargarLista("");
    }

    public void Delete() {
        List<Usuario> u = m.ListarUsuario(v.getTxtBuscar().getText().toUpperCase());
        int s = v.getTablaUsuario().getSelectedRow();
        int confirmar = JOptionPane.showConfirmDialog(v, "Esta seguro que desea eliminar el usuario", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
        if (confirmar == 0) {
            m.setNomb_usu(u.get(s).getNomb_usu());
            if (m.Eliminar()) {
                JOptionPane.showMessageDialog(v, "Usuario eliminado con exito");
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        }
        CargarLista("");
    }
    
    public void cerrar(){
        v.setVisible(false);
    }
}
