/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador.inventario;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import pokercash.vista.mesa.VistaMesa;

/**
 *
 * @author CyberLink
 */
public class CtrlMesa {

    private VistaMesa v;

    public CtrlMesa(VistaMesa v) {
        this.v = v;

        v.getDlgOrganizacion().setSize(450, 450);
        v.getDlgOrganizacion().setLocationRelativeTo(v);
        v.getDlgOrganizacion().setModal(true);
        v.getDlgOrganizacion().setVisible(true);
    }

    public void IniciarControl() {
        
    }
}
