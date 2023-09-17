package app;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MainApp {
    public static void main(String[] args) {

        final String userName = "alexandre.myemail@gmail.com"; // E-mail do remetente
        final String password = "qyfyxvuhsodnzxlp"; // Senha 2 fatores Gmail
        int count = 0; // Quantidade de E-mails enviados com sucesso

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        // Lista de destinatários
        List<String> recipients = new ArrayList<>();
        recipients.add("lineage.org@gmail.com");
        recipients.add("alexandre.myemail@gmail.com");
        // Criando mensagens
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName, "BOT"));
            // mensagens para todos destinatários
            for (String r : recipients) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(r));
                count++;
            }
            message.setSubject("Assunto do e-mail"); // Titulo
            message.setText("Conteúdo do e-mail"); // Corpo
            // Enviando ...
            Transport.send(message);
            // Sucesso ...
            System.out.println(count + " E-mail enviados com sucesso!");

        } catch (Exception e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}