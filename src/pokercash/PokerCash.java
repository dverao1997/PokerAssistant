/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash;

import pokercash.controlador.ctrlInicio;
import pokercash.modelo.persona.modlUsuario;
import pokercash.vista.vistaInicio;

/**
 *
 * @author CyberLink
 */
public class PokerCash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        modlUsuario m=new modlUsuario();
        vistaInicio v=new vistaInicio();
        ctrlInicio ctrl=new ctrlInicio(m, v);
        ctrl.iniciarControl();
    }
    
}
