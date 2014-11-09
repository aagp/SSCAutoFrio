//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)SSCAutoFrio.java Created on 17/10/2014, 01:02:49 PM
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses>
 *
 * Copyright (C) 2014 Alan García. All rights reserved.
 */
//</editor-fold>
package com.ssc.gui;

import com.ssc.excepciones.NonexistentEntityException;
import com.ssc.excepciones.PreexistingEntityException;
import com.ssc.interfaznegocio.INegocio;
import com.ssc.negocio.FNegocio;
import com.ssc.objetosnegocio.*;
import com.ssc.reports.Reportes;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 * Class SSCAutoFrio
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class SSCAutoFrio extends javax.swing.JFrame {

    INegocio ne;
    Reportes re;
    DefaultTableModel table1, table2, table3, table4, table5, table6, table7;
    String id, idV, idSer, desc;
    double cant = 0.0;
    double impor = 0.0;
    double punit = 0.0;
    double subt = 0.0;
    double iva = 0.0;
    double tot = 0.0;

    /**
     * Creates new form SSCAutoFrio
     */
    public SSCAutoFrio() {
        ne = new FNegocio();
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        table1 = (DefaultTableModel) tableReg.getModel();
        table2 = (DefaultTableModel) tableCot.getModel();
        table3 = (DefaultTableModel) tableAce.getModel();
        table4 = (DefaultTableModel) tableTie.getModel();
        table5 = (DefaultTableModel) tableSer.getModel();
        table6 = (DefaultTableModel) tableDet.getModel();
        table7 = (DefaultTableModel) tableBusCli.getModel();
        tableCot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableCot.getSelectedRow();
                id = table2.getValueAt(fila, 0).toString();
                if (e.getClickCount() == 2) {
                    id = table2.getValueAt(fila, 0).toString();
                    pasarCotizacion();
                    borrarDet();
                    actualizaListaDet();
                } else {
                    id = table2.getValueAt(fila, 0).toString();
                }
            }
        });
        tableAce.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableAce.getSelectedRow();
                id = table3.getValueAt(fila, 0).toString();
                if (e.getClickCount() == 2) {
                    id = table3.getValueAt(fila, 0).toString();
                    pasarCotizacion();
                    borrarDet();
                    actualizaListaDet();
                } else {
                    id = table3.getValueAt(fila, 0).toString();
                }
            }
        });
        tableReg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableReg.getSelectedRow();
                id = table1.getValueAt(fila, 0).toString();
            }
        });
        tableTie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableTie.getSelectedRow();
                idV = table4.getValueAt(fila, 0).toString();
            }
        });
        actualizaListas();
    }

    //<editor-fold defaultstate="collapsed" desc=" Icon/Centrar ">    
    private Image iconSSCAutoFrio() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("com/ssc/img/sscicon.png"));
        return retValue;
    }

    private void centrar(JInternalFrame internalFrame) {
        int x = (desktopPane.getWidth() / 2) - internalFrame.getWidth() / 2;
        int y = (desktopPane.getHeight() / 2) - internalFrame.getHeight() / 2;
        if (internalFrame.isShowing()) {
            internalFrame.setLocation(x, y);
        } else {
            desktopPane.add(internalFrame);
            internalFrame.setLocation(x, y);
            internalFrame.show();
        }
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jLabel4 = new javax.swing.JLabel();
        Monitor = new javax.swing.JInternalFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableReg = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableCot = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableAce = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnMonAgre = new javax.swing.JButton();
        btnMonElim = new javax.swing.JButton();
        btnMonCot = new javax.swing.JButton();
        btnMonCan = new javax.swing.JButton();
        btnMonAce = new javax.swing.JButton();
        btnMonRegACot = new javax.swing.JButton();
        btnMonLib = new javax.swing.JButton();
        TomaOrden = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblFolio = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtOrdenId = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        lblNom = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCom = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTie = new javax.swing.JTable();
        btnOrdenAceptar = new javax.swing.JButton();
        btnOrdenCancelar = new javax.swing.JButton();
        Cotizacion = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblCotId = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblCotNom = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblCotDir = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblCotRfc = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblCotVeh = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblCotTel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDet = new javax.swing.JTable();
        btnAnadirSer = new javax.swing.JButton();
        btnEliminarSer = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        lblTot = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();
        btnCotAce = new javax.swing.JButton();
        btnCotCan = new javax.swing.JButton();
        BuscarServicio = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtSerNom = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnSerCan = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableSer = new javax.swing.JTable();
        btnSerSel = new javax.swing.JButton();
        BuscarCliente = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtBusCli = new javax.swing.JTextField();
        btnBusCli = new javax.swing.JButton();
        btnBusCliCan = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableBusCli = new javax.swing.JTable();
        btnBusCliSel = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnMonitor = new javax.swing.JButton();
        btnOrden = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Seguimiento de Clientes Auto Frio");
        setIconImage(iconSSCAutoFrio());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/ssc.png"))); // NOI18N
        jLabel4.setBounds(610, 160, 660, 430);
        desktopPane.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Monitor.setClosable(true);
        Monitor.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        Monitor.setIconifiable(true);
        Monitor.setTitle("SSCAutoFrio - Monitor de Ordenes");
        Monitor.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/monitor24.png"))); // NOI18N
        Monitor.setVisible(false);

        tableReg.setAutoCreateRowSorter(true);
        tableReg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Vehículo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableReg);
        tableReg.getColumnModel().getColumn(0).setPreferredWidth(5);
        tableReg.getColumnModel().getColumn(3).setPreferredWidth(20);

        tableCot.setAutoCreateRowSorter(true);
        tableCot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Vehículo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tableCot);
        tableCot.getColumnModel().getColumn(0).setPreferredWidth(5);
        tableCot.getColumnModel().getColumn(3).setPreferredWidth(20);

        tableAce.setAutoCreateRowSorter(true);
        tableAce.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Vehículo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableAce);
        tableAce.getColumnModel().getColumn(0).setPreferredWidth(5);
        tableAce.getColumnModel().getColumn(3).setPreferredWidth(20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Ordenes Registradas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 204));
        jLabel2.setText("Ordenes Cotizadas");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 204));
        jLabel3.setText("Ordenes Aceptadas");

        btnMonAgre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add24.png"))); // NOI18N
        btnMonAgre.setToolTipText("Añadir Orden");
        btnMonAgre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonAgreActionPerformed(evt);
            }
        });

        btnMonElim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/del24.png"))); // NOI18N
        btnMonElim.setToolTipText("Eliminar Orden");
        btnMonElim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonElimActionPerformed(evt);
            }
        });

        btnMonCot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/redo24.png"))); // NOI18N
        btnMonCot.setToolTipText("Cotizar");
        btnMonCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonCotActionPerformed(evt);
            }
        });

        btnMonCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/del24.png"))); // NOI18N
        btnMonCan.setToolTipText("Eliminar Cotización");
        btnMonCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonCanActionPerformed(evt);
            }
        });

        btnMonAce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/redo24.png"))); // NOI18N
        btnMonAce.setToolTipText("Aceptar");
        btnMonAce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonAceActionPerformed(evt);
            }
        });

        btnMonRegACot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/undo24.png"))); // NOI18N
        btnMonRegACot.setToolTipText("Devolver");
        btnMonRegACot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonRegACotActionPerformed(evt);
            }
        });

        btnMonLib.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/apply24.png"))); // NOI18N
        btnMonLib.setToolTipText("Liberar");
        btnMonLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonLibActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MonitorLayout = new javax.swing.GroupLayout(Monitor.getContentPane());
        Monitor.getContentPane().setLayout(MonitorLayout);
        MonitorLayout.setHorizontalGroup(
            MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonitorLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel1)
                .addGap(291, 291, 291)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(148, 148, 148))
            .addGroup(MonitorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MonitorLayout.createSequentialGroup()
                        .addComponent(btnMonAgre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMonElim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMonCot))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MonitorLayout.createSequentialGroup()
                        .addComponent(btnMonCan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMonAce))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MonitorLayout.createSequentialGroup()
                        .addComponent(btnMonRegACot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMonLib))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        MonitorLayout.setVerticalGroup(
            MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonitorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGap(12, 12, 12)
                .addGroup(MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(MonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMonLib)
                    .addComponent(btnMonElim)
                    .addComponent(btnMonAgre)
                    .addComponent(btnMonCot)
                    .addComponent(btnMonCan)
                    .addComponent(btnMonAce)
                    .addComponent(btnMonRegACot))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        Monitor.setBounds(0, 0, 1270, 520);
        desktopPane.add(Monitor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TomaOrden.setClosable(true);
        TomaOrden.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        TomaOrden.setIconifiable(true);
        TomaOrden.setTitle("SSCAutoFrio - Toma de Orden");
        TomaOrden.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/orden24.png"))); // NOI18N
        TomaOrden.setVisible(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Orden", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Folio:");

        lblFolio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFolio.setForeground(new java.awt.Color(255, 0, 0));
        lblFolio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFolio.setText("1");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID Cliente:");

        txtOrdenId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtOrdenId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOrdenId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrdenIdActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/find16.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add16.png"))); // NOI18N

        lblNom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Problema:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtCom.setColumns(20);
        txtCom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCom.setRows(5);
        jScrollPane1.setViewportView(txtCom);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableTie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableTie);
        tableTie.getColumnModel().getColumn(0).setMaxWidth(60);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOrdenId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtOrdenId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnOrdenAceptar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOrdenAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/apply24.png"))); // NOI18N
        btnOrdenAceptar.setText("Aceptar");
        btnOrdenAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenAceptarActionPerformed(evt);
            }
        });

        btnOrdenCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOrdenCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/cancel.png"))); // NOI18N
        btnOrdenCancelar.setText("Cancelar");
        btnOrdenCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TomaOrdenLayout = new javax.swing.GroupLayout(TomaOrden.getContentPane());
        TomaOrden.getContentPane().setLayout(TomaOrdenLayout);
        TomaOrdenLayout.setHorizontalGroup(
            TomaOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TomaOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TomaOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TomaOrdenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOrdenAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenCancelar)))
                .addContainerGap())
        );
        TomaOrdenLayout.setVerticalGroup(
            TomaOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TomaOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TomaOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOrdenCancelar)
                    .addComponent(btnOrdenAceptar))
                .addContainerGap())
        );

        TomaOrden.setBounds(0, 0, 460, 482);
        desktopPane.add(TomaOrden, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Cotizacion.setClosable(true);
        Cotizacion.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        Cotizacion.setIconifiable(true);
        Cotizacion.setTitle("SSCAutoFrio - Cotización");
        Cotizacion.setToolTipText("");
        Cotizacion.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/cotizar24.png"))); // NOI18N
        Cotizacion.setVisible(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cotización", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Folio Orden:");
        jLabel6.setEnabled(false);

        lblCotId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCotId.setForeground(new java.awt.Color(255, 0, 0));
        lblCotId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Nombre:");
        jLabel9.setEnabled(false);

        lblCotNom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Dirección:");
        jLabel11.setEnabled(false);

        lblCotDir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("R.F.C.");
        jLabel13.setEnabled(false);

        lblCotRfc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Vehículo:");
        jLabel15.setEnabled(false);

        lblCotVeh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Teléfono:");
        jLabel17.setEnabled(false);

        lblCotTel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Servicios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tableDet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableDet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cant", "Concepto", "P. Unit", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableDetKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableDet);
        tableDet.getColumnModel().getColumn(0).setMaxWidth(40);
        tableDet.getColumnModel().getColumn(1).setMaxWidth(40);
        tableDet.getColumnModel().getColumn(3).setMaxWidth(70);
        tableDet.getColumnModel().getColumn(4).setMaxWidth(80);

        btnAnadirSer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add16.png"))); // NOI18N
        btnAnadirSer.setToolTipText("Agregar Servicio");
        btnAnadirSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirSerActionPerformed(evt);
            }
        });

        btnEliminarSer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/del16.png"))); // NOI18N
        btnEliminarSer.setToolTipText("Eliminar Servicio");
        btnEliminarSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSerActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Subtotal:    $");
        jLabel19.setEnabled(false);

        lblTot.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTot.setForeground(new java.awt.Color(255, 0, 0));
        lblTot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("IVA (16%)  $");
        jLabel21.setEnabled(false);

        lblIva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIva.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Total:          $");
        jLabel23.setEnabled(false);

        lblSub.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSub.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSub.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTot, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblIva, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnAnadirSer)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminarSer))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnadirSer)
                    .addComponent(btnEliminarSer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Comentarios:");

        txtComentarios.setColumns(20);
        txtComentarios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComentarios.setRows(5);
        jScrollPane7.setViewportView(txtComentarios);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(144, 144, 144))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblCotId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblCotVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCotTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblCotDir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblCotNom, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCotRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(144, 144, 144))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCotId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCotNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCotRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lblCotDir, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCotVeh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCotTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnCotAce.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCotAce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/apply24.png"))); // NOI18N
        btnCotAce.setText("Aceptar");
        btnCotAce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotAceActionPerformed(evt);
            }
        });

        btnCotCan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCotCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/cancel.png"))); // NOI18N
        btnCotCan.setText("Cancelar");
        btnCotCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotCanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CotizacionLayout = new javax.swing.GroupLayout(Cotizacion.getContentPane());
        Cotizacion.getContentPane().setLayout(CotizacionLayout);
        CotizacionLayout.setHorizontalGroup(
            CotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CotizacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCotAce)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCotCan)
                .addContainerGap())
            .addGroup(CotizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CotizacionLayout.setVerticalGroup(
            CotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CotizacionLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCotCan)
                    .addComponent(btnCotAce))
                .addGap(0, 40, Short.MAX_VALUE))
        );

        Cotizacion.setBounds(0, 0, 587, 636);
        desktopPane.add(Cotizacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        BuscarServicio.setClosable(true);
        BuscarServicio.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        BuscarServicio.setIconifiable(true);
        BuscarServicio.setTitle("SSCAutoFrio - Buscar Servicio");
        BuscarServicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BuscarServicio.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/find16.png"))); // NOI18N
        BuscarServicio.setVisible(false);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Ingrese el nombre del servicio:");

        txtSerNom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSerNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerNomKeyReleased(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/find16.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(txtSerNom, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel8)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSerNom, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnSerCan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSerCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/cancel.png"))); // NOI18N
        btnSerCan.setText("Cancelar");
        btnSerCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerCanActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableSer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableSer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Concepto", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tableSer);
        tableSer.getColumnModel().getColumn(0).setMaxWidth(100);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSerSel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSerSel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/apply24.png"))); // NOI18N
        btnSerSel.setText("Seleccionar");
        btnSerSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerSelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BuscarServicioLayout = new javax.swing.GroupLayout(BuscarServicio.getContentPane());
        BuscarServicio.getContentPane().setLayout(BuscarServicioLayout);
        BuscarServicioLayout.setHorizontalGroup(
            BuscarServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarServicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BuscarServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(BuscarServicioLayout.createSequentialGroup()
                        .addComponent(btnSerSel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSerCan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BuscarServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BuscarServicioLayout.setVerticalGroup(
            BuscarServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarServicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BuscarServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSerSel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSerCan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        BuscarServicio.setBounds(0, 0, 585, 388);
        desktopPane.add(BuscarServicio, javax.swing.JLayeredPane.DEFAULT_LAYER);

        BuscarCliente.setClosable(true);
        BuscarCliente.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        BuscarCliente.setIconifiable(true);
        BuscarCliente.setTitle("SSCAutoFrio - Buscar Cliente");
        BuscarCliente.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/find16.png"))); // NOI18N
        BuscarCliente.setVisible(false);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Ingrese el nombre del cliente:");

        txtBusCli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBusCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusCliKeyReleased(evt);
            }
        });

        btnBusCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBusCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/find16.png"))); // NOI18N
        btnBusCli.setText("Buscar");
        btnBusCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(txtBusCli, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel10)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusCli, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusCli, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnBusCliCan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBusCliCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/cancel.png"))); // NOI18N
        btnBusCliCan.setText("Cancelar");
        btnBusCliCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusCliCanActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableBusCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Ap. Paterno", "Ap. Materno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tableBusCli);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBusCliSel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBusCliSel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/apply24.png"))); // NOI18N
        btnBusCliSel.setText("Seleccionar");
        btnBusCliSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusCliSelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BuscarClienteLayout = new javax.swing.GroupLayout(BuscarCliente.getContentPane());
        BuscarCliente.getContentPane().setLayout(BuscarClienteLayout);
        BuscarClienteLayout.setHorizontalGroup(
            BuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBusCliSel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusCliCan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        BuscarClienteLayout.setVerticalGroup(
            BuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBusCliSel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusCliCan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        BuscarCliente.setBounds(0, 0, 578, 383);
        desktopPane.add(BuscarCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.setRollover(true);

        btnMonitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/monitor24.png"))); // NOI18N
        btnMonitor.setToolTipText("Monitor de Ordenes");
        btnMonitor.setFocusable(false);
        btnMonitor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMonitor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitorActionPerformed(evt);
            }
        });
        jToolBar1.add(btnMonitor);

        btnOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/orden24.png"))); // NOI18N
        btnOrden.setFocusable(false);
        btnOrden.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOrden.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOrden);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void btnMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitorActionPerformed
        if (Monitor.isVisible()) {
            Monitor.setVisible(false);
        } else {
            Monitor.setVisible(true);
            centrar(Monitor);
        }
    }//GEN-LAST:event_btnMonitorActionPerformed

    private void btnOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenActionPerformed
        if (TomaOrden.isVisible()) {
            TomaOrden.setVisible(false);
        } else {
            TomaOrden.setVisible(true);
            centrar(TomaOrden);
            int sig = ne.lastInsert() + 1;
            lblFolio.setText(String.valueOf(sig));
        }
    }//GEN-LAST:event_btnOrdenActionPerformed

    private void txtOrdenIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrdenIdActionPerformed
        buscarCliente();
    }//GEN-LAST:event_txtOrdenIdActionPerformed

    private void btnOrdenAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenAceptarActionPerformed
        if (txtOrdenId.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Debe de ingresar un cliente",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        if (idV == null) {
            JOptionPane.showMessageDialog(this, "Debe de seleccionar un vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        String com = txtCom.getText().trim();
        Date date;
        date = new Date();
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha;
        fecha = dateFormat.format(date);
        try {
            Vehiculo v = new Vehiculo(Integer.parseInt(idV));
            Cliente c = ne.buscarCliente(txtOrdenId.getText().trim());
            Orden o = new Orden(c, v, com, fecha, "Registrada");
            ne.registrarOrden(o);
            JOptionPane.showMessageDialog(this, "Orden Agregada correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "No existe el cliente",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(this, "Ya existe esa orden",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        borrarTiene();
        txtOrdenId.setText("");
        lblNom.setText("");
        txtCom.setText("");
        borrarListas();
        actualizaListas();
        TomaOrden.setVisible(false);
    }//GEN-LAST:event_btnOrdenAceptarActionPerformed

    private void btnOrdenCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenCancelarActionPerformed
        TomaOrden.setVisible(false);
    }//GEN-LAST:event_btnOrdenCancelarActionPerformed

    private void btnMonAgreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonAgreActionPerformed
        if (TomaOrden.isVisible()) {
            TomaOrden.setVisible(false);
        } else {
            TomaOrden.setVisible(true);
            centrar(TomaOrden);
            int sig = ne.lastInsert() + 1;
            lblFolio.setText(String.valueOf(sig));
        }
    }//GEN-LAST:event_btnMonAgreActionPerformed

    private void btnMonElimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonElimActionPerformed
        try {
            Orden o = new Orden(Integer.parseInt(id));
            Orden buscarOrden = ne.buscarOrden(o);
            o.setIdOrden(buscarOrden.getIdOrden());
            o.setIdCliente(buscarOrden.getIdCliente());
            o.setIdVehiculo(buscarOrden.getIdVehiculo());
            ne.eliminarOrden(o);
            JOptionPane.showMessageDialog(this, "Orden Eliminada correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "No existe la orden",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        borrarListas();
        actualizaListas();
    }//GEN-LAST:event_btnMonElimActionPerformed

    private void btnMonCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonCotActionPerformed
        pasarCotizacion();
        borrarDet();
        lblSub.setText("");
        lblIva.setText("");
        lblTot.setText("");
        txtComentarios.setText("");
        btnCotAce.setEnabled(true);
    }//GEN-LAST:event_btnMonCotActionPerformed

    private void txtSerNomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerNomKeyReleased
        borrarServ();
        try {
            actualizaListaServ();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSerNomKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        borrarServ();
        try {
            actualizaListaServ();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSerCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerCanActionPerformed
        BuscarServicio.setVisible(false);
    }//GEN-LAST:event_btnSerCanActionPerformed

    private void btnSerSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerSelActionPerformed
        int fila = 0;
        fila = tableSer.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe de seleccionar un servicio "
                    + "de la lista para poder agregarlo a la cotización",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            Boolean repetido = false;
            idSer = tableSer.getValueAt(fila, 0).toString();
            cant = 1.0;
            desc = tableSer.getValueAt(fila, 1).toString();
            punit = Double.parseDouble(tableSer.getValueAt(fila, 2).toString());
            impor = punit * cant;
            for (int i = 0; i < tableDet.getRowCount(); i++) {
                if (tableDet.getValueAt(i, 0).equals(idSer)) {
                    JOptionPane.showMessageDialog(this, "Ya se encuentra agregado "
                            + "el servicio",
                            "Error", JOptionPane.ERROR_MESSAGE, null);
                    repetido = true;
                }
            }
            if (repetido == false) {
                table6.addRow(new Object[]{idSer, cant, desc, punit, impor});
                BuscarServicio.setVisible(false);
                subt = 0.0;
                calcularImporte();
            }
        }
    }//GEN-LAST:event_btnSerSelActionPerformed

    private void btnAnadirSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirSerActionPerformed
        if (BuscarServicio.isVisible()) {
            BuscarServicio.setVisible(false);
        } else {
            borrarServ();
            BuscarServicio.setVisible(true);
            centrar(BuscarServicio);
            try {
                actualizaListaServ();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAnadirSerActionPerformed

    private void btnCotCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotCanActionPerformed
        Cotizacion.setVisible(false);
    }//GEN-LAST:event_btnCotCanActionPerformed

    private void tableDetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDetKeyReleased
        int fila = 0;
        fila = tableDet.getSelectedRow();
        double c1 = Double.parseDouble(tableDet.getValueAt(fila, 1).toString());
        double c2 = Double.parseDouble(tableDet.getValueAt(fila, 3).toString());
        double c3 = c1 * c2;
        tableDet.setValueAt(c3, fila, 4);
        subt = 0.0;
        calcularImporte();
    }//GEN-LAST:event_tableDetKeyReleased

    private void btnEliminarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSerActionPerformed
        int fila = 0;
        fila = tableDet.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe de seleccionar una fila a "
                    + "eliminar",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            table6.removeRow(fila);
            subt = 0.0;
            calcularImporte();
        }
    }//GEN-LAST:event_btnEliminarSerActionPerformed

    private void btnCotAceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotAceActionPerformed
        if (tableDet.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "La cotización debe contener al"
                    + " menos un servicio a realizar",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            int idOrden = Integer.parseInt(lblCotId.getText().trim());
            try {
                Orden b = new Orden(idOrden);
                Orden o = ne.buscarOrden(b);
                o.setIdOrden(idOrden);
                o.setIdCliente(o.getIdCliente());
                o.setIdVehiculo(o.getIdVehiculo());
                o.setSubtotal(Double.parseDouble(lblSub.getText().trim()));
                o.setIva(Double.parseDouble(lblIva.getText().trim()));
                o.setTotal(Double.parseDouble(lblTot.getText().trim()));
                o.setComentarios(txtComentarios.getText().trim());
                o.setEstado("Cotizada");
                for (int i = 0; i < tableDet.getRowCount(); i++) {
                    int idServicio = Integer.parseInt(tableDet.getValueAt(i, 0).toString());
                    double cantidad = Double.parseDouble(tableDet.getValueAt(i, 1).toString());
                    double punit = Double.parseDouble(tableDet.getValueAt(i, 3).toString());
                    double importe = Double.parseDouble(tableDet.getValueAt(i, 4).toString());
                    Orden orden = new Orden(o.getIdOrden());
                    Servicio ser = new Servicio(idServicio);
                    Detalleorden detOrden = new Detalleorden(orden, ser, cantidad, punit, importe);
                    try {
                        ne.guardarDetalle(detOrden);
                    } catch (PreexistingEntityException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                ne.actulizarOrden(o);
                re = new Reportes();
                re.generarCotizacion(o.getIdOrden().toString());
                JOptionPane.showMessageDialog(this, "Orden Cotizada correctamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
                Cotizacion.setVisible(false);
            } catch (NonexistentEntityException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "No se pudo cotizar la orden",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error en el sistema",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
            borrarListas();
            actualizaListas();
        }
    }//GEN-LAST:event_btnCotAceActionPerformed

    private void btnMonCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonCanActionPerformed
        estado("Cancelada");
    }//GEN-LAST:event_btnMonCanActionPerformed

    private void btnMonAceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonAceActionPerformed
        estado("Aceptada");
    }//GEN-LAST:event_btnMonAceActionPerformed

    private void btnMonRegACotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonRegACotActionPerformed
        estado("Cotizada");
    }//GEN-LAST:event_btnMonRegACotActionPerformed

    private void btnMonLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonLibActionPerformed
        estado("Liberada");
    }//GEN-LAST:event_btnMonLibActionPerformed

    private void txtBusCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusCliKeyReleased
        borrarBuscarClientes();
        try {
            actualizaListaBuscarClientes();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBusCliKeyReleased

    private void btnBusCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusCliActionPerformed
        borrarBuscarClientes();
        try {
            actualizaListaBuscarClientes();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBusCliActionPerformed

    private void btnBusCliCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusCliCanActionPerformed
        BuscarCliente.setVisible(false);
    }//GEN-LAST:event_btnBusCliCanActionPerformed

    private void btnBusCliSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusCliSelActionPerformed
        int fila = 0;
        fila = tableBusCli.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe de seleccionar un cliente "
                    + "de la lista para poder agregarlo a la toma de orden",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            txtOrdenId.setText(tableBusCli.getValueAt(fila, 0).toString());
            buscarCliente();
            BuscarCliente.setVisible(false);
        }

    }//GEN-LAST:event_btnBusCliSelActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (BuscarCliente.isVisible()) {
            BuscarCliente.setVisible(false);
        } else {
            borrarBuscarClientes();
            BuscarCliente.setVisible(true);
            centrar(BuscarCliente);
            try {
                actualizaListaBuscarClientes();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void actualizaListaBuscarClientes() throws NonexistentEntityException {
        String nombre = txtBusCli.getText().trim();
        if (!nombre.equalsIgnoreCase("")) {
            List<Cliente> lista = ne.getClienteLike(nombre);
            for (Cliente c : lista) {
                table7.addRow(new Object[]{c.getIdCliente(), c.getNombre(),
                    c.getApellidoPat(), c.getApellidoMat()});
            }
        } else {
            List<Cliente> lista = ne.listaClientes();
            for (Cliente c : lista) {
                table7.addRow(new Object[]{c.getIdCliente(), c.getNombre(),
                    c.getApellidoPat(), c.getApellidoMat()});
            }
        }
    }

    private void borrarBuscarClientes() {
        for (int i = 0; i < tableBusCli.getRowCount(); i++) {
            table7.removeRow(i);
            i--;
        }
    }

    private void estado(String estado) {
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Debe de seleccionar una orden",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            Orden o = new Orden(Integer.parseInt(id));
            try {
                Orden buscarOrden = ne.buscarOrden(o);
                buscarOrden.setIdOrden(buscarOrden.getIdOrden());
                buscarOrden.setIdCliente(buscarOrden.getIdCliente());
                buscarOrden.setIdVehiculo(buscarOrden.getIdVehiculo());
                buscarOrden.setEstado(estado);
                ne.actulizarOrden(buscarOrden);
                JOptionPane.showMessageDialog(this, "Orden " + estado + " correctamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
                borrarListas();
                actualizaListas();
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(this, "Error en la base de datos",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en el sistema",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }

    private void pasarCotizacion() {
        if (Cotizacion.isVisible()) {
            Cotizacion.setVisible(false);
        } else {
            if (id == null) {
                JOptionPane.showMessageDialog(this, "Debe de seleccionar una orden",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            } else {
                Cotizacion.setVisible(true);
                centrar(Cotizacion);
                try {
                    Orden o = new Orden(Integer.parseInt(id));
                    Orden buscarOrden = ne.buscarOrden(o);
                    o.setIdOrden(buscarOrden.getIdOrden());
                    o.setIdCliente(buscarOrden.getIdCliente());
                    o.setIdVehiculo(buscarOrden.getIdVehiculo());
                    lblCotId.setText(o.getIdOrden().toString());
                    lblCotNom.setText(o.getIdCliente().getNombre() + " "
                            + o.getIdCliente().getApellidoPat() + " "
                            + o.getIdCliente().getApellidoMat());
                    lblCotRfc.setText(o.getIdCliente().getRfc());
                    lblCotDir.setText(o.getIdCliente().getDireccion() + " "
                            + o.getIdCliente().getNumExt() + " "
                            + o.getIdCliente().getColonia() + " "
                            + o.getIdCliente().getCp());
                    lblCotVeh.setText(o.getIdVehiculo().getAño().toString() + " "
                            + o.getIdVehiculo().getMarca() + " "
                            + o.getIdVehiculo().getModelo() + " "
                            + o.getIdVehiculo().getCilindros() + " "
                            + o.getIdVehiculo().getMotor());
                    lblCotTel.setText(o.getIdCliente().getIdCliente());
                } catch (NonexistentEntityException ex) {
                    JOptionPane.showMessageDialog(this, "No existe la orden",
                            "Error", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        }
    }

    private void actualizaListaDet() {
        Orden o = new Orden(Integer.parseInt(id));
        try {
            Orden buscarOrden = ne.buscarOrden(o);
            List<Detalleorden> lista = ne.getDetalleOrden(buscarOrden);
            for (Detalleorden detOrden : lista) {
                table6.addRow(new Object[]{
                    detOrden.getIdServicio().getIdServicio(),
                    detOrden.getCantidad(),
                    detOrden.getIdServicio().getDescripcion(),
                    detOrden.getPUnit(), detOrden.getImporte()});
            }
            lblSub.setText(buscarOrden.getSubtotal().toString());
            lblIva.setText(buscarOrden.getIva().toString());
            lblTot.setText(buscarOrden.getTotal().toString());
            txtComentarios.setText(buscarOrden.getComentarios());
            btnCotAce.setEnabled(false);


        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void borrarDet() {
        for (int i = 0; i < tableDet.getRowCount(); i++) {
            table6.removeRow(i);
            i--;
        }
    }

    private void actualizaListaServ() throws NonexistentEntityException {
        String nombre = txtSerNom.getText().trim();
        if (!nombre.equalsIgnoreCase("")) {
            List<Servicio> lista = ne.getServicioLike(nombre);
            for (Servicio s : lista) {
                table5.addRow(new Object[]{s.getIdServicio(),
                    s.getDescripcion(), s.getPrecio()});
            }
        } else {
            List<Servicio> lista = ne.listaServicios();
            for (Servicio s : lista) {
                table5.addRow(new Object[]{s.getIdServicio(),
                    s.getDescripcion(), s.getPrecio()});
            }
        }
    }

    private void borrarServ() {
        for (int i = 0; i < tableSer.getRowCount(); i++) {
            table5.removeRow(i);
            i--;
        }
    }

    private void calcularImporte() {
        for (int i = 0; i < tableDet.getRowCount(); i++) {
            subt += Double.parseDouble(tableDet.getValueAt(i, 4).toString());
        }
        iva = subt * 0.16;
        tot = subt + iva;
        lblSub.setText(Double.toString(subt));
        lblIva.setText(Double.toString(iva));
        lblTot.setText(Double.toString(tot));
    }

    //<editor-fold defaultstate="collapsed" desc=" Registrar Orden ">    
    private void buscarCliente() {
        if (!txtOrdenId.getText().equals("")) {
            try {
                Cliente c = ne.buscarCliente(txtOrdenId.getText().trim());
                lblNom.setText(c.getNombre() + " " + c.getApellidoPat() + " " + c.getApellidoMat());
                borrarTiene();
                cargarVehiculos(c);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "     No se encontro al Cliente", "Error", JOptionPane.ERROR_MESSAGE);
                lblNom.setText("");
            }
        } else {
            lblNom.setText("");
        }
    }

    private void cargarVehiculos(Cliente cliente) throws NonexistentEntityException {
        List<Tiene> tiene = ne.getVehiculosPorCliente(cliente);
        for (Tiene t : tiene) {
            table4.addRow(new Object[]{t.getIdVehiculo().getIdVehiculo(),
                t.getIdVehiculo().getAño() + " " + t.getIdVehiculo().getMarca()
                + " " + t.getIdVehiculo().getModelo() + " "
                + t.getIdVehiculo().getCilindros() + " "
                + t.getIdVehiculo().getMotor()});
        }
    }

    public void borrarTiene() {
        for (int i = 0; i < tableTie.getRowCount(); i++) {
            table4.removeRow(i);
            i--;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Listas Monitor ">    
    private void actualizaListas() {
        List<Orden> lista1 = ne.listaEstado("Registrada");
        List<Orden> lista2 = ne.listaEstado("Cotizada");
        List<Orden> lista3 = ne.listaEstado("Aceptada");
        for (Orden o : lista1) {
            table1.addRow(new Object[]{o.getIdOrden(),
                o.getIdCliente().getNombre() + " "
                + o.getIdCliente().getApellidoPat(),
                o.getIdVehiculo().getAño() + " "
                + o.getIdVehiculo().getModelo() + " "
                + o.getIdVehiculo().getMarca(), o.getFecha()});
        }
        for (Orden o : lista2) {
            table2.addRow(new Object[]{o.getIdOrden(),
                o.getIdCliente().getNombre() + " "
                + o.getIdCliente().getApellidoPat(),
                o.getIdVehiculo().getAño() + " "
                + o.getIdVehiculo().getModelo() + " "
                + o.getIdVehiculo().getMarca(), o.getFecha()});
        }
        for (Orden o : lista3) {
            table3.addRow(new Object[]{o.getIdOrden(),
                o.getIdCliente().getNombre() + " "
                + o.getIdCliente().getApellidoPat(),
                o.getIdVehiculo().getAño() + " "
                + o.getIdVehiculo().getModelo() + " "
                + o.getIdVehiculo().getMarca(), o.getFecha()});
        }
    }

    public void borrarListas() {
        for (int i = 0; i < table1.getRowCount(); i++) {
            table1.removeRow(i);
            i--;
        }
        for (int i = 0; i < table2.getRowCount(); i++) {
            table2.removeRow(i);
            i--;
        }
        for (int i = 0; i < table3.getRowCount(); i++) {
            table3.removeRow(i);
            i--;
        }
    }
    //</editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Synthetica Look and Feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SSCAutoFrio().setVisible(true);
            }
        });
    }
    //<editor-fold defaultstate="collapsed" desc=" Variables ">    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame BuscarCliente;
    private javax.swing.JInternalFrame BuscarServicio;
    private javax.swing.JInternalFrame Cotizacion;
    private javax.swing.JInternalFrame Monitor;
    private javax.swing.JInternalFrame TomaOrden;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btnAnadirSer;
    private javax.swing.JButton btnBusCli;
    private javax.swing.JButton btnBusCliCan;
    private javax.swing.JButton btnBusCliSel;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCotAce;
    private javax.swing.JButton btnCotCan;
    private javax.swing.JButton btnEliminarSer;
    private javax.swing.JButton btnMonAce;
    private javax.swing.JButton btnMonAgre;
    private javax.swing.JButton btnMonCan;
    private javax.swing.JButton btnMonCot;
    private javax.swing.JButton btnMonElim;
    private javax.swing.JButton btnMonLib;
    private javax.swing.JButton btnMonRegACot;
    private javax.swing.JButton btnMonitor;
    private javax.swing.JButton btnOrden;
    private javax.swing.JButton btnOrdenAceptar;
    private javax.swing.JButton btnOrdenCancelar;
    private javax.swing.JButton btnSerCan;
    private javax.swing.JButton btnSerSel;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCotDir;
    private javax.swing.JLabel lblCotId;
    private javax.swing.JLabel lblCotNom;
    private javax.swing.JLabel lblCotRfc;
    private javax.swing.JLabel lblCotTel;
    private javax.swing.JLabel lblCotVeh;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTot;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTable tableAce;
    private javax.swing.JTable tableBusCli;
    private javax.swing.JTable tableCot;
    private javax.swing.JTable tableDet;
    private javax.swing.JTable tableReg;
    private javax.swing.JTable tableSer;
    private javax.swing.JTable tableTie;
    private javax.swing.JTextField txtBusCli;
    private javax.swing.JTextArea txtCom;
    private javax.swing.JTextArea txtComentarios;
    private javax.swing.JTextField txtOrdenId;
    private javax.swing.JTextField txtSerNom;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}