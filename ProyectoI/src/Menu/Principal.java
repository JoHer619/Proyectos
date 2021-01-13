/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;
import Loggin.Login;
import formularios.Clientes;
import formularios.Departamento;
import formularios.Empleado;
import formularios.Productos;
import formularios.Proveedores;
import formularios.Reporte_Facturas;
import formularios.Usuario;

/**
 *
 * @author LENOVO
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jdp_escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cambiar_usuario = new javax.swing.JCheckBoxMenuItem();
        Formularios = new javax.swing.JMenu();
        jmUsuario = new javax.swing.JMenuItem();
        jm_empleado = new javax.swing.JMenuItem();
        jm_cliente = new javax.swing.JMenuItem();
        jm_productos = new javax.swing.JMenuItem();
        jm_proveedores = new javax.swing.JMenuItem();
        jm_departamento = new javax.swing.JMenuItem();
        jm_verFac = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jm_salir = new javax.swing.JCheckBoxMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jdp_escritorioLayout = new javax.swing.GroupLayout(jdp_escritorio);
        jdp_escritorio.setLayout(jdp_escritorioLayout);
        jdp_escritorioLayout.setHorizontalGroup(
            jdp_escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );
        jdp_escritorioLayout.setVerticalGroup(
            jdp_escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivos ");

        cambiar_usuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        cambiar_usuario.setSelected(true);
        cambiar_usuario.setText("Cambiar Usuario");
        cambiar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiar_usuarioActionPerformed(evt);
            }
        });
        jMenu1.add(cambiar_usuario);

        jMenuBar1.add(jMenu1);

        Formularios.setText("Mantenimiento");
        Formularios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormulariosActionPerformed(evt);
            }
        });

        jmUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jmUsuario.setText("Usuario");
        jmUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUsuarioActionPerformed(evt);
            }
        });
        Formularios.add(jmUsuario);

        jm_empleado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jm_empleado.setText("Empleado");
        jm_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_empleadoActionPerformed(evt);
            }
        });
        Formularios.add(jm_empleado);

        jm_cliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jm_cliente.setText("Cliente");
        jm_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_clienteActionPerformed(evt);
            }
        });
        Formularios.add(jm_cliente);

        jm_productos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jm_productos.setText("Productos");
        jm_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_productosActionPerformed(evt);
            }
        });
        Formularios.add(jm_productos);

        jm_proveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jm_proveedores.setText("Proveedores");
        jm_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_proveedoresActionPerformed(evt);
            }
        });
        Formularios.add(jm_proveedores);

        jm_departamento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jm_departamento.setText("Departamento");
        jm_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_departamentoActionPerformed(evt);
            }
        });
        Formularios.add(jm_departamento);

        jMenuBar1.add(Formularios);

        jm_verFac.setText("Reporte de Facturas");

        jCheckBoxMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Ver Facturas");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jm_verFac.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jm_verFac);

        jMenu3.setText("Ayuda");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Salir");

        jm_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jm_salir.setSelected(true);
        jm_salir.setText("Salir");
        jm_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_salirActionPerformed(evt);
            }
        });
        jMenu4.add(jm_salir);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp_escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp_escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FormulariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormulariosActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_FormulariosActionPerformed

    private void jmUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUsuarioActionPerformed
        
        Usuario verU = new Usuario();
        jdp_escritorio.add(verU);
        verU.show();
    }//GEN-LAST:event_jmUsuarioActionPerformed

    private void jm_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_empleadoActionPerformed
        // TODO add your handling code here:
        Empleado verE = new Empleado();
        jdp_escritorio.add(verE);
        verE.show();
    }//GEN-LAST:event_jm_empleadoActionPerformed

    private void jm_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_clienteActionPerformed
        // TODO add your handling code here:
        
     Clientes cl = new Clientes();
     jdp_escritorio.add(cl);
     cl.show();
    }//GEN-LAST:event_jm_clienteActionPerformed

    private void jm_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_departamentoActionPerformed
        // TODO add your handling code here:
        Departamento dep = new Departamento();
        jdp_escritorio.add(dep);
        dep.show();
    }//GEN-LAST:event_jm_departamentoActionPerformed

    private void jm_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_proveedoresActionPerformed
        // TODO add your handling code here:
        Proveedores pro = new Proveedores();
        jdp_escritorio.add(pro);
        pro.show();
    }//GEN-LAST:event_jm_proveedoresActionPerformed

    private void jm_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_productosActionPerformed
        // TODO add your handling code here:
        Productos prod = new Productos();
        jdp_escritorio.add(prod);
        prod.show();
    }//GEN-LAST:event_jm_productosActionPerformed

    private void cambiar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiar_usuarioActionPerformed
       Login lo = new Login();
       lo.setVisible(true);
       this.dispose();
       
    }//GEN-LAST:event_cambiar_usuarioActionPerformed

    private void jm_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jm_salirActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
       Reporte_Facturas fac = new Reporte_Facturas();
       jdp_escritorio.add(fac);
       fac.show();
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Formularios;
    private javax.swing.JCheckBoxMenuItem cambiar_usuario;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JDesktopPane jdp_escritorio;
    private javax.swing.JMenuItem jmUsuario;
    private javax.swing.JMenuItem jm_cliente;
    private javax.swing.JMenuItem jm_departamento;
    private javax.swing.JMenuItem jm_empleado;
    private javax.swing.JMenuItem jm_productos;
    private javax.swing.JMenuItem jm_proveedores;
    private javax.swing.JCheckBoxMenuItem jm_salir;
    private javax.swing.JMenu jm_verFac;
    // End of variables declaration//GEN-END:variables
}
