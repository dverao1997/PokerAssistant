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
public class ModlPersona extends Persona {

    ConexionPg con = new ConexionPg();

    public ModlPersona() {
    }

    public ModlPersona(int id_persona, String nombre, String apellido, String telefono, String genero) {
        super(id_persona, nombre, apellido, telefono, genero);
    }

    public List<Persona> ListarpersonaID(int aguja) {
        try {
            List<Persona> lp = new ArrayList<>();
            String sql = "Select * from Persona where id_persona=" + aguja + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Persona p = new Persona();
                p.setId_persona(rs.getInt("id_persona"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setTelefono(rs.getString("telefono"));
                p.setGenero(rs.getString("genero"));
                lp.add(p);
            }
            rs.close();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(ModlPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Persona> Listarpersona() {
        try {
            List<Persona> lp = new ArrayList<>();
            String sql = "Select * from Persona;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Persona p = new Persona();
                p.setId_persona(rs.getInt("id_persona"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setTelefono(rs.getString("telefono"));
                p.setGenero(rs.getString("genero"));
                lp.add(p);
            }
            rs.close();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(ModlPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


}
