/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.mesa;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.controlador.inventario.ImagenTabla;
import pokercash.controlador.persona.CtrlDialogoJugador;
import pokercash.modelo.inventario.DetalleFichas;
import pokercash.modelo.inventario.Fichas;
import pokercash.modelo.inventario.ModlDetallFichas;
import pokercash.modelo.inventario.ModlFichas;
import pokercash.modelo.mesa.Cambio;
import pokercash.modelo.mesa.Deudas;
import pokercash.modelo.mesa.EstadJugador;
import pokercash.modelo.mesa.Mesa;
import pokercash.modelo.mesa.ModlCambio;
import pokercash.modelo.mesa.ModlDeudas;
import pokercash.modelo.mesa.ModlEstadJugador;
import pokercash.modelo.mesa.ModlIngrJugMesa;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.modelo.mesa.ModlPropinas;
import pokercash.modelo.mesa.Propinas;
import pokercash.modelo.persona.Jugador;
import pokercash.modelo.persona.ModlJugador;
import pokercash.vista.mesa.VistaDialogoDeudas;
import pokercash.vista.mesa.VistaDialogoJugador;
import pokercash.vista.mesa.VistaDialogueFichas;
import pokercash.vista.mesa.VistaDialogueRegisto;
import pokercash.vista.mesa.VistaDialogueSalir;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlMesa {

    private VistaMesa v;
    private ModlMesa m;
    private int ID_MESA;

    public CtrlMesa(VistaMesa v, ModlMesa m, int ID_MESA) {
        this.v = v;
        this.m = m;
        this.ID_MESA = ID_MESA;

        v.setTitle("Poker table Administrator");
        cargar();
    }

    public void IniciarControl() {
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {

            }

            @Override
            public void mousePressed(MouseEvent me) {
                int s = v.getTablaJugadores().getSelectedRow();
                if (s != -1) {
                    Encender(s);
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {

            }
        };
        v.getTablaJugadores().addMouseListener(ml);
        v.getBtnAgregarFichas().addActionListener(l -> CargarDialogo());
        v.getBtnAgregar().addActionListener(l -> AgregarJugador());
        v.getBtnIngresar().addActionListener(l -> {
            int s = v.getTablaFichas().getSelectedRow();
            if (s != -1) {
                AgregarFichas();
            } else {
                JOptionPane.showMessageDialog(v.getDlgFichas(), "Seleccione fichas para agregar");
            }

        });

        v.getBtnFichas().addActionListener(l -> {
            int s = v.getTablaJugadores().getSelectedRow();
            if (s != -1) {
                if (Double.parseDouble(v.getLblfichas().getText()) == 0) {
                    JOptionPane.showMessageDialog(v.getDlgFichas(), "Fichas en maletin Insuficientes ");
                } else {
                    ComprarFichas(s);
                }

            } else {
                JOptionPane.showMessageDialog(v.getDlgFichas(), "Seleccione un Jugador");
            }

        });

        v.getBtnCobrar().addActionListener(l -> {
            int s = v.getTablaJugadores().getSelectedRow();
            if (s != -1) {
                CancelarDeuda(s);
            } else {
                JOptionPane.showMessageDialog(v.getDlgFichas(), "Seleccione un Jugador");
            }
        });
        v.getBtnSalir().addActionListener(l -> {
            int s = v.getTablaJugadores().getSelectedRow();
            int confirmar = JOptionPane.showConfirmDialog(v, "Seguro que desea retirar al jugador", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            if (confirmar == 0) {
                Salir(s);
            }
        });
        v.getBtnActivar().addActionListener(l -> {
            int s = v.getTablaJugadores().getSelectedRow();
            int confirmar = JOptionPane.showConfirmDialog(v, "Seguro que desea regresar al jugador", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            if (confirmar == 0) {
                Activar(s);
            }
        });
        v.getBtnRemover().addActionListener(l -> {
            int s = v.getTablaJugadores().getSelectedRow();
            int confirmar = JOptionPane.showConfirmDialog(v, "Seguro que desea retirar al jugador", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            if (confirmar == 0) {
                remover(s);
            }
        });
        v.getBtnRegistro().addActionListener(l -> {
            int s = v.getTablaJugadores().getSelectedRow();

            if (s != -1) {
                Registro(s);
            } else {
                JOptionPane.showMessageDialog(v.getDlgFichas(), "Seleccione un Jugador");
            }
        });
        v.getBtnCasilla().addActionListener(l -> AgregarCasilla());
        v.getBtnPropinaS().addActionListener(l -> AgregarPropinaServicio());
        v.getBtnPropinaD().addActionListener(l -> AgregarPropinaDeler());
        v.show();
    }

    public void botones() {
        v.getBtnFichas().setEnabled(false);
        v.getBtnCobrar().setEnabled(true);
        v.getBtnSalir().setEnabled(false);
        v.getBtnRemover().setEnabled(false);
        v.getBtnActivar().setEnabled(false);
        v.getBtnRegistro().setEnabled(true);
    }

    public void Encender(int s) {

        ModlJugador mj = new ModlJugador();
        List<Jugador> lj = mj.ListarJugadores(this.ID_MESA);

        if (lj.get(s).getEstado() == 1) {
            v.getBtnFichas().setEnabled(true);
            v.getBtnCobrar().setEnabled(true);
            v.getBtnSalir().setEnabled(true);
            v.getBtnRemover().setEnabled(true);
            v.getBtnActivar().setEnabled(false);
            v.getBtnRegistro().setEnabled(true);
        } else {
            v.getBtnFichas().setEnabled(false);
            v.getBtnCobrar().setEnabled(true);
            v.getBtnSalir().setEnabled(false);
            v.getBtnRemover().setEnabled(true);
            v.getBtnActivar().setEnabled(true);
            v.getBtnRegistro().setEnabled(true);
        }

    }

    public void AgregarFichas() {
        int id = id_detalle();
        int s = v.getTablaFichas().getSelectedRow();
        ModlFichas mf = new ModlFichas();
        ModlDetallFichas md = new ModlDetallFichas();
        List<Fichas> l = mf.ListarFMesa();
        md.setId_detalle(id);
        md.setId_ficahs(l.get(s).getId_fichas());
        md.setId_mesa(this.ID_MESA);
        mf.setEstado("1");
        mf.setId_fichas(l.get(s).getId_fichas());
        md.Insertar();
        mf.Estado();
        JOptionPane.showMessageDialog(v.getDlgFichas(), "Fichas agregadas exitosamente");
        v.getDlgFichas().setVisible(false);
        CargarFichasTotales();

    }

    public void CargarFichasTotales() {
        ModlDetallFichas md = new ModlDetallFichas();
        ModlFichas mf = new ModlFichas();
        ModlDeudas mdd = new ModlDeudas();
        List<DetalleFichas> l = md.listar();

        double fichas = 0;
        double total_ingresos = T_ingresos();
        double cambios = T_Cambios();
        double casilla = T_Casilla();
        double p_servi = T_PropinaS();
        double p_deler = T_PropinaD();
        double maletin = 0;
        double cantidad;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getId_mesa() == this.ID_MESA) {
                List<Fichas> lf = mf.ListarFichas(l.get(i).getId_ficahs());
                cantidad = lf.get(0).getCantidad() * lf.get(0).getValor();
                fichas = fichas + cantidad;
            }
        }

        maletin = fichas - total_ingresos + cambios + casilla + p_servi + p_deler;
        v.getLblfichas().setText(maletin + "");
    }

    public double T_ingresos() {
        double i = 0;
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> l = me.ListarJ(this.ID_MESA);
        for (int j = 0; j < l.size(); j++) {
            i = i + l.get(j).getIngreso_total();
        }
        return i;
    }

    public double T_Cambios() {
        double i = 0;
        ModlCambio mc = new ModlCambio();
        List<Cambio> lc = mc.ListaCambiosTotalesMesa(this.ID_MESA);
        for (int j = 0; j < lc.size(); j++) {
            i = i + lc.get(j).getValor();
        }
        return i;
    }

    public double T_Casilla() {

        return Double.parseDouble(v.getLblCasilla().getText());
    }

    public double T_PropinaS() {

        return Double.parseDouble(v.getLblPopinaS().getText());
    }

    public double T_PropinaD() {

        return Double.parseDouble(v.getLblPropinaD().getText());
    }

    public void CargarDialogo() {

        v.getDlgFichas().setSize(410, 365);
        v.getDlgFichas().setLocationRelativeTo(v);
        v.getDlgFichas().setModal(true);
        CargarFichas();
        v.getDlgFichas().setVisible(true);
    }

    public void CargarJugadores() {
        ModlEstadJugador per = new ModlEstadJugador();
        ModlJugador j = new ModlJugador();
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaJugadores().getModel();
        tbmodel.setRowCount(0);
        List<EstadJugador> lp = per.ListarJM(this.ID_MESA);
        List<Jugador> lj = j.ListarJugadores(this.ID_MESA);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lj.stream().forEach(l -> {
            lp.stream().filter(x -> l.getId_jugador() == x.getId_jugador()).forEach(t -> {
                tbmodel.addRow(new Object[n]);
                v.getTablaJugadores().setValueAt(l.getNombre() + " " + l.getApellido(), i.value, 0);
                v.getTablaJugadores().setValueAt(t.getIngreso_total(), i.value, 1);
                v.getTablaJugadores().setValueAt(t.getDeudas_en_contra(), i.value, 2);
            });
            i.value++;
        });
    }

    public void CargarFichas() {
        ModlFichas mf = new ModlFichas();
        v.getTablaFichas().setDefaultRenderer(Object.class, new ImagenTabla());
        v.getTablaFichas().setRowHeight(100);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        DefaultTableModel tbModel;//estructura del JTable
        tbModel = (DefaultTableModel) v.getTablaFichas().getModel();
        tbModel.setNumRows(0);
        List<Fichas> lf = mf.ListarFMesa();
        int ncolums = tbModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lf.stream().forEach(l -> {
            tbModel.addRow(new Object[ncolums]);
            v.getTablaFichas().setValueAt(l.getCantidad(), i.value, 2);
            v.getTablaFichas().setValueAt(l.getValor(), i.value, 1);
            Image img = l.getFoto();
            if (img != null) {
                Image nimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(nimg);
                render.setIcon(icon);
                v.getTablaFichas().setValueAt(new JLabel(icon), i.value, 3);
            } else {
                v.getTablaFichas().setValueAt(null, i.value, 3);
            }

            String c = l.getColor();
            String vl[] = c.split(",");
            int r, g, b;
            r = Integer.parseInt(vl[0]);
            g = Integer.parseInt(vl[1]);
            b = Integer.parseInt(vl[2]);
            Color cl = new Color(r, g, b);
            JPanel pl = new JPanel();
            pl.setSize(100, 100);
            pl.setBackground(cl);
            v.getTablaFichas().setValueAt(pl, i.value, 0);
            i.value++;
        });
    }

    private int id_detalle() {
        int n = 0;
        ModlDetallFichas md = new ModlDetallFichas();
        List<DetalleFichas> ms = md.listar();
        n = ms.size() + 1;
        do {
            ms = md.listar(n);
            if (ms.size() == 1) {
                n++;
            } else {
                break;
            }

        } while (true);
        return n;
    }

    public void AgregarJugador() {
        VistaDialogoJugador vj = new VistaDialogoJugador(v, true);
        ModlJugador mj = new ModlJugador();
        CtrlDialogoJugador c = new CtrlDialogoJugador(vj, v, mj, ID_MESA);
        c.IniciarControl();
    }

    public void ComprarFichas(int s) {
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> lp = me.ListarJM(this.ID_MESA);
        VistaDialogueFichas vf = new VistaDialogueFichas(v, true);
        ModlIngrJugMesa mi = new ModlIngrJugMesa();
        CtrlingresarFichas c = new CtrlingresarFichas(v, vf, mi, lp.get(s).getId_est_jug(), Double.parseDouble(v.getLblfichas().getText()));
        c.IniciarControl();
    }

    public void CancelarDeuda(int s) {
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> lp = me.ListarJM(this.ID_MESA);
        if (lp.get(s).getDeudas_en_contra() > 0) {
            VistaDialogoDeudas vd = new VistaDialogoDeudas(v, true);
            ModlDeudas md = new ModlDeudas();
            CtrlDeudasJugador c = new CtrlDeudasJugador(v, vd, md, lp.get(s).getId_est_jug());
            System.out.println("");
            c.IniciarControlCobrar_Deuda();
        } else {
            JOptionPane.showMessageDialog(v.getDlgFichas(), "El jugador no tiene deudas en la mesa");
        }

    }

    public void Salir(int s) {
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> le = me.ListarJM(this.ID_MESA);
        VistaDialogueSalir ve = new VistaDialogueSalir(v, true);
        CtrlSalir c = new CtrlSalir(ve, v, le.get(s).getId_est_jug(), me);
        c.IniciarControl();
    }

    public void Activar(int s) {
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> le = me.ListarJM(this.ID_MESA);
        ModlJugador j = new ModlJugador();
        List<Jugador> lj = j.ListarJugadores(this.ID_MESA);
        j.setId_jugador(le.get(s).getId_jugador());
        j.setEstado(1);

        if (j.estado()) {
            JOptionPane.showMessageDialog(v, "Jugador regresa con exito");
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }
        Encender(s);
    }

    public void remover(int s) {
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> le = me.ListarJM(this.ID_MESA);
        ModlJugador j = new ModlJugador();
        List<Jugador> lj = j.ListarJugadores(this.ID_MESA);

        if (le.get(s).getIngreso_total() == 0) {
            me.setId_est_jug(le.get(s).getId_est_jug());
            j.setEstado(0);
            j.setId_jugador(le.get(s).getId_jugador());
            if (me.SalirMesa()) {
                j.estado();
                JOptionPane.showMessageDialog(v, "Jugador retirado con exito");
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        } else {
            JOptionPane.showMessageDialog(v, "Jugador no puede ser retirado por jugar en la mesa");
        }
        CargarJugadores();
        CargarFichasTotales();
        botones();
    }

    public void Registro(int s) {
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> le = me.ListarJM(this.ID_MESA);
        ModlJugador j = new ModlJugador();
        List<Jugador> lj = j.ListarJugadores(this.ID_MESA);
        ModlIngrJugMesa mi = new ModlIngrJugMesa();
        VistaDialogueRegisto vr = new VistaDialogueRegisto(v, true);
        CtrlIngr_jugador c = new CtrlIngr_jugador(v, vr, mi, le.get(s).getId_est_jug(), lj.get(s).getNombre() + " " + lj.get(s).getApellido());
        c.IniciarControl();
    }

    public void AgregarCasilla() {
        double valor = Double.parseDouble(v.getLblCasilla().getText());
        double nuevo = Double.parseDouble(v.getTxtCasilla().getText());
        double casilla = valor + nuevo;
        m.setCasilla(casilla);
        m.setId_mesa(ID_MESA);
        if (m.ActualizarCasilla()) {
            JOptionPane.showMessageDialog(v, "Casilla subida con exito");
        } else {
            JOptionPane.showMessageDialog(v, "Error");
        }
        v.getTxtCasilla().setText("0");
        cargar();
    }

    public void CargarCasilla() {
        List<Mesa> l = m.Listar(ID_MESA);
        v.getLblCasilla().setText(l.get(0).getCasilla() + "");
    }

    public void CargarPropinaServicio() {
        ModlPropinas mp = new ModlPropinas();
        List<Propinas> l = mp.ListarPropinas(ID_MESA);
        if (!l.isEmpty()) {
            v.getLblPopinaS().setText(l.get(0).getPropina_servi() + "");
        }

    }

    public void CargarPropinaDeler() {
        ModlPropinas mp = new ModlPropinas();
        List<Propinas> l = mp.ListarPropinas(ID_MESA);
        if (!l.isEmpty()) {
            v.getLblPropinaD().setText(l.get(0).getPropina_deler() + "");
        }

    }

    public void AgregarPropinaServicio() {
        ModlPropinas mp = new ModlPropinas();
        List<Propinas> lp = mp.ListarPropinas(ID_MESA);
        int ID_propina = IDPropina();
        double valor = Double.parseDouble(v.getLblPopinaS().getText());
        double nuevo = Double.parseDouble(v.getTxtPropinaS().getText());
        double propina = valor + nuevo;
        mp.setId_propina(ID_propina);
        mp.setId_mesa(ID_MESA);
        mp.setPropina_servi(propina);

        if (lp.isEmpty()) {
            mp.setPropina_deler(0);
            if (mp.Insert()) {
                JOptionPane.showMessageDialog(v, "Propina subida exitosamente");
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        } else {
            mp.setId_propina(lp.get(0).getId_propina());
            mp.setPropina_deler(lp.get(0).getPropina_deler());
            if (mp.Update()) {
                JOptionPane.showMessageDialog(v, "Propina subida exitosamente");
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        }
        v.getTxtPropinaS().setText("0");
        cargar();
    }

    public void AgregarPropinaDeler() {
        ModlPropinas mp = new ModlPropinas();
        List<Propinas> lp = mp.ListarPropinas(ID_MESA);
        int ID_propina = IDPropina();
        double valor = Double.parseDouble(v.getLblPropinaD().getText());
        double nuevo = Double.parseDouble(v.getTxtPropinaD().getText());
        double propina = valor + nuevo;
        mp.setId_propina(ID_propina);
        mp.setId_mesa(ID_MESA);
        mp.setPropina_deler(propina);

        if (lp.isEmpty()) {
            mp.setPropina_servi(0);
            if (mp.Insert()) {
                JOptionPane.showMessageDialog(v, "Propina subida exitosamente");
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        } else {
            mp.setId_propina(lp.get(0).getId_propina());
            mp.setPropina_servi(lp.get(0).getPropina_servi());
            if (mp.Update()) {
                JOptionPane.showMessageDialog(v, "Propina subida exitosamente");
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        }
        v.getTxtPropinaD().setText("0");
        cargar();
    }

    public int IDPropina() {
        int n = 0;
        ModlPropinas mp = new ModlPropinas();
        List<Propinas> lp = mp.ListarTotal();
        n = lp.size() + 1;
        do {
            lp = mp.IDListarTotal(n);
            if (lp.size() == 1) {
                n++;
            } else {
                break;
            }

        } while (true);
        return n;
    }

    public void cargar() {
        CargarCasilla();
        CargarPropinaServicio();
        CargarPropinaDeler();
        CargarJugadores();
        CargarFichasTotales();
        botones();
    }
}
