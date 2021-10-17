/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.mesa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import pokercash.modelo.mesa.EstadJugador;
import pokercash.modelo.mesa.IngrJugMesa;
import pokercash.modelo.mesa.ModlEstadJugador;
import pokercash.modelo.mesa.ModlIngrJugMesa;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.vista.mesa.VistaDialogueFichas;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlingresarFichas {

    private VistaMesa vm;
    private VistaDialogueFichas v;
    private ModlIngrJugMesa m;
    private int id_Est;
    private double maletin;

    public CtrlingresarFichas(VistaMesa vm, VistaDialogueFichas v, ModlIngrJugMesa m, int id_Est, double maletin) {
        this.vm = vm;
        this.v = v;
        this.m = m;
        this.id_Est = id_Est;
        this.maletin = maletin;
        v.setLocationRelativeTo(vm);
        v.setModal(true);
    }

    public void IniciarControl() {
        v.getBtnAceptar().addActionListener(l -> {
            if (Double.parseDouble(v.getTxtValor().getText()) > maletin) {
                JOptionPane.showMessageDialog(v, "Fichas insuficientes en el maletin");
                v.setVisible(false);
            } else {
                Comprar();
            }

        });
        v.show();
    }

    public void Comprar() {
        double valor = Double.parseDouble(v.getTxtValor().getText());
        String t = v.getCbxTransaccion().getSelectedItem().toString();
        int id_ingr = IDIng();
        String hour = Hora();
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> le = me.ID(id_Est);

        m.setId_ingr_jug(id_ingr);
        m.setIngreso(valor);
        m.setHora(hour);
        m.setTipo(t);
        m.setId_est_jug(this.id_Est);
        m.setIngreso_total(le.get(0).getIngreso_total() + valor);
        if ("Deuda".equals(t)) {
            m.setDeudas(le.get(0).getDeudas() + valor);
        } else {
            m.setDeudas(le.get(0).getDeudas());
        }
        if (m.Insertar()) {
            JOptionPane.showMessageDialog(v, "Fichas Comprada exitosamente");
            ModlMesa mm = new ModlMesa();
            CtrlMesa c = new CtrlMesa(vm, mm, le.get(0).getId_mesa());
            v.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
            v.setVisible(false);
        }
    }

    public String Hora() {
        Date d = new Date();
        String pmAm = "HH:mm";
        SimpleDateFormat format = new SimpleDateFormat(pmAm);
        Calendar hoy = Calendar.getInstance();
        return String.format(format.format(d), hoy);
    }

    public int IDIng() {
        int n = 0;

        List<IngrJugMesa> ms = m.ListarIj();
        n = ms.size() + 1;
        do {
            ms = m.IDListarIj(n);
            if (ms.size() == 1) {
                n++;
            } else {
                break;
            }

        } while (true);
        return n;
    }
}
