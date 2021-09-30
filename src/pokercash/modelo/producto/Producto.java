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
public class Producto {
    private int id_producto;
    private String nombre;
    private double precio;
    private int existencias;

    public Producto(int id_producto, String nombre, double precio, int existencias) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
}
