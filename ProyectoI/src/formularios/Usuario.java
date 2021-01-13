package formularios;

import Conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Usuario extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();

    Connection conn = null;
    Statement stmt = null;
    String qry = null;
    PreparedStatement pst = null;
    CallableStatement cStmt = null;
    int resul;

    int fila;

    public void Mostrar() {
        String[] tit = {"ID_USUARIO", "USUARIO", "CONTRASENA", "FECHA_INGRESO", "FECHA_ULTIMA_MODIFICACION", "ID_EMPLEADO", "ESTATUS"};
        String[] dato = new String[7];
        DefaultTableModel modelo = new DefaultTableModel(null, tit);
        String qyl = "select * from USUARIO  order by ID_USUARIO";
        try {
            Connection cn = con.getConnection();
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(qyl);
            while (rs.next()) {
                dato[0] = rs.getString("ID_USUARIO");
                dato[1] = rs.getString("USUARIO");
                dato[2] = rs.getString("CONTRASENA");
                dato[3] = rs.getString("FECHA_INGRESO");
                dato[4] = rs.getString("FECHA_ULTIMA_MODIFICACION");
                dato[5] = rs.getString("ID_EMPLEADO");
                dato[6] = rs.getString("ESTATUS");
                modelo.addRow(dato);

                Jtable_usuario.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
        }
    }

    public void Insertar() {
        String insert = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?,?)";
        try {
            conn = con.getConnection();
            pst = conn.prepareStatement(insert);
            pst.setInt(1, Integer.parseInt(txt_id_usuario2.getText()));
            pst.setString(2, txt_usuario2.getText());
            pst.setString(3, txt_contrasenia2.getText());

            pst.setString(4, txt_fecha_ingreso2.getText());
           
            pst.setString(5, txt_fecha_ultima_mod2.getText());
            
            pst.setString(6, txt_idEmpleado.getText());
            pst.setString(7, txt_estatus2.getText());

            resul = pst.executeUpdate();
            if (resul == 1) {
                JOptionPane.showMessageDialog(null, "Dato guardado");
            } else {
                JOptionPane.showMessageDialog(null, "Dato no guardado");
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "erro" + e);
        }
    }

    public void Modificar() {
        qry = "UPDATE USUARIO SET USUARIO=?, CONTRASENA=?, FECHA_INGRESO=?, FECHA_ULTIMA_MODIFICACION=?, ID_EMPLEADO=?, ESTATUS=?" + "WHERE ID_USUARIO=?";
        try {
            fila = Jtable_usuario.getSelectedRow();
            String dat = (String) Jtable_usuario.getValueAt(fila, 0);

            conn = con.getConnection();
            pst = conn.prepareStatement(qry);

            pst.setString(1, txt_usuario2.getText());
            pst.setString(2, txt_contrasenia2.getText());


            pst.setString(3, txt_fecha_ingreso2.getText());
            pst.setString(4, txt_fecha_ultima_mod2.getText());
            
            pst.setString(5, txt_idEmpleado.getText());
            pst.setString(6, txt_estatus2.getText());

            pst.setString(7, dat);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dato Modificado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e);
        }
    }

    public void EliminarDatos() {
        int filaSeleccionada = Jtable_usuario.getSelectedRow();

        try {
            qry = "DELETE FROM USUARIO WHERE ID_USUARIO= " + Jtable_usuario.getValueAt(filaSeleccionada, 0);

            conn = con.getConnection();
            stmt = conn.createStatement();

            int n = stmt.executeUpdate(qry);

            if (n >= 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro" + e.getMessage());
        }
    }

    public void Eliminar() {
        qry = "delete from usuario where ID_USUARIO=?";
        try {
            conn = con.getConnection();
            cStmt = conn.prepareCall(qry);
            cStmt.setInt(1, Integer.parseInt(txt_id_usuario2.getText()));
            resul = cStmt.executeUpdate();

            if (resul == 1) {
                JOptionPane.showMessageDialog(null, "Dato Eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Dato no eliminado");
            }
            cStmt.close();

        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void buscar(String res) {
        String[] tit = {"ID_USUARIO", "USUARIO", "CONTRASENA", "FECHA_INGRESO", "FECHA_ULTIMA_MODIFICACION", "ESTATUS"};
        String[] dato = new String[7];
        DefaultTableModel modelo = new DefaultTableModel(null, tit);
        String qyl = "select * from USUARIO where ID_USUARIO like '%" + res + "%'";
        try {
            Connection cn = con.getConnection();
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(qyl);

            while (rs.next()) {
                dato[0] = rs.getString("ID_USUARIO");
                dato[1] = rs.getString("USUARIO");
                dato[2] = rs.getString("CONTRASENA");
                dato[3] = rs.getString("FECHA_INGRESO");
                dato[4] = rs.getString("FECHA_ULTIMA_MODIFICACION");
                dato[5] = rs.getString("ID_EMPLEADO");
                dato[6] = rs.getString("ESTATUS");
                modelo.addRow(dato);
                Jtable_usuario.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
        }

    }

    public Void Limpiar() {
        txt_id_usuario2.setText(null);
        txt_usuario2.setText(null);
        txt_contrasenia2.setText(null);
        txt_fecha_ingreso2.setText(null);
        txt_fecha_ultima_mod2.setText(null);
        txt_idEmpleado.setText(null);
        txt_estatus2.setText(null);

        return null;
    }

    public Usuario() {
        initComponents();

        Mostrar();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_estatus2 = new javax.swing.JTextField();
        txt_id_usuario2 = new javax.swing.JTextField();
        txt_usuario2 = new javax.swing.JTextField();
        txt_contrasenia2 = new javax.swing.JTextField();
        btn_modificar2 = new javax.swing.JButton();
        btn_insertar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtable_usuario = new javax.swing.JTable();
        btn_eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_idEmpleado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_fecha_ingreso2 = new javax.swing.JTextField();
        txt_fecha_ultima_mod2 = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuario");

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setText("ESTATUS");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel14.setText("ID_USUARIO");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel15.setText("USUARIO");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel16.setText("CONTRASEÑA");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel17.setText("FECHA INGRESO");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel18.setText("FECHA ULTIMA MODIFICACION");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jPanel3.add(txt_estatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 240, -1));
        jPanel3.add(txt_id_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 240, -1));
        jPanel3.add(txt_usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 240, -1));
        jPanel3.add(txt_contrasenia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 240, -1));

        btn_modificar2.setText("Modificar");
        btn_modificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificar2ActionPerformed(evt);
            }
        });
        jPanel3.add(btn_modificar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, -1));

        btn_insertar.setText("Agregar");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        btn_salir.setText("SALIR");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel3.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        Jtable_usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Jtable_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtable_usuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Jtable_usuario);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 570, 100));

        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jLabel1.setText("Buscar");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel3.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 100, -1));

        jLabel12.setText("Seleccione la fila para modificar");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));
        jPanel3.add(txt_idEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 240, -1));

        jLabel2.setText("ID EMPLEADO");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        jPanel3.add(txt_fecha_ingreso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 190, -1));
        jPanel3.add(txt_fecha_ultima_mod2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_modificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificar2ActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txt_id_usuario2.getText()) || !"".equals(txt_usuario2.getText()) || !"".equals(txt_contrasenia2.getText())
                || !"".equals(txt_fecha_ingreso2.getText()) || !"".equals(txt_fecha_ultima_mod2.getText()) || !"".equals(txt_estatus2.getText())) {
            
       
        Modificar();
        Limpiar();
        Mostrar();
       
 
        
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione la fila que desea Modificar");
        }

        btn_insertar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        
    }//GEN-LAST:event_btn_modificar2ActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txt_id_usuario2.getText()) || !"".equals(txt_usuario2.getText()) || !"".equals(txt_contrasenia2.getText())
                || !"".equals(txt_fecha_ingreso2.getText()) || !"".equals(txt_fecha_ultima_mod2.getText()) || !"".equals(txt_estatus2.getText())) {
            
            
        Insertar();
        Limpiar();
        Mostrar();
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese Datos");
        }


    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
   if (!"".equals(txt_id_usuario2.getText())||!"".equals(txt_usuario2.getText()) ||!"".equals(txt_contrasenia2.getText())
           ||!"".equals(txt_fecha_ingreso2.getText())||!"".equals(txt_fecha_ultima_mod2.getText())||!"".equals(txt_estatus2.getText())){
        
       
        EliminarDatos();
        Limpiar();
        Mostrar();
       
        }else{
        JOptionPane.showMessageDialog(null,"Ingrese el Id_usuario que desea eliminar ");
        }


    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:

        buscar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void Jtable_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtable_usuarioMouseClicked
        fila = Jtable_usuario.rowAtPoint(evt.getPoint());

        txt_id_usuario2.setText(Jtable_usuario.getValueAt(fila, 0).toString());
        txt_usuario2.setText(Jtable_usuario.getValueAt(fila, 1).toString());
        txt_contrasenia2.setText(Jtable_usuario.getValueAt(fila, 2).toString());

        txt_fecha_ingreso2.setText(Jtable_usuario.getValueAt(fila, 3).toString());

        
        
        txt_fecha_ultima_mod2.setText(Jtable_usuario.getValueAt(fila, 4).toString());
        
        
        txt_idEmpleado.setText(Jtable_usuario.getValueAt(fila, 5).toString());
        txt_estatus2.setText(Jtable_usuario.getValueAt(fila, 6).toString());

       // btn_insertar.setEnabled(false);
        //  btn_eliminar.setEnabled(false);

    }//GEN-LAST:event_Jtable_usuarioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Jtable_usuario;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar2;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_contrasenia2;
    private javax.swing.JTextField txt_estatus2;
    private javax.swing.JTextField txt_fecha_ingreso2;
    private javax.swing.JTextField txt_fecha_ultima_mod2;
    private javax.swing.JTextField txt_idEmpleado;
    private javax.swing.JTextField txt_id_usuario2;
    private javax.swing.JTextField txt_usuario2;
    // End of variables declaration//GEN-END:variables
}
