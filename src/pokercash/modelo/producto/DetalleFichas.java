/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.producto;

/**
 *
 * @author CyberLink
 */
public class DetalleFichas {
    private int id_detalle;
    private String id_mesa;
    private int id_ficahs;

    public DetalleFichas(int id_detalle, String id_mesa, int id_ficahs) {
        this.id_detalle = id_detalle;
        this.id_mesa = id_mesa;
        this.id_ficahs = id_ficahs;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(String id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_ficahs() {
        return id_ficahs;
    }

    public void setId_ficahs(int id_ficahs) {
        this.id_ficahs = id_ficahs;
    }
    
    
}
