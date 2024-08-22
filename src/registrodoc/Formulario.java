/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrodoc;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Hp
 */
public class Formulario extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    //colorearFilas colorFilas = new colorearFilas();

    public Formulario() {
        initComponents();
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setResizable(false);

        try {
            inicio();
        } catch (Exception e) {
        }
        //jtDocumentaciones.setDefaultRenderer(jtDocumentaciones.getColumnClass(1), colorFilas);
        //jtDocumentaciones.setSelectionBackground(new java.awt.Color(0, 0, 0));
        //jtDocumentaciones.setSelectionForeground(new java.awt.Color(255, 255, 255));
        obtenerAnio();
        obternerFechaActualCF();
        this.setLocationRelativeTo(null);
        this.setTitle("Sistema de Registros de Trabajos Diarios");
        labelTitulo.setFont(new Font("Verdana", 3, 30));
        labelTitulo.setForeground(new Color(0, 64, 122));
        String anioTitulo = cbxAnio.getSelectedItem().toString();
        labelTitulo1.setText(anioTitulo);
        labelTitulo1.setFont(new Font("Verdana", 3, 30));
        labelTitulo1.setForeground(new Color(0, 64, 122));


        jtDocumentaciones.getTableHeader().setFont(new Font("Verdana", Font.ITALIC, 11));
        jtDocumentaciones.getTableHeader().setOpaque(false);
        jtDocumentaciones.getTableHeader().setBackground(new Color(0, 64, 122));
        jtDocumentaciones.getTableHeader().setForeground(new Color(255, 255, 255));
        jtDocumentaciones.setRowHeight(25);
        try {
            jtDocumentaciones.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.getConexion();

            String anioConsulta = cbxAnio.getSelectedItem().toString();
            //System.out.println(anioConsulta);

            String sql = "SELECT id, fecha_actual, tram, nombre, detalle, tipo, trab_re, cant_hojas, entregado_a, fecha_prepar, fecha_entrega FROM documentos WHERE YEAR(fecha_actual) = " + anioConsulta + " ORDER BY fecha_actual";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("     FECHA");
            modelo.addColumn(" TRAM/HOJ.R");
            modelo.addColumn("                      NOMBRE");
            modelo.addColumn("  SOLICITAN");
            modelo.addColumn("      TIPO");
            modelo.addColumn("                   TRABAJO REALIZADO");
            modelo.addColumn(" Nº HOJAS");
            modelo.addColumn(" SE ENTREGO A");
            modelo.addColumn("FECHA QUE PREPARE");
            modelo.addColumn(" FECHA DE ENTREGA");

            int[] anchos = {0, 25, 30, 170, 30, 30, 225, 20, 50, 75, 75};
            for (int i = 0; i < jtDocumentaciones.getColumnCount(); i++) {
                if (i == 0) {
                    jtDocumentaciones.getColumnModel().getColumn(0).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setPreferredWidth(anchos[i]);
                } else {
                    jtDocumentaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    if (i == 1) {
                        //String ok=(rs.getDate(i + 1)).toString();
                        String fechaRecibida = darFormato(rs.getDate(i + 1));
                        filas[i] = "  " + fechaRecibida;

                    } else if (i == 7) {
                        filas[i] = "  " + rs.getObject(i + 1) + "  hoja(s)";
                    } else {
                        filas[i] = "  " + rs.getObject(i + 1);
                        //System.out.println(rs.getObject(i+1));
                    }

                }
                modelo.addRow(filas);
            }

        } catch (SQLException ex) {
            //System.err.println(ex.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDocumentaciones = new colorearCelda();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cajaTextoNombre = new javax.swing.JTextField();
        cajaTextoEntregado_a = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        cajaTextoBuscar = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        cbxTram = new javax.swing.JComboBox();
        cbxDetalle = new javax.swing.JComboBox();
        botonLimpiar = new javax.swing.JButton();
        cbxTipo = new javax.swing.JComboBox();
        labelFechaEst = new javax.swing.JLabel();
        cajaTextoCantHojas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jftfPrepare = new javax.swing.JFormattedTextField();
        jftfEntrega = new javax.swing.JFormattedTextField();
        cajaTextoTrab_re = new javax.swing.JTextField();
        cbxAnio = new javax.swing.JComboBox();
        labelFechaEst1 = new javax.swing.JLabel();
        labelTitulo1 = new javax.swing.JLabel();
        cajaTextoDia = new javax.swing.JTextField();
        cajaTextoAnio = new javax.swing.JTextField();
        cajaTextoMes = new javax.swing.JTextField();
        botonGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("REGISTRO DE DOCUMENTACIÓN PREPARADA GESTIÓN -");

        jtDocumentaciones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }) {

            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jtDocumentaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtDocumentaciones.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jtDocumentaciones.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDocumentacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtDocumentaciones);
        if (jtDocumentaciones.getColumnModel().getColumnCount() > 0) {
            jtDocumentaciones.getColumnModel().getColumn(0).setResizable(false);
            jtDocumentaciones.getColumnModel().getColumn(1).setResizable(false);
            jtDocumentaciones.getColumnModel().getColumn(2).setResizable(false);
            jtDocumentaciones.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel2.setText("TRAM/HOJ.R:");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel3.setText("NOMBRE:");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel4.setText("SOLICITAN:");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel5.setText("TIPO:");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel6.setText("SE ENTREGO A:");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel7.setText("FECHA DE ENTREGA:");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel8.setText("FECHA QUE PREPARE:");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel9.setText("TRABAJO REALIZADO:");

        botonGuardar.setBackground(new java.awt.Color(0, 64, 122));
        botonGuardar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonGuardar.setForeground(new java.awt.Color(0, 64, 122));
        botonGuardar.setText("GUARDAR");
        botonGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        cajaTextoBuscar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTextoBuscarActionPerformed(evt);
            }
        });

        botonBuscar.setBackground(new java.awt.Color(0, 64, 122));
        botonBuscar.setFont(new java.awt.Font("Verdana", 3, 11)); // NOI18N
        botonBuscar.setForeground(new java.awt.Color(0, 64, 122));
        botonBuscar.setText("BUSCAR NOMBRE");
        botonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        botonEliminar.setBackground(new java.awt.Color(0, 64, 122));
        botonEliminar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(0, 64, 122));
        botonEliminar.setText("ELIMINAR");
        botonEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonModificar.setBackground(new java.awt.Color(0, 64, 122));
        botonModificar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonModificar.setForeground(new java.awt.Color(0, 64, 122));
        botonModificar.setText("MODIFICAR");
        botonModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonModificar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        cbxTram.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Valor", "Verbal", "Nota", "Hoja de Ruta", "REVISADO"}));
        cbxTram.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTramActionPerformed(evt);
            }
        });

        cbxDetalle.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Antiguedad - Cas", "Asesoría - Cas", "Beneficios - Cas", "Cas para CAS", "Contabilidad - Cas", "DDJJ", "Futuro - Cas", "Gestora - Cas", "Otros Cas", "Previsión - Cas", "Recategorización - Cas", "Revisión - Cas", "Senasir - Cas", "SSU - Alta", "SSU - Futuro", "SSU - Gestora", "SSU - Previsión", "SSU - Reingreso"}));
        cbxDetalle.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDetalleActionPerformed(evt);
            }
        });

        botonLimpiar.setBackground(new java.awt.Color(0, 64, 122));
        botonLimpiar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonLimpiar.setForeground(new java.awt.Color(0, 64, 122));
        botonLimpiar.setText("LIMPIAR");
        botonLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Activo", "Pasivo", "Deceso (†)", "Ningúno"}));

        labelFechaEst.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelFechaEst.setText("FECHA:");

        cajaTextoCantHojas.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTextoCantHojasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel1.setText("Nº HOJAS:");

        jftfPrepare.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jftfPrepare.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jftfPrepareActionPerformed(evt);
            }
        });

        jftfEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        cbxAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        cbxAnio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAnioActionPerformed(evt);
            }
        });

        labelFechaEst1.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        labelFechaEst1.setText("AÑO:");

        labelTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTitulo1.setText("XX");

        cajaTextoDia.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTextoDiaActionPerformed(evt);
            }
        });

        cajaTextoAnio.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTextoAnioActionPerformed(evt);
            }
        });

        cajaTextoMes.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaTextoMesActionPerformed(evt);
            }
        });

        botonGenerar.setBackground(new java.awt.Color(0, 64, 122));
        botonGenerar.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
        botonGenerar.setForeground(new java.awt.Color(0, 64, 122));
        botonGenerar.setText("GENERAR REPORTE");
        botonGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonGenerar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2).addComponent(cbxTram, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel9)).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGap(189, 189, 189).addComponent(jLabel1).addGap(108, 108, 108)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3).addComponent(cajaTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(22, 22, 22)))).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(jPanelLayout.createSequentialGroup().addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(96, 96, 96).addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanelLayout.createSequentialGroup().addComponent(cajaTextoTrab_re, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cajaTextoCantHojas, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGap(73, 73, 73).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(cajaTextoEntregado_a, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4).addComponent(cbxDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel6).addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5).addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jftfPrepare, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel8)).addGap(18, 18, 18).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel7).addComponent(jftfEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(botonGenerar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(22, 22, 22)).addGroup(jPanelLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(labelFechaEst1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cbxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(49, 49, 49).addComponent(cajaTextoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(labelFechaEst).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cajaTextoDia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cajaTextoMes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cajaTextoAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(55, 55, 55)).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1341, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanelLayout.createSequentialGroup().addGap(146, 146, 146).addComponent(labelTitulo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(labelTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(0, 12, Short.MAX_VALUE)))));
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGap(30, 30, 30).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelTitulo).addComponent(labelTitulo1)).addGap(28, 28, 28).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(labelFechaEst).addComponent(cajaTextoDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cajaTextoMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cajaTextoAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cajaTextoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(botonBuscar).addComponent(cbxAnio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(labelFechaEst1))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(jLabel5)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cbxDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(cajaTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jLabel3)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cbxTram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(27, 27, 27).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel7).addComponent(jLabel1).addComponent(jLabel9)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cajaTextoEntregado_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jftfPrepare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jftfEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cajaTextoTrab_re, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cajaTextoCantHojas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(88, 88, 88)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup().addGap(47, 47, 47).addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(botonGuardar).addComponent(botonModificar).addComponent(botonEliminar).addComponent(botonLimpiar).addComponent(botonGenerar)).addGap(33, 33, 33)))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>

    private void jftfPrepareActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cajaTextoCantHojasActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        refrescarEntradas();
        reiniciar();
    }

    private void cbxDetalleActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cbxTramActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void botonGenerarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Conexion con = new Conexion();
            Connection conn = con.getConexion();

            Conexion objCon =    new Conexion();
            Connection conexion = (Connection) objCon.getConexion();
            PreparedStatement ps2 = null;
            int cantidadCas = 0;
            int cantidadDDJJ = 0;
            int cantidadSSU = 0;
            ResultSet rs = null;
            ps2 = conexion.prepareStatement("SELECT COUNT(*) FROM registros_documentacion.documentos WHERE detalle IN ('Cas Antiguedad', 'Antiguedad - Cas', 'Cas Asesoría', 'Asesoría - Cas', 'Cas Beneficios', 'Beneficios - Cas', 'Cas - Contabilidad', 'Contabilidad - Cas', 'Cas Futuro', 'Futuro - Cas',  'Cas - Gestora', 'Gestora - Cas', 'Cas Otros', 'Otros Cas', 'Cas para CAS', 'Cas Previsión',  'Previsión - Cas', 'Cas - Recategorización', 'Recategorización - Cas', 'Cas Revisión', 'Revisión - Cas', 'Cas Senasir', 'Senasir - Cas') AND YEAR(fecha_actual)="+cbxAnio.getSelectedItem().toString());
            rs = ps2.executeQuery();
            while (rs.next()) {
                cantidadCas = rs.getInt(1);
            }
            ps2 = conexion.prepareStatement("SELECT COUNT(*) FROM registros_documentacion.documentos WHERE detalle IN ('DDJJ') AND YEAR(fecha_actual)="+cbxAnio.getSelectedItem().toString());
            rs = ps2.executeQuery();
            while (rs.next()) {
                cantidadDDJJ = rs.getInt(1);
            }
            ps2 = conexion.prepareStatement("SELECT COUNT(*) FROM registros_documentacion.documentos WHERE detalle IN ('SSU - Alta', 'SSU - Futuro', 'SSU - Gestora', 'SSU - Previsión', 'SSU - Reingreso') AND YEAR(fecha_actual)="+cbxAnio.getSelectedItem().toString());
            rs = ps2.executeQuery();
            while (rs.next()) {
                cantidadSSU = rs.getInt(1);
            }
            

            JasperReport reporte = null;
            String path = "src\\reportes\\Documentacion.jasper";

            Map parametro = new HashMap();
            parametro.put("gestion", cbxAnio.getSelectedItem().toString());
            parametro.put("cantidadCas", cantidadCas);
            parametro.put("cantidadDDJJ", cantidadDDJJ);
            parametro.put("cantidadSSU", cantidadSSU);

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

            view.setExtendedState(view.MAXIMIZED_BOTH);

        } catch (JRException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Registrar el Certificado de Solvencia", "Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println(ex);
        }

    }

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {

        //int Fila = jtDocumentaciones.getSelectedRow();
        PreparedStatement ps = null;
        try {
            Conexion objCon = new Conexion();
            Connection conn = objCon.getConexion();

            int Fila = jtDocumentaciones.getSelectedRow();
            String codigo = jtDocumentaciones.getValueAt(Fila, 0).toString();
            //System.out.println("Modificando " + codigo);

            ps = conn.prepareStatement("UPDATE documentos SET fecha_actual=?, tram=?, nombre=?, detalle=?, tipo=?, trab_re=?, cant_hojas=?, entregado_a=?, fecha_prepar=?, fecha_entrega=?  WHERE id=?");

            ps.setString(1, cajaTextoAnio.getText() + "-" + cajaTextoMes.getText() + "-" + cajaTextoDia.getText());
            ps.setString(2, cbxTram.getSelectedItem().toString());
            ps.setString(3, cajaTextoNombre.getText());
            ps.setString(4, cbxDetalle.getSelectedItem().toString());
            ps.setString(5, cbxTipo.getSelectedItem().toString());
            ps.setString(6, cajaTextoTrab_re.getText());
            ps.setString(7, cajaTextoCantHojas.getText());
            ps.setString(8, cajaTextoEntregado_a.getText());
            ps.setString(9, jftfPrepare.getText());
            ps.setString(10, jftfEntrega.getText());
            ps.setString(11, codigo);

            ps.execute();

            reiniciar();

            JOptionPane.showMessageDialog(null, "La Documentación ha sido Modificada", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            /*jtProductos.setValueAt(txtCodigo.getText(), Fila, 0);
            jtProductos.setValueAt(txtNombre.getText(), Fila, 1);
            jtProductos.setValueAt(txtPrecio.getText(), Fila, 2);
            jtProductos.setValueAt(txtCantidad.getText(), Fila, 3);*/

            refrescarEntradas();

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Error al Modificar la Documentación");
            JOptionPane.showMessageDialog(null, "Error al Modificar la Documentación", "Error", JOptionPane.ERROR_MESSAGE);

            //System.out.println(ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha modificado ninguna documentación, ya que no se escogió alguna", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        PreparedStatement ps = null;
        try {

            Conexion objCon = new Conexion();
            Connection conn = objCon.getConexion();

            int Fila = jtDocumentaciones.getSelectedRow();
            String codigo = jtDocumentaciones.getValueAt(Fila, 0).toString();
            //System.out.println("eliminando " + codigo);

            ps = conn.prepareStatement("DELETE FROM documentos WHERE id=?");
            ps.setString(1, codigo);
            ps.execute();

            //modelo.removeRow(Fila);
            reiniciar();
            JOptionPane.showMessageDialog(null, "La Documentación ha sido Eliminada", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            refrescarEntradas();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar la Documentación", "Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println(ex.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha eliminado ninguna documentación, ya que no se escogió alguna", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String campo = cajaTextoBuscar.getText();
        String anioConsulta = cbxAnio.getSelectedItem().toString();
        String sql = "";
        String where = "";
        if (!"".equals(campo)) {
            //where = "WHERE tram = '" + campo + "'";
            where = "WHERE nombre LIKE '%" + campo + "%'";
            sql = "SELECT * FROM documentos " + where + " AND YEAR(fecha_actual) = " + anioConsulta + " ORDER BY fecha_actual";
        } else {
            sql = "SELECT * FROM documentos WHERE YEAR(fecha_actual) = " + anioConsulta + " ORDER BY fecha_actual";
        }
        //SELECT * FROM tabla WHERE campo LIKE '%valorrecibido%'
        try {
            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jtDocumentaciones.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            //String sql = "SELECT id, tram, nombre, detalle, tipo, trab_re, entregado_a, fecha_prepar, fecha_entrega FROM documentos " + where;
            //sql = "SELECT * FROM documentos " + where +" AND YEAR(fecha_actual) = "+anioConsulta;
            //System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("     FECHA");
            modelo.addColumn(" TRAM/HOJ.R");
            modelo.addColumn("                      NOMBRE");
            modelo.addColumn("  SOLICITAN");
            modelo.addColumn("      TIPO");
            modelo.addColumn("                   TRABAJO REALIZADO");
            modelo.addColumn(" Nº HOJAS");
            modelo.addColumn(" SE ENTREGO A");
            modelo.addColumn("FECHA QUE PREPARE");
            modelo.addColumn(" FECHA DE ENTREGA");

            int[] anchos = {0, 25, 30, 170, 30, 30, 225, 20, 50, 75, 75};
            for (int i = 0; i < jtDocumentaciones.getColumnCount(); i++) {
                if (i == 0) {
                    jtDocumentaciones.getColumnModel().getColumn(0).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setPreferredWidth(anchos[i]);
                } else {
                    jtDocumentaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    if (i == 1) {
                        //String ok=(rs.getDate(i + 1)).toString();
                        String fechaRecibida = darFormato(rs.getDate(i + 1));
                        filas[i] = "  " + fechaRecibida;

                    } else if (i == 7) {
                        filas[i] = "  " + rs.getObject(i + 1) + "  hoja(s)";
                    } else {
                        filas[i] = "  " + rs.getObject(i + 1);
                    }
                    //System.out.println(i+"  "+rs.getObject(i+1));
                }
                modelo.addRow(filas);
            }

        } catch (Exception ex) {
            //System.err.println(ex.toString());
        }
    }

    private void cajaTextoBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        PreparedStatement ps = null;
        int indice = 0;
        try {
            Conexion objCon = new Conexion();
            Connection conexion = objCon.getConexion();
            ps = conexion.prepareStatement("INSERT INTO documentos (fecha_actual, tram, nombre, detalle, tipo, trab_re, cant_hojas, entregado_a, fecha_prepar, fecha_entrega) VALUES (?,?,?,?,?,?,?,?,?,?)");
            //ps.setString(1, obtenerFechaActualSF());
            ps.setString(1, cajaTextoAnio.getText() + "-" + cajaTextoMes.getText() + "-" + cajaTextoDia.getText());
            ps.setString(2, cbxTram.getSelectedItem().toString());
            ps.setString(3, cajaTextoNombre.getText());
            ps.setString(4, cbxDetalle.getSelectedItem().toString());
            ps.setString(5, cbxTipo.getSelectedItem().toString());
            ps.setString(6, cajaTextoTrab_re.getText());
            ps.setString(7, cajaTextoCantHojas.getText());
            ps.setString(8, cajaTextoEntregado_a.getText());
            ps.setString(9, jftfPrepare.getText());
            ps.setString(10, jftfEntrega.getText());
            ps.execute();

            PreparedStatement ps2 = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.getConexion();

            String sql = "SELECT id FROM documentos ORDER BY id DESC LIMIT 1";
            ps2 = con.prepareStatement(sql);
            rs = ps2.executeQuery();

            while (rs.next()) {
                //System.out.println("guardando " + (rs.getInt(1)));
                indice = rs.getInt(1);
            }
            /*Object[] fila = new Object[9];
            fila[0] = indice;
            fila[1] = cajaTextoTram.getText();
            fila[2] = cajaTextoNombre.getText();
            fila[3] = cajaTextoDetalle.getText();
            fila[4] = cajaTextoTipo.getText();
            fila[5] = cajaTextoTrab_re.getText();
            fila[6] = cajaTextoEntregado_a.getText();
            fila[7] = cajaTextoFecha_prepar.getText();
            fila[8] = cajaTextoFecha_entrega.getText();
            modelo.addRow(fila);*/

            reiniciar();

            JOptionPane.showMessageDialog(null, "Documentación Registrada", "Realizado", JOptionPane.INFORMATION_MESSAGE);

            limpiar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Guardar la Documentación", "Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println(ex);
        }
    }

    private void jtDocumentacionesMouseClicked(java.awt.event.MouseEvent evt) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion objCon = new Conexion();
            Connection conn = objCon.getConexion();

            int Fila = jtDocumentaciones.getSelectedRow();
            String id = jtDocumentaciones.getValueAt(Fila, 0).toString();

            ps = conn.prepareStatement("SELECT id, fecha_actual, tram, nombre, detalle, tipo, trab_re, cant_hojas, entregado_a, fecha_prepar, fecha_entrega FROM documentos WHERE id=?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                //cajaTextoFechaActual.setText(darFormato(rs.getDate("fecha_actual")));
                cajaTextoDia.setText(recibirDia(rs.getDate("fecha_actual")));
                cajaTextoMes.setText(recibirMes(rs.getDate("fecha_actual")));
                cajaTextoAnio.setText(recibirAnio(rs.getDate("fecha_actual")));
                cbxTram.setSelectedItem(rs.getString("tram"));
                cajaTextoNombre.setText(rs.getString("nombre"));
                /*if(rs.getString("detalle").equals("Antiguedad - Cas") || rs.getString("detalle").equals("Asesoría - Cas")|| rs.getString("detalle").equals("Beneficios - Cas")|| rs.getString("detalle").equals("Contabilidad - Cas")|| rs.getString("detalle").equals("Futuro - Cas")|| rs.getString("detalle").equals("Gestora - Cas")|| rs.getString("detalle").equals("Otros Cas")|| rs.getString("detalle").equals("Previsión - Cas")|| rs.getString("detalle").equals("Recategorización - Cas")|| rs.getString("detalle").equals("Revisión - Cas")|| rs.getString("detalle").equals("Senasir - Cas")){
                    System.out.println("asfsd");
                    cbxDetalle.setSelectedIndex(8);*/
                if(rs.getString("detalle").equals("Cas Antiguedad")){
                    cbxDetalle.setSelectedIndex(0);
                }if(rs.getString("detalle").equals("Cas Asesoría")){
                    cbxDetalle.setSelectedIndex(1);
                }if(rs.getString("detalle").equals("Cas Beneficios")){
                    cbxDetalle.setSelectedIndex(2);
                }if(rs.getString("detalle").equals("Cas - Contabilidad")){
                    cbxDetalle.setSelectedIndex(4);
                }if(rs.getString("detalle").equals("Cas Futuro")){
                    cbxDetalle.setSelectedIndex(6);
                }if(rs.getString("detalle").equals("Cas - Gestora")){
                    cbxDetalle.setSelectedIndex(7);
                }if(rs.getString("detalle").equals("Cas Otros")){
                    cbxDetalle.setSelectedIndex(8);
                }if(rs.getString("detalle").equals("Cas Previsión")){
                    cbxDetalle.setSelectedIndex(9);
                }if(rs.getString("detalle").equals("Cas - Recategorización")){
                    cbxDetalle.setSelectedIndex(10);
                }if(rs.getString("detalle").equals("Cas Revisión")){
                    cbxDetalle.setSelectedIndex(11);
                }if(rs.getString("detalle").equals("Cas Senasir")){
                    cbxDetalle.setSelectedIndex(12);
                }else{
                    cbxDetalle.setSelectedItem(rs.getString("detalle"));
                }
                cbxTipo.setSelectedItem(rs.getString("tipo"));
                cajaTextoTrab_re.setText(rs.getString("trab_re"));
                cajaTextoCantHojas.setText(rs.getString("cant_hojas"));
                cajaTextoEntregado_a.setText(rs.getString("entregado_a"));
                jftfPrepare.setText(rs.getString("fecha_prepar"));
                jftfEntrega.setText(rs.getString("fecha_entrega"));
            }
        } catch (SQLException ex) {
            //System.out.println(ex.toString());
        }
    }

    private void cbxAnioActionPerformed(java.awt.event.ActionEvent evt) {
        reiniciar();
        refrescarEntradas();
        String anioTitulo = cbxAnio.getSelectedItem().toString();
        labelTitulo1.setText(anioTitulo);
    }

    private void cajaTextoDiaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cajaTextoAnioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void cajaTextoMesActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void reiniciar() {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jtDocumentaciones.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            java.sql.Connection con = conn.getConexion();

            String anioConsulta = cbxAnio.getSelectedItem().toString();

            String sql = "SELECT id, fecha_actual, tram, nombre, detalle, tipo, trab_re, cant_hojas, entregado_a, fecha_prepar, fecha_entrega FROM documentos WHERE YEAR(fecha_actual) = " + anioConsulta + " ORDER BY fecha_actual";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("     FECHA");
            modelo.addColumn(" TRAM/HOJ.R");
            modelo.addColumn("                      NOMBRE");
            modelo.addColumn("  SOLICITAN");
            modelo.addColumn("      TIPO");
            modelo.addColumn("                   TRABAJO REALIZADO");
            modelo.addColumn(" Nº HOJAS");
            modelo.addColumn(" SE ENTREGO A");
            modelo.addColumn("FECHA QUE PREPARE");
            modelo.addColumn(" FECHA DE ENTREGA");

            int[] anchos = {0, 25, 30, 170, 30, 30, 225, 20, 50, 75, 75};
            for (int i = 0; i < jtDocumentaciones.getColumnCount(); i++) {
                if (i == 0) {
                    jtDocumentaciones.getColumnModel().getColumn(0).setMaxWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setMinWidth(anchos[i]);
                    jtDocumentaciones.getColumnModel().getColumn(0).setPreferredWidth(anchos[i]);
                } else {
                    jtDocumentaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    if (i == 1) {
                        //String ok=(rs.getDate(i + 1)).toString();
                        String fechaRecibida = darFormato(rs.getDate(i + 1));
                        filas[i] = "  " + fechaRecibida;

                    } else if (i == 7) {
                        filas[i] = "  " + rs.getObject(i + 1) + "  hoja(s)";
                    } else {
                        filas[i] = "  " + rs.getObject(i + 1);
                    }
                    //System.out.println(i+"  "+rs.getObject(i+1));
                }
                modelo.addRow(filas);
            }

        } catch (SQLException ex) {
            //System.err.println(ex.toString());
        }
    }

    public String obtenerFechaActualSF() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return año + "-" + mes + "-" + dia;
    }

    public void obtenerAnio() {
        String anioString = "";
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        String[] opciones = new String[10];
        for (int i = 0; i < 10; i++) {
            anioString = Integer.toString(año);
            opciones[i] = anioString;
            año = año - 1;
        }
        cbxAnio.setModel(new javax.swing.DefaultComboBoxModel(opciones));
        //cbxAnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Pasivo", "Ningúno" }));
        //return Integer.toString(año);
    }

    public void obternerFechaActualCF() {
        //String mesString = "";
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        /*switch (mes) {
        case 0:
        mesString = "Enero";
        break;
        case 1:
        mesString = "Febrero";
        break;
        case 2:
        mesString = "Marzo";
        break;
        case 3:
        mesString = "Abril";
        break;
        case 4:
        mesString = "Mayo";
        break;
        case 5:
        mesString = "Junio";
        break;
        case 6:
        mesString = "Julio";
        break;
        case 7:
        mesString = "Agosto";
        break;
        case 8:
        mesString = "Septiembre";
        break;
        case 9:
        mesString = "Octubre";
        break;
        case 10:
        mesString = "Noviembre";
        break;
        case 11:
        mesString = "Diciembre";
        break;
        }
        labelFechaActual.setText(dia + " de " + mesString + " del " + año);*/
        cajaTextoDia.setText(Integer.toString(dia));
        cajaTextoMes.setText(Integer.toString(mes + 1));
        cajaTextoAnio.setText(Integer.toString(año));

        //System.out.println(mes);
        //System.out.println("Fecha Actual: " + dia + "/" + mesString + "/" + año);
    }

    public String recibirDia(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(dia);
    }

    public String recibirMes(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int mes = cal.get(Calendar.MONTH);
        //System.out.println(mes);
        return Integer.toString(mes + 1);
    }

    public String recibirAnio(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int anio = cal.get(Calendar.YEAR);
        return Integer.toString(anio);
    }

    public String darFormato(Date fecha) {
        String mesString = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int diaActual = cal.get(Calendar.DAY_OF_MONTH);
        int mesActual = cal.get(Calendar.MONTH);
        int año = cal.get(Calendar.YEAR);
        switch (mesActual) {
            case 0:
                mesString = "Ene.";
                break;
            case 1:
                mesString = "Feb.";
                break;
            case 2:
                mesString = "Mar.";
                break;
            case 3:
                mesString = "Abr.";
                break;
            case 4:
                mesString = "May.";
                break;
            case 5:
                mesString = "Jun.";
                break;
            case 6:
                mesString = "Jul.";
                break;
            case 7:
                mesString = "Ago.";
                break;
            case 8:
                mesString = "Sep.";
                break;
            case 9:
                mesString = "Oct.";
                break;
            case 10:
                mesString = "Nov.";
                break;
            case 11:
                mesString = "Dic.";
                break;
        }
        //System.out.println("este es el mes: "+mesString+" "+diaActual);
        return diaActual + " " + mesString + " " + año;
    }

    private void refrescarEntradas() {
        obternerFechaActualCF();
        //reiniciar();
        cajaTextoBuscar.setText("");
        cbxTram.setSelectedIndex(0);
        cajaTextoNombre.setText("");
        cbxDetalle.setSelectedIndex(0);
        cbxTipo.setSelectedIndex(0);
        cajaTextoTrab_re.setText("");
        cajaTextoCantHojas.setText("");
        cajaTextoEntregado_a.setText("");
        jftfPrepare.setText("");
        jftfEntrega.setText("");
    }

    private void limpiar() {
        obternerFechaActualCF();
        cajaTextoBuscar.setText("");
        //cbxTram.setSelectedIndex(0);
        cajaTextoNombre.setText("");
        //cbxDetalle.setSelectedIndex(0);
        cbxTipo.setSelectedIndex(0);
        cajaTextoTrab_re.setText("");
        cajaTextoCantHojas.setText("");
        cajaTextoEntregado_a.setText("");
    }

    private void inicio() throws ParseException {

        jftfPrepare.setEnabled(true);
        jftfPrepare.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("   ##  /  ##  /  ##")));

        jftfEntrega.setEnabled(true);
        jftfEntrega.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("   ##  /  ##  /  ##")));

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonGenerar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField cajaTextoAnio;
    private javax.swing.JTextField cajaTextoBuscar;
    private javax.swing.JTextField cajaTextoCantHojas;
    private javax.swing.JTextField cajaTextoDia;
    private javax.swing.JTextField cajaTextoEntregado_a;
    private javax.swing.JTextField cajaTextoMes;
    private javax.swing.JTextField cajaTextoNombre;
    private javax.swing.JTextField cajaTextoTrab_re;
    private javax.swing.JComboBox cbxAnio;
    private javax.swing.JComboBox cbxDetalle;
    private javax.swing.JComboBox cbxTipo;
    private javax.swing.JComboBox cbxTram;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField jftfEntrega;
    private javax.swing.JFormattedTextField jftfPrepare;
    private javax.swing.JTable jtDocumentaciones;
    private javax.swing.JLabel labelFechaEst;
    private javax.swing.JLabel labelFechaEst1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTitulo1;
    // End of variables declaration
}
