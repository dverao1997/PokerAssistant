/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash;

import pokercash.controlador.CtrlInicio;
import pokercash.modelo.persona.ModlUsuario;
import pokercash.vista.principal.VistaInicio;



/**
 *
 * @author CyberLink
 */
public class PokerCash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModlUsuario m=new ModlUsuario();
        VistaInicio v=new VistaInicio();
        CtrlInicio ctrl=new CtrlInicio(m, v);
        ctrl.iniciarControl();
    }
    
}
