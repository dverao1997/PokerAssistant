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
public class ModlDetallProducto extends DetallProducto {

    ConexionPg con = new ConexionPg();

    public ModlDetallProducto(int id_deta_prod, int cantidad, int id_mesa, int id_producto, String nombre, double precio, int existencias) {
        super(id_deta_prod, cantidad, id_mesa, id_producto, nombre, precio, existencias);
    }

    public ModlDetallProducto() {
    }

    public List<DetallProducto> ListarTotalMesa(int ID_MESA) {
        try {
            List<DetallProducto> l = new ArrayList<>();
            String sql = "SELECT p.id_producto, p.nombre, p.precio, p.existencias,d.id_data_prod, d.cantidad,d.id_mesa\n"
                    + "FROM producto p join detalle_prod d on p.id_producto=d.id_producto\n"
                    + "WHERE d.id_mesa=" + ID_MESA + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                DetallProducto d = new DetallProducto();
                d.setId_producto(rs.getInt("id_producto"));
                d.setNombre(rs.getString("nombre"));
                d.setPrecio(rs.getDouble("precio"));
                d.setExistencias(rs.getInt("existencias"));
                d.setId_deta_prod(rs.getInt("id_data_prod"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setId_mesa(rs.getInt("id_mesa"));
                l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDetallProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<DetallProducto> ListarTotal() {
        try {
            List<DetallProducto> l = new ArrayList<>();
            String sql = "SELECT p.id_producto, p.nombre, p.precio, p.existencias,d.id_data_prod, d.cantidad,d.id_mesa\n"
                    + "FROM producto p join detalle_prod d on p.id_producto=d.id_producto;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                DetallProducto d = new DetallProducto();
                d.setId_producto(rs.getInt("id_producto"));
                d.setNombre(rs.getString("nombre"));
                d.setPrecio(rs.getDouble("precio"));
                d.setExistencias(rs.getInt("existencias"));
                d.setId_deta_prod(rs.getInt("id_data_prod"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setId_mesa(rs.getInt("id_mesa"));
                l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDetallProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<DetallProducto> IDListarTotal(int ID_DETA) {
        try {
            List<DetallProducto> l = new ArrayList<>();
            String sql = "SELECT p.id_producto, p.nombre, p.precio, p.existencias,d.id_data_prod, d.cantidad,d.id_mesa\n"
                    + "FROM producto p join detalle_prod d on p.id_producto=d.id_producto "
                    + "     WHERE d.id_data_prod=" + ID_DETA + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                DetallProducto d = new DetallProducto();
                d.setId_producto(rs.getInt("id_producto"));
                d.setNombre(rs.getString("nombre"));
                d.setPrecio(rs.getDouble("precio"));
                d.setExistencias(rs.getInt("existencias"));
                d.setId_deta_prod(rs.getInt("id_data_prod"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setId_mesa(rs.getInt("id_mesa"));
                l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDetallProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<DetallProducto> IDListarProducto(int ID_PROD) {
        try {
            List<DetallProducto> l = new ArrayList<>();
            String sql = "SELECT p.id_producto, p.nombre, p.precio, p.existencias,d.id_data_prod, d.cantidad,d.id_mesa\n"
                    + "FROM producto p join detalle_prod d on p.id_producto=d.id_producto "
                    + "     WHERE p.id_producto=" + ID_PROD + ";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                DetallProducto d = new DetallProducto();
                d.setId_producto(rs.getInt("id_producto"));
                d.setNombre(rs.getString("nombre"));
                d.setPrecio(rs.getDouble("precio"));
                d.setExistencias(rs.getInt("existencias"));
                d.setId_deta_prod(rs.getInt("id_data_prod"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setId_mesa(rs.getInt("id_mesa"));
                l.add(d);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlDetallProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertar() {
        String sql = "INSERT INTO detalle_prod(\n"
                + "            id_data_prod, cantidad, id_producto, id_mesa)\n"
                + "    VALUES (" + getId_deta_prod() + ", " + getCantidad() + ", " + getId_producto() + ", " + getId_mesa() + ");";
        return con.accion(sql);
    }

    public boolean Update() {
        String sql = "UPDATE detalle_prod\n"
                + "   SET cantidad=" + getCantidad() + "\n"
                + " WHERE id_data_prod=" + getId_deta_prod() + ";";
        return con.accion(sql);
    }

    public boolean UpdateProducto() {
        String sql = "UPDATE producto\n"
                + "   SET  existencias=" + getExistencias()+ "\n"
                + " WHERE id_producto=" + getId_producto() + ";";
        return con.accion(sql);
    }
}
