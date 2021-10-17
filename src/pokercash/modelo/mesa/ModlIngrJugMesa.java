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
public class ModlIngrJugMesa extends IngrJugMesa {

    ConexionPg con = new ConexionPg();

    public ModlIngrJugMesa() {
    }

    public ModlIngrJugMesa(int id_ingr_jug, double ingreso, String hora, String tipo, int id_est_jug, double ingreso_total, int id_jugador, int id_mesa, double perdidas, double ganancias, double deudas) {
        super(id_ingr_jug, ingreso, hora, tipo, id_est_jug, ingreso_total, id_jugador, id_mesa, perdidas, ganancias, deudas);
    }

    public List<IngrJugMesa> ListarIj() {
        try {
            List<IngrJugMesa> l = new ArrayList<>();
            String sql = "SELECT id_ingr_juga, id_estad_jug, ingreso, hora, tipo\n"
                    + "  FROM ingr_jug_mesa;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                IngrJugMesa i = new IngrJugMesa();
                i.setId_ingr_jug(rs.getInt("id_ingr_juga"));
                i.setId_est_jug(rs.getInt("id_estad_jug"));
                i.setIngreso(rs.getDouble("ingreso"));
                i.setHora(rs.getString("hora"));
                i.setTipo(rs.getString("tipo"));
                l.add(i);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlIngrJugMesa.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<IngrJugMesa> IDListarIj(int id) {
        try {
            List<IngrJugMesa> l = new ArrayList<>();
            String sql = "SELECT id_ingr_juga, id_estad_jug, ingreso, hora, tipo\n"
                    + "  FROM ingr_jug_mesa "
                    + "WHERE id_ingr_juga=" + id + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                IngrJugMesa i = new IngrJugMesa();
                i.setId_ingr_jug(rs.getInt("id_ingr_juga"));
                i.setId_est_jug(rs.getInt("id_estad_jug"));
                i.setIngreso(rs.getDouble("ingreso"));
                i.setHora(rs.getString("hora"));
                i.setTipo(rs.getString("tipo"));
                l.add(i);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlIngrJugMesa.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Insertar() {

        String sql = "INSERT INTO ingr_jug_mesa(\n"
                + "            id_ingr_juga, id_estad_jug, ingreso, hora, tipo)\n"
                + "    VALUES (" + getId_ingr_jug() + ", " + getId_est_jug() + ", " + getIngreso() + ", '" + getHora() + "', '" + getTipo() + "');\n"
                + "UPDATE estad_jugador\n"
                + "   SET ingreso_total="+getIngreso_total()+", deudas="+getDeudas()+"\n"
                + " WHERE id_estad_jug="+getId_est_jug()+";";
        return con.accion(sql);
    }
}
