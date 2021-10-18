/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.mesa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokercash.modelo.ConexionPg;

/**
 *
 * @author CyberLink
 */
public class ModlCambio extends Cambio {

    ConexionPg con = new ConexionPg();

    public ModlCambio(int id_cambio, double valor, int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas_en_contra, double deudas_a_favor) {
        super(id_cambio, valor, id_est_jug, ingreso_total, id_jugador, id_mesa, perdidas, ganancias, deudas_en_contra, deudas_a_favor);
    }

    public ModlCambio() {
    }

    public List<Cambio> ListaCambios(int id_estad) {
        try {
            List<Cambio> lc = new ArrayList<>();
            String sql = "SELECT id_cambio, valor, id_est_jugador\n"
                    + "  FROM cambio_fichas WHERE id_est_jugador=" + id_estad + " ORDER BY id_cambio;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Cambio c = new Cambio();
                c.setId_cambio(rs.getInt("id_cambio"));
                c.setValor(rs.getDouble("valor"));
                c.setId_est_jug(rs.getInt("id_est_jugador"));
                lc.add(c);
            }
            rs.close();
            return lc;
        } catch (SQLException ex) {
            Logger.getLogger(ModlCambio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Cambio> ListaCambiosTotales() {
        try {
            List<Cambio> lc = new ArrayList<>();
            String sql = "SELECT id_cambio, valor, id_est_jugador\n"
                    + "  FROM cambio_fichas ORDER BY id_cambio;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Cambio c = new Cambio();
                c.setId_cambio(rs.getInt("id_cambio"));
                c.setValor(rs.getDouble("valor"));
                c.setId_est_jug(rs.getInt("id_est_jugador"));
                lc.add(c);
            }
            rs.close();
            return lc;
        } catch (SQLException ex) {
            Logger.getLogger(ModlCambio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Cambio> ListaCambiosTotalesMesa(int id_mesa) {
        try {
            List<Cambio> lc = new ArrayList<>();
            String sql = "SELECT c.id_cambio, c.valor, c.id_est_jugador\n"
                    + "  FROM estad_jugador e join cambio_fichas c on e.id_estad_jug=c.id_est_jugador\n"
                    + "  where e.id_mesa=" + id_mesa + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Cambio c = new Cambio();
                c.setId_cambio(rs.getInt("id_cambio"));
                c.setValor(rs.getDouble("valor"));
                c.setId_est_jug(rs.getInt("id_est_jugador"));
                lc.add(c);
            }
            rs.close();
            return lc;
        } catch (SQLException ex) {
            Logger.getLogger(ModlCambio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Cambio> IDListaCambios(int id_cambios) {
        try {
            List<Cambio> lc = new ArrayList<>();
            String sql = "SELECT id_cambio, valor, id_est_jugador\n"
                    + "  FROM cambio_fichas WHERE id_cambio=" + id_cambios + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Cambio c = new Cambio();
                c.setId_cambio(rs.getInt("id_cambio"));
                c.setValor(rs.getDouble("valor"));
                c.setId_est_jug(rs.getInt("id_est_jugador"));
                lc.add(c);
            }
            rs.close();
            return lc;
        } catch (SQLException ex) {
            Logger.getLogger(ModlCambio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean SalirMesa() {
        String sql = "INSERT INTO cambio_fichas(\n"
                + "            id_cambio, valor, id_est_jugador)\n"
                + "    VALUES (" + getId_cambio() + ", " + getValor() + ", " + getId_est_jug() + ");"
                + " UPDATE estad_jugador\n"
                + "   SET perdidas=" + getPerdidas() + ", ganancias=" + getGanancias() + ", deudas=" + getDeudas_en_contra() + " \n"
                + " WHERE id_estad_jug=" + getId_est_jug() + ";";
        return con.accion(sql);
    }
}
