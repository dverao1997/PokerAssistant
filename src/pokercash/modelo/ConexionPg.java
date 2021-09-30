/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CyberLink
 */
public class ConexionPg {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    //Conexion de la base de datos
    private String cadConexion = "jdbc:postgresql://localhost:5432/pokertable";
    private String usuarioPg = "postgres";
    private String pssPG = "1234";

    public ConexionPg() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionPg.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con=DriverManager.getConnection(cadConexion, usuarioPg, pssPG);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet consulta(String sqlcs){
        try {
            st=con.createStatement();
            ResultSet rs=st.executeQuery(sqlcs);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPg.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean accion(String sqla){
        try {
            st=con.createStatement();
            boolean rt=st.execute(sqla);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPg.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Connection getCon() {
        return con;
    }
}
