/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.mesa;

import java.util.List;
import javax.swing.JOptionPane;
import pokercash.modelo.mesa.Cambio;
import pokercash.modelo.mesa.EstadJugador;
import pokercash.modelo.mesa.ModlCambio;
import pokercash.modelo.mesa.ModlDeudas;
import pokercash.modelo.mesa.ModlEstadJugador;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.modelo.persona.Jugador;
import pokercash.modelo.persona.ModlJugador;
import pokercash.vista.mesa.VistaDialogoDeudas;
import pokercash.vista.mesa.VistaDialogueSalir;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlSalir {

    private VistaDialogueSalir v;
    private VistaMesa vm;
    private ModlEstadJugador m;
    private int ID_ESTADISTICA;

    private double DEUDA = 0;

    public CtrlSalir(VistaDialogueSalir v, VistaMesa vm, int ID_ESTADISTICA, ModlEstadJugador m) {
        this.v = v;
        this.vm = vm;
        this.ID_ESTADISTICA = ID_ESTADISTICA;
        this.m = m;
        v.setTitle("Retirar Jugador");
        v.setLocationRelativeTo(vm);;
        v.setModal(true);
        Cargar();
    }

    public void IniciarControl() {
        v.getBtnAceptar().addActionListener(l -> RetirarJugador());
        v.show();
    }

    public void Cargar() {
        ModlJugador mj = new ModlJugador();
        List<EstadJugador> le = m.ID(ID_ESTADISTICA);
        List<Jugador> lj = mj.ListarNombresJugadores(le.get(0).getId_jugador());
        v.getLblJugador().setText(lj.get(0).getNombre() + " " + lj.get(0).getApellido());
    }

    public void RetirarJugador() {
        double FICHAS_DE_CAMBIO;
        double ULTIMO_INGRESO = 0;
        double CAMBIOS = 0;
        double GANANCIAS = 0;
        double PERDIDAS = 0;
        double DINERO = 0;

        ModlJugador mj = new ModlJugador();
        List<EstadJugador> le = m.ID(ID_ESTADISTICA);
        ModlCambio mc = new ModlCambio();
        List<Cambio> lc = mc.ListaCambios(ID_ESTADISTICA);

        FICHAS_DE_CAMBIO = Double.parseDouble(v.getTxtValor().getText());

        GANANCIAS = le.get(0).getGanancias();

        PERDIDAS = le.get(0).getPerdidas();
        int id_cambio = ID_cambio();

        if (lc.isEmpty()) {
            ULTIMO_INGRESO = le.get(0).getIngreso_total();
        } else {
            for (int i = 0; i < lc.size(); i++) {
                CAMBIOS = CAMBIOS + lc.get(i).getValor();
            }
            ULTIMO_INGRESO = le.get(0).getIngreso_total() - CAMBIOS + GANANCIAS - PERDIDAS;
        }

        if (ULTIMO_INGRESO == FICHAS_DE_CAMBIO) {
            DINERO = FICHAS_DE_CAMBIO - le.get(0).getDeudas_en_contra();
            mc.setId_cambio(id_cambio);
            mc.setValor(FICHAS_DE_CAMBIO);
            mc.setGanancias(GANANCIAS);
            mc.setPerdidas(PERDIDAS);
            mc.setDeudas_en_contra(0);
            mc.setId_est_jug(ID_ESTADISTICA);
            JOptionPane.showMessageDialog(v, "Jugador debe recibir $ " + DINERO);

            if (mc.SalirMesa()) {
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        } else {
            if (ULTIMO_INGRESO < FICHAS_DE_CAMBIO) {
                DINERO = FICHAS_DE_CAMBIO - le.get(0).getDeudas_en_contra();
                GANANCIAS = GANANCIAS + (FICHAS_DE_CAMBIO - ULTIMO_INGRESO);
                mc.setId_cambio(id_cambio);
                mc.setValor(FICHAS_DE_CAMBIO);
                mc.setGanancias(GANANCIAS);
                mc.setPerdidas(PERDIDAS);
                mc.setDeudas_en_contra(0);
                mc.setId_est_jug(ID_ESTADISTICA);
                JOptionPane.showMessageDialog(v, "Jugador debe recibir $ " + DINERO);
                if (mc.SalirMesa()) {
                } else {
                    JOptionPane.showMessageDialog(v, "ERROR");
                }
            } else {
                if (ULTIMO_INGRESO > FICHAS_DE_CAMBIO) {
                    DINERO = FICHAS_DE_CAMBIO - le.get(0).getDeudas_en_contra();
                    if (DINERO < 0) {
                        DINERO = DINERO * -1;
                    }
                    PERDIDAS = PERDIDAS + (ULTIMO_INGRESO - FICHAS_DE_CAMBIO);
                    mc.setId_cambio(id_cambio);
                    mc.setValor(FICHAS_DE_CAMBIO);
                    mc.setGanancias(GANANCIAS);
                    mc.setPerdidas(PERDIDAS);
                    mc.setDeudas_en_contra(DINERO);
                    mc.setId_est_jug(ID_ESTADISTICA);
                    JOptionPane.showMessageDialog(v, "Jugador debe pagar $ " + DINERO);
                    if (mc.SalirMesa()) {
                    } else {
                        JOptionPane.showMessageDialog(v, "ERROR");
                    }
                }
            }
        }

        mj.setEstado(
                0);
        mj.setId_jugador(le.get(0).getId_jugador());
        if (mj.estado()) {
            ModlMesa mm = new ModlMesa();
            CtrlMesa c = new CtrlMesa(vm, mm, le.get(0).getId_mesa());
            JOptionPane.showMessageDialog(v, "Jugador retirado con exito");
            v.setVisible(false);
        }
    }

    public int ID_cambio() {
        int num;
        ModlCambio mc = new ModlCambio();
        List<Cambio> lista = mc.ListaCambiosTotales();
        num = lista.size() + 1;
        do {
            lista = mc.IDListaCambios(num);

            if (lista.size() == 1) {
                num++;
            } else {
                break;
            }
        } while (true);
        return num;
    }
}
