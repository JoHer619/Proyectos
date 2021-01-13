package formularios;

import Conexion.Conexion;
import Conexion.sql;
import DAO.PdfDAO;
import Tabla.Tabla_PdfVO;
import VO.PdfVO;
import Visual.MostrarPDF;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import Loggin.Login;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistroVentas extends javax.swing.JInternalFrame implements Printable {

    DefaultTableModel m;
    static double total;
    String sub_total;
    String igv;

    DefaultListModel modelEnca;
    DefaultListModel modelVentas;
    DefaultListModel modelTotal;

    DecimalFormat df = new DecimalFormat("0.00");

    String codigo, descripcion, precio, cant, importe;

    ArrayList<ventas> lista1 = new ArrayList<>();

    Conexion con = new Conexion();

    public RegistroVentas() {
        initComponents();

        total = 0;

        txt_titulo.setOpaque(true);
        txt_titulo.setBackground(Color.black);
        MostrarProducto();
        MostrarCliente();

        modelEnca = new DefaultListModel();
        modelVentas = new DefaultListModel();
        modelTotal = new DefaultListModel();
        lst_encabezado.setModel(modelEnca);
        listaVentas.setModel(modelVentas);
        lst_total.setModel(modelTotal);
       txt_caja.setText(Login.txt_usuario.getText());
       txt_subtotal.setEnabled(false);
       txt_igv.setEnabled(false);
       txt_total.setEnabled(false);
       txt_caja.setEnabled(false);
    }

    public void agregarEnca() {
        String fechaHora = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        fechaHora = dateFormat.format(date);
        modelEnca.addElement("                             FACTURA -" + codigo1);
        modelEnca.addElement("                  Fecha Emision: " + fechaHora);
        modelEnca.addElement("                     TIENDA LA EZPERANZA ");
        modelEnca.addElement("                  QUINTA AVENIDA 2-55 ZONA 1 ");
        modelEnca.addElement("                      SALAMA BAJA VERAPAZ ");
        modelEnca.addElement("NIT: " + txt_idCliente.getText());

        modelEnca.addElement(" Nombre:  " + txt_nombre.getText());
        modelEnca.addElement(" Apellido  :  " + txt_apellido.getText());
        modelEnca.addElement("                 ");
        modelEnca.addElement(" Cantidad            Descripcion " + "          Precio          importe ");

    }

    public void AgregarVentas() {
        int i;

        for (i = 0; i < lista1.size(); i++) {

            modelVentas.addElement("   " + lista1.get(i).getCantidad() + "                       " + lista1.get(i).getDescripcion()
                    + "                  " + lista1.get(i).getPrecio() + "                " + lista1.get(i).getImporte());

        }

    }

    public void AgregarTotal() {

        modelTotal.addElement("                                                                Sub_total:  Q " + txt_subtotal.getText());
        modelTotal.addElement("                                                                      IVA:  Q " + txt_igv.getText());
        modelTotal.addElement("                                                                     Total: Q " + txt_total.getText());
        modelTotal.addElement("               _____________________________________ ");
         modelTotal.addElement("                                                               Atendido Por:  "+Login.txt_usuario.getText());
     //   modelTotal.addElement("                                    Atendido por:  " + txt_caja.getText());

    }

    public void MostrarProducto() {
        String[] tit = {"ID_PRODUCTO", "DESCRIPCION", "PRECIO_UNITARIO"};
        String[] dato = new String[3];
        DefaultTableModel modelo = new DefaultTableModel(null, tit);
        String qyl = "select ID_PRODUCTO , DESCRIPCION , PRECIO_UNITARIO from PRODUCTO ORDER BY ID_PRODUCTO";
        try {
            Connection cn = con.getConnection();
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(qyl);
            while (rs.next()) {
                dato[0] = rs.getString("ID_PRODUCTO");
                dato[1] = rs.getString("DESCRIPCION");
                dato[2] = rs.getString("PRECIO_UNITARIO");

                modelo.addRow(dato);

                tabla_produ.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
        }
    }

    public void MostrarCliente() {
        String[] tit = {"NIT", "NOMBRE", "APELLIDO", "DIRECCION", "EMAIL"};
        String[] dato = new String[5];
        DefaultTableModel modelo = new DefaultTableModel(null, tit);
        String qyl = "select NIT, NOMBRE, APELLIDO, DIRECCION, EMAIL from CLIENTE ORDER BY ID_CLIENTE";
        try {
            Connection cn = con.getConnection();
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(qyl);
            while (rs.next()) {
                dato[0] = rs.getString("NIT");
                dato[1] = rs.getString("NOMBRE");
                dato[2] = rs.getString("APELLIDO");
                dato[3] = rs.getString("DIRECCION");
                dato[4] = rs.getString("EMAIL");

                modelo.addRow(dato);

                tabla_cliente.setModel(modelo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
        }
    }
public void buscar (String res){
         String [] tit = {"ID_PRODUCTO","DESCRIPCION","PRECIO_UNITARIO"};
         String [] dato = new String[3];
   DefaultTableModel  modelo = new DefaultTableModel(null, tit);
   String qyl = "select ID_PRODUCTO , DESCRIPCION , PRECIO_UNITARIO from PRODUCTO  WHERE  DESCRIPCION like '%"+res+"%'";
       try {
          Connection cn = con.getConnection(); 
          Statement stm = cn.createStatement();
          ResultSet rs = stm.executeQuery(qyl);
          
          while(rs.next()){
               dato[0] = rs.getString("ID_PRODUCTO");
                dato[1] = rs.getString("DESCRIPCION");
                dato[2] = rs.getString("PRECIO_UNITARIO");

                modelo.addRow(dato);

                tabla_produ.setModel(modelo);
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "error"+e.getMessage());
       }
   
   }
    public void regisVentas() {
        int filsel = tabla_produ.getSelectedRow();
        try {
            Double calcula = 0.0, x = 0.0, igvs = 0.0;
            int canti = 0;
            if (filsel == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un producto ", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                m = (DefaultTableModel) tabla_produ.getModel();
                codigo = tabla_produ.getValueAt(filsel, 0).toString();
                descripcion = tabla_produ.getValueAt(filsel, 1).toString();
                precio = tabla_produ.getValueAt(filsel, 2).toString();
                cant = txt_cantidad.getText();

                x = (Double.parseDouble(precio) * Integer.parseInt(cant));
                importe = String.valueOf(x);

                m = (DefaultTableModel) tabla_ventas.getModel();
                String filaelemento[] = {cant, descripcion, precio, importe};
                m.addRow(filaelemento);

                calcula = (Double.parseDouble(importe));
                total = total + calcula;
                igvs = total * 0.12;
                igv = df.format(igvs);

                sub_total = df.format((total - igvs));

                txt_total.setText("" + total);
                txt_subtotal.setText("" + sub_total);
                txt_igv.setText("" + igv);

                ventas Ven = new ventas(cant, descripcion, precio, importe);

                lista1.add(Ven);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:  ", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }
    int codigo1;
    String ruta;

    public void abrirPdf() {
        //int codigo1;
        codigo1 = s.auto_increment("SELECT MAX (codigopdf) FROM pdf");

        ruta = "C:\\Users\\Joel\\Pictures\\Facturas\\prueba" + codigo1 + ".pdf";
        String fechaHora = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        fechaHora = dateFormat.format(date);
        try {
            FileOutputStream archivo = new FileOutputStream(ruta);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Paragraph para = new Paragraph("                                                  FACTURA", FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLD, BaseColor.BLACK));
            doc.add(para);
            Paragraph para13 = new Paragraph("                                                         Numero de serie:  Fac-" + codigo1, FontFactory.getFont(FontFactory.defaultEncoding, 13, Font.BOLD, BaseColor.BLACK));
            doc.add(para13);
            Paragraph para9 = new Paragraph("                                        Fecha:  " + fechaHora, FontFactory.getFont(FontFactory.COURIER, 13, Font.BOLD, BaseColor.BLACK));
            doc.add(para9);
            Paragraph para10 = new Paragraph("                                                     ");
            doc.add(para10);
            Paragraph para11 = new Paragraph("                                             TIEDA NOM_TIENDA ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK));
            doc.add(para11);
            Paragraph para12 = new Paragraph("                                        QUINTA AVENIDA 2-55 ZONA 1 ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK));
            doc.add(para12);
            Paragraph para14 = new Paragraph("                                           SALAMA BAJA VERAPAZ ", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK));
            doc.add(para14);
            Paragraph para15 = new Paragraph("                                                                                ");
            doc.add(para15);
            Paragraph para16 = new Paragraph("                                                                                ");
            doc.add(para16);

            Paragraph para1 = new Paragraph("  NIT: " + txt_idCliente.getText());
            doc.add(para1);
            Paragraph para2 = new Paragraph("  Nombre: " + txt_nombre.getText() + " " + txt_apellido.getText());
            doc.add(para2);
            Paragraph para3 = new Paragraph("  Direccion: " + txt_direccion.getText());
            doc.add(para3);
            Paragraph para4 = new Paragraph(" CANTIDAD" + "      DESCRIPCION " + "      PRECIO" + "      IMPORTE", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD, BaseColor.BLACK));
            doc.add(para4);

            int i;

            for (i = 0; i < lista1.size(); i++) {
                Paragraph para5 = new Paragraph(" " + lista1.get(i).getCantidad() + "                              " + lista1.get(i).getDescripcion()
                        + "                       " + lista1.get(i).getPrecio() + "                          " + lista1.get(i).getImporte());
                doc.add(para5);
            }
            Paragraph para17 = new Paragraph("                                                                                ");
            doc.add(para17);
            Paragraph para6 = new Paragraph("                                   Sub_Total: Q " + txt_subtotal.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD, BaseColor.BLACK));
            doc.add(para6);
            Paragraph para7 = new Paragraph("                                         IVA: Q " + txt_igv.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD, BaseColor.BLACK));
            doc.add(para7);
            Paragraph para8 = new Paragraph("                                        Total: Q " + txt_total.getText(), FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD, BaseColor.BLACK));
            doc.add(para8);
            Paragraph para18 = new Paragraph("                                                                                ");
            doc.add(para18);
              Paragraph para20 = new Paragraph("  Atendido por: "+ txt_caja.getText());
            doc.add(para20);
           
       
            Paragraph para19 = new Paragraph("                             GRACIAS POR SU COMPRA                                  ");
            doc.add(para19);
            doc.close();

            JOptionPane.showMessageDialog(null, "Pdf correctamente creado");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
//         try {
//                    
//                          ProcessBuilder p =  new ProcessBuilder();
//                        p.command("cmd.exe","/c",ruta);
//			 p.start();
//
//  	  } catch (Exception ex) {
//		ex.printStackTrace();
//	  }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Buscar_Pro = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_produ = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        btn_comprar = new javax.swing.JButton();
        Bus_cliente = new javax.swing.JDialog();
        panel1 = new java.awt.Panel();
        jLabel7 = new javax.swing.JLabel();
        txt_busCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_cliente = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        JD_factur = new javax.swing.JDialog();
        jp_imprimir = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lst_encabezado = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        lst_total = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        listaVentas = new javax.swing.JList();
        btn_imprimir = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        JD_mostrarPDF = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        txtCorreo = new javax.swing.JTextField();
        txtAsunto = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        CB_enviar = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        txt_idCliente = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_titulo = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        txt_igv = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_caja = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btn_nuevaVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_ventas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_nombre1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_direccion1 = new javax.swing.JTextField();
        txt_idCliente1 = new javax.swing.JTextField();
        txt_apellido1 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla_ventas1 = new javax.swing.JTable();
        txt_titulo1 = new javax.swing.JLabel();
        btn_agregar1 = new javax.swing.JButton();
        btn_eliminar1 = new javax.swing.JButton();
        btn_cancelar1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_total1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel8.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 221, -1));

        jLabel13.setText("Buscar:");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jScrollPane3.setViewportView(tabla_produ);

        jPanel8.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 630, 170));

        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, -1, -1));

        jLabel14.setText("Cantidad:");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 16, 70, 20));

        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyTyped(evt);
            }
        });
        jPanel8.add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 180, -1));

        btn_comprar.setText("COMPRAR");
        btn_comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_comprarActionPerformed(evt);
            }
        });
        jPanel8.add(btn_comprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        javax.swing.GroupLayout Buscar_ProLayout = new javax.swing.GroupLayout(Buscar_Pro.getContentPane());
        Buscar_Pro.getContentPane().setLayout(Buscar_ProLayout);
        Buscar_ProLayout.setHorizontalGroup(
            Buscar_ProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Buscar_ProLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        Buscar_ProLayout.setVerticalGroup(
            Buscar_ProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Buscar_ProLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Buscar");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 22, -1, -1));

        txt_busCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busClienteActionPerformed(evt);
            }
        });
        panel1.add(txt_busCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 300, -1));

        jScrollPane2.setViewportView(tabla_cliente);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 480, 170));

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, -1, -1));

        jButton4.setText("Agregar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        javax.swing.GroupLayout Bus_clienteLayout = new javax.swing.GroupLayout(Bus_cliente.getContentPane());
        Bus_cliente.getContentPane().setLayout(Bus_clienteLayout);
        Bus_clienteLayout.setHorizontalGroup(
            Bus_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Bus_clienteLayout.setVerticalGroup(
            Bus_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Bus_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
        );

        JD_factur.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_imprimir.setForeground(new java.awt.Color(255, 255, 255));
        jp_imprimir.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setViewportView(lst_encabezado);

        jp_imprimir.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 210));

        jScrollPane6.setViewportView(lst_total);

        jp_imprimir.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 340, 130));

        jScrollPane7.setViewportView(listaVentas);

        jp_imprimir.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 340, 250));

        JD_factur.getContentPane().add(jp_imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 590));

        btn_imprimir.setText("Imprimir");
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });
        JD_factur.getContentPane().add(btn_imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 610, -1, -1));

        btn_salir.setText("Salir");
        JD_factur.getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, -1, -1));

        jButton5.setText("Enviar Por correo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtMensaje.setColumns(20);
        txtMensaje.setRows(5);
        jScrollPane9.setViewportView(txtMensaje);

        CB_enviar.setText("Enviar  por Correo");
        CB_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_enviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CB_enviar)
                        .addGap(53, 53, 53))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton5)
                        .addGap(37, 37, 37)))
                .addGap(118, 118, 118)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(CB_enviar)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Archivo"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabla);

        javax.swing.GroupLayout JD_mostrarPDFLayout = new javax.swing.GroupLayout(JD_mostrarPDF.getContentPane());
        JD_mostrarPDF.getContentPane().setLayout(JD_mostrarPDFLayout);
        JD_mostrarPDFLayout.setHorizontalGroup(
            JD_mostrarPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JD_mostrarPDFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JD_mostrarPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE))
                .addContainerGap())
        );
        JD_mostrarPDFLayout.setVerticalGroup(
            JD_mostrarPDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JD_mostrarPDFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(102, 204, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NOMBRE");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txt_nombre.setEnabled(false);
        jPanel2.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 160, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/iconfinder_magnifyingglass_1055031.png"))); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("DIRECCION:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("NIT:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txt_direccion.setEnabled(false);
        jPanel2.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 160, -1));

        txt_idCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idClienteActionPerformed(evt);
            }
        });
        txt_idCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idClienteKeyReleased(evt);
            }
        });
        jPanel2.add(txt_idCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 160, -1));

        txt_apellido.setEnabled(false);
        txt_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoActionPerformed(evt);
            }
        });
        jPanel2.add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 130, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 580, 140));

        txt_titulo.setBackground(new java.awt.Color(0, 0, 0));
        txt_titulo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_titulo.setForeground(new java.awt.Color(255, 255, 255));
        txt_titulo.setText("                                                           DATOS GENERALES");
        jPanel3.add(txt_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 580, -1));

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/iconfinder_sign-add_299068.png"))); // NOI18N
        btn_agregar.setText("AGREGAR");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, -1, -1));

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/iconfinder_icon-90_667351.png"))); // NOI18N
        btn_eliminar.setText("ELIMIMAR");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, -1, -1));

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/iconfinder_Artboard_1_1790660.png"))); // NOI18N
        btn_cancelar.setText("CANCELAR");
        jPanel3.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("TOTAL");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));
        jPanel1.add(txt_subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 70, 30));

        jLabel6.setText("SUB TOTAL");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 90, -1));
        jPanel1.add(txt_igv, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 70, 30));

        jLabel17.setText("IVA");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jLabel18.setText("Atendiendo Por:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));
        jPanel1.add(txt_caja, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 50, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 600, 50));

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/iconfinder_printer_289614.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel3.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/iconfinder_bill_416404.png"))); // NOI18N
        jButton6.setText("GENERAR FACTURA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, 180, -1));

        btn_nuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/iconfinder_cashier_sale_shop_4177567.png"))); // NOI18N
        btn_nuevaVenta.setText("NUEVA VENTA");
        btn_nuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevaVentaActionPerformed(evt);
            }
        });
        jPanel3.add(btn_nuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, -1));

        tabla_ventas.setBackground(new java.awt.Color(255, 255, 255));
        tabla_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Descripcion", "Precio", "Importe"
            }
        ));
        tabla_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ventasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_ventas);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 590, 150));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("                                                       LISTA DE PRODUCTOS");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 590, 20));

        jPanel4.setBackground(new java.awt.Color(51, 255, 51));
        jPanel4.setForeground(new java.awt.Color(153, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Datos Generales");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 123, -1));

        jPanel5.setForeground(new java.awt.Color(102, 204, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("CLIENTE:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));
        jPanel5.add(txt_nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 100, -1));

        jButton7.setText("BUSCAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, -1));

        jLabel10.setText("DIRECCION:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel11.setText("ID_CLIENTE:");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanel5.add(txt_direccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 140, -1));
        jPanel5.add(txt_idCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 100, -1));
        jPanel5.add(txt_apellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 100, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 600, 140));

        tabla_ventas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Descripcion", "Precio", "Importe"
            }
        ));
        jScrollPane8.setViewportView(tabla_ventas1);

        jPanel4.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 600, 130));

        txt_titulo1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_titulo1.setForeground(new java.awt.Color(255, 255, 255));
        txt_titulo1.setText("                                                             LISTA DE PRODUCTOS");
        jPanel4.add(txt_titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 600, -1));

        btn_agregar1.setText("AGREGAR");
        btn_agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar1ActionPerformed(evt);
            }
        });
        jPanel4.add(btn_agregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, -1, -1));

        btn_eliminar1.setText("ELIMIMAR");
        jPanel4.add(btn_eliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, -1, -1));

        btn_cancelar1.setText("CANCELAR");
        jPanel4.add(btn_cancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, -1, -1));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setText("TOTAL");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, -1));

        jLabel15.setText("SUB TOTAL");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        jPanel6.add(txt_total1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 120, -1));

        jLabel16.setText("I.G.V");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 40, -1));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 600, 40));

        jButton8.setText("GRABAR VENTA");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, -1, -1));

        jButton9.setText("GENREAR FACTURA");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, -1, -1));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 411, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 412, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 266, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 265, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        Buscar_Pro.setVisible(true);

        Buscar_Pro.setSize(700, 350);
        Buscar_Pro.setLocation(600, 200);


    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_comprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_comprarActionPerformed
        regisVentas();
   //AgregarVentas();

    }//GEN-LAST:event_btn_comprarActionPerformed

    private void txt_busClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Bus_cliente.setVisible(true);
        Bus_cliente.setSize(700, 350);
        Buscar_Pro.setLocation(600, 200);
    }//GEN-LAST:event_jButton1ActionPerformed
    String Correo;
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int filsel = tabla_cliente.getSelectedRow();
        try {
            String id, nombre, apellido, direccion;

            if (filsel == -1) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un producto ", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                m = (DefaultTableModel) tabla_produ.getModel();
                id = tabla_cliente.getValueAt(filsel, 0).toString();
                nombre = tabla_cliente.getValueAt(filsel, 1).toString();
                apellido = tabla_cliente.getValueAt(filsel, 2).toString();
                direccion = tabla_cliente.getValueAt(filsel, 3).toString();
                Correo = tabla_cliente.getValueAt(filsel, 4).toString();

                txt_idCliente.setText("" + id);
                txt_nombre.setText("" + nombre);
                txt_apellido.setText("" + apellido);
                txt_direccion.setText("" + direccion);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:  ", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void guardar_pdf(int codigo, String nombre, File ruta) {
        PdfDAO pa = new PdfDAO();
        PdfVO po = new PdfVO();
        po.setCodigopdf(codigo);
        po.setNombrepdf(nombre);
        try {
            byte[] pdf = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(pdf);
            po.setArchivopdf(pdf);
        } catch (IOException ex) {
            po.setArchivopdf(null);
            //System.out.println("Error al agregar archivo pdf "+ex.getMessage());
        }
        pa.Agregar_PdfVO(po);
    }
    Tabla_PdfVO tpdf = new Tabla_PdfVO();
    sql s = new sql();

    public void Guardar() {

        int codigo;
        codigo = s.auto_increment("SELECT MAX (codigopdf) FROM pdf");

        String nombre = "Fact " + codigo;
        String ruta_archivo = "C:\\Users\\Joel\\Pictures\\Facturas\\prueba" + codigo + ".pdf";

        File ruta = new File(ruta_archivo);

        if (nombre.trim().length() != 0 && ruta_archivo.trim().length() != 0) {
            guardar_pdf(codigo, nombre, ruta);
            tpdf.visualizar_PdfVO(tabla);
            ruta_archivo = "";

        } else {
            JOptionPane.showMessageDialog(null, "Rellenar todo los campos");
        }

    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        abrirPdf();
        Guardar();
        JD_mostrarPDF.setVisible(true);
        JD_mostrarPDF.setSize(700, 400);
        JD_mostrarPDF.setLocation(600, 300);


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.Buscar_Pro.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        JD_factur.setVisible(true);
        JD_factur.setSize(400,700);
        JD_factur.setLocationRelativeTo(null);
        this.setResizable( false);
        agregarEnca();
        AgregarVentas();
        AgregarTotal();
     //   System.out.println(ob.getNombre());
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btn_agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregar1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.Bus_cliente.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        m = (DefaultTableModel) tabla_ventas.getModel();

        int a = tabla_ventas.getSelectedRow();
        if (a < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila que desea eliminar");
        } else {
            Double im = Double.parseDouble(tabla_ventas.getValueAt(a, 3).toString());

            Double precioActual = Double.parseDouble(txt_total.getText()) - im;
            total = precioActual;
            Double igvs = total * 0.12;
            igv = df.format(igvs);

            sub_total = df.format((total - igvs));

            txt_total.setText("" + total);
            txt_subtotal.setText("" + sub_total);
            txt_igv.setText("" + igv);

            eliminaarArr(datBus);
            eli();
            m.removeRow(a);
        }


    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
        try {
            PrinterJob gap = PrinterJob.getPrinterJob();
            gap.setPrintable(this);
            //gap.print();
            boolean top = gap.printDialog();
            if (top) {

                gap.print();

            }
        } catch (PrinterException pex) {
            JOptionPane.showMessageDialog(null, "error " + pex);
            //  JOptionPane.INFORMATION_MESSAGE
        }
    }//GEN-LAST:event_btn_imprimirActionPerformed
    void limpiaTabla() {
        try {
            m = (DefaultTableModel) tabla_ventas.getModel();
            int a = m.getRowCount() - 1;
            for (int i = 0; i < a; i++) {
                m.removeRow(i);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void eli() {

        DefaultListModel listModel = (DefaultListModel) listaVentas.getModel();
        listModel.removeAllElements();
    }

    public void eli2() {
        DefaultListModel listModel = (DefaultListModel) lst_total.getModel();
        listModel.removeAllElements();

    }


    private void btn_nuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevaVentaActionPerformed
        txt_idCliente.setText("");
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_direccion.setText("");

        lista1.clear();
        DefaultTableModel mo = (DefaultTableModel) tabla_ventas.getModel();
        while (mo.getRowCount() > 0) {
            mo.removeRow(0);
        }
        txt_subtotal.setText("");
        txt_igv.setText("");
        txt_total.setText("");
        DefaultListModel listModel = (DefaultListModel) lst_encabezado.getModel();
        listModel.removeAllElements();
        eli();
        eli2();

        limpiaTabla();


    }//GEN-LAST:event_btn_nuevaVentaActionPerformed

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoActionPerformed
    public void eliminaarArr(String r) {
        int index;
        try {

            for (ventas vn : lista1) {
                if (vn.getCantidad().equals(cant) && vn.getDescripcion().equals(datBus) && vn.getPrecio().equals(preci) && vn.getImporte().equals(impor)) {
                    while (lista1.iterator().hasNext()) {

                        if (lista1.stream().anyMatch(ve -> ve.getDescripcion().equals(r))) {
                            index = lista1.indexOf(vn);
                            lista1.remove(index);
                            break;

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
    String canti, datBus, preci, impor;

    //int impor;
    private void tabla_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventasMouseClicked
        int filaSeleccionada = tabla_ventas.rowAtPoint(evt.getPoint());
        cant = (tabla_ventas.getValueAt(filaSeleccionada, 0).toString());
        datBus = (tabla_ventas.getValueAt(filaSeleccionada, 1).toString());
        preci = (tabla_ventas.getValueAt(filaSeleccionada, 2).toString());
        impor = (tabla_ventas.getValueAt(filaSeleccionada, 3).toString());


    }//GEN-LAST:event_tabla_ventasMouseClicked
    int id = -1;
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tabla.getRowHeight();

        if (row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() && column >= 0) {
            id = (int) tabla.getValueAt(row, 0);
            Object value = tabla.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getText().equals("Vacio")) {
                    JOptionPane.showMessageDialog(null, "No hay archivo");
                } else {
                    PdfDAO pd = new PdfDAO();
                    pd.ejecutar_archivoPDF(id);
                    try {
                        Desktop.getDesktop().open(new File("new.pdf"));
                    } catch (Exception ex) {
                    }
                }

            } else {
                String name = "" + tabla.getValueAt(row, 1);

            }
        }

    }//GEN-LAST:event_tablaMouseClicked
    public void BuscarCli(String nit) {
        // select * from cliente where ID_CLIENTE like '%"+res+"%'";
        try {
            String[] dato = new String[3];
            String qyl = "Select NOMBRE, APELLIDO, DIRECION FROM CLIENTE WHERE ID_CLIENTE like '%" + nit + "%' ";
            Connection cn = con.getConnection();
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(qyl);
            while (rs.next()) {
                dato[0] = rs.getString("NOMBRE");
                dato[1] = rs.getString("APELLDO");
                dato[2] = rs.getString("DIRECCION");

                String nom = dato[1];
                String apellido = dato[2];
                System.out.println(nom);
                System.out.println(apellido);
                txt_nombre.setText(dato[0]);
                txt_apellido.setText(dato[1]);
                txt_direccion.setText(dato[2]);

            }

        } catch (Exception e) {
            e.getMessage();
        }

    }
    private void txt_idClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BuscarCli(txt_idCliente.getText());
            System.out.println("hola vergas");
        }

    }//GEN-LAST:event_txt_idClienteKeyPressed

    private void txt_idClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idClienteKeyReleased

    }//GEN-LAST:event_txt_idClienteKeyReleased

    private void CB_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_enviarActionPerformed
        if (CB_enviar.isSelected()) {
            txtCorreo.setText(Correo);
            txtAsunto.setText("FACTURA");
            txtMensaje.setText("Gracias por su compra");

        }
    }//GEN-LAST:event_CB_enviarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        mandarCorreo();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_idClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idClienteActionPerformed

    private void txt_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyTyped
        
   char digito =evt.getKeyChar();
        if (!Character.isDigit(digito))
        {
        evt.consume();
        }


    }//GEN-LAST:event_txt_cantidadKeyTyped

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
         buscar(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    public void mandarCorreo() {
        // El correo gmail de envío
        String correoEnvia = "pruebasmtp007@gmail.com";
        String claveCorreo = "correoprueba";

        String asunto = txtAsunto.getText();
        String mensaje = txtMensaje.getText();
        String correoRecibe = txtCorreo.getText();

        // La configuración para enviar correo
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        // Obtener la sesion
        Session session = Session.getInstance(properties, null);
        int aviso = 0;
        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "Pruebas SMTP"));

            // Los destinatarios
            InternetAddress[] internetAddresses = {new InternetAddress(correoRecibe)};
//		     new InternetAddress("correo2@gmail.com"),
//		     new InternetAddress("correo3@gmail.com") };

            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);

            // Agregar el asunto al correo
            mimeMessage.setSubject(asunto);

            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(mensaje);

            MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
            mimeBodyPartAdjunto.attachFile(ruta);

            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(mimeBodyPartAdjunto);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            transport.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            aviso = 1;
        }
        if (aviso == 0) {
            JOptionPane.showMessageDialog(null, "¡Correo electrónico enviado exitosamente!");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Bus_cliente;
    private javax.swing.JDialog Buscar_Pro;
    private javax.swing.JCheckBox CB_enviar;
    private javax.swing.JDialog JD_factur;
    private javax.swing.JDialog JD_mostrarPDF;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_agregar1;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cancelar1;
    private javax.swing.JButton btn_comprar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_eliminar1;
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JButton btn_nuevaVenta;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel jp_imprimir;
    private javax.swing.JList listaVentas;
    private javax.swing.JList lst_encabezado;
    private javax.swing.JList lst_total;
    private java.awt.Panel panel1;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_cliente;
    private javax.swing.JTable tabla_produ;
    private javax.swing.JTable tabla_ventas;
    private javax.swing.JTable tabla_ventas1;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtMensaje;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_apellido1;
    private javax.swing.JTextField txt_busCliente;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_caja;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_direccion1;
    private javax.swing.JTextField txt_idCliente;
    private javax.swing.JTextField txt_idCliente1;
    private javax.swing.JTextField txt_igv;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_nombre1;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JLabel txt_titulo;
    private javax.swing.JLabel txt_titulo1;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total1;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graf, PageFormat pagfor, int index) throws PrinterException {

        if (index > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D hub = (Graphics2D) graf;
        hub.translate(pagfor.getImageableX() + 30, pagfor.getImageableY() + 30);
        hub.scale(1.0, 1.0);
        jp_imprimir.paint(graf);
        return PAGE_EXISTS;
    }
}
