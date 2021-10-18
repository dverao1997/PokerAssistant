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
public class ModlPropinas extends Propinas {

    ConexionPg con = new ConexionPg();

    public ModlPropinas(int id_propina, int id_mesa, double propina_servi, double propina_deler) {
        super(id_propina, id_mesa, propina_servi, propina_deler);
    }

    public ModlPropinas() {

    }

    public List<Propinas> ListarPropinas(int ID_MESA) {
        try {
            List<Propinas> l = new ArrayList<>();
            String sql = "SELECT id_propina, propina_servi, propina_deler, id_mesa\n"
                    + "  FROM propinas WHERE id_mesa=" + ID_MESA + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Propinas p = new Propinas();
                p.setId_propina(rs.getInt("id_propina"));
                p.setPropina_servi(rs.getDouble("propina_servi"));
                p.setPropina_deler(rs.getDouble("propina_deler"));
                p.setId_mesa(rs.getInt("id_mesa"));
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {

            Logger.getLogger(ModlPropinas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Propinas> ListarTotal() {
        try {
            List<Propinas> l = new ArrayList<>();
            String sql = "SELECT id_propina, propina_servi, propina_deler, id_mesa\n"
                    + "  FROM propinas ;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Propinas p = new Propinas();
                p.setId_propina(rs.getInt("id_propina"));
                p.setPropina_servi(rs.getDouble("propina_servi"));
                p.setPropina_deler(rs.getDouble("propina_deler"));
                p.setId_mesa(rs.getInt("id_mesa"));
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {

            Logger.getLogger(ModlPropinas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Propinas> IDListarTotal(int ID_Propina) {
        try {
            List<Propinas> l = new ArrayList<>();
            String sql = "SELECT id_propina, propina_servi, propina_deler, id_mesa\n"
                    + "  FROM propinas WHERE id_propina=" + ID_Propina + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Propinas p = new Propinas();
                p.setId_propina(rs.getInt("id_propina"));
                p.setPropina_servi(rs.getDouble("propina_servi"));
                p.setPropina_deler(rs.getDouble("propina_deler"));
                p.setId_mesa(rs.getInt("id_mesa"));
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {

            Logger.getLogger(ModlPropinas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Insert() {
        String sql = "INSERT INTO propinas(\n"
                + "            id_propina, propina_servi, propina_deler, id_mesa)\n"
                + "    VALUES (" + getId_propina() + ", " + getPropina_servi() + ", " + getPropina_deler() + ", " + getId_mesa() + ");";
        return con.accion(sql);
    }

    public boolean Update() {
        String sql = "UPDATE propinas\n"
                + "   SET propina_servi=" + getPropina_servi() + ", propina_deler=" + getPropina_deler() + "\n"
                + " WHERE id_propina=" + getId_propina() + ";";
        return con.accion(sql);
    }

}
