/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.persona;

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
public class modlUsuario extends Usuario{
    
    ConexionPg con =new ConexionPg();
    
    public modlUsuario(String nomb_usu, String password, String id_empleado) {
        super(nomb_usu, password, id_empleado);
    }

    public modlUsuario() {
    }
    
    public List<Usuario> IniciarSesion(String nombUsuario){
        
        try {
            List<Usuario> lu =new ArrayList<>();
            String sql="select * from usuario where nomb_usu='"+nombUsuario+"';";
            ResultSet rs = con.consulta(sql); 
                while (rs.next()) {
                    Usuario u=new Usuario();
                    u.setNomb_usu(rs.getString("nomb_usu"));
                    u.setPassword(rs.getString("password"));
                    lu.add(u);
                }
            
            return lu;
        } catch (SQLException ex) {
            Logger.getLogger(modlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
