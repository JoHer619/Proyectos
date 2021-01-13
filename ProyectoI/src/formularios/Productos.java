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
public class Productos extends javax.swing.JInternalFrame {

     Conexion con = new Conexion();
    
        Connection conn = null;
        Statement stmt = null;
        String qry = null;
        PreparedStatement pst =null;
        CallableStatement cStmt = null;
        int resul;
         
        int fila;
        
       public void Mostrar()  {
         String [] tit = {"ID_PRODUCTO","DESCRIPCION","STOCK","STOCK_MINIMO","PRECIO_UNITARIO","ID_PROVEEDOR"};
         String [] dato = new String[7];
         DefaultTableModel  modelo = new DefaultTableModel(null, tit);
          String qyl = "select * from PRODUCTO ORDER BY ID_PRODUCTO"; 
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          while(rs.next()){
              dato[0]=rs.getString("ID_PRODUCTO");
              dato[1]=rs.getString("DESCRIPCION");
              dato[2]=rs.getString("STOCK");
              dato[3]=rs.getString("STOCK_MINIMO");
              dato[4]=rs.getString("PRECIO_UNITARIO");
              dato[5]=rs.getString("ID_PROVEEDOR");
              
              modelo.addRow(dato);
             
              tabla_producto.setModel(modelo);
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
    }
   public void Insertar(){
      String insert =  "INSERT INTO producto VALUES (?,?,?,?,?,?)";
               try { 
              conn = con.getConnection();
              pst =conn.prepareStatement(insert);
              pst.setInt(1,Integer.parseInt(txt_id_producto.getText()));
              pst.setString(2,txt_descripcion.getText());
              pst.setString(3,txt_stock.getText());
              pst.setString(4,txt_stockM.getText());
              pst.setString(5,txt_precio.getText());
              pst.setString(6,txt_id_proveedor.getText());
           
              resul = pst.executeUpdate();
              if (resul==1){
              JOptionPane.showMessageDialog(null,"Dato guardado");
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Dato no guardado");
              }
          } catch (SQLException | HeadlessException e) {
              JOptionPane.showMessageDialog(null,"erro"+e);
          }
      }
      
   public void Modificar(){
   qry="UPDATE producto SET DESCRIPCION=?, STOCK=?, STOCK_MINIMO=?, PRECIO_UNITARIO=?,ID_PROVEEDOR=?"+"WHERE ID_PRODUCTO=?";
       try {
           fila=tabla_producto.getSelectedRow();
           String dat = (String)tabla_producto.getValueAt(fila,0);
           
              conn = con.getConnection();
              pst =conn.prepareStatement(qry);
              
              pst.setString(1,txt_descripcion.getText());
              pst.setString(2,txt_stock.getText());
              pst.setString(3,txt_stockM.getText());
              pst.setString(4,txt_precio.getText());
              pst.setString(5,txt_id_proveedor.getText());
              
              pst.setString(6,dat);
              pst.execute();
              
              JOptionPane.showMessageDialog(null,"Dato Modificado");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"error"+e);
       }
   }
    
   public void Eliminar (){
           qry = "delete from producto where ID_PRODUCTO=?";
           try {
            conn = con.getConnection(); 
            cStmt=conn.prepareCall(qry);
            cStmt.setInt(1,Integer.parseInt(txt_id_producto.getText()));
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
         String [] tit = {"ID_PRODUCTO","DESCRIPCION","STOCK","STOCK_MINIMO","PRECIO_UNITARIO","ID_PROVEEDOR"};
         String [] dato = new String[7];
   DefaultTableModel  modelo = new DefaultTableModel(null, tit);
   String qyl = "select * from PRODUCTO where DESCRIPCION like '%"+res+"%'";
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          
          while(rs.next()){
               dato[0]=rs.getString("ID_PRODUCTO");
              dato[1]=rs.getString("DESCRIPCION");
              dato[2]=rs.getString("STOCK");
              dato[3]=rs.getString("STOCK_MINIMO");
              dato[4]=rs.getString("PRECIO_UNITARIO");
              dato[5]=rs.getString("ID_PROVEEDOR");
              modelo.addRow(dato);
              tabla_producto.setModel(modelo); 
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
   
   }
   public Void Limpiar(){
   txt_id_producto.setText(null);
   txt_descripcion.setText(null);
   txt_stock.setText(null);
   txt_stockM.setText(null);
   txt_precio.setText(null);
   txt_id_proveedor.setText(null);

        return null;
   }
    
    public Productos() {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id_proveedor = new javax.swing.JTextField();
        txt_id_producto = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        txt_stock = new javax.swing.JTextField();
        txt_stockM = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_insertar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Productos");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID PRODUCTO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("DESCRIPCION");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 20));

        jLabel3.setText("ID PROVEEDOR");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel4.setText("STOCK");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 20));

        jLabel5.setText("STOCK MINIMO");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        jLabel6.setText("PRECIO UNITARIO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        txt_id_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_proveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txt_id_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 130, -1));

        txt_id_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_productoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_id_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 130, -1));

        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyTyped(evt);
            }
        });
        jPanel1.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 130, -1));

        txt_stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stockKeyTyped(evt);
            }
        });
        jPanel1.add(txt_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 130, -1));

        txt_stockM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stockMKeyTyped(evt);
            }
        });
        jPanel1.add(txt_stockM, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 130, -1));

        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioKeyTyped(evt);
            }
        });
        jPanel1.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 130, -1));

        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        btn_insertar.setText("INSERTAR");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        btn_modificar.setText("MODIFICAR");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        btn_eliminar.setText("ELMINAR");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jLabel12.setText("Seleccione la fila para modificar");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, 20));

        jTabbedPane1.addTab("Formulario", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_producto);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 70, 470, 213));

        jLabel7.setText("Buscar");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel2.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 130, -1));

        jTabbedPane1.addTab("Tabla", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txt_id_producto.getText())||!"".equals(txt_descripcion.getText()) ||!"".equals(txt_stock.getText())
           ||!"".equals(txt_stockM.getText())||!"".equals(txt_precio.getText())||!"".equals(txt_id_proveedor.getText())){
                 Modificar();
                Limpiar();
                 Mostrar();
                 btn_insertar.setEnabled(true);
                btn_eliminar.setEnabled(true);
        }else{
        JOptionPane.showMessageDialog(null,"Seleccione la fila que desea Modificar");
        }
       
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        // TODO add your handling code here:
         if (!"".equals(txt_id_producto.getText())||!"".equals(txt_descripcion.getText()) ||!"".equals(txt_stock.getText())
           ||!"".equals(txt_stockM.getText())||!"".equals(txt_precio.getText())||!"".equals(txt_id_proveedor.getText())){
                 Insertar();
                Limpiar();
                 Mostrar();
             
        }else{
        JOptionPane.showMessageDialog(null,"Ingrese Datos");
        }
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
         if (!"".equals(txt_id_producto.getText())||!"".equals(txt_descripcion.getText()) ||!"".equals(txt_stock.getText())
           ||!"".equals(txt_stockM.getText())||!"".equals(txt_precio.getText())||!"".equals(txt_id_proveedor.getText())){
                 Eliminar();
                Limpiar();
                 Mostrar();
               
        }else{
        JOptionPane.showMessageDialog(null,"Ingres Id_producto que del registro que desea eliminar");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
         buscar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void tabla_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productoMouseClicked
        // TODO add your handling code here:
          fila = tabla_producto.rowAtPoint(evt.getPoint());
        
        txt_id_producto.setText(tabla_producto.getValueAt(fila, 0).toString());
          txt_descripcion.setText(tabla_producto.getValueAt(fila, 1).toString());
            txt_stock.setText(tabla_producto.getValueAt(fila, 2).toString());
              txt_stockM.setText(tabla_producto.getValueAt(fila, 3).toString());
                txt_precio.setText(tabla_producto.getValueAt(fila, 4).toString());
                  txt_id_proveedor.setText(tabla_producto.getValueAt(fila, 5).toString());
                  
         btn_insertar.setEnabled(false);
         btn_eliminar.setEnabled(false);
                    
    }//GEN-LAST:event_tabla_productoMouseClicked

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_id_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_productoKeyTyped
        // TODO add your handling code here:
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_id_productoKeyTyped

    private void txt_descripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyTyped
        // TODO add your handling code here:
         char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_descripcionKeyTyped

    private void txt_stockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stockKeyTyped
        // TODO add your handling code here:
           char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_stockKeyTyped

    private void txt_stockMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stockMKeyTyped
        // TODO add your handling code here:
           char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_stockMKeyTyped

    private void txt_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyTyped
        // TODO add your handling code here:
           char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_precioKeyTyped

    private void txt_id_proveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_proveedorKeyTyped
        // TODO add your handling code here:
           char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_id_proveedorKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla_producto;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_id_producto;
    private javax.swing.JTextField txt_id_proveedor;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_stock;
    private javax.swing.JTextField txt_stockM;
    // End of variables declaration//GEN-END:variables
}
