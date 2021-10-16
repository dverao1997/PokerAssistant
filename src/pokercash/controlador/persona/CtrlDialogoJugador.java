/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.persona;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import pokercash.controlador.mesa.CtrlMesa;
import pokercash.modelo.mesa.EstadJugador;
import pokercash.modelo.mesa.ModlEstadJugador;
import pokercash.modelo.mesa.ModlMesa;
import pokercash.modelo.persona.Jugador;
import pokercash.modelo.persona.ModlJugador;
import pokercash.modelo.persona.ModlPersona;
import pokercash.modelo.persona.Persona;
import pokercash.vista.mesa.VistaDialogoJugador;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlDialogoJugador {

    private VistaDialogoJugador v;
    private VistaMesa vm;
    private ModlJugador m;
    private int ID_mesa;

    public CtrlDialogoJugador(VistaDialogoJugador v, VistaMesa vm, ModlJugador m, int ID_mesa) {
        this.v = v;
        this.vm = vm;
        this.m = m;
        this.ID_mesa = ID_mesa;
        v.setModal(true);
        v.setLocationRelativeTo(vm);
        CargarPersonas("");
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
                CargarPersonas(v.getTxtBuscar().getText().toUpperCase());
            }
        };
        v.getTxtBuscar().addKeyListener(kl);
        v.getBtnAgregar().addActionListener(l -> {
            int s = v.getTablaJugador().getSelectedRow();
            if (s != -1) {
                Agregar(s);
            } else {
                JOptionPane.showMessageDialog(v, "Seleccione un persona para continuar");
            }
        });
        v.getBtnCerrar().addActionListener(l -> Cerrar());
        v.getBtnCrear().addActionListener(l->CargarDialogue());
        v.getBtnAceptar().addActionListener(l->Crearjugador());
        v.getBtnCancelar().addActionListener(l->Cerrar());
        v.show();
    }

    public void CargarPersonas(String aguja) {
        DefaultTableModel tbmodel;
        tbmodel = (DefaultTableModel) v.getTablaJugador().getModel();
        tbmodel.setRowCount(0);
        List<Jugador> lp = m.ListarP(aguja);
        int n = tbmodel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);
        lp.stream().forEach(listaEm -> {
            tbmodel.addRow(new Object[n]);
            v.getTablaJugador().setValueAt(listaEm.getNombre(), i.value, 0);
            v.getTablaJugador().setValueAt(listaEm.getApellido(), i.value, 1);
            v.getTablaJugador().setValueAt(listaEm.getTelefono(), i.value, 2);
            v.getTablaJugador().setValueAt(listaEm.getGenero(), i.value, 3);
            i.value++;
        });
    }

    public void Agregar(int s) {
        List<Jugador> lp = m.ListarP(v.getTxtBuscar().getText().toUpperCase());
        List<Jugador> lj = m.ListarJ(lp.get(s).getId_persona());
        if (lj.size() == 1) {
            if (lj.get(0).getEstado() == 1) {
                JOptionPane.showMessageDialog(v, "La persona ya se encuentra jugando una mesa");
            } else {
                Jugar(lj.get(0).getId_jugador());
            }
        } else {
            int idJ = IDJug();
            m.setId_jugador(idJ);
            m.setId_persona(lp.get(s).getId_persona());
            m.setFecha_ingreso(LocalDate.now());
            m.setEstado(0);
            if (m.insertar()) {
                Jugar(idJ);
            } else {
                JOptionPane.showMessageDialog(v, "ERROR");
            }
        }
    }

    public void Cerrar() {
        ModlMesa mm = new ModlMesa();
        CtrlMesa c = new CtrlMesa(vm, mm, ID_mesa);
        v.getDlgJugador().setVisible(false);
        v.setVisible(false);
    }

    public void Jugar(int jug) {
        int idEst = IDEst();
        ModlEstadJugador p = new ModlEstadJugador(idEst, 0, jug, ID_mesa, 0, 0, 0);
        if (p.JugarMesa()) {
            JOptionPane.showMessageDialog(v, "Jugador ingresado con exito");
            m.setId_jugador(jug);
            m.setEstado(1);
            m.estado();
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }
    }

    public int IDEst() {
        int num;
        ModlEstadJugador p = new ModlEstadJugador();
        List<EstadJugador> lista = p.ListarE();
        num = lista.size() + 1;

        do {
            lista = p.ID(num);

            if (lista.size() == 1) {
                num++;
            } else {
                break;
            }
        } while (true);
        return num;
    }

    public int IDPer() {
        int num;
        ModlPersona p = new ModlPersona();
        List<Persona> lista = p.Listarpersona();
        num = lista.size() + 1;

        do {
            lista = p.ListarpersonaID(num);

            if (lista.size() == 1) {
                num++;
            } else {
                break;
            }
        } while (true);
        return num;
    }

    public int IDJug() {
        int num;

        List<Jugador> lista = m.ListarJ();
        num = lista.size() + 1;
        do {
            lista = m.ListarIDJ(num);

            if (lista.size() == 1) {
                num++;
            } else {
                break;
            }
        } while (true);
        return num;
    }

    public void Crearjugador() {

        int Id_jug = IDJug();
        int Id_per = IDPer();

        m.setId_persona(Id_per);
        m.setId_jugador(Id_jug);
        m.setNombre(v.getTxtNombre().getText());
        m.setApellido(v.getTxtApellido().getText());
        m.setGenero(v.getCbxGenero().getSelectedItem().toString());
        m.setTelefono(v.getTxtTelefono().getText());
        m.setFecha_ingreso(LocalDate.now());
        m.setEstado(0);
        if (m.CrearJugador()) {
            Jugar(Id_jug);
            Cerrar();
        } else {
            JOptionPane.showMessageDialog(v, "ERROR");
        }

    }

    public void CargarDialogue() {
        v.getDlgJugador().setSize(315, 300);
        v.getDlgJugador().setLocationRelativeTo(v);
        v.getDlgJugador().setModal(true);
        v.setVisible(false);
        v.getDlgJugador().show();
    }

   

    
    
    
}
