/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.mesa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokercash.modelo.ConexionPg;

/**
 *
 * @author CyberLink
 */
public class ModlMesa extends Mesa {

    ConexionPg con = new ConexionPg();

    public ModlMesa() {
    }

    public ModlMesa(int id_mesa, double casilla, LocalDate fecha, double gastos, double propinaServi, double efectivoServi, double efectivoAdmin, double propinaDeler, double efectivoDeler, double casillaDeler, int gastosDeler, double porcentajeClub, double efectivoClub, int gastosClub, int estado) {
        super(id_mesa, casilla, fecha, gastos, propinaServi, efectivoServi, efectivoAdmin, propinaDeler, efectivoDeler, casillaDeler, gastosDeler, porcentajeClub, efectivoClub, gastosClub, estado);
    }

    public List<Mesa> Listar(int id) {
        try {
            List<Mesa> l = new ArrayList<>();
            String sql = "select * from mesa where id_mesa=" + id + " order by id_mesa;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Mesa m = new Mesa();
                m.setCasilla(rs.getDouble("casilla"));
                m.setCasillaDeler(rs.getDouble("casilla_deler"));
                m.setEfectivoAdmin(rs.getDouble("efectivo_admin"));
                m.setEfectivoClub(rs.getDouble("efectivo_club"));
                m.setEfectivoDeler(rs.getDouble("efectivo_deler"));
                m.setEfectivoServi(rs.getDouble("efectivo_servicio"));
                m.setFecha(rs.getDate("fecha").toLocalDate());
                m.setGastos(rs.getDouble("gastos"));
                m.setGastosClub(rs.getInt("gastos_club"));
                m.setGastosDeler(rs.getInt("gastos_deler"));
                m.setId_mesa(rs.getInt("id_mesa"));
                m.setEstado(rs.getInt("id_mesa"));
                m.setPorcentajeClub(rs.getDouble("porcentaje_club"));
                m.setPropinaDeler(rs.getDouble("propinas_deler"));
                m.setPropinaServi(rs.getDouble("propina_servicio"));
                l.add(m);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstIngrEmpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Mesa> Listar() {
        try {
            List<Mesa> l = new ArrayList<>();
            String sql = "select * from mesa order by id_mesa;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Mesa m = new Mesa();
                m.setCasilla(rs.getDouble("casilla"));
                m.setCasillaDeler(rs.getDouble("casilla_deler"));
                m.setEfectivoAdmin(rs.getDouble("efectivo_admin"));
                m.setEfectivoClub(rs.getDouble("efectivo_club"));
                m.setEfectivoDeler(rs.getDouble("efectivo_deler"));
                m.setEfectivoServi(rs.getDouble("efectivo_servicio"));
                m.setFecha(rs.getDate("fecha").toLocalDate());
                m.setGastos(rs.getDouble("gastos"));
                m.setGastosClub(rs.getInt("gastos_club"));
                m.setGastosDeler(rs.getInt("gastos_deler"));
                m.setId_mesa(rs.getInt("id_mesa"));
                m.setPorcentajeClub(rs.getDouble("porcentaje_club"));
                m.setPropinaDeler(rs.getDouble("propinas_deler"));
                m.setPropinaServi(rs.getDouble("propina_servicio"));
                m.setEstado(rs.getInt("id_mesa"));
                l.add(m);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstIngrEmpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Insertar() {
        String sql = "INSERT INTO mesa(\n"
                + "            casilla, fecha, gastos, id_mesa, propina_servicio, efectivo_servicio, \n"
                + "            efectivo_admin, propinas_deler, efectivo_deler, casilla_deler, \n"
                + "            gastos_deler, porcentaje_club, efectivo_club, gastos_club,estado)\n"
                + "    VALUES (" + getCasilla() + ", '" + getFecha() + "', " + getGastos() + ", " + getId_mesa() + ", " + getPropinaServi() + ", " + getEfectivoServi() + ", \n"
                + "            " + getEfectivoAdmin() + ", " + getPropinaDeler() + ", " + getEfectivoDeler() + ", " + getCasillaDeler() + ", \n"
                + "            " + getGastosDeler() + ", " + getPorcentajeClub() + ", " + getEfectivoClub() + ", " + getGastosClub() + "," + getEstado() + ");";
        return con.accion(sql);
    }
}
