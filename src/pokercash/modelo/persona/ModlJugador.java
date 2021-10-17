/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.persona;

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
public class ModlJugador extends Jugador {

    ConexionPg con = new ConexionPg();

    public ModlJugador() {
    }

    public ModlJugador(int id_jugador, LocalDate fecha_ingreso, int estado, int id_persona, String nombre, String apellido, String telefono, String genero) {
        super(id_jugador, fecha_ingreso, estado, id_persona, nombre, apellido, telefono, genero);
    }

    public List<Jugador> ListarP(String aguja) {
        try {
            List<Jugador> l = new ArrayList<>();
            String sql = "Select * from persona where upper(nombre) like '%" + aguja + "%'\n"
                    + "or  upper(apellido) like '%" + aguja + "%';";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Jugador p = new Jugador();
                p.setId_persona(rs.getInt("id_persona"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setTelefono(rs.getString("telefono"));
                p.setGenero(rs.getString("genero"));
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Jugador> ListarJ(int id) {
        try {
            List<Jugador> l = new ArrayList<>();
            String sql = "Select * from jugador where id_persona=" + id + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Jugador p = new Jugador();
                p.setId_persona(rs.getInt("id_persona"));
                p.setEstado(rs.getInt("estado"));
                p.setId_jugador(rs.getInt("id_jugador"));
                p.setFecha_ingreso(rs.getDate("fecha_ingreso").toLocalDate());
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Jugador> ListarIDJ(int id) {
        try {
            List<Jugador> l = new ArrayList<>();
            String sql = "Select * from jugador where id_jugador=" + id + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Jugador p = new Jugador();
                p.setId_persona(rs.getInt("id_persona"));
                p.setEstado(rs.getInt("estado"));
                p.setId_jugador(rs.getInt("id_jugador"));
                p.setFecha_ingreso(rs.getDate("fecha_ingreso").toLocalDate());
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Jugador> ListarJ() {
        try {
            List<Jugador> l = new ArrayList<>();
            String sql = "SELECT p.nombre,p.apellido,j.fecha_ingreso, j.id_persona, j.id_jugador, j.estado\n"
                    + "FROM persona p join jugador j on p.id_persona=j.id_persona\n"
                    + "WHERE j.estado=1;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Jugador p = new Jugador();
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setId_persona(rs.getInt("id_persona"));
                p.setEstado(rs.getInt("estado"));
                p.setId_jugador(rs.getInt("id_jugador"));
                p.setFecha_ingreso(rs.getDate("fecha_ingreso").toLocalDate());
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Jugador> ListarJNombres(int id) {
        try {
            List<Jugador> l = new ArrayList<>();
            String sql = "SELECT p.nombre,p.apellido,j.fecha_ingreso, j.id_persona, j.id_jugador, j.estado\n"
                    + "FROM persona p join jugador j on p.id_persona=j.id_persona\n"
                    + "WHERE j.id_jugador="+id+";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Jugador p = new Jugador();
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setId_persona(rs.getInt("id_persona"));
                p.setEstado(rs.getInt("estado"));
                p.setId_jugador(rs.getInt("id_jugador"));
                p.setFecha_ingreso(rs.getDate("fecha_ingreso").toLocalDate());
                l.add(p);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlJugador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean estado() {
        String sql = "UPDATE jugador\n"
                + "   SET estado=" + getEstado() + "\n"
                + " WHERE id_jugador=" + getId_jugador() + ";";
        return con.accion(sql);
    }

    public boolean insertar() {
        String sql = "INSERT INTO jugador(\n"
                + "            fecha_ingreso, id_persona, id_jugador, estado)\n"
                + "    VALUES ('" + getFecha_ingreso() + "', " + getId_persona() + ", " + getId_jugador() + ", " + getEstado() + ");";
        return con.accion(sql);
    }

    public boolean CrearJugador() {
        String sql = "INSERT INTO persona(\n"
                + "            id_persona, nombre, apellido, telefono, genero)\n"
                + "    VALUES (" + getId_persona() + ", '" + getNombre() + "', '" + getApellido() + "', '" + getTelefono() + "', '" + getGenero() + "');\n"
                + "INSERT INTO jugador(\n"
                + "            fecha_ingreso, id_persona, id_jugador, estado)\n"
                + "    VALUES ('" + getFecha_ingreso() + "', " + getId_persona() + ", " + getId_jugador() + ", " + getEstado() + ");";
        return con.accion(sql);
    }
}
