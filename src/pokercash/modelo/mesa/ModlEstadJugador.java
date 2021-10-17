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
public class ModlEstadJugador extends EstadJugador {

    ConexionPg con = new ConexionPg();

    public ModlEstadJugador(int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas) {
        super(id_est_jug, ingreso_total, id_jugador, id_mesa, perdidas, ganancias, deudas);
    }

    public ModlEstadJugador() {
    }

    public List<EstadJugador> ID(int id) {
        try {
            List<EstadJugador> l = new ArrayList<>();
            String sql = "SELECT id_estad_jug, ingreso_total, perdidas, ganancias, deudas, id_mesa, \n"
                    + "       id_jugador\n"
                    + "  FROM estad_jugador where id_estad_jug=" + id + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                EstadJugador e = new EstadJugador();
                e.setId_est_jug(rs.getInt("id_estad_jug"));
                e.setIngreso_total(rs.getDouble("ingreso_total"));
                e.setPerdidas(rs.getDouble("perdidas"));
                e.setGanancias(rs.getDouble("ganancias"));
                e.setDeudas(rs.getDouble("deudas"));
                e.setId_mesa(rs.getInt("id_mesa"));
                e.setId_jugador(rs.getInt("id_jugador"));
                l.add(e);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstadJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<EstadJugador> ListarJ(int id) {
        try {
            List<EstadJugador> l = new ArrayList<>();
            String sql = "SELECT id_estad_jug, ingreso_total, perdidas, ganancias, deudas, id_mesa, \n"
                    + "       id_jugador\n"
                    + "  FROM estad_jugador where id_mesa=" + id + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                EstadJugador e = new EstadJugador();
                e.setId_est_jug(rs.getInt("id_estad_jug"));
                e.setIngreso_total(rs.getDouble("ingreso_total"));
                e.setPerdidas(rs.getDouble("perdidas"));
                e.setGanancias(rs.getDouble("ganancias"));
                e.setDeudas(rs.getDouble("deudas"));
                e.setId_mesa(rs.getInt("id_mesa"));
                e.setId_jugador(rs.getInt("id_jugador"));
                l.add(e);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstadJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<EstadJugador> ListarJM(int id) {
        try {
            List<EstadJugador> l = new ArrayList<>();
            String sql = "SELECT p.nombre,p.apellido,e.ingreso_total,e.deudas,j.id_jugador,e.id_estad_jug\n"
                    + "FROM persona p join jugador j on p.id_persona=j.id_persona join estad_jugador e on e.id_jugador=j.id_jugador join mesa m on m.id_mesa=e.id_mesa\n"
                    + "where m.id_mesa="+id+";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                EstadJugador e = new EstadJugador();
                e.setId_est_jug(rs.getInt("id_estad_jug"));
                e.setIngreso_total(rs.getDouble("ingreso_total"));
                e.setDeudas(rs.getDouble("deudas"));
                e.setId_jugador(rs.getInt("id_jugador"));
                l.add(e);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstadJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<EstadJugador> ListarE() {
        try {
            List<EstadJugador> l = new ArrayList<>();
            String sql = "SELECT id_estad_jug, ingreso_total, perdidas, ganancias, deudas, id_mesa, \n"
                    + "       id_jugador\n"
                    + "  FROM estad_jugador;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                EstadJugador e = new EstadJugador();
                e.setId_est_jug(rs.getInt("id_estad_jug"));
                e.setIngreso_total(rs.getDouble("ingreso_total"));
                e.setPerdidas(rs.getDouble("perdidas"));
                e.setGanancias(rs.getDouble("ganancias"));
                e.setDeudas(rs.getDouble("deudas"));
                e.setId_mesa(rs.getInt("id_mesa"));
                e.setId_jugador(rs.getInt("id_jugador"));
                l.add(e);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstadJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean JugarMesa() {
        String sql = "INSERT INTO estad_jugador(\n"
                + "            id_estad_jug, ingreso_total, perdidas, ganancias, deudas, id_mesa, \n"
                + "            id_jugador)\n"
                + "    VALUES (" + getId_est_jug() + ", " + getIngreso_total() + ", " + getPerdidas() + ", " + getGanancias() + ", " + getDeudas() + ", " + getId_mesa() + ", \n"
                + "            " + getId_jugador() + ");";
        return con.accion(sql);
    }
}
