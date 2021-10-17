/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.mesa;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import pokercash.modelo.mesa.Deudas;
import pokercash.modelo.mesa.EstadJugador;
import pokercash.modelo.mesa.ModlDeudas;
import pokercash.modelo.mesa.ModlEstadJugador;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.modelo.persona.Jugador;
import pokercash.modelo.persona.ModlJugador;
import pokercash.vista.mesa.VistaDialogoDeudas;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlDeudasJugador {

    private VistaMesa vm;
    private VistaDialogoDeudas v;
    private ModlDeudas m;
    private int ID_ESTADISTICA;

    public CtrlDeudasJugador(VistaMesa vm, VistaDialogoDeudas v, ModlDeudas m, int ID_ESTADISTICA) {
        this.vm = vm;
        this.v = v;
        this.m = m;
        this.ID_ESTADISTICA = ID_ESTADISTICA;

    }

    public void IniciarControl() {
        v.setTitle("Deuda Jugador");
        v.setLocationRelativeTo(vm);
        Cargar();
        v.setModal(true);
        
        v.getBtnAceptar().addActionListener(l -> {
            if (Double.parseDouble(v.getTxtValor().getText())>Double.parseDouble(v.getLblValor().getText())) {
                JOptionPane.showMessageDialog(v, "ingrese un valor menor que la deuda a pagar");
                
            }else{
                PagarDeuda();
            }
        });

        v.show();
    }

    public void Cargar() {
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> l = me.ID(ID_ESTADISTICA);
        ModlJugador mj=new ModlJugador();
        List<Jugador> lj=mj.ListarJNombres(l.get(0).getId_jugador());
        v.getLblJugador().setText(lj.get(0).getNombre()+" "+lj.get(0).getApellido());
        v.getLblValor().setText(l.get(0).getDeudas()+"");
    }

    public void PagarDeuda() {
        Double valor = Double.parseDouble(v.getTxtValor().getText());
        String t = v.getCbxTransaccion().getSelectedItem().toString();
        int id_deuda = IDDeuda();
        ModlEstadJugador me = new ModlEstadJugador();
        List<EstadJugador> l = me.ID(ID_ESTADISTICA);
        m.setId_deudas(id_deuda);
        m.setValor(valor);
        m.setFecha(LocalDate.now());
        m.setTipo(t);
        m.setId_est_jug(ID_ESTADISTICA);
        m.setDeudas(l.get(0).getDeudas() - valor);
        if (m.Pagar()) {
            ModlMesa mm = new ModlMesa();
            CtrlMesa c = new CtrlMesa(vm, mm, l.get(0).getId_mesa());
            JOptionPane.showMessageDialog(v, "Deuda pagada exitosamente");
            v.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(v, "Error");
        }
    }

    public int IDDeuda() {
        int num;

        List<Deudas> lista = m.ListarD();
        num = lista.size() + 1;
        do {
            lista = m.IDListarD(num);

            if (lista.size() == 1) {
                num++;
            } else {
                break;
            }
        } while (true);
        return num;
    }
}
