/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.modelo.inventario;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFileChooser;
import org.postgresql.util.Base64;
import pokercash.modelo.ConexionPg;

/**
 *
 * @author Usuario
 */
public class ModlFichas extends Fichas {

    ConexionPg con = new ConexionPg();

    public ModlFichas() {
    }

    public ModlFichas(int id_fichas, String color, double valor, String estado, Image foto, int cantidad) {
        super(id_fichas, color, valor, estado, foto, cantidad);
    }

    public List<Fichas> ListarFichas() {
        try {
            List<Fichas> l = new ArrayList<>();
            String sql = "SELECT *\n"
                    + "	FROM fichas;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Fichas f = new Fichas();
                f.setId_fichas(rs.getInt("id_fichas"));
                f.setColor(rs.getString("color"));
                f.setValor(rs.getDouble("valor"));
                f.setEstado(rs.getString("estado"));
                f.setCantidad(rs.getInt("cantidad"));
                byte[] bf = rs.getBytes("foto");
                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);
                    f.setFoto(ObtImagen(bf));

                } else {
                    f.setFoto(null);
                }
                l.add(f);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlFichas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Fichas> ListarFichas(int id) {
        try {
            List<Fichas> l = new ArrayList<>();
            String sql = "SELECT *\n"
                    + "	FROM fichas "
                    + "WHERE id_fichas="+id+";";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Fichas f = new Fichas();
                f.setId_fichas(rs.getInt("id_fichas"));
                f.setColor(rs.getString("color"));
                f.setValor(rs.getDouble("valor"));
                f.setEstado(rs.getString("estado"));
                f.setCantidad(rs.getInt("cantidad"));
                byte[] bf = rs.getBytes("foto");
                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);
                    f.setFoto(ObtImagen(bf));

                } else {
                    f.setFoto(null);
                }
                l.add(f);
            }
            rs.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ModlFichas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Image ObtImagen(byte[] bytes) {

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader) it.next();
        Object source = bis;
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(source);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            param.setSourceSubsampling(1, 1, 0, 0);
            return reader.read(0, param);

        } catch (IOException ex) {
            Logger.getLogger(ModlFichas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    private BufferedImage imgBinari(Image img) {
        //conversion de foto
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGR = bi.createGraphics();
        bGR.drawImage(img, 0, 0, null);
        bGR.dispose();
        return bi;
    }

    public boolean Ingresar() {
        String foto64 = null;
        BufferedImage img = imgBinari(getFoto());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {

            ImageIO.write(img, "PNG", bos);
            byte[] imgb = bos.toByteArray();
            foto64 = Base64.encodeBytes(imgb);
        } catch (IOException ex) {
            Logger.getLogger(ModlFichas.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO public.fichas(\n"
                + "	id_fichas, color, valor, foto, cantidad, estado)\n"
                + "	VALUES (" + getId_fichas() + ", '" + getColor() + "', " + getValor() + ", '" + foto64 + "', " + getCantidad() + ", '" + getEstado() + "');";
        return con.accion(sql);

    }

}
