/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.mesa;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.mesa.EstIngrEmpl;
import pokercash.modelo.mesa.Mesa;
import pokercash.modelo.mesa.ModlEstIngrEmpl;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.modelo.persona.Empleado;
import pokercash.modelo.persona.ModlEmpleado;
import pokercash.vista.mesa.VistaDialogoMesa;
import pokercash.vista.mesa.VistaMesa;
import pokercash.vista.principal.VistaPrincipal;

/**
 *
 * @author CyberLink
 */
public class CtrlDialogoMesa {

    private VistaDialogoMesa v;
    private VistaPrincipal vp;

    List<Empleado> listaD = new ArrayList<>();
    List<Empleado> listaO = new ArrayList<>();
    List<Empleado> listaT = new ArrayList<>();

    private int next = 1;
    private int back = 1;

    double PorcentajeClub = 100;
    double EfectivoClub;
    int GastosClub = 1;
    int mesa;
    double propinasServ = 0;
    double efectivoServ = 0;
    double efectivoAdmin = 0;
    double propinasDeler = 0;
    double efectivoDeler = 0;
    double porcentajeCasilla = 0;
    int gastosDeler = 0;

    public CtrlDialogoMesa(VistaDialogoMesa v, VistaPrincipal vp) {
        this.v = v;
        this.vp = vp;
        v.setLocationRelativeTo(null);
        CargarPanel(v.getPanelOrganizacion());
        v.getBtnFinalizar().setEnabled(false);
        v.getBtnAtras().setEnabled(false);
        v.getBtnSiguiente().setEnabled(true);
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
                PorcentajeClub = Double.parseDouble(v.getTxtClubPor().getText());
                EfectivoClub = Double.parseDouble(v.getTxtClubEf().getText());

                propinasServ = Double.parseDouble(v.getTxtPropServi().getText());
                efectivoServ = Double.parseDouble(v.getTxtEfectServi().getText());
                efectivoAdmin = Double.parseDouble(v.getTxtValorAdm().getText());
                propinasDeler = Double.parseDouble(v.getTxtPropDeler().getText());
                efectivoDeler = Double.parseDouble(v.getTxtEfectDeler().getText());
                porcentajeCasilla = Double.parseDouble(v.getTxtCasiDeler().getText());
            }
        };
        v.getBtnSiguiente().addActionListener(l -> {
            next++;
            Ventana(next);
        });

        v.getBtnAtras().addActionListener(l -> {
            back--;
            Ventana(back);
        });
        v.getBtnAgregarClub().addActionListener(
                l -> AgregarD(v.getCbxClub().getSelectedItem().toString(), v.getCbxClub().getSelectedIndex()));
        v.getBtnAgregarOrg().addActionListener(
                l -> AgregarO(v.getCbxOrg().getSelectedItem().toString(), v.getCbxOrg().getSelectedIndex()));
        v.getBtnFinalizar().addActionListener(l -> Finalizar());

        v.getRbPorc().addActionListener(l -> {
            v.getTxtClubPor().setEnabled(true);
            v.getTxtClubEf().setEnabled(false);
            v.getTxtClubEf().setText("0");
            EfectivoClub = 0;
        });
        v.getRbEfect().addActionListener(l -> {
            v.getTxtClubPor().setEnabled(false);
            v.getTxtClubEf().setEnabled(true);
            v.getTxtClubPor().setText("0");
            PorcentajeClub = 0;
        });

        v.getRbCgastos().addActionListener(l -> {
            GastosClub = 1;
        });
        v.getRbSgastos().addActionListener(l -> {
            GastosClub = 0;
        });
        v.getTxtClubPor().addKeyListener(kl);
        v.getTxtClubEf().addKeyListener(kl);

        v.getTxtPropServi().addKeyListener(kl);
        v.getTxtEfectServi().addKeyListener(kl);
        v.getTxtValorAdm().addKeyListener(kl);
        v.getTxtPropDeler().addKeyListener(kl);
        v.getTxtEfectDeler().addKeyListener(kl);
        v.getTxtCasiDeler().addKeyListener(kl);

        v.getBtnRemoverClub().addActionListener(l -> BorrarD());
        v.getBtnRemoverOrg().addActionListener(l -> BorrarO());
        v.getBtnSeleccionart().addActionListener(l -> CargarDialogo());
        v.getBtnAgregarte().addActionListener(l -> AgregarT());
        v.getBtnAceptarte().addActionListener(l -> {
            CargarT();
            v.getDlgEmpleados().setVisible(false);
        });
        v.getBtnRemovert().addActionListener(l -> BorrarT());
        v.getBtnCancelarte().addActionListener(l -> {
            CargarT();
            v.getDlgEmpleados().setVisible(false);
        });

        v.getCbPropservi().addActionListener(l -> {
            if (v.getCbPropservi().isSelected()) {
                v.getTxtPropServi().setEnabled(true);
                propinasServ = Double.parseDouble(v.getTxtPropServi().getText());
            } else {
                v.getTxtPropServi().setEnabled(false);
                v.getTxtPropServi().setText("0");
                propinasServ = 0;
            }
        });
        v.getCbEfectserv().addActionListener(l -> {
            if (v.getCbEfectserv().isSelected()) {
                v.getTxtEfectServi().setEnabled(true);
                efectivoServ = Double.parseDouble(v.getTxtEfectServi().getText());
            } else {
                v.getTxtEfectServi().setEnabled(false);
                v.getTxtEfectServi().setText("0");
                efectivoServ = 0;
            }
        });
        v.getCbPropDeler().addActionListener(l -> {
            if (v.getCbPropDeler().isSelected()) {
                v.getTxtPropDeler().setEnabled(true);
                propinasDeler = Double.parseDouble(v.getTxtPropDeler().getText());
            } else {
                v.getTxtPropDeler().setEnabled(false);
                v.getTxtPropDeler().setText("0");
                propinasDeler = 0;
            }
        });
        v.getCbEfectDeler().addActionListener(l -> {
            if (v.getCbEfectDeler().isSelected()) {
                v.getTxtEfectDeler().setEnabled(true);
                efectivoDeler = Double.parseDouble(v.getTxtEfectDeler().getText());
            } else {
                v.getTxtEfectDeler().setEnabled(false);
                v.getTxtEfectDeler().setText("0");
                efectivoDeler = 0;
            }
        });
        v.getCbCasiDeler().addActionListener(l -> {
            if (v.getCbCasiDeler().isSelected()) {
                v.getTxtCasiDeler().setEnabled(true);
                porcentajeCasilla = Double.parseDouble(v.getTxtCasiDeler().getText());
            } else {
                v.getTxtCasiDeler().setEnabled(false);
                v.getTxtCasiDeler().setText("0");
                porcentajeCasilla = 0;
            }
        });
        v.show();
    }

    public void CargarPanel(JPanel p) {
        Cargar();
        CargarClub();
        CargarOrg();
        CargarT();
        p.setSize(450, 450);
        p.setLocation(0, 0);

        v.getContent().removeAll();
        v.getContent().add(p, BorderLayout.CENTER);
        v.getContent().revalidate();
        v.getContent().repaint();
    }

    public void Ventana(int opc) {

        if (opc < 1) {
            opc = 1;
        } else {
            if (opc > 3) {
                opc = 3;
            }
        }
        switch (opc) {
            case 1:
                CargarPanel(v.getPanelOrganizacion());
                v.getBtnFinalizar().setEnabled(false);
                v.getBtnAtras().setEnabled(false);
                v.getBtnSiguiente().setEnabled(true);
                break;
            case 2:
                if (listaD.isEmpty()) {
                    JOptionPane.showMessageDialog(v, "Ingrese minimo un Dueño");
                    opc--;
                } else {
                    CargarPanel(v.getPanelTrabajadores());
                    v.getBtnFinalizar().setEnabled(false);
                    v.getBtnAtras().setEnabled(true);
                    v.getBtnSiguiente().setEnabled(true);
                }

                break;
            case 3:
                int valor = 0;
                v.getCbPropservi().setEnabled(false);
                v.getCbEfectserv().setEnabled(false);
                v.getTxtValorAdm().setEnabled(false);
                for (int i = 0; i < listaT.size(); i++) {
                    if ("Deler".equals(listaT.get(i).getRol())) {
                        valor = 1;
                    }
                    if ("Servicio".equals(listaT.get(i).getRol())) {
                        v.getCbPropservi().setEnabled(true);
                        v.getCbEfectserv().setEnabled(true);
                    }
                    if ("Administrador".equals(listaT.get(i).getRol())) {
                        v.getTxtValorAdm().setEnabled(true);
                    }
                }
                if (valor == 0) {
                    JOptionPane.showMessageDialog(v, "Ingrese minimo un deler");
                    opc--;
                } else {
                    CargarPanel(v.getPanelPagos());
                    v.getBtnFinalizar().setEnabled(true);
                    v.getBtnAtras().setEnabled(true);
                    v.getBtnSiguiente().setEnabled(false);
                }

                break;
        }
        back = opc;
        next = opc;
    }

    public void Finalizar() {
        InsertarMesa();
        InsertarEst();
        VistaMesa vm = new VistaMesa();
        ModlMesa mod = new ModlMesa();
        vp.getDktContenedor().add(vm);
        CtrlMesa c = new CtrlMesa(vm, mod, mesa);
        c.IniciarControl();
        v.setVisible(false);
    }

    public void InsertarEst() {
        ModlEstIngrEmpl m = new ModlEstIngrEmpl();
        listaD.stream().forEach(l -> {
            m.setId_empleado(l.getId_empleado());
            m.setId_est_ingr(Id_est());
            m.setId_mesa(mesa);
            m.setIngreso(0);
            m.Insertar();
        });
        listaO.stream().forEach(l -> {
            m.setId_empleado(l.getId_empleado());
            m.setId_est_ingr(Id_est());
            m.setId_mesa(mesa);
            m.setIngreso(0);
            m.Insertar();
        });
        listaT.stream().forEach(l -> {
            m.setId_empleado(l.getId_empleado());
            m.setId_est_ingr(Id_est());
            m.setId_mesa(mesa);
            m.setIngreso(0);
            m.Insertar();
        });

    }

    public void InsertarMesa() {
        int id_mesa = Id_mesa();
        mesa = id_mesa;
        ModlMesa m = new ModlMesa(
                id_mesa,
                0,
                LocalDate.now(),
                0,
                propinasServ,
                efectivoServ,
                efectivoAdmin,
                propinasDeler,
                efectivoDeler,
                porcentajeCasilla,
                gastosDeler,
                PorcentajeClub,
                EfectivoClub,
                GastosClub, 1);

        if (m.Insertar()) {

        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }
    }

    public void Cargar() {
        v.getCbxClub().removeAllItems();
        v.getCbxOrg().removeAllItems();
        ModlEmpleado mod = new ModlEmpleado();
        List<Empleado> le = mod.ListarMesa("Dueño");
        le.stream().forEach(l -> {
            v.getCbxClub().addItem(l.getNombre() + " " + l.getApellido());
        });
        le = mod.ListarMesa("Organizador");
        le.stream().forEach(l -> {
            v.getCbxOrg().addItem(l.getNombre() + " " + l.getApellido());
        });

    }

    public void AgregarD(String valor, int pos) {
        boolean bandera = false;
        ModlEmpleado mod = new ModlEmpleado();
        List<Empleado> le = mod.ListarMesa("Dueño");
        mod.setId_empleado(le.get(pos).getId_empleado());
        mod.setNombre(le.get(pos).getNombre());
        mod.setApellido(le.get(pos).getApellido());

        listaD.stream().forEach(l -> {

            if (l.getId_empleado() == le.get(pos).getId_empleado()) {
                mod.setId_empleado(0);
                mod.setNombre("");
                mod.setApellido("");
            }

        });
        if (mod.getId_empleado() != 0) {
            listaD.add(mod);
        } else {
            JOptionPane.showMessageDialog(v, "El dueño ya esta agregado");
        }
        CargarClub();
    }

    public void CargarClub() {
        if (!listaD.isEmpty()) {
            DefaultListModel model = new DefaultListModel();
            model.removeAllElements();
            v.getListClub().setModel(model);
            listaD.stream().forEach(l -> {
                model.addElement(l.getNombre() + " " + l.getApellido());
            });
        }

    }

    public void AgregarO(String valor, int pos) {

        ModlEmpleado mod = new ModlEmpleado();
        List<Empleado> le = mod.ListarMesa("Organizador");
        mod.setId_empleado(le.get(pos).getId_empleado());
        mod.setNombre(le.get(pos).getNombre());
        mod.setApellido(le.get(pos).getApellido());

        listaO.stream().forEach(l -> {

            if (l.getId_empleado() == le.get(pos).getId_empleado()) {
                mod.setId_empleado(0);
                mod.setNombre("");
                mod.setApellido("");
            }

        });
        if (mod.getId_empleado() != 0) {
            listaO.add(mod);
        } else {
            JOptionPane.showMessageDialog(v, "El Organizador ya esta agregado");
        }
        CargarOrg();
    }

    public void CargarOrg() {
        if (!listaO.isEmpty()) {
            DefaultListModel model = new DefaultListModel();
            model.removeAllElements();
            v.getListOrg().setModel(model);
            listaO.stream().forEach(l -> {
                model.addElement(l.getNombre() + " " + l.getApellido());
            });
        }

    }

    public void BorrarD() {
        int s = v.getListClub().getSelectedIndex();
        if (s != -1) {
            listaD.remove(s);
            CargarClub();
        } else {
            JOptionPane.showMessageDialog(v, "Sleccione un dueño para remover");
        }
    }

    public void BorrarO() {
        int s = v.getListOrg().getSelectedIndex();
        if (s != -1) {
            listaO.remove(s);
            CargarOrg();
        } else {
            JOptionPane.showMessageDialog(v, "Sleccione un organizador para remover");
        }
    }

    public void BorrarT() {
        int s = v.getTablaTrabajadores().getSelectedRow();
        if (s != -1) {
            listaT.remove(s);
            CargarT();
        } else {
            JOptionPane.showMessageDialog(v, "Sleccione un Trabajador para remover");
        }
    }

    public void CargarDialogo() {
        CargarEmpleados();
        v.getDlgEmpleados().setSize(400, 410);
        v.getDlgEmpleados().setLocationRelativeTo(v);
        v.getDlgEmpleados().setModal(true);
        v.getDlgEmpleados().setVisible(true);
    }

    public void CargarEmpleados() {
        ModlEmpleado mod = new ModlEmpleado();
        List<Empleado> listaE = mod.ListarMesaT();
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaEmpleados().getModel();
        tbmodel.setRowCount(0);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        listaE.stream().forEach(listaEm -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaEmpleados().setValueAt(listaEm.getNombre(), i.value, 0);
            v.getTablaEmpleados().setValueAt(listaEm.getApellido(), i.value, 1);
            v.getTablaEmpleados().setValueAt(listaEm.getRol(), i.value, 2);
            i.value++;
        });
    }

    public void CargarT() {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaTrabajadores().getModel();
        tbmodel.setRowCount(0);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        listaT.stream().forEach(l -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaTrabajadores().setValueAt(l.getNombre() + " " + l.getApellido(), i.value, 0);
            v.getTablaTrabajadores().setValueAt(l.getRol(), i.value, 1);
            i.value++;
        });
    }

    public void AgregarT() {
        ModlEmpleado mod = new ModlEmpleado();
        List<Empleado> l = mod.ListarMesaT();
        int s = v.getTablaEmpleados().getSelectedRow();
        if (s != -1) {

            mod.setId_empleado(l.get(s).getId_empleado());
            mod.setNombre(l.get(s).getNombre());
            mod.setApellido(l.get(s).getApellido());
            mod.setRol(l.get(s).getRol());
            listaT.stream().forEach(lt -> {
                if (lt.getId_empleado() == mod.getId_empleado()) {
                    mod.setId_empleado(0);
                    mod.setNombre("");
                    mod.setApellido("");
                    mod.setRol("");
                }
            });
            if (mod.getId_empleado() == 0) {
                JOptionPane.showMessageDialog(v.getDlgEmpleados(), "Empleado ya se encuentra agregado");
            } else {
                listaT.add(mod);
                JOptionPane.showMessageDialog(v.getDlgEmpleados(), "Empleado Agregado");
            }

        } else {
            JOptionPane.showMessageDialog(v.getDlgEmpleados(), "Seleccione un empleado para agregar");
        }
    }

    private int Id_mesa() {
        int n = 0;
        ModlMesa md = new ModlMesa();
        List<Mesa> ms = md.Listar();
        n = ms.size() + 1;
        do {
            ms = md.Listar(n);
            if (ms.size() == 1) {
                n++;
            } else {
                break;
            }

        } while (true);
        return n;
    }

    private int Id_est() {
        int n = 0;
        ModlEstIngrEmpl md = new ModlEstIngrEmpl();
        List<EstIngrEmpl> ms = md.Listar();
        n = ms.size() + 1;
        do {
            ms = md.Listar(n);
            if (ms.size() == 1) {
                n++;
            } else {
                break;
            }

        } while (true);
        return n;
    }

}
