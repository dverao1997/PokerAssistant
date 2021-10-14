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
public class ModlUsuario extends Usuario {

    ConexionPg con = new ConexionPg();

    public ModlUsuario() {
    }

    public ModlUsuario(String nomb_usu, String password, int id_empleado) {
        super(nomb_usu, password, id_empleado);
    }

    public List<Usuario> IniciarSesion(String nombUsuario) {

        try {
            List<Usuario> lu = new ArrayList<>();
            String sql = "select * from usuario where nomb_usu='" + nombUsuario + "';";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNomb_usu(rs.getString("nomb_usu"));
                u.setPassword(rs.getString("password"));
                lu.add(u);
            }

            return lu;
        } catch (SQLException ex) {
            Logger.getLogger(ModlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Usuario> ListarUsuario(String aguja) {

        try {
            List<Usuario> lu = new ArrayList<>();
            String sql = "select * from usuario where upper(nomb_usu) like '%" + aguja + "%';";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNomb_usu(rs.getString("nomb_usu"));
                u.setPassword(rs.getString("password"));
                u.setId_empleado(rs.getInt("id_empleado"));
                lu.add(u);
            }

            return lu;
        } catch (SQLException ex) {
            Logger.getLogger(ModlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Usuario> ListarEmpleado(String aguja) {

        try {
            List<Usuario> lu = new ArrayList<>();
            String sql = "SELECT e.id_empleado,p.nombre,p.apellido,p.telefono,p.genero\n"
                    + "  FROM persona p join empleado e on p.id_persona=e.id_persona left join usuario u on e.id_empleado=u.id_empleado\n"
                    + "  WHERE (u.id_empleado is null) and (e.rol='Administrador' or e.rol='Dueño' or e.rol='Organizador')"
                    + " and (upper(p.nombre) like '%" + aguja + "%' or upper(p.apellido) like '%" + aguja + "%');";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_empleado(rs.getInt("id_empleado"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setTelefono(rs.getString("telefono"));
                u.setGenero(rs.getString("genero"));
                lu.add(u);
            }

            return lu;
        } catch (SQLException ex) {
            Logger.getLogger(ModlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Insertar() {
        String sql = "INSERT INTO usuario(\n"
                + "            nomb_usu, password, id_empleado)\n"
                + "    VALUES ('" + getNomb_usu() + "', '" + getPassword() + "', " + getId_empleado() + ");";
        return con.accion(sql);
    }

    public boolean Editar() {
        String sql = "UPDATE usuario\n"
                + "   SET nomb_usu='" + getNomb_usu() + "', password='" + getPassword() + "'\n"
                + " WHERE id_empleado=" + getId_empleado() + ";";
        return con.accion(sql);
    }

    public boolean Eliminar() {
        String sql = "DELETE FROM usuario\n"
                + " WHERE nomb_usu='" + getNomb_usu() + "';";
        return con.accion(sql);
    }
}
