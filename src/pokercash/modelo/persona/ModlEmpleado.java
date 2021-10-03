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
public class ModlEmpleado extends Empleado {

    ConexionPg con = new ConexionPg();

    public ModlEmpleado() {
    }

    public ModlEmpleado(String id_empleado, String rol, String id_persona, String nombre, String apellido, String telefono, String genero) {
        super(id_empleado, rol, id_persona, nombre, apellido, telefono, genero);
    }

    public List<Empleado> listarEmpleado(String aguja) {
        try {
            List<Empleado> le = new ArrayList<>();

            String sql = "select p.id_persona,p.nombre,p.apellido,p.telefono,p.genero,e.id_empleado,e.rol\n"
                    + "from persona p join empleado e on p.id_persona=e.id_persona\n"
                    + "where upper(p.nombre) like '%" + aguja + "%'\n"
                    + "or  upper(p.apellido) like '%" + aguja + "%';";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_persona(rs.getString("id_persona"));
                e.setId_empleado(rs.getString("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setTelefono(rs.getString("telefono"));
                e.setGenero(rs.getString("genero"));
                e.setRol(rs.getString("rol"));

                le.add(e);
            }
            rs.close();
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Insertar() {
        String sql = "INSERT INTO persona(\n"
                + "            id_persona, nombre, apellido, telefono, genero)\n"
                + "    VALUES ('" + getId_persona() + "','" + getNombre() + "','" + getApellido() + "','" + getTelefono() + "','" + getGenero() + "');\n"
                + "INSERT INTO empleado(\n"
                + "            id_empleado, id_persona, rol)\n"
                + "    VALUES ('" + getId_empleado() + "', '" + getId_persona() + "', '" + getRol() + "');";
        return con.accion(sql);
    }
}
