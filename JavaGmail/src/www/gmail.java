package www;


/*
  Java Mail 簡易版
 */
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class gmail {
	// GMail user name (just the part before "@gmail.com")
	private static String USER_NAME = "信箱帳號";
	// GMail password
	// 如果你有開兩步驟，需要另外建立一個「應用程式密碼」
	// 如果沒有開兩步驟，需要開起「允許安全性較低的應用程式」
	private static String PASSWORD = "信箱密碼";
	private static String RECIPIENT = "對方信箱@gmail.com";

	public static void main(String[] args) {
//		System.out.println("WWWW");
		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT }; // list of recipient email addresses
		String subject = "信件標題";
		String body = "信件內容";

		sendFromGMail(from, pass, to, subject, body);
	}

	private static void sendFromGMail(String from, String pass, String[] to,
			String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		// 下面設定是根據GMail官方文件所寫的，開啟TLS以及SMTP AUTH
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
		
			   System.out.println("寄送email結束.");

			 
			 
		
	}
	
	

}