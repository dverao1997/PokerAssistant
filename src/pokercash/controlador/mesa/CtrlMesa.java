/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.mesa;

import java.awt.Color;
import java.awt.Image;
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
import pokercash.modelo.mesa.EstadJugador;
import pokercash.modelo.mesa.ModlEstadJugador;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.modelo.persona.Jugador;
import pokercash.modelo.persona.ModlJugador;
import pokercash.vista.mesa.VistaDialogoJugador;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlMesa {

    private VistaMesa v;
    private ModlMesa m;
    private int id;

    public CtrlMesa(VistaMesa v, ModlMesa m, int id) {
        this.v = v;
        this.m = m;
        this.id = id;

        v.setTitle("Poker table Administrator");

     
        CargarJugadores();
        CargarFichasTotales();
       
    }

    public void IniciarControl() {
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
         v.show();
    }

    public void AgregarFichas() {
        int id = id_detalle();
        int s = v.getTablaFichas().getSelectedRow();
        ModlFichas mf = new ModlFichas();
        ModlDetallFichas md = new ModlDetallFichas();
        List<Fichas> l = mf.ListarFMesa();
        md.setId_detalle(id);
        md.setId_ficahs(l.get(s).getId_fichas());
        md.setId_mesa(this.id);
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
        List<DetalleFichas> l = md.listar();

        double valor = 0;
        double cantidad;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getId_mesa() == this.id) {
                List<Fichas> lf = mf.ListarFichas(l.get(i).getId_ficahs());
                cantidad = lf.get(0).getCantidad() * lf.get(0).getValor();
                valor = valor + cantidad;
            }
        }
        v.getLblfichas().setText(valor + "");
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
        List<EstadJugador> lp = per.ListarJ(this.id);
        List<Jugador> lj = j.ListarJ();
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lj.stream().forEach(l -> {
            lp.stream().filter(x -> l.getId_jugador() == x.getId_jugador()).forEach(t -> {
                tbmodel.addRow(new Object[n]);
                v.getTablaJugadores().setValueAt(l.getNombre() + " " + l.getApellido(), i.value, 0);
                v.getTablaJugadores().setValueAt(t.getIngreso_total(), i.value, 1);
                v.getTablaJugadores().setValueAt(t.getDeudas(), i.value, 2);
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
        CtrlDialogoJugador c = new CtrlDialogoJugador(vj, v, mj, id);
        c.IniciarControl();
    }
}
