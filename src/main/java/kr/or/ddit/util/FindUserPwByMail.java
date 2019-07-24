package kr.or.ddit.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FindUserPwByMail {
	
	public String sendMail(String to, String user_id) {
		String host = "smtp.naver.com";
		final String user = "youbi89@naver.com"; // 보내는 메일의 id
		final String password = "kbh711482!"; // 보내는 메일의 비밀번호
//		String to = "";// 수신측의 메일 주소
		String newPW = "";
		
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
			message.setSubject("TeamSQL 새로운 비밀번호 생성");
			
			for (int i = 0; i < 5; i++) {
				char ch = (char) ((Math.random() * 26) + 97);
				newPW += ch;
			}
			
			for (int i = 0; i < 3; i++) {
				int random = (int) (Math.random() * 10);
				newPW += random;
			}
			
			newPW += "@";
			
			
			// 메일 내용
			message.setText(user_id + "님의 새로운 비밀번호는 " + newPW + " 입니다");

			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return newPW;
		
	}
}
