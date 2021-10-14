/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.inventario;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.inventario.Fichas;
import pokercash.modelo.inventario.ModlFichas;
import pokercash.vista.inventario.VistaFichas;

/**
 *
 * @author Usuario
 */
public class CtrlFichas {

    private ModlFichas m;
    private VistaFichas v;
    private Image imagen;
    private String col = "240,240,240";
    private int opc;

    public CtrlFichas(ModlFichas m, VistaFichas v) {
        this.m = m;
        this.v = v;
        v.setTitle("Ingresar Fichas");
        cargarLista();
        v.setVisible(true);
    }

    public void IniciarControl() {
        v.getBtnIngresar().addActionListener(l -> CargarDialogo(1));
        v.getBtnColor().addActionListener(l -> Color());
        v.getBtnCerrar().addActionListener(l->cerrar());
        v.getBtnAceptar().addActionListener(l ->{
            switch (opc) {
                case 1:
                    IngresarFichas();
                    break;
                case 2:
                    Editar();
                    break;
            }
        } );
        v.getBtnCargar().addActionListener(l -> cargarFoto());
        v.getBtnEditar().addActionListener(l -> {
            int s = v.getTablaFichas().getSelectedRow();
            if (s != -1) {
                CargarDialogo(2);
            } else {
                JOptionPane.showMessageDialog(v, "Seleccione ficha para editar");
            }

        });
    }

    public void cargarLista() {
        v.getTablaFichas().setDefaultRenderer(Object.class, new ImagenTabla());
        v.getTablaFichas().setRowHeight(100);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        DefaultTableModel tbModel;//estructura del JTable
        tbModel = (DefaultTableModel) v.getTablaFichas().getModel();
        tbModel.setNumRows(0);
        List<Fichas> lf = m.ListarFichas();
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

    public void CargarDialogo(int opc) {
        switch (opc) {
            case 1:
                v.getDlgFichas().setTitle("Ingresar Fichas");
                LimpiarCampos();
                break;
            case 2:
                v.getDlgFichas().setTitle("Editar Ficha");
                int s = v.getTablaFichas().getSelectedRow();
                List<Fichas> l = m.ListarFichas();
                v.getTxtValor().setText(String.valueOf(l.get(s).getValor()));
                v.getTxtCantidad().setText(String.valueOf(l.get(s).getCantidad()));
                String c = l.get(s).getColor();
                this.col = c;
                String vl[] = c.split(",");
                int r,
                 g,
                 b;
                r = Integer.parseInt(vl[0]);
                g = Integer.parseInt(vl[1]);
                b = Integer.parseInt(vl[2]);
                Color cl = new Color(r, g, b);
                v.getPanelColor().setBackground(cl);

                Image img = l.get(s).getFoto();
                this.imagen = img;
                Icon icon = new ImageIcon(img);
                v.getLblImagen().setIcon(icon);
                v.getLblImagen().updateUI();
                break;
        }
        this.opc=opc;
        v.getDlgFichas().setSize(370, 340);
        v.getDlgFichas().setLocationRelativeTo(v);
        v.getDlgFichas().setModal(true);
        v.getDlgFichas().setVisible(true);
    }

    public void IngresarFichas() {
        int id = IdFichas();
        ModlFichas mf = new ModlFichas();
        mf.setId_fichas(id);
        mf.setEstado("0");
        mf.setValor(Double.parseDouble(v.getTxtValor().getText()));
        mf.setCantidad(Integer.parseInt(v.getTxtCantidad().getText()));
        mf.setColor(col);
        mf.setFoto(imagen);
        if (mf.Ingresar()) {
            JOptionPane.showMessageDialog(v, "Fichas Ingresadas Correctamente");
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }
        LimpiarCampos();
        v.getDlgFichas().setVisible(false);
    }

    public void LimpiarCampos() {
        v.getTxtCantidad().setText("0");
        v.getTxtValor().setText("0");
        v.getLblImagen().setIcon(null);
        v.getPanelColor().setBackground(null);
        cargarLista();
    }

    public void Color() {
        Color c = Color.WHITE;
        c = JColorChooser.showDialog(v, "ESCOGER COLOR", c);
        v.getPanelColor().setBackground(c);
        col = c.toString();
        col = col.substring(15);
        col = col.replace("]", "");
        col = col.replace("=", "");
        col = col.replace("r", "");
        col = col.replace("g", "");
        col = col.replace("b", "");
    }

    public int IdFichas() {
        List<Fichas> lf = m.ListarFichas();
        int id;
        id = lf.size() + 1;
        do {
            lf = m.ListarFichas(id);
            if (lf.size() == 1) {
                id++;
            } else {
                return id;
            }
        } while (true);
    }

    private void cargarFoto() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                Image imagen = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
                        v.getLblImagen().getWidth(),
                        v.getLblImagen().getHeight(),
                        Image.SCALE_DEFAULT);
                this.imagen = imagen;
                Icon icon = new ImageIcon(imagen);
                v.getLblImagen().setIcon(icon);
                v.getLblImagen().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(ModlFichas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Editar() {
        int s = v.getTablaFichas().getSelectedRow();
        List<Fichas> l = m.ListarFichas();
        ModlFichas mf = new ModlFichas();
        mf.setId_fichas(l.get(s).getId_fichas());
        mf.setValor(Double.parseDouble(v.getTxtValor().getText()));
        mf.setCantidad(Integer.parseInt(v.getTxtCantidad().getText()));
        mf.setColor(col);
        mf.setFoto(imagen);
        if (mf.Editar()) {
            JOptionPane.showMessageDialog(v, "Fichas Editada con exito");
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }
        LimpiarCampos();
        v.getDlgFichas().setVisible(false);
    }
    
    public void cerrar(){
        v.setVisible(false);
    }
}
