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
public class ModlEstIngrEmpl extends EstIngrEmpl {

    ConexionPg con = new ConexionPg();

    public ModlEstIngrEmpl() {
    }

    public ModlEstIngrEmpl(int id_est_ingr, double ingreso, int id_empleado, int id_mesa) {
        super(id_est_ingr, ingreso, id_empleado, id_mesa);
    }
    
    public List<EstIngrEmpl> Listar(int id){
        try {
            List<EstIngrEmpl> l=new ArrayList<>();
            String sql="select * from est_ingresos_empl where id_est_ingr="+id+" order by id_est_ingr;";
            ResultSet rs=con.consulta(sql);
            while (rs.next()) {
                EstIngrEmpl e=new EstIngrEmpl();
                e.setId_est_ingr(rs.getInt("id_est_ingr"));
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setId_mesa(rs.getInt("id_mesa"));
                e.setIngreso(rs.getDouble("ingreso"));
                l.add(e);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstIngrEmpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<EstIngrEmpl> Listar(){
        try {
            List<EstIngrEmpl> l=new ArrayList<>();
            String sql="select * from est_ingresos_empl order by id_est_ingr;";
            ResultSet rs=con.consulta(sql);
            while (rs.next()) {
                EstIngrEmpl e=new EstIngrEmpl();
                e.setId_est_ingr(rs.getInt("id_est_ingr"));
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setId_mesa(rs.getInt("id_mesa"));
                e.setIngreso(rs.getDouble("ingreso"));
                l.add(e);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEstIngrEmpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean Insertar() {
        String sql = "INSERT INTO est_ingresos_empl(\n"
                + "            id_est_ingr, ingreso, id_empleado, id_mesa)\n"
                + "    VALUES ("+getId_est_ingr()+", "+getIngreso()+", "+getId_empleado()+", "+getId_mesa()+");";
        return con.accion(sql);
    }
}
