package kr.or.ddit.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FindAccountPwByMail {
	
	public void sendMail(String to, String user_id, String account_id, String account_pw) {
		String host = "smtp.naver.com";
		final String user = "youbi89@naver.com"; // 보내는 메일의 id
		final String password = "qjagnl!"; // 보내는 메일의 비밀번호
//		String to = "";// 수신측의 메일 주소
		
		int underBarIdx = account_id.lastIndexOf("_");
		String accountName = account_id.substring(0, underBarIdx);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host); // 네이버일 경우
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// 메일 제목
			message.setSubject("TeamSQL DB계정 비밀번호");
			
			// 메일 내용
			message.setText(user_id + "님의 DB계정('" + accountName + "')의 비밀번호는 " + account_pw + " 입니다");

			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
