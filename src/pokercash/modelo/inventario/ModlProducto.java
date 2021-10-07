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
                    + "  where upper(nombre) like '%" + aguja + "%';";
            ResultSet rs=con.consulta(sql);
            while (rs.next()) {
                Producto p=new Producto();
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
}
