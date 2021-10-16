/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.inventario;

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
public class ModlDetallFichas extends DetalleFichas {

    ConexionPg con = new ConexionPg();

    public ModlDetallFichas(int id_detalle, int id_mesa, int id_ficahs) {
        super(id_detalle, id_mesa, id_ficahs);
    }

    public ModlDetallFichas() {
    }

    public List<DetalleFichas> listar(int id){
        try {
            List<DetalleFichas> l=new ArrayList<>();
            String sql="select * from detalle_fichas where id_detalle="+id+";";
            ResultSet rs=con.consulta(sql);
            while (rs.next()) {
                DetalleFichas d=new DetalleFichas();
                d.setId_detalle(rs.getInt("id_detalle"));
                d.setId_ficahs(rs.getInt("id_fichas"));
                d.setId_mesa(rs.getInt("id_mesa"));
            l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDetallFichas.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
        
    }
    public List<DetalleFichas> listar(){
        try {
            List<DetalleFichas> l=new ArrayList<>();
            String sql="select * from detalle_fichas;";
            ResultSet rs=con.consulta(sql);
            while (rs.next()) {
                DetalleFichas d=new DetalleFichas();
                d.setId_detalle(rs.getInt("id_detalle"));
                d.setId_ficahs(rs.getInt("id_fichas"));
                d.setId_mesa(rs.getInt("id_mesa"));
            l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDetallFichas.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
        
    }
    
    public boolean Insertar() {
        String sql = "INSERT INTO detalle_fichas(\n"
                + "            id_detalle, id_fichas, id_mesa)\n"
                + "    VALUES ("+getId_detalle()+", "+getId_ficahs()+", "+getId_mesa()+");";
        return con.accion(sql);
    }
}
