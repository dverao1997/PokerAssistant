/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokercash.vista.principal;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author CyberLink
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        dktContenedor = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuEmpleados = new javax.swing.JMenuItem();
        mnuUsuario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuProducto = new javax.swing.JMenuItem();
        mnuFichas = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuMesa = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        lblMensaje.setText("Pantalla Principal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(lblMensaje))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pokercash/vista/icons/logo_letras.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dktContenedor.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dktContenedorLayout = new javax.swing.GroupLayout(dktContenedor);
        dktContenedor.setLayout(dktContenedorLayout);
        dktContenedorLayout.setHorizontalGroup(
            dktContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dktContenedorLayout.setVerticalGroup(
            dktContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setText("Empleados");

        mnuEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pokercash/vista/icons/user.png"))); // NOI18N
        mnuEmpleados.setText("Ingresar Empleados");
        jMenu1.add(mnuEmpleados);

        mnuUsuario.setText("Ingresar Usuario");
        jMenu1.add(mnuUsuario);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Inventario");

        mnuProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pokercash/vista/icons/application.png"))); // NOI18N
        mnuProducto.setText("Ingresar Producto");
        jMenu2.add(mnuProducto);

        mnuFichas.setText("Ingresar Fichas");
        jMenu2.add(mnuFichas);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Mesa");

        mnuMesa.setText("Crear Mesa");
        jMenu3.add(mnuMesa);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dktContenedor)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dktContenedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMenuItem getMnuMesa() {
        return mnuMesa;
    }

    public void setMnuMesa(JMenuItem mnuMesa) {
        this.mnuMesa = mnuMesa;
    }

    public JMenuItem getMnuUsuario() {
        return mnuUsuario;
    }

    public void setMnuUsaurio(JMenuItem mnuUsaurio) {
        this.mnuUsuario = mnuUsaurio;
    }

    public JDesktopPane getDktContenedor() {
        return dktContenedor;
    }

    public void setDktContenedor(JDesktopPane dktContenedor) {
        this.dktContenedor = dktContenedor;
    }

    public JLabel getLblMensaje() {
        return lblMensaje;
    }

    public void setLblMensaje(JLabel lblMensaje) {
        this.lblMensaje = lblMensaje;
    }

    public JMenuItem getMnuEmpleados() {
        return mnuEmpleados;
    }

    public void setMnuEmpleados(JMenuItem mnuEmpleados) {
        this.mnuEmpleados = mnuEmpleados;
    }

    public JMenuItem getMnuProducto() {
        return mnuProducto;
    }

    public void setMnuProducto(JMenuItem mnuProducto) {
        this.mnuProducto = mnuProducto;
    }

    public JMenuItem getMnuFichas() {
        return mnuFichas;
    }

    public void setMnuFichas(JMenuItem mnuFichas) {
        this.mnuFichas = mnuFichas;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dktContenedor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JMenuItem mnuEmpleados;
    private javax.swing.JMenuItem mnuFichas;
    private javax.swing.JMenuItem mnuMesa;
    private javax.swing.JMenuItem mnuProducto;
    private javax.swing.JMenuItem mnuUsuario;
    // End of variables declaration//GEN-END:variables
}
