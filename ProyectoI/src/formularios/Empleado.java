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
public class Empleado extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    
        Connection conn = null;
        Statement stmt = null;
        String qry = null;
        PreparedStatement pst =null;
        CallableStatement cStmt = null;
        int resul;
         
        int fila;
        
       public void Mostrar()  {
         String [] tit = {"ID_EMPLEADO","PRIMER_NOMBRE","SEGUNDO_NOMBRE","PRIMER_APELLIDO","SEGUNDO_APELLIDO","NIT","SALARIO","ESTATUS","ID_DEPARTAMENTO"};
         String [] dato = new String[10];
         DefaultTableModel  modelo = new DefaultTableModel(null, tit);
          String qyl = "select * from EMPLEADO order by ID_EMPLEADO"; 
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          while(rs.next()){
              dato[0]=rs.getString("ID_EMPLEADO");
              dato[1]=rs.getString("PRIMER_NOMBRE");
              dato[2]=rs.getString("SEGUNDO_NOMBRE");
              dato[3]=rs.getString("PRIMER_APELLIDO");
              dato[4]=rs.getString("SEGUNDO_APELLIDO");
              dato[5]=rs.getString("NIT");
              dato[6]=rs.getString("SALARIO");
              dato[7]=rs.getString("ESTATUS");
              dato[8]=rs.getString("ID_DEPARTAMENTO");
              modelo.addRow(dato);
             
              tabla_empleado.setModel(modelo);
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
    }
   public void Insertar(){
      String insert =  "INSERT INTO EMPLEADO VALUES (?,?,?,?,?,?,?,?,?)";
              
              try { 
              conn = con.getConnection();
              pst =conn.prepareStatement(insert);
              
              pst.setInt(1,Integer.parseInt(txt_id_empleado.getText()));
              pst.setString(2,txt_nombre1.getText());
              pst.setString(3,txt_nombre2.getText());
              pst.setString(4,txt_apellido1.getText());
              pst.setString(5,txt_apellido2.getText());
              pst.setString(6,txt_nit.getText());
              pst.setString(7,txt_salario.getText());
              pst.setString(8,txt_estatus.getText());
               pst.setString(9,txt_id_departamento.getText());
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
        qry = "UPDATE EMPLEADO SET PRIMER_NOMBRE=?,SEGUNDO_NOMBRE=?, PRIMER_APELLIDO=?,SEGUNDO_APELLIDO=?, NIT=?, SALARIO=?,ESTATUS=?,ID_DEPARTAMENTO=? "
                +"WHERE ID_EMPLEADO=?";  
           fila=tabla_empleado.getSelectedRow();
          String dat = (String)tabla_empleado.getValueAt(fila,0);
           
              conn = con.getConnection();
              pst =conn.prepareStatement(qry);
               pst.setString(1,txt_nombre1.getText());  
              pst.setString(2,txt_nombre2.getText());
               pst.setString(3,txt_apellido1.getText());
              pst.setString(4,txt_apellido2.getText());
              pst.setString(5,txt_nit.getText());
              pst.setString(6,txt_salario.getText());
              pst.setString(7,txt_estatus.getText());
              pst.setString(8,txt_id_departamento.getText());          

              pst.setString(9,dat);
              pst.execute();
              
              JOptionPane.showMessageDialog(null,"Dato Modificado");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"error"+e);
       }
   }
    
   public void Eliminar (){
           qry = "delete from empleado where ID_EMPLEADO=?";
           try {
            conn = con.getConnection(); 
            cStmt=conn.prepareCall(qry);
            cStmt.setInt(1,Integer.parseInt(txt_id_empleado.getText()));
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
         String [] tit = {"ID_EMPLEADO","NOMBRE","APELLIDO","NIT","SALARIO","ESTATUS","ID_DEPARTAMENTO"};
         String [] dato = new String[9];
         DefaultTableModel  modelo = new DefaultTableModel(null, tit);
         String qyl = "select * from EMPLEADO where ID_EMPLEADO like '%"+res+"%'";
            try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          while(rs.next()){
              dato[0]=rs.getString("ID_EMPLEADO");
              dato[1]=rs.getString("PRIMER_NOMBRE");
              dato[2]=rs.getString("SEGUNDO_NOMBRE");
              dato[3]=rs.getString("PRIMER_APELLIDO");
              dato[4]=rs.getString("SEGUNDO_APELLIDO");
              dato[5]=rs.getString("NIT");
              dato[6]=rs.getString("SALARIO");
              dato[7]=rs.getString("ESTATUS");
              dato[8]=rs.getString("ID_DEPARTAMENTO");
              modelo.addRow(dato);
              tabla_empleado.setModel(modelo); 
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
   }
   public Void Limpiar(){  
   txt_id_empleado.setText(null);
   txt_nombre1.setText(null);
   txt_nombre2.setText(null);
   txt_apellido1.setText(null);
   txt_apellido2.setText(null);
   txt_nit.setText(null);
   txt_salario.setText(null);
   txt_estatus.setText(null);
   txt_id_departamento.setText(null);
        return null;
   }
    public Empleado() {
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id_departamento = new javax.swing.JTextField();
        txt_id_empleado = new javax.swing.JTextField();
        txt_nombre2 = new javax.swing.JTextField();
        txt_apellido2 = new javax.swing.JTextField();
        txt_nit = new javax.swing.JTextField();
        txt_salario = new javax.swing.JTextField();
        btn_insertar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_estatus = new javax.swing.JTextField();
        txt_nombre1 = new javax.swing.JTextField();
        txt_apellido1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_empleado = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Empleado");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID_DEPARTAMENTO");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        jLabel2.setText("ID EMPLEADO");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setText("SEGUNDO NOMBRE");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setText("SEGUNDO APELLIDO");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        jLabel5.setText("NIT");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel6.setText("SALARIO");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        txt_id_departamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_departamentoKeyTyped(evt);
            }
        });
        jPanel2.add(txt_id_departamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 140, -1));

        txt_id_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_empleadoKeyTyped(evt);
            }
        });
        jPanel2.add(txt_id_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 140, -1));

        txt_nombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre2KeyTyped(evt);
            }
        });
        jPanel2.add(txt_nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 140, -1));

        txt_apellido2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellido2ActionPerformed(evt);
            }
        });
        txt_apellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellido2KeyTyped(evt);
            }
        });
        jPanel2.add(txt_apellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 140, -1));

        txt_nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nitKeyTyped(evt);
            }
        });
        jPanel2.add(txt_nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 140, -1));

        txt_salario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_salarioKeyTyped(evt);
            }
        });
        jPanel2.add(txt_salario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 140, -1));

        btn_insertar.setText("Agregar");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_insertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, 30));

        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel2.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, -1));

        jLabel8.setText("ESTATUS");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        txt_estatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_estatusKeyTyped(evt);
            }
        });
        jPanel2.add(txt_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 130, -1));

        txt_nombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre1KeyTyped(evt);
            }
        });
        jPanel2.add(txt_nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 140, -1));

        txt_apellido1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellido1KeyTyped(evt);
            }
        });
        jPanel2.add(txt_apellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 140, -1));

        jLabel9.setText("PRIMER NOMBRE");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel10.setText("PRIMER APELLIDO");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel12.setText("Seleccione la fila para modificar");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jTabbedPane1.addTab("Formulario", jPanel2);

        tabla_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_empleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_empleado);

        jLabel7.setText("Buscar");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tabla", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 320));

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void tabla_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_empleadoMouseClicked
        // TODO add your handling code here:
        
        int fila = tabla_empleado.rowAtPoint(evt.getPoint());
        
        txt_id_empleado.setText(tabla_empleado.getValueAt(fila, 0).toString());
         txt_nombre1.setText(tabla_empleado.getValueAt(fila, 1).toString());
        txt_nombre2.setText(tabla_empleado.getValueAt(fila, 2).toString());
        txt_apellido1.setText(tabla_empleado.getValueAt(fila,3).toString());
        txt_apellido2.setText(tabla_empleado.getValueAt(fila,4).toString());
        txt_nit.setText(tabla_empleado.getValueAt(fila, 5).toString());
        txt_salario.setText(tabla_empleado.getValueAt(fila, 6).toString());
        txt_estatus.setText(tabla_empleado.getValueAt(fila, 7).toString());
        txt_id_departamento.setText(tabla_empleado.getValueAt(fila, 8).toString());
          
        
        btn_insertar.setEnabled(false);
         btn_eliminar.setEnabled(false);
         
    }//GEN-LAST:event_tabla_empleadoMouseClicked

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        // TODO add your handling code here:
        
        buscar(txt_buscar.getText());
        
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        // TODO add your handling code here:
           if (!"".equals(txt_id_empleado.getText())||!"".equals(txt_nombre1.getText()) ||!"".equals(txt_nombre2.getText())
           ||!"".equals(txt_apellido1.getText())||!"".equals(txt_apellido2.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_salario.getText())
                ||!"".equals(txt_estatus.getText())||!"".equals(txt_id_departamento.getText())){
                  Insertar();
                Limpiar();
                 Mostrar();
                 
        }else{
        JOptionPane.showMessageDialog(null,"Inserte Datos");
        }
       
     
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
                    // TODO add your handling code here:
            if (!"".equals(txt_id_empleado.getText())||!"".equals(txt_nombre1.getText()) ||!"".equals(txt_nombre2.getText())
           ||!"".equals(txt_apellido1.getText())||!"".equals(txt_apellido2.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_salario.getText())
                ||!"".equals(txt_estatus.getText())||!"".equals(txt_id_departamento.getText())){
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
        if (!"".equals(txt_id_empleado.getText())||!"".equals(txt_nombre1.getText()) ||!"".equals(txt_nombre2.getText())
           ||!"".equals(txt_apellido1.getText())||!"".equals(txt_apellido2.getText())||!"".equals(txt_nit.getText())||!"".equals(txt_salario.getText())
                ||!"".equals(txt_estatus.getText())||!"".equals(txt_id_departamento.getText())){
                  Eliminar();
                Limpiar();
                 Mostrar();
                 
        }else{
        JOptionPane.showMessageDialog(null,"Ingre id_empleado que desea eliminar");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_id_empleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_empleadoKeyTyped
          char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_id_empleadoKeyTyped

    private void txt_nombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre1KeyTyped
        // TODO add your handling code here:
         char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_nombre1KeyTyped

    private void txt_nombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre2KeyTyped
        // TODO add your handling code here:
         char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_nombre2KeyTyped

    private void txt_apellido1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido1KeyTyped
        // TODO add your handling code here:
         char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_apellido1KeyTyped

    private void txt_apellido2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellido2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellido2ActionPerformed

    private void txt_apellido2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido2KeyTyped
        // TODO add your handling code here:
         char letra=evt.getKeyChar();
        if (!Character.isLetter(letra))
        {
        evt.consume();
        }
    }//GEN-LAST:event_txt_apellido2KeyTyped

    private void txt_nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nitKeyTyped
        // TODO add your handling code here:
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_nitKeyTyped

    private void txt_salarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_salarioKeyTyped
        // TODO add your handling code here:
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_salarioKeyTyped

    private void txt_estatusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_estatusKeyTyped
        // TODO add your handling code here:
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_estatusKeyTyped

    private void txt_id_departamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_departamentoKeyTyped
        // TODO add your handling code here:
         char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }

    }//GEN-LAST:event_txt_id_departamentoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla_empleado;
    private javax.swing.JTextField txt_apellido1;
    private javax.swing.JTextField txt_apellido2;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_estatus;
    private javax.swing.JTextField txt_id_departamento;
    private javax.swing.JTextField txt_id_empleado;
    private javax.swing.JTextField txt_nit;
    private javax.swing.JTextField txt_nombre1;
    private javax.swing.JTextField txt_nombre2;
    private javax.swing.JTextField txt_salario;
    // End of variables declaration//GEN-END:variables
}
