
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;
import Conexion.Conexion;
import Menu.Principal;
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
public class Clientes extends javax.swing.JInternalFrame {

      Conexion con = new Conexion();
    
        Connection conn = null;
        Statement stmt = null;
        String qry = null;
        PreparedStatement pst =null;
        CallableStatement cStmt = null;
        int resul;
         
        int fila;
    
        public void Mostrar()  {
         String [] tit = {"ID_CLIENTE","NOMBRE","APELLIDO","RAZON_SOCIAL","NIT","DIRECCION","TELEFONO","EMAIL","FECHA_INGRESO","ESTATUS"};
         String [] dato = new String[10];
         DefaultTableModel  modelo = new DefaultTableModel(null, tit);
          String qyl = "select * from CLIENTE ORDER BY ID_CLIENTE"; 
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          while(rs.next()){
              dato[0]=rs.getString("ID_CLIENTE");
              dato[1]=rs.getString("NOMBRE");
              dato[2]=rs.getString("APELLIDO");
              dato[3]=rs.getString("RAZON_SOCIAL");
              dato[4]=rs.getString("NIT");
              dato[5]=rs.getString("DIRECCION");
              dato[6]=rs.getString("TELEFONO");
              dato[7]=rs.getString("EMAIL");
              dato[8]=rs.getString("FECHA_INGRESO");
              dato[9]=rs.getString("ESTATUS");
              
              modelo.addRow(dato);
             
              Jtabla_cliente.setModel(modelo);
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
       
    }
        public void Insertar(){
      String insert =  "INSERT INTO CLIENTE VALUES (?,?,?,?,?,?,?,?,?,?)";
               try { 
              conn = con.getConnection();
              pst =conn.prepareStatement(insert);
              
              pst.setInt(1,Integer.parseInt(txt_id_cliente.getText()));
              pst.setString(2,txt_nombre.getText());
              pst.setString(3,txt_apellido.getText());
              pst.setString(4,txt_razon.getText());
              pst.setString(5,txt_nit.getText());
              pst.setString(6,txt_direccion.getText());
              pst.setString(7,txt_telefono.getText());
              pst.setString(8,txt_email.getText());
              pst.setString(9,txt_fecha.getText());
              pst.setString(10,txt_estatus.getText());
              
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
       try {
           qry = "UPDATE CLIENTE SET NOMBRE=?, APELLIDO=?, RAZON_SOCIAL=?, NIT=?, DIRECCION=?, TELEFONO=?, EMAIL=?, FECHA_INGRESO=?,ESTATUS=?"
           +"WHERE ID_CLIENTE=?";
           
           fila = Jtabla_cliente.getSelectedRow();
           String dat = (String)Jtabla_cliente.getValueAt(fila,0);
              conn = con.getConnection();
              pst =conn.prepareStatement(qry);
              
              
              pst.setString(1,txt_nombre.getText());
              pst.setString(2,txt_apellido.getText());
              pst.setString(3,txt_razon.getText());
              pst.setString(4,txt_nit.getText());
              pst.setString(5,txt_direccion.getText());
              pst.setString(6,txt_telefono.getText());
              pst.setString(7,txt_email.getText());
              pst.setString(8,txt_fecha.getText());
              pst.setString(9,txt_estatus.getText());
            
              pst.setString(10,dat);
              pst.execute();
              
              JOptionPane.showMessageDialog(null,"Dato Modificado");
              
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"error  "+e);
       }
   }
    
    public void Eliminar (){
        
           qry = "delete from CLIENTE where ID_CLIENTE=?";
           try {
            conn = con.getConnection(); 
            cStmt=conn.prepareCall(qry);
            cStmt.setInt(1,Integer.parseInt(txt_id_cliente.getText()));
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
         String [] tit = {"ID_CLIENTE","NOMBRE","APELLIDO","RAZON_SOCIAL","NIT","DIRECCION","TELEFONO","EMAIL","FECHA_INGRESO","ESTATUS"};
         String [] dato = new String[10];
   DefaultTableModel  modelo = new DefaultTableModel(null, tit);
   String qyl = "select * from cliente where ID_CLIENTE like '%"+res+"%'";
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          
          while(rs.next()){
              dato[0]=rs.getString("ID_CLIENTE");
              dato[1]=rs.getString("NOMBRE");
              dato[2]=rs.getString("APELLIDO");
              dato[3]=rs.getString("RAZON_SOCIAL");
              dato[4]=rs.getString("NIT");
              dato[5]=rs.getString("DIRECCION");
              dato[6]=rs.getString("TELEFONO");
              dato[7]=rs.getString("EMAIL");
              dato[8]=rs.getString("FECHA_INGRESO");
              dato[9]=rs.getString("ESTATUS");
              modelo.addRow(dato);
              Jtabla_cliente.setModel(modelo); 
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
     }
          public void Limpiar(){
      txt_id_cliente.setText(null);
      txt_nombre.setText(null);
      txt_apellido.setText(null);
      txt_razon.setText(null);
      txt_nit.setText(null);
      txt_direccion.setText(null);
       txt_telefono.setText(null);
       txt_email.setText(null);
         txt_fecha.setText(null);
         txt_estatus.setText(null);
          }
    
    public Clientes() {
         
        
        initComponents();
        Mostrar();
//        String [] tit = {"ID_CLIENTE","NOMBRE","APELLIDO","RAZON_SOCIAL","NIT","DIRECCION","TELEFONO","EMAIL","FECHA_INGRESO","ESTATUS"};
//        DefaultTableModel  modelo = new DefaultTableModel(null, tit);
//        Jtabla_cliente.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        txt_email = new javax.swing.JTextField();
        txt_id_cliente = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_razon = new javax.swing.JTextField();
        txt_nit = new javax.swing.JTextField();
        btn_modificar = new javax.swing.JButton();
        btn_insertar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jtabla_cliente = new javax.swing.JTable();
        btn_eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_estatus = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setText("EMAIL");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        jLabel14.setText("ID_CLIENTE");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel15.setText("NOMBRE");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel16.setText("APELLIDO");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel17.setText("RAZON SOCIAL");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel18.setText("NIT");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));
        jPanel3.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 150, -1));

        txt_id_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_clienteActionPerformed(evt);
            }
        });
        txt_id_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_clienteKeyTyped(evt);
            }
        });
        jPanel3.add(txt_id_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 240, -1));

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 240, -1));

        txt_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellidoKeyTyped(evt);
            }
        });
        jPanel3.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 240, -1));

        txt_razon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_razonKeyTyped(evt);
            }
        });
        jPanel3.add(txt_razon, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 240, -1));

        txt_nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nitKeyTyped(evt);
            }
        });
        jPanel3.add(txt_nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 90, -1));

        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

        btn_insertar.setText("Agregar");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, -1, -1));

        btn_salir.setText("SALIR");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel3.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));

        Jtabla_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Jtabla_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtabla_clienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Jtabla_cliente);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 630, 160));

        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        jLabel1.setText("Buscar");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel3.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 100, -1));

        jLabel19.setText("DIRECCION");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        txt_estatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_estatusKeyTyped(evt);
            }
        });
        jPanel3.add(txt_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 150, -1));

        jLabel20.setText("ESTATUS");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 60, 20));

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });
        jPanel3.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 150, -1));

        jLabel21.setText("TELEFONO");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });
        jPanel3.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 90, -1));

        jLabel22.setText("FECHA_INGRESO");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        jPanel3.add(txt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 90, -1));

        jLabel12.setText("Seleccione la fila para modificar");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txt_id_cliente.getText())||!"".equals(txt_nombre.getText()) ||!"".equals(txt_apellido.getText())
           ||!"".equals(txt_razon.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_direccion.getText())||!"".equals(txt_telefono.getText())
                ||!"".equals(txt_email.getText())||!"".equals(txt_fecha.getText())){
                 Modificar();
                Limpiar();
                 Mostrar();
                 btn_insertar.setEnabled(true);
                btn_eliminar.setEnabled(true);
        }else{
        JOptionPane.showMessageDialog(null,"Seleccione la fila que desea Modificar");
        }

    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        // TODO add your handling code here:
          if (!"".equals(txt_id_cliente.getText())||!"".equals(txt_nombre.getText()) ||!"".equals(txt_apellido.getText())
           ||!"".equals(txt_razon.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_direccion.getText())||!"".equals(txt_telefono.getText())
                ||!"".equals(txt_email.getText())||!"".equals(txt_fecha.getText())){
                 Insertar();
                Limpiar();
                 Mostrar();
                 
        }else{
        JOptionPane.showMessageDialog(null,"Igrese Datos");
        }
        
       
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
     this.dispose();       
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
          if (!"".equals(txt_id_cliente.getText())||!"".equals(txt_nombre.getText()) ||!"".equals(txt_apellido.getText())
           ||!"".equals(txt_razon.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_direccion.getText())||!"".equals(txt_telefono.getText())
                ||!"".equals(txt_email.getText())||!"".equals(txt_fecha.getText())){
                
                  Eliminar();
                  Limpiar();
                   Mostrar();
        }else{
        JOptionPane.showMessageDialog(null,"Ingrese id_cleinte del registro que desea eliminar");
        }
      
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
       buscar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void txt_id_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_clienteActionPerformed

    private void Jtabla_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtabla_clienteMouseClicked
  
        int fila = Jtabla_cliente.rowAtPoint(evt.getPoint());
        
         txt_id_cliente.setText(Jtabla_cliente.getValueAt(fila, 0).toString());
         txt_nombre.setText(Jtabla_cliente.getValueAt(fila, 1).toString());
         txt_apellido.setText(Jtabla_cliente.getValueAt(fila, 2).toString());
         txt_razon.setText(Jtabla_cliente.getValueAt(fila, 3).toString());
         txt_nit.setText(Jtabla_cliente.getValueAt(fila, 4).toString());
         txt_direccion.setText(Jtabla_cliente.getValueAt(fila, 5).toString());
         txt_telefono.setText(Jtabla_cliente.getValueAt(fila, 6).toString());
         txt_email.setText(Jtabla_cliente.getValueAt(fila, 7).toString());
         txt_fecha.setText(Jtabla_cliente.getValueAt(fila, 8).toString());
         txt_estatus.setText(Jtabla_cliente.getValueAt(fila, 9).toString());
         
         btn_insertar.setEnabled(false);
         btn_eliminar.setEnabled(false);
    }//GEN-LAST:event_Jtabla_clienteMouseClicked

    private void txt_id_clienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_clienteKeyTyped
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_id_clienteKeyTyped

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
       char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidoKeyTyped
         char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_apellidoKeyTyped

    private void txt_razonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_razonKeyTyped
          char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_razonKeyTyped

    private void txt_nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nitKeyTyped
           char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_nitKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
     char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_direccionKeyTyped

    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
        // TODO add your handling code here:
             char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_telefonoKeyTyped

    private void txt_estatusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_estatusKeyTyped
               char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_estatusKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Jtabla_cliente;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_estatus;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_id_cliente;
    private javax.swing.JTextField txt_nit;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_razon;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
