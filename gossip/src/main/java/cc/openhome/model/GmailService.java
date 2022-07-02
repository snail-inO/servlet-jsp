package cc.openhome.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GmailService implements EmailService {
	private final Properties props = new Properties();
	private final String mailUser;
	private final String mailPassword;
	
	public GmailService(String mailUser, String mailPassword) {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", 587);
		this.mailUser = mailUser;
		this.mailPassword = mailPassword;
	}

	@Override
	public void validationLink(Account acct) {
		try {
			var link = String.format(
				"http://localhost:8080/gossip/verify?email=%s&token=%s",
				acct.getEmail(), acct.getEncrypt()
			);
			var anchor = String.format("<a href='%s'>验证邮件</a>", link);
			var html = String.format("请按 %s 启用账户或复制链接至网址列：<br><br> %s", anchor, link);
			var message = createMessage(mailUser, acct.getEmail(), "Gossip 注册结果", html);
			
			Transport.send(message);
		} catch (MessagingException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void failedRegistration(String acctName, String acctEmail) {
		try {
			var message = createMessage(mailUser, acctEmail, "Gossip 注册结果",
				String.format("账户申请失败，使用者名称 %s 或邮件 %s 已存在", acctName, acctEmail));
			Transport.send(message);
		} catch (MessagingException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private javax.mail.Message createMessage(String from, String to, String subject, String text)
		throws MessagingException, AddressException, IOException {
		var session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailUser, mailPassword);
			}
		});
		
		var multiPart = multiPart(text);
		
		var message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setContent(multiPart);
		
		return message;
	}

	private Multipart multiPart(String text) throws MessagingException,
		UnsupportedEncodingException, IOException {
		var htmlPart = new MimeBodyPart();
		htmlPart.setContent(text, "text/html;charset=UTF-8");
		
		var multiPart = new MimeMultipart();
		multiPart.addBodyPart(htmlPart);
		
		return multiPart;
	}

	@Override
	public void passwordResetLink(Account acct) {
		try {
			var link = String.format(
					"http://localhost:8080/gossip/reset_password?name=%s&email=%s&token=%s",
					acct.getName(), acct.getEmail(), acct.getEncrypt()
			);
			var anchor = String.format("<a href='%s'>重设密码</a>", link);
			var html = String.format("请按 %s 或复制链接至网址列：<br><br> %s", anchor, link);
			var message = createMessage(mailUser, acct.getEmail(), "Gossip 重设密码", html);
				
			Transport.send(message);
		} catch (MessagingException | IOException e) {
			throw new RuntimeException(e);
		}
	}
		
}
