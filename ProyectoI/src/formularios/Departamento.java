/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;
import Conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Departamento extends javax.swing.JInternalFrame {

    Connection conn = null;
        Statement stmt = null;
        String qry = null;
        PreparedStatement pst =null;
         CallableStatement cStmt = null;
         int resul;
         
        int fila;
       
        
         Conexion con = new Conexion();
      DefaultTableModel  modelo = new DefaultTableModel();
        
      public void Insertar(){
      String insert =  "INSERT INTO DEPARTAMENTO VALUES (?,?)";
     
               try { 
              conn = con.getConnection();
              pst =conn.prepareStatement(insert);
              pst.setInt(1,Integer.parseInt(txt_id_departamento.getText()));
              pst.setString(2,txt_descripcion.getText());
          
           
              resul = pst.executeUpdate();
              if (resul==1){
              JOptionPane.showMessageDialog(null,"Dato guardado");
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Dato no guardado");
              }
          } catch (SQLException | HeadlessException e) {
              JOptionPane.showMessageDialog(null,"Error"+e);
          }
      }
      public void Mostrar()  {
           
        
         String [] tit = {"ID_DEPARTAMENTO","DESCRIPCION"};
         String [] dato = new String[2];
         DefaultTableModel  modelo = new DefaultTableModel(null, tit);
          String qyl = "select * from DEPARTAMENTO ORDER BY ID_DEPARTAMENTO";
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          
          while(rs.next()){
              dato[0]=rs.getString("ID_DEPARTAMENTO");
              dato[1]=rs.getString("DESCRIPCION");
               modelo.addRow(dato);
               tabla_departamento.setModel(modelo);
          }
          
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
    }
         public void Modificar(){
             fila = tabla_departamento.getSelectedRow();
             int resul;
             qry =  "update departamento set DESCRIPCION=? where ID_DEPARTAMENTO=? ";
             try {
              conn=con.getConnection();
              pst =conn.prepareStatement(qry);
                
              pst.setString(1,txt_descripcion.getText());
              
              pst.setInt(2,Integer.parseInt(txt_id_departamento.getText()));
             
               resul = pst.executeUpdate();
              
              if (resul==1){
              JOptionPane.showMessageDialog(null,"Dato Modificado");
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Dato No modificado ");
              }
             } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null,"erro"+e);
             }
         }
           Empleado em = new Empleado();
         public void Eliminar (){
           qry = "delete from DEPARTAMENTO where ID_DEPARTAMENTO=?";
           try {
             
             
            conn = con.getConnection(); 
            cStmt=conn.prepareCall(qry);
            cStmt.setInt(1,Integer.parseInt(txt_id_departamento.getText()));
            resul = cStmt.executeUpdate();
            
            if (resul==1)
            {
            JOptionPane.showMessageDialog(null, "Dato Eliminado");
            }
            else 
            {
             JOptionPane.showMessageDialog(null, "Dato no eliminado");
            }
            cStmt.close();
            
           } catch (SQLException e) {
           System.out.println("Error"+e);
           }
         }
          public void buscar (String res){
          
   String [] tit = {"ID_DEPARTAMENTO","DESCRIPCION"};
   String [] dato = new String[2];
   DefaultTableModel  modelo = new DefaultTableModel(null, tit);
   String qyl = "select * from DEPARTAMENTO where ID_DEPARTAMENTO like '%"+res+"%'";
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          while(rs.next()){
               dato[0]=rs.getString("ID_DEPARTAMENTO");
              dato[1]=rs.getString("DESCRIPCION");
               modelo.addRow(dato);
               tabla_departamento.setModel(modelo);
          }
          
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
   
   }
          public void Limpiar(){
          txt_id_departamento.setText(null);
          txt_descripcion.setText(null);
          }
    public Departamento() {
        initComponents();
        Mostrar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id_departamento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_departamento = new javax.swing.JTable();
        txt_buscar = new javax.swing.JTextField();
        Buscar = new javax.swing.JLabel();
        btn_insertar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Departamento");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID_DEPARTAMENTO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txt_id_departamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_departamentoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_id_departamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 140, -1));

        jLabel2.setText("DESCRIPCION");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));
        jPanel1.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 140, -1));

        tabla_departamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_departamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_departamento);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 470, 130));

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel1.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 110, -1));

        Buscar.setText("Buscar");
        jPanel1.add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        btn_insertar.setText("Insertar");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, -1));

        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        jLabel12.setText("Seleccione la fila para modificar");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel1.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 270));

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_departamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_departamentoMouseClicked
        // TODO add your handling code here:
        
        int fila = tabla_departamento.rowAtPoint(evt.getPoint());
        
        txt_id_departamento.setText(tabla_departamento.getValueAt(fila, 0).toString());
         txt_descripcion.setText(tabla_departamento.getValueAt(fila, 1).toString());
          btn_insertar.setEnabled(false);
         btn_eliminar.setEnabled(false);
    }//GEN-LAST:event_tabla_departamentoMouseClicked

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
        
         buscar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        // TODO add your handling code here:
        
        if (!"".equals(txt_id_departamento.getText())||!"".equals(txt_descripcion.getText()) ){
                Insertar();
                Mostrar();
                Limpiar();
              }
              else{             
                 JOptionPane.showMessageDialog(null,"Ingrese Datos");
              }     
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
          if (!"".equals(txt_id_departamento.getText())||!"".equals(txt_descripcion.getText()) ){
              Modificar();
             Mostrar();
                Limpiar();
             btn_insertar.setEnabled(true);
            btn_eliminar.setEnabled(true);
              }
              else{             
                 JOptionPane.showMessageDialog(null,"Seleccione la fila que desea Modificar");
              }
        
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
         if (!"".equals(txt_id_departamento.getText())||!"".equals(txt_descripcion.getText()) ){
               Eliminar();
                Mostrar();
                Limpiar();
              }
              else{             
                 JOptionPane.showMessageDialog(null,"Ingrese id_departemnto que desea eliminar");
              }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void txt_id_departamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_departamentoKeyTyped
        // TODO add your handling code here:
           char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_id_departamentoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Buscar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_departamento;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_id_departamento;
    // End of variables declaration//GEN-END:variables
}
