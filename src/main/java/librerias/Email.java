
package librerias;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email implements Runnable{
    private final Properties props;

    public Email(String to, String subject, String content) {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
        
        //   "--add modules java.activation"

        props.put("username", "algo2.20202@gmail.com");
	props.put("password", "secreto.123");
        
        props.put("to", to);
        props.put("subject", subject);
        props.put("content", content);
        
    }
    
    public void enviar()  {
        try {
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
                }
            });

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(props.getProperty("to")));
            message.setSubject(props.getProperty("subject"));
            message.setContent(props.getProperty("content"), "text/html; charset=utf-8");
            //message.setText(" Mensaje de ejemplo");
            //Transport.send(message);

            System.out.println("¡Mensaje enviado!");
        }catch(MessagingException e ){
            System.out.println("Error en envio de correo." );
            e.printStackTrace();
        }
            
    }

    @Override
    public void run() {
        this.enviar();
    }
    
    
}