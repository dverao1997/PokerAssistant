/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.mesa;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.modelo.mesa.IngrJugMesa;
import pokercash.modelo.mesa.ModlIngrJugMesa;
import pokercash.vista.mesa.VistaDialogueRegisto;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlIngr_jugador {

    private VistaMesa vm;
    private VistaDialogueRegisto v;
    private ModlIngrJugMesa m;
    private int ID_ESTADISTICA;
    private String NOMBRE;

    public CtrlIngr_jugador(VistaMesa vm, VistaDialogueRegisto v, ModlIngrJugMesa m, int ID_ESTADISTICA, String NOMBRE) {
        this.vm = vm;
        this.v = v;
        this.m = m;
        this.ID_ESTADISTICA = ID_ESTADISTICA;
        this.NOMBRE = NOMBRE;
        v.setTitle("Ingresos del Jugador");
        v.setLocationRelativeTo(vm);
        v.setModal(true);
    }

    public void IniciarControl() {
        cargarLista();
        v.show();
    }

    public void cargarLista() {
        v.getLblJugador().setText(NOMBRE);
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaIngresos().getModel();
        tbmodel.setRowCount(0);
        List<IngrJugMesa> lp = m.ListarIngresos(ID_ESTADISTICA);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lp.stream().forEach(l -> {

            tbmodel.addRow(new Object[n]);
            v.getTablaIngresos().setValueAt(l.getIngreso(), i.value, 0);
            v.getTablaIngresos().setValueAt(l.getHora(), i.value, 1);
            v.getTablaIngresos().setValueAt(l.getTipo(), i.value, 2);

            i.value++;
        });
    }
}
