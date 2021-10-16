/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.mesa;

import java.time.LocalDate;

/**
 *
 * @author CyberLink
 */
public class Mesa {

    private int id_mesa;
    private double casilla;
    private LocalDate fecha;
    private double gastos;
    private double propinaServi;
    private double efectivoServi;
    private double efectivoAdmin;
    private double propinaDeler;
    private double efectivoDeler;
    private double casillaDeler;
    private int gastosDeler;
    private double porcentajeClub;
    private double efectivoClub;
    private int gastosClub;
    private int estado;

    public Mesa() {
    }

    public Mesa(int id_mesa, double casilla, LocalDate fecha, double gastos, double propinaServi, double efectivoServi, double efectivoAdmin, double propinaDeler, double efectivoDeler, double casillaDeler, int gastosDeler, double porcentajeClub, double efectivoClub, int gastosClub, int estado) {
        this.id_mesa = id_mesa;
        this.casilla = casilla;
        this.fecha = fecha;
        this.gastos = gastos;
        this.propinaServi = propinaServi;
        this.efectivoServi = efectivoServi;
        this.efectivoAdmin = efectivoAdmin;
        this.propinaDeler = propinaDeler;
        this.efectivoDeler = efectivoDeler;
        this.casillaDeler = casillaDeler;
        this.gastosDeler = gastosDeler;
        this.porcentajeClub = porcentajeClub;
        this.efectivoClub = efectivoClub;
        this.gastosClub = gastosClub;
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }



    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public double getCasilla() {
        return casilla;
    }

    public void setCasilla(double casilla) {
        this.casilla = casilla;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public double getPropinaServi() {
        return propinaServi;
    }

    public void setPropinaServi(double propinaServi) {
        this.propinaServi = propinaServi;
    }

    public double getEfectivoServi() {
        return efectivoServi;
    }

    public void setEfectivoServi(double efectivoServi) {
        this.efectivoServi = efectivoServi;
    }

    public double getEfectivoAdmin() {
        return efectivoAdmin;
    }

    public void setEfectivoAdmin(double efectivoAdmin) {
        this.efectivoAdmin = efectivoAdmin;
    }

    public double getPropinaDeler() {
        return propinaDeler;
    }

    public void setPropinaDeler(double propinaDeler) {
        this.propinaDeler = propinaDeler;
    }

    public double getEfectivoDeler() {
        return efectivoDeler;
    }

    public void setEfectivoDeler(double efectivoDeler) {
        this.efectivoDeler = efectivoDeler;
    }

    public double getCasillaDeler() {
        return casillaDeler;
    }

    public void setCasillaDeler(double casillaDeler) {
        this.casillaDeler = casillaDeler;
    }

    public int getGastosDeler() {
        return gastosDeler;
    }

    public void setGastosDeler(int gastosDeler) {
        this.gastosDeler = gastosDeler;
    }

    public double getPorcentajeClub() {
        return porcentajeClub;
    }

    public void setPorcentajeClub(double porcentajeClub) {
        this.porcentajeClub = porcentajeClub;
    }

    public double getEfectivoClub() {
        return efectivoClub;
    }

    public void setEfectivoClub(double efectivoClub) {
        this.efectivoClub = efectivoClub;
    }

    public int getGastosClub() {
        return gastosClub;
    }

    public void setGastosClub(int gastosClub) {
        this.gastosClub = gastosClub;
    }

}
