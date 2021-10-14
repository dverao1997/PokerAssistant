/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.inventario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.inventario.ModlProducto;
import pokercash.modelo.inventario.Producto;
import pokercash.vista.inventario.VistaProducto;

/**
 *
 * @author CyberLink
 */
public class CtrlProductos {

    private ModlProducto m;
    private VistaProducto v;
    private int opcion;

    public CtrlProductos(ModlProducto m, VistaProducto v) {
        this.m = m;
        this.v = v;
        v.setTitle("Productos");
        spaum(false);
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
                v.getSpAumentar().setValue(0);
                spaum(false);
            }
        };
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {

            }

            @Override
            public void mousePressed(MouseEvent me) {
                List<Producto> l = m.ListarProducto(v.getTxtBuscar().getText().toUpperCase());
                int valor = l.get(v.getTablaProducto().getSelectedRow()).getExistencias();
                spaum(true);

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
        v.getTablaProducto().addMouseListener(ml);
        v.getTxtBuscar().addKeyListener(kl);
        v.getBtnNuevo().addActionListener(l -> CargarDialogue(1));
        v.getBtnCerrar().addActionListener(l->Cerrar());
        v.getBtnAumentar().addActionListener(l -> Aumentar());
        v.getBtnCancelar().addActionListener(l -> Cancelar());
        v.getBtnEditar().addActionListener((l) -> {
            int s = v.getTablaProducto().getSelectedRow();
            if (s != -1) {
                CargarDialogue(2);
            } else {
                JOptionPane.showMessageDialog(v, "Seleccione el producto a editar");
            }
        });
        v.getBtnAceptar().addActionListener((l) -> {
            switch (opcion) {
                case 1:
                    IngresarProducto();
                    break;
                case 2:
                    Editar();
                    break;
            }

        });

    }

    public void CargarLista(String aguja) {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaProducto().getModel();
        tbmodel.setRowCount(0);
        List<Producto> lp = m.ListarProducto(aguja);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        lp.stream().forEach(l -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaProducto().setValueAt(l.getNombre(), i.value, 0);
            v.getTablaProducto().setValueAt(l.getExistencias(), i.value, 1);
            v.getTablaProducto().setValueAt(l.getPrecio(), i.value, 2);
            i.value++;
        });
    }

    public void IngresarProducto() {
        int Id_prod = Id();
        ModlProducto mp = new ModlProducto();
        mp.setId_producto(Id_prod);
        mp.setNombre(v.getTxtNombre().getText());
        mp.setPrecio(Double.parseDouble(v.getTxtPrecio().getText()));
        mp.setExistencias((int) v.getSpCantidad().getValue());
        if (mp.InsertarProducto()) {
            JOptionPane.showMessageDialog(v.getDlgProducto(), "Producto Ingresado con exito");
        } else {
            JOptionPane.showMessageDialog(v.getDlgProducto(), "error");
        }
        v.getDlgProducto().setVisible(false);
        LimpiarCampos();
    }

    public void LimpiarCampos() {
        v.getTxtNombre().setText("");
        v.getTxtBuscar().setText("");
        v.getTxtPrecio().setText("0");
        spaum(false);
        v.getSpCantidad().setValue(0);
        CargarLista("");
    }

    public int Id() {
        List<Producto> lp = m.ListarProducto("");
        int valor = lp.size() + 1;
        do {
            lp = m.IDProducto(valor);
            if (lp.size() == 1) {
                valor++;
            } else {
                break;
            }

        } while (true);
        return valor;
    }

    public void CargarDialogue(int opc) {
        switch (opc) {
            case 1:
                v.getDlgProducto().setTitle("Ingresar Producto");
                LimpiarCampos();
                break;
            case 2:
                int s = v.getTablaProducto().getSelectedRow();
                v.getDlgProducto().setTitle("Editar Prodcuto");
                List<Producto> lp = m.ListarProducto(v.getTxtBuscar().getText().toUpperCase());
                m.setId_producto(lp.get(s).getId_producto());
                v.getTxtNombre().setText(lp.get(s).getNombre());
                v.getTxtPrecio().setText(lp.get(s).getPrecio() + "");
                v.getSpCantidad().setValue(lp.get(s).getExistencias());
                break;
        }
        opcion = opc;
        
        v.getDlgProducto().setSize(230, 230);
        v.getDlgProducto().setLocationRelativeTo(v);
        v.getDlgProducto().setModal(true);
        v.getDlgProducto().setVisible(true);
    }

    public void spaum(boolean sp) {
        v.getSpAumentar().setValue(0);
        v.getSpAumentar().setEnabled(sp);
        v.getBtnAumentar().setEnabled(sp);
    }

    public void Aumentar() {
        ModlProducto mp = new ModlProducto();
        List<Producto> l = mp.ListarProducto(v.getTxtBuscar().getText().toUpperCase());
        int valor = (int) v.getSpAumentar().getValue() + l.get(v.getTablaProducto().getSelectedRow()).getExistencias();
        mp.setId_producto(l.get(v.getTablaProducto().getSelectedRow()).getId_producto());
        mp.setExistencias(valor);
        if (mp.AumentarProducto()) {
            JOptionPane.showMessageDialog(v, "Producto aumentado con exito");
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }
        CargarLista(v.getTxtBuscar().getText().toUpperCase());
        spaum(false);
    }

    public void Cancelar() {
        spaum(false);
        LimpiarCampos();
        v.getDlgProducto().setVisible(false);
    }

    public void Editar() {
        ModlProducto mp = new ModlProducto();
        mp.setId_producto(m.getId_producto());
        mp.setNombre(v.getTxtNombre().getText());
        mp.setPrecio(Double.parseDouble(v.getTxtPrecio().getText()));
        mp.setExistencias((int) v.getSpCantidad().getValue());
        if (mp.Editar()) {
            JOptionPane.showMessageDialog(v.getDlgProducto(), "Producto editado Exitosamente");
        } else {
            JOptionPane.showMessageDialog(v.getDlgProducto(), "ERROR");
        }
        Cancelar();
    }

    public void Cerrar() {
        v.setVisible(false);

    }
}
