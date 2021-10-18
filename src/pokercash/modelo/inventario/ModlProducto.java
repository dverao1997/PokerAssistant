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
public class ModlProducto extends Producto {

    ConexionPg con = new ConexionPg();

    public ModlProducto() {
    }

    public ModlProducto(int id_producto, String nombre, double precio, int existencias) {
        super(id_producto, nombre, precio, existencias);
    }

    public List<Producto> ListarProducto(String aguja) {
        try {
            List<Producto> lp = new ArrayList<>();
            String sql = "SELECT id_producto, nombre, precio, existencias\n"
                    + "  FROM producto\n"
                    + "  where upper(nombre) like '%" + aguja + "%' order by id_producto;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setExistencias(rs.getInt("existencias"));
                p.setPrecio(rs.getDouble("precio"));
                lp.add(p);
            }
            rs.close();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(ModlProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Producto> IDProducto(int aguja) {
        try {
            List<Producto> lp = new ArrayList<>();
            String sql = "SELECT id_producto, nombre, precio, existencias\n"
                    + "  FROM producto\n"
                    + "  where id_producto=" + aguja + " id_producto;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setExistencias(rs.getInt("existencias"));
                p.setPrecio(rs.getDouble("precio"));
                lp.add(p);
            }
            rs.close();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(ModlProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean InsertarProducto() {
        String sql = "INSERT INTO producto(\n"
                + "            id_producto, nombre, precio, existencias)\n"
                + "    VALUES (" + getId_producto() + ", '" + getNombre() + "', " + getPrecio() + ", " + getExistencias() + ");";
        return con.accion(sql);
    }

    public boolean AumentarProducto() {
        String sql = "UPDATE producto\n"
                + "   SET existencias=" + getExistencias() + "\n"
                + " WHERE id_producto=" + getId_producto() + ";";
        return con.accion(sql);
    }

    public boolean Editar() {
        String sql = "UPDATE producto\n"
                + "  SET nombre='" + getNombre() + "', precio=" + getPrecio() + ", existencias=" + getExistencias() + "\n"
                + " WHERE id_producto=" + getId_producto() + ";";
        return con.accion(sql);
    }
}
