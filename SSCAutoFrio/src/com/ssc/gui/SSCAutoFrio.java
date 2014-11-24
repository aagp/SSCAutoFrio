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

//<editor-fold defaultstate="collapsed" desc=" Imports ">
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
import java.awt.event.KeyEvent;
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
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
//</editor-fold>

/**
 * Class SSCAutoFrio
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class SSCAutoFrio extends javax.swing.JFrame {

    //<editor-fold defaultstate="collapsed" desc=" SSCAutoFrio ">
    /**
     * Creates new form SSCAutoFrio
     */
    public SSCAutoFrio() {
        ne = new FNegocio();
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        initTables();
        actualizaListas();
    }
    //</editor-fold>

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

    //<editor-fold defaultstate="collapsed" desc=" Tables ">
    private void initTables() {
        table1 = (DefaultTableModel) tableReg.getModel();
        table2 = (DefaultTableModel) tableCot.getModel();
        table3 = (DefaultTableModel) tableAce.getModel();
        table4 = (DefaultTableModel) tableTie.getModel();
        table5 = (DefaultTableModel) tableSer.getModel();
        table6 = (DefaultTableModel) tableDet.getModel();
        table7 = (DefaultTableModel) tableBusCli.getModel();
        tableCliBus.setAutoCreateRowSorter(true);
        table8 = (DefaultTableModel) tableCliBus.getModel();
        table9 = (DefaultTableModel) tableVeh.getModel();
        table10 = (DefaultTableModel) tableCliTie.getModel();
        table11 = (DefaultTableModel) tableServ.getModel();
        tableCot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableCot.getSelectedRow();
                id = table2.getValueAt(fila, 0).toString();
                if (e.getClickCount() == 2) {
                    id = table2.getValueAt(fila, 0).toString();
                    pasarCotizacion();
                    borrarTabla(table6, tableDet);
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
                    borrarTabla(table6, tableDet);
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
                idV = table4.getValueAt(fila, 1).toString();
            }
        });
        tableCliBus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableCliBus.getSelectedRow();
                idCli = table8.getValueAt(fila, 0).toString();
                datosCliente(idCli);
            }
        });
        tableCliTie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableCliTie.getSelectedRow();
                idTie = Integer.parseInt(table10.getValueAt(fila, 0).toString());
            }
        });
        tableVeh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableVeh.getSelectedRow();
                idVeh = Integer.parseInt(table9.getValueAt(fila, 0).toString());
                datosVehiculo(idVeh);
            }
        });
        tableServ.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila;
                fila = tableServ.getSelectedRow();
                idServ = Integer.parseInt(table11.getValueAt(fila, 0).toString());
                datosServicio(idServ);
            }
        });
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
        btnOrdBusCli = new javax.swing.JButton();
        btnOrdenAgregaCliente = new javax.swing.JButton();
        lblNom = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCom = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTie = new javax.swing.JTable();
        btnOrdenAgregaVehiculo = new javax.swing.JButton();
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
        AdmonClientes = new javax.swing.JInternalFrame();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCliNom = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCliAPat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCliAMat = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCliDir = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtCliNExt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCliNInt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtCliCol = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtCliMun = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtCliCiu = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtCliEst = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtCliCp = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtCliPai = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtCliRfc = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtCliTel = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtCliEma = new javax.swing.JTextField();
        jToolBar2 = new javax.swing.JToolBar();
        btnCliNue = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnCliAgr = new javax.swing.JButton();
        btnCliAct = new javax.swing.JButton();
        btnCliEli = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnCliBus = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableCliBus = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        txtCliBus = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableCliTie = new javax.swing.JTable();
        btnCliAgrVeh = new javax.swing.JButton();
        btnCliEliVeh = new javax.swing.JButton();
        AdmonVehiculos = new javax.swing.JInternalFrame();
        jPanel11 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtVehAn = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtVehMar = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtVehMod = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtVehCil = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtVehMot = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtVehId = new javax.swing.JTextField();
        jToolBar3 = new javax.swing.JToolBar();
        btnVehNue = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnVehAgr = new javax.swing.JButton();
        btnVehAct = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        txtVehBus = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableVeh = new javax.swing.JTable();
        btnVehSel = new javax.swing.JButton();
        AdmonServicios = new javax.swing.JInternalFrame();
        jToolBar4 = new javax.swing.JToolBar();
        btnSerNue = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnSerAgr = new javax.swing.JButton();
        txtSerAct = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txtSerId = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtSerDesc = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtSerPre = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        txtSerBus = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableServ = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnMonitor = new javax.swing.JButton();
        btnOrden = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnClientes = new javax.swing.JButton();
        btnServicios = new javax.swing.JButton();
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

        tableReg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableReg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Vehículo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableReg);
        tableReg.getColumnModel().getColumn(0).setMaxWidth(50);
        tableReg.getColumnModel().getColumn(3).setPreferredWidth(20);

        tableCot.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableCot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Vehículo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tableCot);
        tableCot.getColumnModel().getColumn(0).setMaxWidth(50);
        tableCot.getColumnModel().getColumn(3).setPreferredWidth(20);

        tableAce.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableAce.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Vehículo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableAce);
        tableAce.getColumnModel().getColumn(0).setMaxWidth(50);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Orden", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

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

        btnOrdBusCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/find16.png"))); // NOI18N
        btnOrdBusCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdBusCliActionPerformed(evt);
            }
        });

        btnOrdenAgregaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add16.png"))); // NOI18N

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

        tableTie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableTie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdTiene", "Id", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableTie);
        tableTie.getColumnModel().getColumn(0).setMinWidth(0);
        tableTie.getColumnModel().getColumn(0).setMaxWidth(0);
        tableTie.getColumnModel().getColumn(1).setMaxWidth(60);

        btnOrdenAgregaVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add16.png"))); // NOI18N
        btnOrdenAgregaVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenAgregaVehiculoActionPerformed(evt);
            }
        });

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
                        .addComponent(btnOrdBusCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenAgregaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrdenAgregaVehiculo)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOrdBusCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOrdenAgregaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtOrdenId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdenAgregaVehiculo))
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

        TomaOrden.setBounds(0, 0, 469, 482);
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

        tableDet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        tableSer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableSer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción", "Precio"
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
        tableSer.getColumnModel().getColumn(0).setMaxWidth(50);
        tableSer.getColumnModel().getColumn(2).setMaxWidth(150);

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
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(BuscarServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(BuscarServicioLayout.createSequentialGroup()
                        .addComponent(btnSerSel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSerCan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BuscarServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
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

        BuscarServicio.setBounds(0, 0, 594, 388);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tableBusCli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        BuscarCliente.setBounds(0, 0, 561, 383);
        desktopPane.add(BuscarCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        AdmonClientes.setClosable(true);
        AdmonClientes.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AdmonClientes.setIconifiable(true);
        AdmonClientes.setTitle("SSCAutoFrio - Administrar Clientes");
        AdmonClientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AdmonClientes.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/clientes.png"))); // NOI18N
        AdmonClientes.setVisible(false);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Id:");

        txtCliId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Nombre:");

        txtCliNom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("A. Paterno:");

        txtCliAPat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("A. Materno:");

        txtCliAMat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Dirección:");

        txtCliDir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Num. Ext.");

        txtCliNExt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Num. Int.");

        txtCliNInt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Colonia:");

        txtCliCol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Municipio:");

        txtCliMun.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Ciudad:");

        txtCliCiu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Estado:");

        txtCliEst.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("C.P.");

        txtCliCp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("País:");

        txtCliPai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("R.F.C.");

        txtCliRfc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Teléfono:");

        txtCliTel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Email:");

        txtCliEma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        btnCliNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/new24.png"))); // NOI18N
        btnCliNue.setToolTipText("Nuevo");
        btnCliNue.setFocusable(false);
        btnCliNue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCliNue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCliNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliNueActionPerformed(evt);
            }
        });
        jToolBar2.add(btnCliNue);
        jToolBar2.add(jSeparator1);

        btnCliAgr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add24.png"))); // NOI18N
        btnCliAgr.setToolTipText("Agregar");
        btnCliAgr.setFocusable(false);
        btnCliAgr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCliAgr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCliAgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAgrActionPerformed(evt);
            }
        });
        jToolBar2.add(btnCliAgr);

        btnCliAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/refresh24.png"))); // NOI18N
        btnCliAct.setToolTipText("Actualizar");
        btnCliAct.setFocusable(false);
        btnCliAct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCliAct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCliAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActActionPerformed(evt);
            }
        });
        jToolBar2.add(btnCliAct);

        btnCliEli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/del24.png"))); // NOI18N
        btnCliEli.setToolTipText("Eliminar");
        btnCliEli.setFocusable(false);
        btnCliEli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCliEli.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCliEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEliActionPerformed(evt);
            }
        });
        jToolBar2.add(btnCliEli);
        jToolBar2.add(jSeparator2);

        btnCliBus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/find24.png"))); // NOI18N
        btnCliBus.setToolTipText("Buscar");
        btnCliBus.setFocusable(false);
        btnCliBus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCliBus.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCliBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliBusActionPerformed(evt);
            }
        });
        jToolBar2.add(btnCliBus);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliMun, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCliPai, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel27)
                                .addGap(154, 154, 154)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCliRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel33))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCliCiu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCliEst, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCliCp, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(txtCliEma)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliNom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliAPat, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliAMat))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCliDir, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliNExt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliNInt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliCol, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCliNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(txtCliAPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtCliAMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtCliDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtCliNExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtCliNInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txtCliCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(txtCliCiu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29)
                        .addComponent(txtCliEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)
                        .addComponent(txtCliCp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCliMun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(txtCliRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(txtCliEma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        tableCliBus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableCliBus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "A. Pat", "A. Mat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCliBus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableCliBusKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(tableCliBus);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Ingrese el nombre del cliente:");

        txtCliBus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCliBus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliBusKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliBus, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtCliBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vehículos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        tableCliTie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableCliTie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdTiene", "Id", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tableCliTie);
        tableCliTie.getColumnModel().getColumn(0).setMinWidth(0);
        tableCliTie.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCliTie.getColumnModel().getColumn(1).setMaxWidth(50);

        btnCliAgrVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add16.png"))); // NOI18N
        btnCliAgrVeh.setToolTipText("Añadir Vehículo");
        btnCliAgrVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAgrVehActionPerformed(evt);
            }
        });

        btnCliEliVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/del16.png"))); // NOI18N
        btnCliEliVeh.setToolTipText("Remover Vehículo");
        btnCliEliVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEliVehActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnCliAgrVeh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCliEliVeh)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliAgrVeh)
                    .addComponent(btnCliEliVeh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout AdmonClientesLayout = new javax.swing.GroupLayout(AdmonClientes.getContentPane());
        AdmonClientes.getContentPane().setLayout(AdmonClientesLayout);
        AdmonClientesLayout.setHorizontalGroup(
            AdmonClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdmonClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdmonClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AdmonClientesLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        AdmonClientesLayout.setVerticalGroup(
            AdmonClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdmonClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdmonClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        AdmonClientes.setBounds(0, 0, 862, 647);
        desktopPane.add(AdmonClientes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        AdmonClientes.getAccessibleContext().setAccessibleName("AdmonClientes");

        AdmonVehiculos.setClosable(true);
        AdmonVehiculos.setIconifiable(true);
        AdmonVehiculos.setTitle("SSCAutoFrio - Administración de Vehículos");
        AdmonVehiculos.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/veh.png"))); // NOI18N
        AdmonVehiculos.setVisible(false);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Añadir Nuevo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Año:");

        txtVehAn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVehAn.setToolTipText("Ej: 1999");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Marca:");

        txtVehMar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVehMar.setToolTipText("Ej: Ford");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Modelo:");

        txtVehMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVehMod.setToolTipText("Ej: Ranger");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Cilindros:");

        txtVehCil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVehCil.setToolTipText("Ej: 4 cil.");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Motor:");

        txtVehMot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVehMot.setToolTipText("Ej: 3.0");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Id:");

        txtVehId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVehId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVehId.setEnabled(false);

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);

        btnVehNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/new24.png"))); // NOI18N
        btnVehNue.setToolTipText("Nuevo");
        btnVehNue.setFocusable(false);
        btnVehNue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVehNue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVehNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehNueActionPerformed(evt);
            }
        });
        jToolBar3.add(btnVehNue);
        jToolBar3.add(jSeparator4);

        btnVehAgr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add24.png"))); // NOI18N
        btnVehAgr.setToolTipText("Agregar");
        btnVehAgr.setFocusable(false);
        btnVehAgr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVehAgr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVehAgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehAgrActionPerformed(evt);
            }
        });
        jToolBar3.add(btnVehAgr);

        btnVehAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/refresh24.png"))); // NOI18N
        btnVehAct.setToolTipText("Actualizar");
        btnVehAct.setFocusable(false);
        btnVehAct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVehAct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVehAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehActActionPerformed(evt);
            }
        });
        jToolBar3.add(btnVehAct);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel38)
                    .addComponent(jLabel37)
                    .addComponent(jLabel40)
                    .addComponent(jLabel36)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVehId, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehMod, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehMar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehCil, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehMot, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVehAn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtVehId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txtVehMar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVehMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtVehCil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVehMot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtVehAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Ingrese el modelo del vehículo:");

        txtVehBus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtVehBus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVehBusKeyReleased(evt);
            }
        });

        tableVeh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableVeh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Año", "Marca", "Modelo", "Cilindros", "Motor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tableVeh);
        tableVeh.getColumnModel().getColumn(0).setMaxWidth(50);

        btnVehSel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVehSel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/apply24.png"))); // NOI18N
        btnVehSel.setText("Seleccionar");
        btnVehSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehSelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVehBus, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVehSel)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtVehBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVehSel)
                .addContainerGap())
        );

        javax.swing.GroupLayout AdmonVehiculosLayout = new javax.swing.GroupLayout(AdmonVehiculos.getContentPane());
        AdmonVehiculos.getContentPane().setLayout(AdmonVehiculosLayout);
        AdmonVehiculosLayout.setHorizontalGroup(
            AdmonVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdmonVehiculosLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdmonVehiculosLayout.setVerticalGroup(
            AdmonVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdmonVehiculosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdmonVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AdmonVehiculosLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );

        AdmonVehiculos.setBounds(0, 0, 703, 408);
        desktopPane.add(AdmonVehiculos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        AdmonServicios.setClosable(true);
        AdmonServicios.setIconifiable(true);
        AdmonServicios.setTitle("SSCAutoFrio - Administrar Servicios");
        AdmonServicios.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/serv24.png"))); // NOI18N
        AdmonServicios.setVisible(false);

        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);

        btnSerNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/new24.png"))); // NOI18N
        btnSerNue.setToolTipText("Nuevo");
        btnSerNue.setFocusable(false);
        btnSerNue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSerNue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSerNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerNueActionPerformed(evt);
            }
        });
        jToolBar4.add(btnSerNue);
        jToolBar4.add(jSeparator5);

        btnSerAgr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/add24.png"))); // NOI18N
        btnSerAgr.setToolTipText("Agregar");
        btnSerAgr.setFocusable(false);
        btnSerAgr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSerAgr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSerAgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerAgrActionPerformed(evt);
            }
        });
        jToolBar4.add(btnSerAgr);

        txtSerAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/refresh24.png"))); // NOI18N
        txtSerAct.setToolTipText("Modificar");
        txtSerAct.setFocusable(false);
        txtSerAct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtSerAct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        txtSerAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerActActionPerformed(evt);
            }
        });
        jToolBar4.add(txtSerAct);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Id:");

        txtSerId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSerId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSerId.setEnabled(false);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Descripción:");

        txtSerDesc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Precio:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSerId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSerDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSerPre, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtSerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(txtSerDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(txtSerPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Introdusca la descripción del servicio:");

        txtSerBus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSerBus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerBusKeyReleased(evt);
            }
        });

        tableServ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableServ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(tableServ);
        tableServ.getColumnModel().getColumn(0).setMaxWidth(50);
        tableServ.getColumnModel().getColumn(2).setMaxWidth(100);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSerBus, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtSerBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AdmonServiciosLayout = new javax.swing.GroupLayout(AdmonServicios.getContentPane());
        AdmonServicios.getContentPane().setLayout(AdmonServiciosLayout);
        AdmonServiciosLayout.setHorizontalGroup(
            AdmonServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(AdmonServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdmonServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdmonServiciosLayout.setVerticalGroup(
            AdmonServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdmonServiciosLayout.createSequentialGroup()
                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        AdmonServicios.setBounds(0, 0, 713, 541);
        desktopPane.add(AdmonServicios, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.setFloatable(false);
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
        btnOrden.setToolTipText("Registrar Orden");
        btnOrden.setFocusable(false);
        btnOrden.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOrden.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOrden);
        jToolBar1.add(jSeparator3);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/clientes.png"))); // NOI18N
        btnClientes.setToolTipText("Administrar Clientes");
        btnClientes.setFocusable(false);
        btnClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnClientes);

        btnServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ssc/img/serv24.png"))); // NOI18N
        btnServicios.setToolTipText("Admonistrar Servicios");
        btnServicios.setFocusable(false);
        btnServicios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnServicios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosActionPerformed(evt);
            }
        });
        jToolBar1.add(btnServicios);

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

    //<editor-fold defaultstate="collapsed" desc=" Monitor: Actions Performed  "> 
    private void btnMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitorActionPerformed
        if (Monitor.isVisible()) {
            Monitor.setVisible(false);
        } else {
            Monitor.setVisible(true);
            centrar(Monitor);
        }
    }//GEN-LAST:event_btnMonitorActionPerformed

    private void btnMonAgreActionPerformed(java.awt.event.ActionEvent evt) {
        if (TomaOrden.isVisible()) {
            TomaOrden.setVisible(false);
        } else {
            TomaOrden.setVisible(true);
            centrar(TomaOrden);
            idV = "";
            int sig = ne.lastInsert() + 1;
            lblFolio.setText(String.valueOf(sig));
        }
    }

    private void btnMonElimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonElimActionPerformed
        Orden o = new Orden(Integer.parseInt(id));
        try {
            Orden buscarOrden = ne.buscarOrden(o);
            if (buscarOrden.getEstado().equalsIgnoreCase("Registrada")) {
                try {
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
            } else {
                JOptionPane.showMessageDialog(this, "La orden no se encuentra"
                        + " en el apartado de registros, por lo tanto no se "
                        + " puede eliminar la orden",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnMonElimActionPerformed

    private void btnMonCotActionPerformed(java.awt.event.ActionEvent evt) {
        Orden o = new Orden(Integer.parseInt(id));
        try {
            Orden buscarOrden = ne.buscarOrden(o);
            if (buscarOrden.getEstado().equalsIgnoreCase("Registrada")) {
                pasarCotizacion();
                borrarTabla(table6, tableDet);
                lblSub.setText("");
                lblIva.setText("");
                lblTot.setText("");
                txtComentarios.setText("");
                btnCotAce.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "La orden no se encuentra"
                        + " en el apartado de registros, por lo tanto no se "
                        + " puede cotizar la orden",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnMonCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonCanActionPerformed
        Orden o = new Orden(Integer.parseInt(id));
        try {
            Orden buscarOrden = ne.buscarOrden(o);
            if (buscarOrden.getEstado().equalsIgnoreCase("Cotizada")) {
                estado("Cancelada");
            } else {
                JOptionPane.showMessageDialog(this, "La orden no se encuentra"
                        + " cotizada, por lo tanto no se puede cancelar la orden",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMonCanActionPerformed

    private void btnMonAceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonAceActionPerformed
        Orden o = new Orden(Integer.parseInt(id));
        try {
            Orden buscarOrden = ne.buscarOrden(o);
            if (buscarOrden.getEstado().equalsIgnoreCase("Cotizada")) {
                estado("Aceptada");
            } else {
                JOptionPane.showMessageDialog(this, "La orden no se encuentra"
                        + " cotizada, por lo tanto no se puede aceptar la orden",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMonAceActionPerformed

    private void btnMonRegACotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonRegACotActionPerformed
        Orden o = new Orden(Integer.parseInt(id));
        try {
            Orden buscarOrden = ne.buscarOrden(o);
            if (buscarOrden.getEstado().equalsIgnoreCase("Aceptada")) {
                estado("Cotizada");
            } else {
                JOptionPane.showMessageDialog(this, "La orden no se encuentra"
                        + " aceptada, por lo tanto no se puede regresar a cotizada",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMonRegACotActionPerformed

    private void btnMonLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonLibActionPerformed
        Orden o = new Orden(Integer.parseInt(id));
        try {
            Orden buscarOrden = ne.buscarOrden(o);
            if (buscarOrden.getEstado().equalsIgnoreCase("Aceptada")) {
                estado("Liberada");
            } else {
                JOptionPane.showMessageDialog(this, "La orden no se encuentra"
                        + " aceptada, por lo tanto no se puede liberar la orden",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMonLibActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Orden: Actions Performed ">
    private void btnOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenActionPerformed
        if (TomaOrden.isVisible()) {
            TomaOrden.setVisible(false);
        } else {
            TomaOrden.setVisible(true);
            centrar(TomaOrden);
            idV = "";
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
        } else if (idV.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Debe de seleccionar un vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            String com = txtCom.getText().trim();
            Date date;
            date = new Date();
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecha;
            fecha = dateFormat.format(date);
            try {
                Vehiculo v = ne.buscarVehiculo(Integer.valueOf(idV));
                Cliente c = ne.buscarCliente(txtOrdenId.getText().trim());
                Orden o = new Orden(c, v, com, fecha, "Registrada");
                ne.registrarOrden(o);
                JOptionPane.showMessageDialog(this, "Orden Agregada correctamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
                borrarTabla(table4, tableTie);
                txtOrdenId.setText("");
                lblNom.setText("");
                txtCom.setText("");
                borrarListas();
                actualizaListas();
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
            TomaOrden.setVisible(false);
        }
    }//GEN-LAST:event_btnOrdenAceptarActionPerformed

    private void btnOrdenCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenCancelarActionPerformed
        TomaOrden.setVisible(false);
    }//GEN-LAST:event_btnOrdenCancelarActionPerformed

    private void txtBusCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusCliKeyReleased
        borrarTabla(table7, tableBusCli);
        try {
            actualizaListaBuscarClientes();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBusCliKeyReleased

    private void btnBusCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusCliActionPerformed
        borrarTabla(table7, tableBusCli);
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

    private void btnOrdBusCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdBusCliActionPerformed
        if (BuscarCliente.isVisible()) {
            BuscarCliente.setVisible(false);
        } else {
            borrarTabla(table7, tableBusCli);
            BuscarCliente.setVisible(true);
            centrar(BuscarCliente);
            try {
                actualizaListaBuscarClientes();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnOrdBusCliActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Cotización: Actions Performed ">
    private void txtSerNomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerNomKeyReleased
        borrarTabla(table5, tableSer);
        try {
            actualizaListaServ();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSerNomKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        borrarTabla(table5, tableSer);
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
            borrarTabla(table5, tableSer);
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" AdmonClientes: Actions Performed ">
    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        if (AdmonClientes.isVisible()) {
            AdmonClientes.setVisible(false);
        } else {
            AdmonClientes.setVisible(true);
            centrar(AdmonClientes);
            borrarTabla(table8, tableCliBus);
            borrarTabla(table10, tableCliTie);
            try {
                actualizaListaClientesBuscar();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnCliNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliNueActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCliNueActionPerformed

    private void btnCliAgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAgrActionPerformed
        try {
            if (!txtCliId.getText().equalsIgnoreCase("")) {
                if (!(txtCliId.getText().length() != 10)) {
                    ne.agregarCliente(insertarCliente());
                    JOptionPane.showMessageDialog(this, "Cliente Agregado"
                            + " correctamente",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
                    limpiar();
                    borrarTabla(table8, tableCliBus);
                    borrarTabla(table10, tableCliTie);
                    try {
                        actualizaListaClientesBuscar();
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede agregar"
                            + " debido a que el campo ID debe contener 10"
                            + " dígitos", "Error",
                            JOptionPane.ERROR_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede agregar"
                        + " debido a que el campo ID se encuentra vacío",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(this, "Ya existe el cliente",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btnCliAgrActionPerformed

    private void btnCliActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActActionPerformed
        try {
            if (!txtCliId.getText().equalsIgnoreCase("")) {
                if (!(txtCliId.getText().length() != 10)) {
                    ne.actualizarCliente(insertarCliente());
                    JOptionPane.showMessageDialog(this, "Cliente Actualizado"
                            + " Correctamente",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
                    limpiar();
                    borrarTabla(table8, tableCliBus);
                    borrarTabla(table10, tableCliTie);
                    try {
                        actualizaListaClientesBuscar();
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede actualizar"
                            + " debido a que el campo ID debe contener 10"
                            + " dígitos", "Error",
                            JOptionPane.ERROR_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede actualizar"
                        + " debido a que el campo ID se encuentra vacío",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "No existe el cliente",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btnCliActActionPerformed

    private void btnCliEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEliActionPerformed
        try {
            if (!txtCliId.getText().equalsIgnoreCase("")) {
                if (!(txtCliId.getText().length() != 10)) {
                    ne.eliminarCliente(insertarCliente());
                    JOptionPane.showMessageDialog(this, "Cliente Eliminado"
                            + " Correctamente",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
                    limpiar();
                    borrarTabla(table8, tableCliBus);
                    borrarTabla(table10, tableCliTie);
                    try {
                        actualizaListaClientesBuscar();
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede eliminar"
                            + " debido a que el campo ID debe contener 10"
                            + " dígitos", "Error",
                            JOptionPane.ERROR_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar"
                        + " debido a que el campo ID se encuentra vacío",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "No existe el cliente",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btnCliEliActionPerformed

    private void btnCliBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliBusActionPerformed
        if (idCli == null || idCli.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Debe de ingresar el Id de un "
                    + "cliente para poder realizar la busqueda del mismo",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            idCli = txtCliId.getText().trim();
            datosCliente(idCli);
        }
    }//GEN-LAST:event_btnCliBusActionPerformed

    private void txtCliBusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliBusKeyReleased
        borrarTabla(table8, tableCliBus);
        borrarTabla(table10, tableCliTie);
        try {
            actualizaListaClientesBuscar();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCliBusKeyReleased

    private void tableCliBusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCliBusKeyReleased
        int fila;
        fila = tableCliBus.getSelectedRow();
        idCli = table8.getValueAt(fila, 0).toString();
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            datosCliente(idCli);
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            datosCliente(idCli);
        }
        revalidate();
    }//GEN-LAST:event_tableCliBusKeyReleased

    private void btnCliAgrVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAgrVehActionPerformed
        if (idCli == null || idCli.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Debe de seleccionar a un "
                    + "cliente para poder asignar un vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } else {
            if (AdmonVehiculos.isVisible()) {
                AdmonVehiculos.setVisible(false);
            } else {
                AdmonVehiculos.setVisible(true);
                centrar(AdmonVehiculos);
                limpiarVeh();
                borrarTabla(table9, tableVeh);
                try {
                    actualizaListaVehiculos();
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnCliAgrVehActionPerformed

    private void btnCliEliVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEliVehActionPerformed
        if (idTie != 0) {
            Cliente c = new Cliente(idCli);
            Tiene t = new Tiene(idTie);
            try {
                ne.eliminarTiene(t);
                JOptionPane.showMessageDialog(this, "Vehículo desasignado"
                        + " correctamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE, null);
                borrarTabla(table10, tableCliTie);
                cargarVehiculos(table10, c);
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(this, "No existe ese vehículo",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en el sistema",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún"
                    + " vehículo", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btnCliEliVehActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" AdmonVehiculos: Actions Performed ">
    private void btnVehNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehNueActionPerformed
        limpiarVeh();
    }//GEN-LAST:event_btnVehNueActionPerformed

    private void btnVehAgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehAgrActionPerformed
        try {
            ne.agregarVehiculo(insertarVehiculo());
            JOptionPane.showMessageDialog(this, "Vehículo Agregado"
                    + " correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
            limpiarVeh();
            borrarTabla(table9, tableVeh);
            try {
                actualizaListaVehiculos();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(this, "Ya existe el vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btnVehAgrActionPerformed

    private void btnVehActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehActActionPerformed
        try {
            ne.actualizarVehiculo(insertarVehiculo());
            JOptionPane.showMessageDialog(this, "Vehículo Actualizado"
                    + " correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
            limpiarVeh();
            borrarTabla(table9, tableVeh);
            try {
                actualizaListaVehiculos();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "No existe el vehículo",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btnVehActActionPerformed

    private void txtVehBusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehBusKeyReleased
        borrarTabla(table9, tableVeh);
        try {
            actualizaListaVehiculos();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtVehBusKeyReleased

    private void btnVehSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehSelActionPerformed
        try {
            Cliente c = ne.buscarCliente(idCli);
            Vehiculo v = ne.buscarVehiculo(idVeh);
            Tiene t = new Tiene(c, v);
            ne.agregarTiene(t);
            JOptionPane.showMessageDialog(this, "Vehículo asignado al cliente"
                    + " correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
            borrarTabla(table10, tableCliTie);
            cargarVehiculos(table10, c);
            AdmonVehiculos.setVisible(false);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVehSelActionPerformed

    private void btnOrdenAgregaVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenAgregaVehiculoActionPerformed
        if (AdmonClientes.isVisible()) {
            AdmonClientes.setVisible(false);
        } else {
            AdmonClientes.setVisible(true);
            centrar(AdmonClientes);
            borrarTabla(table8, tableCliBus);
            borrarTabla(table10, tableCliTie);
            try {
                actualizaListaClientesBuscar();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnOrdenAgregaVehiculoActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" AdmonServicios: Actions performed ">
    private void btnServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosActionPerformed
        if (AdmonServicios.isVisible()) {
            AdmonServicios.setVisible(false);
        } else {
            AdmonServicios.setVisible(true);
            centrar(AdmonServicios);
            borrarTabla(table11, tableServ);
            try {
                actualizaListaServicios();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnServiciosActionPerformed

    private void btnSerNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerNueActionPerformed
        limpiarServ();
    }//GEN-LAST:event_btnSerNueActionPerformed

    private void btnSerAgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerAgrActionPerformed
        try {
            ne.agregarServicio(insertarServicio());
            JOptionPane.showMessageDialog(this, "Servicio Agregado"
                    + " correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
            limpiarServ();
            borrarTabla(table11, tableServ);
            try {
                actualizaListaServicios();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(this, "Ya existe el servicio",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_btnSerAgrActionPerformed

    private void txtSerActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerActActionPerformed
        try {
            ne.actualizarServicio(insertarServicio());
            JOptionPane.showMessageDialog(this, "Servicio Actualizado"
                    + " correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE, null);
            limpiarServ();
            borrarTabla(table11, tableServ);
            try {
                actualizaListaServicios();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "No existe el servicio",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el sistema",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_txtSerActActionPerformed

    private void txtSerBusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerBusKeyReleased
        borrarTabla(table11, tableServ);
        try {
            actualizaListaServicios();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSerBusKeyReleased
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Monitor "> 
    private void borrarTabla(DefaultTableModel table, JTable tabla) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            table.removeRow(i);
            i--;
        }
    }

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

    private void estado(String estado) {
        if (id.equalsIgnoreCase("")) {
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Orden ">    
    private void buscarCliente() {
        if (!txtOrdenId.getText().equals("")) {
            try {
                Cliente c = ne.buscarCliente(txtOrdenId.getText().trim());
                lblNom.setText(c.getNombre() + " " + c.getApellidoPat() + " " + c.getApellidoMat());
                borrarTabla(table4, tableTie);
                cargarVehiculos(table4, c);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se encontro al Cliente",
                        "Error", JOptionPane.ERROR_MESSAGE);
                lblNom.setText("");
            }
        } else {
            lblNom.setText("");
        }
    }

    private void cargarVehiculos(DefaultTableModel table, Cliente cliente) throws NonexistentEntityException {
        List<Tiene> tiene = ne.getVehiculosPorCliente(cliente);
        if (!tiene.isEmpty()) {
            for (Tiene t : tiene) {
                table.addRow(new Object[]{t.getIdTiene(), t.getIdVehiculo().getIdVehiculo(),
                    t.getIdVehiculo().getAño() + " " + t.getIdVehiculo().getMarca()
                    + " " + t.getIdVehiculo().getModelo() + " "
                    + t.getIdVehiculo().getCilindros() + " "
                    + t.getIdVehiculo().getMotor()});
            }
        } else {
            JOptionPane.showMessageDialog(this, "El Cliente no tiene "
                    + "vehículos registrados\n Agregar vehículos para poder "
                    + "continuar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Cotización ">
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" AdmonClientes ">
    private void actualizaListaClientesBuscar() throws NonexistentEntityException {
        String nombre = txtCliBus.getText().trim();
        if (!nombre.equalsIgnoreCase("")) {
            List<Cliente> lista = ne.getClienteLike(nombre);
            for (Cliente c : lista) {
                table8.addRow(new Object[]{c.getIdCliente(), c.getNombre(),
                    c.getApellidoPat(), c.getApellidoMat()});
            }
        } else {
            List<Cliente> lista = ne.listaClientes();
            for (Cliente c : lista) {
                table8.addRow(new Object[]{c.getIdCliente(), c.getNombre(),
                    c.getApellidoPat(), c.getApellidoMat()});
            }
        }
    }

    private Cliente insertarCliente() {
        Cliente c = new Cliente();
        c.setIdCliente(txtCliId.getText().trim());
        c.setNombre(txtCliNom.getText().trim());
        c.setApellidoPat(txtCliAPat.getText().trim());
        c.setApellidoMat(txtCliAMat.getText().trim());
        c.setDireccion(txtCliDir.getText().trim());
        c.setNumExt(txtCliNExt.getText().trim());
        c.setNumInt(txtCliNInt.getText().trim());
        c.setColonia(txtCliCol.getText().trim());
        c.setMunicipio(txtCliMun.getText().trim());
        c.setCiudad(txtCliCiu.getText().trim());
        c.setEstado(txtCliEst.getText().trim());
        c.setCp(txtCliCp.getText().trim());
        c.setPais(txtCliPai.getText().trim());
        c.setRfc(txtCliRfc.getText().trim());
        c.setTelefono(txtCliTel.getText().trim());
        c.setEmail(txtCliEma.getText().trim());
        return c;
    }

    private void datosCliente(String idCliente) {
        try {
            Cliente c = ne.buscarCliente(idCliente);
            txtCliId.setText(c.getIdCliente());
            txtCliNom.setText(c.getNombre());
            txtCliAPat.setText(c.getApellidoPat());
            txtCliAMat.setText(c.getApellidoMat());
            txtCliDir.setText(c.getDireccion());
            txtCliNExt.setText(c.getNumExt());
            txtCliNInt.setText(c.getNumInt());
            txtCliCol.setText(c.getColonia());
            txtCliMun.setText(c.getMunicipio());
            txtCliCiu.setText(c.getCiudad());
            txtCliEst.setText(c.getEstado());
            txtCliCp.setText(c.getCp());
            txtCliPai.setText(c.getPais());
            txtCliRfc.setText(c.getRfc());
            txtCliTel.setText(c.getTelefono());
            txtCliEma.setText(c.getEmail());
            borrarTabla(table10, tableCliTie);
            cargarVehiculos(table10, c);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpiar() {
        txtCliId.setText("");
        txtCliNom.setText("");
        txtCliAPat.setText("");
        txtCliAMat.setText("");
        txtCliDir.setText("");
        txtCliNExt.setText("");
        txtCliNInt.setText("");
        txtCliCol.setText("");
        txtCliMun.setText("");
        txtCliCiu.setText("");
        txtCliEst.setText("");
        txtCliCp.setText("");
        txtCliPai.setText("");
        txtCliRfc.setText("");
        txtCliTel.setText("");
        txtCliEma.setText("");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" AdmonVehiculos ">
    private void actualizaListaVehiculos() throws NonexistentEntityException {
        String nombre = txtVehBus.getText().trim();
        if (!nombre.equalsIgnoreCase("")) {
            List<Vehiculo> lista = ne.getVehiculoLike(nombre);
            for (Vehiculo v : lista) {
                table9.addRow(new Object[]{v.getIdVehiculo(), v.getAño(),
                    v.getMarca(), v.getModelo(), v.getCilindros(), v.getMotor()});
            }
        } else {
            List<Vehiculo> lista = ne.listaVehiculos();
            for (Vehiculo v : lista) {
                table9.addRow(new Object[]{v.getIdVehiculo(), v.getAño(),
                    v.getMarca(), v.getModelo(), v.getCilindros(), v.getMotor()});
            }
        }
    }

    private Vehiculo insertarVehiculo() {
        Vehiculo v = new Vehiculo();
        if (!txtVehId.getText().trim().equalsIgnoreCase("")) {
            v.setIdVehiculo(Integer.parseInt(txtVehId.getText().trim()));
        } else {
            v.setIdVehiculo(0);
        }
        v.setAño(Integer.parseInt(txtVehAn.getText().trim()));
        v.setMarca(txtVehMar.getText().trim());
        v.setModelo(txtVehMod.getText().trim());
        v.setCilindros(txtVehCil.getText().trim());
        v.setMotor(Double.parseDouble(txtVehMot.getText().trim()));
        return v;
    }

    private void datosVehiculo(int v) {
        try {
            Vehiculo veh = ne.buscarVehiculo(v);
            txtVehId.setText(veh.getIdVehiculo().toString().trim());
            txtVehAn.setText(veh.getAño().toString().trim());
            txtVehMar.setText(veh.getMarca().trim());
            txtVehMod.setText(veh.getModelo().trim());
            txtVehCil.setText(veh.getCilindros().trim());
            txtVehMot.setText(veh.getMotor().toString().trim());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpiarVeh() {
        txtVehId.setText("");
        txtVehAn.setText("");
        txtVehMar.setText("");
        txtVehMod.setText("");
        txtVehCil.setText("");
        txtVehMot.setText("");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" AdmonServicios ">
    private void actualizaListaServicios() throws NonexistentEntityException {
        String nombre = txtSerBus.getText().trim();
        if (!nombre.equalsIgnoreCase("")) {
            List<Servicio> lista = ne.getServicioLike(nombre);
            for (Servicio s : lista) {
                table11.addRow(new Object[]{s.getIdServicio(),
                    s.getDescripcion(), s.getPrecio()});
            }
        } else {
            List<Servicio> lista = ne.listaServicios();
            for (Servicio s : lista) {
                table11.addRow(new Object[]{s.getIdServicio(),
                    s.getDescripcion(), s.getPrecio()});
            }
        }
    }

    private Servicio insertarServicio() {
        Servicio s = new Servicio();
        if (!txtSerId.getText().trim().equalsIgnoreCase("")) {
            s.setIdServicio(Integer.parseInt(txtSerId.getText().trim()));
        } else {
            s.setIdServicio(0);
        }
        s.setDescripcion(txtSerDesc.getText().trim());
        s.setPrecio(Double.parseDouble(txtSerPre.getText().trim()));
        return s;
    }

    private void datosServicio(int s) {
        try {
            Servicio ser = ne.buscarServicio(s);
            txtSerId.setText(ser.getIdServicio().toString().trim());
            txtSerDesc.setText(ser.getDescripcion().trim());
            txtSerPre.setText(ser.getPrecio().toString());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SSCAutoFrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpiarServ() {
        txtSerId.setText("");
        txtSerDesc.setText("");
        txtSerPre.setText("");
    }
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc=" Main ">
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
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" Variables ">    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame AdmonClientes;
    private javax.swing.JInternalFrame AdmonServicios;
    private javax.swing.JInternalFrame AdmonVehiculos;
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
    private javax.swing.JButton btnCliAct;
    private javax.swing.JButton btnCliAgr;
    private javax.swing.JButton btnCliAgrVeh;
    private javax.swing.JButton btnCliBus;
    private javax.swing.JButton btnCliEli;
    private javax.swing.JButton btnCliEliVeh;
    private javax.swing.JButton btnCliNue;
    private javax.swing.JButton btnClientes;
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
    private javax.swing.JButton btnOrdBusCli;
    private javax.swing.JButton btnOrden;
    private javax.swing.JButton btnOrdenAceptar;
    private javax.swing.JButton btnOrdenAgregaCliente;
    private javax.swing.JButton btnOrdenAgregaVehiculo;
    private javax.swing.JButton btnOrdenCancelar;
    private javax.swing.JButton btnSerAgr;
    private javax.swing.JButton btnSerCan;
    private javax.swing.JButton btnSerNue;
    private javax.swing.JButton btnSerSel;
    private javax.swing.JButton btnServicios;
    private javax.swing.JButton btnVehAct;
    private javax.swing.JButton btnVehAgr;
    private javax.swing.JButton btnVehNue;
    private javax.swing.JButton btnVehSel;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
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
    private javax.swing.JTable tableCliBus;
    private javax.swing.JTable tableCliTie;
    private javax.swing.JTable tableCot;
    private javax.swing.JTable tableDet;
    private javax.swing.JTable tableReg;
    private javax.swing.JTable tableSer;
    private javax.swing.JTable tableServ;
    private javax.swing.JTable tableTie;
    private javax.swing.JTable tableVeh;
    private javax.swing.JTextField txtBusCli;
    private javax.swing.JTextField txtCliAMat;
    private javax.swing.JTextField txtCliAPat;
    private javax.swing.JTextField txtCliBus;
    private javax.swing.JTextField txtCliCiu;
    private javax.swing.JTextField txtCliCol;
    private javax.swing.JTextField txtCliCp;
    private javax.swing.JTextField txtCliDir;
    private javax.swing.JTextField txtCliEma;
    private javax.swing.JTextField txtCliEst;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliMun;
    private javax.swing.JTextField txtCliNExt;
    private javax.swing.JTextField txtCliNInt;
    private javax.swing.JTextField txtCliNom;
    private javax.swing.JTextField txtCliPai;
    private javax.swing.JTextField txtCliRfc;
    private javax.swing.JTextField txtCliTel;
    private javax.swing.JTextArea txtCom;
    private javax.swing.JTextArea txtComentarios;
    private javax.swing.JTextField txtOrdenId;
    private javax.swing.JButton txtSerAct;
    private javax.swing.JTextField txtSerBus;
    private javax.swing.JTextField txtSerDesc;
    private javax.swing.JTextField txtSerId;
    private javax.swing.JTextField txtSerNom;
    private javax.swing.JTextField txtSerPre;
    private javax.swing.JTextField txtVehAn;
    private javax.swing.JTextField txtVehBus;
    private javax.swing.JTextField txtVehCil;
    private javax.swing.JTextField txtVehId;
    private javax.swing.JTextField txtVehMar;
    private javax.swing.JTextField txtVehMod;
    private javax.swing.JTextField txtVehMot;
    // End of variables declaration//GEN-END:variables
    INegocio ne;
    Reportes re;
    DefaultTableModel table1, table2, table3, table4, table5, table6, table7, table8, table9, table10, table11;
    String id, idV, idCli, idSer, desc;
    int idVeh = 0;
    int idTie = 0;
    int idServ = 0;
    double cant = 0.0;
    double impor = 0.0;
    double punit = 0.0;
    double subt = 0.0;
    double iva = 0.0;
    double tot = 0.0;
    //</editor-fold>
}
