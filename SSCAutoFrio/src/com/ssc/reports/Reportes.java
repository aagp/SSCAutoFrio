//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)Cotizacion.java Created on 23/10/2014, 05:53:21 PM
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
package com.ssc.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 * Class Cotizacion
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class Reportes {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String RUTA = "jdbc:mysql://db4free.net/sscautofrio";
    public static final String USER = "aagp";
    public static final String PASSWORD = "9517538426";
    public static Connection con;

    public Reportes() {
    }

    public void generarCotizacion(String idOrden) throws ClassNotFoundException, SQLException, IOException {
        Class.forName(DRIVER);
        con = DriverManager.getConnection(RUTA, USER, PASSWORD);
        JasperPrint jasperPrint;
        Map parametros = new HashMap();
        parametros.clear();
        //parametros de entrada
        parametros.put("orden_idOrden", idOrden);
        try {
            InputStream jrxml = getClass().getResourceAsStream("/com/ssc/reports/reporteCotizacion.jrxml");
            InputStream jasper = getClass().getResourceAsStream("/com/ssc/reports/reporteCotizacion.jasper");
            JasperCompileManager.compileReport(jrxml);
            jasperPrint = JasperFillManager.fillReport(jasper, parametros, con);
            //JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            //jasperViewer.setVisible(false);
            JasperViewer.viewReport(jasperPrint, false);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\Presupuesto_" + idOrden + ".pdf");
//            File file = new File("D:\\Presupuesto_"+idOrden+".pdf");
//            if (file.toString().endsWith(".pdf")) {
//                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
//            } else {
//                Desktop desktop = Desktop.getDesktop();
//                desktop.open(file);
//            }
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        con.close();
    }
}