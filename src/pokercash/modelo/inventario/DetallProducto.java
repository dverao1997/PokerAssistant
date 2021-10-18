/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.inventario;

/**
 *
 * @author CyberLink
 */
public class DetallProducto extends Producto{
    private int id_deta_prod;
    private int cantidad;
    private int id_mesa;

    public DetallProducto(int id_deta_prod, int cantidad, int id_mesa, int id_producto, String nombre, double precio, int existencias) {
        super(id_producto, nombre, precio, existencias);
        this.id_deta_prod = id_deta_prod;
        this.cantidad = cantidad;
        this.id_mesa = id_mesa;
    }



    public DetallProducto() {
    }

    public int getId_deta_prod() {
        return id_deta_prod;
    }

    public void setId_deta_prod(int id_deta_prod) {
        this.id_deta_prod = id_deta_prod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }


}
