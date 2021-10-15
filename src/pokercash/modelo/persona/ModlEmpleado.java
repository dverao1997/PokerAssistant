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

    public ModlEmpleado(int id_empleado, String rol, int estado, int id_persona, String nombre, String apellido, String telefono, String genero) {
        super(id_empleado, rol, estado, id_persona, nombre, apellido, telefono, genero);
    }

    public List<Empleado> listarEmpleado(String aguja) {
        try {
            List<Empleado> le = new ArrayList<>();

            String sql = "select p.id_persona,p.nombre,p.apellido,p.telefono,p.genero,e.id_empleado,e.rol,e.estado\n"
                    + "from persona p join empleado e on p.id_persona=e.id_persona\n"
                    + "where e.estado=0 and (upper(p.nombre) like '%" + aguja + "%'\n"
                    + "or  upper(p.apellido) like '%" + aguja + "%');";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_persona(rs.getInt("id_persona"));
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setTelefono(rs.getString("telefono"));
                e.setGenero(rs.getString("genero"));
                e.setRol(rs.getString("rol"));
                e.setEstado(rs.getInt("estado"));
                le.add(e);
            }
            rs.close();
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Empleado> ListarMesa(String aguja) {
        try {
            List<Empleado> le = new ArrayList<>();

            String sql = "SELECT p.nombre,p.apellido,e.id_empleado\n"
                    + "  FROM persona p join empleado e on p.id_persona=e.id_persona\n"
                    + "  WHERE rol='" + aguja + "';";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                le.add(e);
            }
            rs.close();
            return le;
        } catch (SQLException ex) {
            Logger.getLogger(ModlEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Empleado> ListarMesaT() {
        try {
            List<Empleado> le = new ArrayList<>();

            String sql = "SELECT p.nombre,e.id_empleado,e.rol,p.apellido\n"
                    + "  FROM persona p join empleado e on p.id_persona=e.id_persona\n"
                    + "  WHERE rol='Deler' or rol='Servicio' or rol='Administrador';";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
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

    public List<Empleado> listarEmpleado(int aguja) {
        try {
            List<Empleado> le = new ArrayList<>();

            String sql = "select p.id_persona,p.nombre,p.apellido,p.telefono,p.genero,e.id_empleado,e.rol\n"
                    + "from persona p join empleado e on p.id_persona=e.id_persona\n"
                    + "where id_empleado=" + aguja + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_persona(rs.getInt("id_persona"));
                e.setId_empleado(rs.getInt("id_empleado"));
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

    public List<Empleado> Persona(String aguja) {
        try {
            List<Empleado> lp = new ArrayList<>();
            String sql = "select p.id_persona,nombre,apellido,telefono,genero,estado,e.id_empleado\n"
                    + "                    from persona p left outer join empleado e on p.id_persona=e.id_persona\n"
                    + "                    where (e.id_persona is null or e.estado=1) and\n"
                    + "                    (upper(p.nombre) like '%" + aguja + "%' or upper(p.apellido) like '%" + aguja + "%');";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Empleado p = new Empleado();
                p.setId_persona(rs.getInt("id_persona"));
                p.setId_empleado(rs.getInt("id_empleado"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setTelefono(rs.getString("telefono"));
                p.setGenero(rs.getString("genero"));
                p.setEstado(rs.getInt("estado"));
                lp.add(p);
            }
            rs.close();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(ModlPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean InsertarEmpleado() {
        String sql = "INSERT INTO persona(\n"
                + "            id_persona, nombre, apellido, telefono, genero)\n"
                + "    VALUES (" + getId_persona() + ", '" + getNombre() + "', '" + getApellido() + "', '" + getTelefono() + "', '" + getGenero() + "');\n"
                + "INSERT INTO empleado(\n"
                + "            rol, id_persona, id_empleado,estado)\n"
                + "    VALUES ('" + getRol() + "', " + getId_persona() + ", " + getId_empleado() + "," + getEstado() + ");";
        return con.accion(sql);
    }

    public boolean InsertEmpleado() {
        String sql = "INSERT INTO empleado(\n"
                + "            rol, id_persona, id_empleado,estado)\n"
                + "    VALUES ('" + getRol() + "', " + getId_persona() + ", " + getId_empleado() + "," + getEstado() + ");";
        return con.accion(sql);
    }

    public boolean EditarEmpleado() {
        String sql = "UPDATE persona\n"
                + "   SET nombre= '" + getNombre() + "', apellido='" + getApellido() + "', telefono='" + getTelefono() + "', genero='" + getGenero() + "'\n"
                + " WHERE id_persona=" + getId_persona() + ";\n"
                + "UPDATE empleado\n"
                + "   SET rol='" + getRol() + "'\n"
                + " WHERE id_empleado=" + getId_empleado() + ";";
        return con.accion(sql);
    }

    public boolean InactEmpleado() {
        String sql = "UPDATE empleado\n"
                + "   SET rol='" + getRol() + "',estado=" + getEstado() + "\n"
                + " WHERE id_empleado=" + getId_empleado() + ";";
        return con.accion(sql);
    }

}
