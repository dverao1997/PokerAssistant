/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.inventario.ModlProducto;
import pokercash.modelo.inventario.Producto;
import pokercash.vista.producto.VistaProducto;

/**
 *
 * @author CyberLink
 */
public class CtrlProductos {

    private ModlProducto m;
    private VistaProducto v;

    public CtrlProductos(ModlProducto m, VistaProducto v) {
        this.m = m;
        this.v = v;
        v.setTitle("Productos");
        v.setVisible(true);
        CargarLista("");
    }

    public void IniciarControl() {
        KeyListener kl=new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                CargarLista(v.getTxtBuscar().getText().toUpperCase());
            }
        };
        
        v.getTxtBuscar().addKeyListener(kl);
    }

    public void CargarLista(String aguja) {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaProducto().getModel();
        tbmodel.setRowCount(0);
        List<Producto> lp = m.ListarProducto(aguja);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        
        lp.stream().forEach(l->{
            tbmodel.addRow(new Object[n]);
            v.getTablaProducto().setValueAt(l.getNombre(), i.value, 0);
            v.getTablaProducto().setValueAt(l.getExistencias(), i.value, 1);
            v.getTablaProducto().setValueAt(l.getPrecio(), i.value, 2);
            i.value++;
        });
    }
}
