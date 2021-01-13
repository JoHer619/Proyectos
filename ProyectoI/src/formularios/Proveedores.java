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
public class Proveedores extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    
        Connection conn = null;
        Statement stmt = null;
        String qry = null;
        PreparedStatement pst =null;
        CallableStatement cStmt = null;
        int resul;
         
        int fila;
    
        public void Mostrar()  {
         String [] tit = {"ID_PROVEEDOR","EMPRESA","DIRECCION","TELEFONO","NIT","CIUDAD","PAIS","CONTACTO","EMAIL","TELEFONO_CONTACTO","ESTATUS"};
         String [] dato = new String[11];
         DefaultTableModel  modelo = new DefaultTableModel(null, tit);
          String qyl = "select * from PROVEEDOR ORDER BY ID_PROVEEDOR"; 
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          while(rs.next()){
              dato[0]=rs.getString("ID_PROVEEDOR");
              dato[1]=rs.getString("EMPRESA");
              dato[2]=rs.getString("DIRECCION");
              dato[3]=rs.getString("TELEFONO");
              dato[4]=rs.getString("NIT");
              dato[5]=rs.getString("CIUDAD");
              dato[6]=rs.getString("PAIS");
              dato[7]=rs.getString("CONTACTO");
              dato[8]=rs.getString("EMAIL");
               dato[9]=rs.getString("TELEFONO_CONTACTO");
              dato[10]=rs.getString("ESTATUS");
              
              modelo.addRow(dato);
             
              tabla_proveedor.setModel(modelo);
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
       
    }
        public void Insertar(){
      String insert =  "INSERT INTO PROVEEDOR VALUES (?,?,?,?,?,?,?,?,?,?,?)";
               try { 
              conn = con.getConnection();
              pst =conn.prepareStatement(insert);
              
              pst.setInt(1,Integer.parseInt(txt_id_proveedor.getText()));
              pst.setString(2,txt_empresa.getText());
              pst.setString(3,txt_direccion.getText());
              pst.setString(4,txt_telefono.getText());
              pst.setString(5,txt_nit.getText());
              pst.setString(6,txt_ciudad.getText());
              pst.setString(7,txt_pais.getText());
              pst.setString(8,txt_contacto.getText());
              pst.setString(9,txt_email.getText());
              pst.setString(10,txt_telefonoC.getText());
              pst.setString(11,txt_estatus.getText());
              
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
           qry = "UPDATE PROVEEDOR SET EMPRESA=?, DIRECCION=?, TELEFONO=?, NIT=?,CIUDAD=?, PAIS=?,CONTACTO=?, EMAIL=?, TELEFONO_CONTACTO=?,ESTATUS=?"
                +"WHERE ID_PROVEEDOR=?";
           fila =tabla_proveedor.getSelectedRow();
           String dat = (String)tabla_proveedor.getValueAt(fila,0);
              conn = con.getConnection();
              pst =conn.prepareStatement(qry);    
              
              pst.setString(1,txt_empresa.getText());
              pst.setString(2,txt_direccion.getText());
              pst.setString(3,txt_telefono.getText());
              pst.setString(4,txt_nit.getText());
              pst.setString(5,txt_ciudad.getText());
              pst.setString(6,txt_pais.getText());
              pst.setString(7,txt_contacto.getText());
              pst.setString(8,txt_email.getText());
              pst.setString(9,txt_telefonoC.getText());
              pst.setString(10,txt_estatus.getText());
            
              pst.setString(11,dat);
              
              pst.executeUpdate();
              
              JOptionPane.showMessageDialog(null,"Dato Modificado");
              
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"error  "+e);
       }
   }
    public void Eliminar (){
           qry = "delete from proveedor where ID_PROVEEDOR=?";
           try {
            conn = con.getConnection(); 
            cStmt=conn.prepareCall(qry);
            cStmt.setInt(1,Integer.parseInt(txt_id_proveedor.getText()));
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
         String [] tit = {"ID_PROVEEDOR","EMPRESA","DIRECCION","TELEFONO","NIT","CIUDAD","PAIS","CONTACTO","EMAIL","TELEFONO_CONTACTO","ESTATUS"};
         String [] dato = new String[11];
   DefaultTableModel  modelo = new DefaultTableModel(null, tit);
   String qyl = "select * from proveedor where ID_PROVEEDOR like '%"+res+"%'";
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          
          while(rs.next()){
            dato[0]=rs.getString("ID_PROVEEDOR");
              dato[1]=rs.getString("EMPRESA");
              dato[2]=rs.getString("DIRECCION");
              dato[3]=rs.getString("TELEFONO");
              dato[4]=rs.getString("NIT");
              dato[5]=rs.getString("CIUDAD");
              dato[6]=rs.getString("PAIS");
              dato[7]=rs.getString("CONTACTO");
              dato[8]=rs.getString("EMAIL");
               dato[9]=rs.getString("TELEFONO_CONTACTO");
              dato[10]=rs.getString("ESTATUS");
              
              modelo.addRow(dato);
              tabla_proveedor.setModel(modelo); 
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
     }
          
   public void Limpiar(){
   txt_id_proveedor.setText(null);
   txt_empresa.setText(null);
   txt_direccion.setText(null);
   txt_telefono.setText(null);
   txt_nit.setText(null);
   txt_ciudad.setText(null);
   txt_pais.setText(null);
   txt_contacto.setText(null);
   txt_email.setText(null);
   txt_telefonoC.setText(null);
   txt_estatus.setText(null);
   
          }
    public Proveedores() {
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_id_proveedor = new javax.swing.JTextField();
        txt_empresa = new javax.swing.JTextField();
        txt_estatus = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_nit = new javax.swing.JTextField();
        txt_ciudad = new javax.swing.JTextField();
        txt_pais = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_telefonoC = new javax.swing.JTextField();
        txt_contacto = new javax.swing.JTextField();
        btn_salir = new javax.swing.JButton();
        btn_insertar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_proveedor = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Proveedores");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ESTATUS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 60, 20));

        jLabel2.setText("ID PROVEEDOR");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 20));

        jLabel3.setText("EMPRESA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        jLabel4.setText("DIRECCION");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 70, 20));

        jLabel5.setText("TELEFONO");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        jLabel6.setText("NIT");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 40, -1));

        jLabel7.setText("CIUDAD");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 90, 20));

        jLabel8.setText("PAIS");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 40, 20));

        jLabel9.setText("CONTACTO");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 70, 20));

        jLabel10.setText("EMAIL");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 40, 20));

        jLabel11.setText("TELEFONO CONTACTO");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 140, 20));

        txt_id_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_proveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txt_id_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 140, 30));

        txt_empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_empresaKeyTyped(evt);
            }
        });
        jPanel1.add(txt_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 140, 30));

        txt_estatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_estatusKeyTyped(evt);
            }
        });
        jPanel1.add(txt_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 140, 30));

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });
        jPanel1.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 140, 30));

        txt_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefonoActionPerformed(evt);
            }
        });
        txt_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 140, 30));

        txt_nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nitKeyTyped(evt);
            }
        });
        jPanel1.add(txt_nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 140, 30));

        txt_ciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ciudadKeyTyped(evt);
            }
        });
        jPanel1.add(txt_ciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 140, 30));

        txt_pais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_paisKeyTyped(evt);
            }
        });
        jPanel1.add(txt_pais, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 140, 30));
        jPanel1.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 140, 30));

        txt_telefonoC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoCKeyTyped(evt);
            }
        });
        jPanel1.add(txt_telefonoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 140, 30));
        jPanel1.add(txt_contacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 140, -1));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel1.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, -1));

        btn_insertar.setText("Insertar");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        jLabel13.setText("Seleccione la fila para modificar");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jTabbedPane1.addTab("Formulario", jPanel1);

        tabla_proveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_proveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_proveedor);

        jLabel12.setText("Buscar");

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Tabla", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        // TODO add your handling code here:
       if (!"".equals(txt_id_proveedor.getText())||!"".equals(txt_empresa.getText()) ||!"".equals(txt_direccion.getText())||!"".equals(txt_telefono.getText())
           ||!"".equals(txt_contacto.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_ciudad.getText())||!"".equals(txt_pais.getText())
                ||!"".equals(txt_email.getText())||!"".equals(txt_telefonoC.getText())||!"".equals(txt_estatus.getText())){
                  Insertar();
                Limpiar();
                 Mostrar();
                 
        }else{
        JOptionPane.showMessageDialog(null,"Inserte Datos");
        }
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
         if (!"".equals(txt_id_proveedor.getText())||!"".equals(txt_empresa.getText()) ||!"".equals(txt_direccion.getText())||!"".equals(txt_telefono.getText())
           ||!"".equals(txt_contacto.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_ciudad.getText())||!"".equals(txt_pais.getText())
                ||!"".equals(txt_email.getText())||!"".equals(txt_telefonoC.getText())||!"".equals(txt_estatus.getText())){
                 Modificar();
                 Limpiar();
                 Mostrar();
                 btn_insertar.setEnabled(true);
              btn_eliminar.setEnabled(true);
        }else{
        JOptionPane.showMessageDialog(null,"Seleccione la fila que desea Modificar");
        }
       
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txt_id_proveedor.getText())||!"".equals(txt_empresa.getText()) ||!"".equals(txt_direccion.getText())||!"".equals(txt_telefono.getText())
           ||!"".equals(txt_contacto.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_ciudad.getText())||!"".equals(txt_pais.getText())
                ||!"".equals(txt_email.getText())||!"".equals(txt_telefonoC.getText())||!"".equals(txt_estatus.getText())){
                Eliminar();
                 Limpiar();
                 Mostrar();

        }else{
        JOptionPane.showMessageDialog(null,"Ingrese id_proveedor del registro que desea eliminar");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarKeyPressed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
             buscar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void tabla_proveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_proveedorMouseClicked

        // TODO add your handling code here:
        int fila = tabla_proveedor.rowAtPoint(evt.getPoint());
        
        txt_id_proveedor.setText(tabla_proveedor.getValueAt(fila, 0).toString());
        txt_empresa.setText(tabla_proveedor.getValueAt(fila, 1).toString());
        txt_direccion.setText(tabla_proveedor.getValueAt(fila,2).toString());
        txt_telefono.setText(tabla_proveedor.getValueAt(fila, 3).toString());
        txt_nit.setText(tabla_proveedor.getValueAt(fila, 4).toString());
        txt_ciudad.setText(tabla_proveedor.getValueAt(fila, 5).toString());
        txt_pais.setText(tabla_proveedor.getValueAt(fila, 6).toString());
        txt_contacto.setText(tabla_proveedor.getValueAt(fila, 7).toString());
        txt_email.setText(tabla_proveedor.getValueAt(fila, 8).toString());
        txt_telefonoC.setText(tabla_proveedor.getValueAt(fila, 9).toString());
        txt_estatus.setText(tabla_proveedor.getValueAt(fila, 10).toString());
        
         btn_insertar.setEnabled(false);
         btn_eliminar.setEnabled(false);
    }//GEN-LAST:event_tabla_proveedorMouseClicked

    private void txt_id_proveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_proveedorKeyTyped
        // TODO add your handling code here:
           char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_id_proveedorKeyTyped

    private void txt_empresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_empresaKeyTyped
        // TODO add your handling code here:
            char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_empresaKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
        // TODO add your handling code here:
            char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_direccionKeyTyped

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoActionPerformed

    private void txt_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoKeyTyped
        // TODO add your handling code here:
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_telefonoKeyTyped

    private void txt_nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nitKeyTyped
        // TODO add your handling code here:
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_nitKeyTyped

    private void txt_ciudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ciudadKeyTyped
        // TODO add your handling code here:
        char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_ciudadKeyTyped

    private void txt_paisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_paisKeyTyped
        // TODO add your handling code here:
        char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_paisKeyTyped

    private void txt_telefonoCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoCKeyTyped
        // TODO add your handling code here:
        
   char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }


    }//GEN-LAST:event_txt_telefonoCKeyTyped

    private void txt_estatusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_estatusKeyTyped
        // TODO add your handling code here:
        
   char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }


    }//GEN-LAST:event_txt_estatusKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla_proveedor;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_ciudad;
    private javax.swing.JTextField txt_contacto;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_empresa;
    private javax.swing.JTextField txt_estatus;
    private javax.swing.JTextField txt_id_proveedor;
    private javax.swing.JTextField txt_nit;
    private javax.swing.JTextField txt_pais;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_telefonoC;
    // End of variables declaration//GEN-END:variables
}
