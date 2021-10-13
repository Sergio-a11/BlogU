/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.utilidades;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dadxc
 */
public class Mail {
    
    
    public void enviarCorreo(String destino, String asunto, String cuerpo){
        try {
            String remitente = "";
            String clave = "";
            Properties pro = System.getProperties();
            pro.put("mail.smtp.host", "smtp.gmail.com");
            pro.put("mail.smtp.user", remitente);
            pro.put("mail.smtp.clave", clave);
            pro.put("mail.smtp.auth", "true");
            pro.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
            pro.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
            Session sesion = Session.getDefaultInstance(pro);
            MimeMessage mensaje = new MimeMessage(sesion);
            
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.addRecipients(Message.RecipientType.TO, destino);
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            
            Transport tp = sesion.getTransport("smtp");
            tp.connect("smtp.gmail.com",remitente,clave);
            tp.sendMessage(mensaje, mensaje.getAllRecipients());
            tp.close();
            System.out.println("Correo enviado");
        } catch (AddressException ex) {
            System.out.println("Error 1: "+ex.getMessage());
        } catch (MessagingException ex) {
            System.out.println("Error 2: "+ex.getMessage());
        }
    }
}
