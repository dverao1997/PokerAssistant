/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import java.util.List;
import javax.swing.JOptionPane;
import pokercash.modelo.persona.Usuario;
import pokercash.modelo.persona.modlUsuario;
import pokercash.vista.vistaInicio;
import pokercash.vista.vistaPrincipal;

/**
 *
 * @author CyberLink
 */
public class ctrlInicio {

    private final modlUsuario m;
    private final vistaInicio v;

    public ctrlInicio(modlUsuario m, vistaInicio v) {
        this.m = m;
        this.v = v;
        v.setTitle("Iniciar Usurio");
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }

    public void iniciarControl() {

        v.getBtnIniciar().addActionListener(l -> iniciar(v.getTxtUsuario().getText(), v.getTxtPassword().getText()));

    }

    public void iniciar(String u, String p) {
        List<Usuario> usu = m.IniciarSesion(u);
        if ("".equals(u) || "".equals(p)) {
            JOptionPane.showMessageDialog(v, "Ingrese Usuario y Contraseña");
        } else {
            if (!usu.isEmpty()) {

                if (usu.get(0).getPassword().equals(p)) {
                    vistaPrincipal vista = new vistaPrincipal();
                    vista.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(v, "Usuario o contraseña Incorrecta");
            }
        }

    }
}
