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
public class ModlDeudas extends Deudas {

    ConexionPg con = new ConexionPg();

    public ModlDeudas(int id_deudas, double valor, LocalDate fecha, String tipo, int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas) {
        super(id_deudas, valor, fecha, tipo, id_est_jug, ingreso_total, id_jugador, id_mesa, perdidas, ganancias, deudas);
    }

    public ModlDeudas() {
    }

    public List<Deudas> ListarDeuda(int id) {
        String sql = "SELECT e.id_jugador,e.deudas,e.id_estad_jug,d.id.deudas,d.valor,d.fecha,d.tipo\n"
                + "FROM estad_jugador e join deudas d on e.id_jugador=d.id_jugador\n"
                + "WHERE e.id_estad_jug=" + id + ";";
        ResultSet rs = con.consulta(sql);
        return null;
    }

    public List<Deudas> ListarD() {
        
        try {
            List<Deudas> l=new ArrayList<>();
            String sql = "SELECT id_deudas, valor, fecha, tipo, id_estad_jug\n"
                    + "  FROM deudas;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Deudas d=new Deudas();
                d.setId_deudas(rs.getInt("id_deudas"));
                d.setValor(rs.getDouble("valor"));
                d.setFecha(rs.getDate("fecha").toLocalDate());
                d.setTipo(rs.getString("tipo"));
                d.setId_est_jug(rs.getInt("id_estad_jug"));
                l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDeudas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<Deudas> IDListarD(int id) {
        
        try {
            List<Deudas> l=new ArrayList<>();
            String sql = "SELECT id_deudas, valor, fecha, tipo, id_estad_jug\n"
                    + "  FROM deudas"
                    + "  WHERE id_deudas="+id+";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Deudas d=new Deudas();
                d.setId_deudas(rs.getInt("id_deudas"));
                d.setValor(rs.getDouble("valor"));
                d.setFecha(rs.getDate("fecha").toLocalDate());
                d.setTipo(rs.getString("tipo"));
                d.setId_est_jug(rs.getInt("id_estad_jug"));
                l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDeudas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Pagar() {
        String sql = "INSERT INTO deudas(\n"
                + "            id_deudas, valor, fecha, tipo, id_estad_jug)\n"
                + "    VALUES (" + getId_deudas() + ", " + getValor() + ", '" + getFecha() + "', '" + getTipo() + "', " + getId_est_jug() + ");\n"
                + "UPDATE estad_jugador\n"
                + "   SET deudas=" + getDeudas() + "\n"
                + " WHERE id_estad_jug=" + getId_est_jug() + ";";
        return con.accion(sql);
    }
}
