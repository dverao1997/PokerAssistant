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
public class DetallProducto {
    private int id_data_prod;
    private int cantidad;
    private int id_mesa;
    private int id_producto;

    public DetallProducto() {
    }

    public DetallProducto(int id_data_prod, int cantidad, int id_mesa, int id_producto) {
        this.id_data_prod = id_data_prod;
        this.cantidad = cantidad;
        this.id_mesa = id_mesa;
        this.id_producto = id_producto;
    }

    public int getId_data_prod() {
        return id_data_prod;
    }

    public void setId_data_prod(int id_data_prod) {
        this.id_data_prod = id_data_prod;
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

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
}
