//<editor-fold defaultstate="collapsed" desc=" License ">
/*
 * @(#)CCorreo.java Created on 13/11/2014, 06:04:03 PM
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
package com.ssc.infraestructura;

import com.ssc.objetosnegocio.Orden;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Class CCorreo
 *
 * @author Alan García <bearz@outlook.com>
 * @version 1.0
 */
public class CCorreo {

    protected CCorreo() {
    }

    protected void enviarCotizacion(Orden orden) throws MessagingException, Exception {
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", "sscautofrio@gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);
        // Adjuntamos el pdf
        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Presupuestos\\Presupuesto_" + orden.getIdOrden() + ".pdf")));
        adjunto.setFileName("Presupuesto_" + orden.getIdOrden() + ".pdf");
        BodyPart texto = new MimeBodyPart();
        texto.setText("Estimado cliente, el siguiente correo es para "
                + "notificarle que el presupuesto para su automovil es el "
                + "siguiente:\n\n"
                + "Presupuesto: #" + orden.getIdOrden() + "\n"
                + "Cliente: " + orden.getIdCliente().toString() + " "
                + orden.getIdCliente().getNombre() + " "
                + orden.getIdCliente().getApellidoPat() + " "
                + orden.getIdCliente().getApellidoMat() + "\n"
                + "Vehículo: " + orden.getIdVehiculo().getAño() + " "
                + orden.getIdVehiculo().getMarca() + " "
                + orden.getIdVehiculo().getModelo() + " "
                + orden.getIdVehiculo().getCilindros() + " "
                + orden.getIdVehiculo().getMotor() + "\n"
                + "Total: $" + orden.getTotal().toString() + "\n"
                + "Comentarios: " + orden.getComentarios().toString() + "\n\n"
                + "Para más información el presupuesto queda adjunto en "
                + "este correo.\n"
                + "Sin más por el momento nos despedimos.\n"
                + "Atte: Auto Frío");
        // Construimos el mensaje
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(adjunto);
        multiParte.addBodyPart(texto);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sscautofrio@gmail.com"));
        String email = orden.getIdCliente().getEmail().toString();
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(email));
        message.setSubject("Presupuesto #" + orden.getIdOrden() + " Fecha: "
                + orden.getFecha());
        message.setContent(multiParte);
        // Lo enviamos.
        Transport t = session.getTransport("smtp");
        t.connect("sscautofrio@gmail.com", "9517538426");
        t.sendMessage(message, message.getAllRecipients());
        // Cierre.
        t.close();
    }
}
