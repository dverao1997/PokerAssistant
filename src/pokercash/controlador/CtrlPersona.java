/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.controlador;

import pokercash.modelo.persona.ModlPersona;
import pokercash.vista.persona.VistaPersona;

/**
 *
 * @author Usuario
 */
public class CtrlPersona {
    private ModlPersona m;
    private VistaPersona v;

    public CtrlPersona(ModlPersona m, VistaPersona v) {
        this.m = m;
        this.v = v;
        v.setVisible(true);
        
    }
    
   public void IniciarControl(int opc){
       
   }
   
   public void CargarLista(){
       
   }
}
