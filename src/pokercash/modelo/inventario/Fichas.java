/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.inventario;

import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author CyberLink
 */
public class Fichas {

    private int id_fichas;
    private String color;
    private double valor;
    private String estado;
    private Image foto;
    private int cantidad;

    public Fichas() {
    }

    public Fichas(int id_fichas, String color, double valor, String estado, Image foto, int cantidad) {
        this.id_fichas = id_fichas;
        this.color = color;
        this.valor = valor;
        this.estado = estado;
        this.foto = foto;
        this.cantidad = cantidad;
    }

    public int getId_fichas() {
        return id_fichas;
    }

    public void setId_fichas(int id_fichas) {
        this.id_fichas = id_fichas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
