/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author CyberLink
 */
public class DetallFichas {
    private int id_detalle;
    private int id_mesa;
    private int id_fichas;

    public DetallFichas() {
    }

    public DetallFichas(int id_detalle, int id_mesa, int id_fichas) {
        this.id_detalle = id_detalle;
        this.id_mesa = id_mesa;
        this.id_fichas = id_fichas;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_fichas() {
        return id_fichas;
    }

    public void setId_fichas(int id_fichas) {
        this.id_fichas = id_fichas;
    }
    
    
}
