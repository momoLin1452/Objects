package www;


/*
  Java Mail ²����
 */
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class gmail {
	// GMail user name (just the part before "@gmail.com")
	private static String USER_NAME = "�H�c�b��";
	// GMail password
	// �p�G�A���}��B�J�A�ݭn�t�~�إߤ@�ӡu���ε{���K�X�v
	// �p�G�S���}��B�J�A�ݭn�}�_�u���\�w���ʸ��C�����ε{���v
	private static String PASSWORD = "�H�c�K�X";
	private static String RECIPIENT = "���H�c@gmail.com";

	public static void main(String[] args) {
//		System.out.println("WWWW");
		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT }; // list of recipient email addresses
		String subject = "�H����D";
		String body = "�H�󤺮e";

		sendFromGMail(from, pass, to, subject, body);
	}

	private static void sendFromGMail(String from, String pass, String[] to,
			String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		// �U���]�w�O�ھ�GMail�x����Ҽg���A�}��TLS�H��SMTP AUTH
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(USER_NAME, PASSWORD);
		    }
		});
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		
			   System.out.println("�H�eemail����.");

			 
			 
		
	}
	
	

}