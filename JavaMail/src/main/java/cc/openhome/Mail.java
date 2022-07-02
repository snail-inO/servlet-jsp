package cc.openhome;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = {"/mail"},
	initParams = {
		@WebInitParam(name = "host", value = "smtp.gmail.com"),
		@WebInitParam(name = "port", value = "587"),
		@WebInitParam(name = "username", value = "hua.void@gmail.com"),
		@WebInitParam(name = "password", value = "norhqhudmxrdiyrh")
	}
)
public class Mail extends HttpServlet {
	private String host;
	private int port;
	private String username;
	private String password;
	private Properties props;
	
	@Override
	public void init() throws ServletException {
		host = getServletConfig().getInitParameter("host");
		port = Integer.parseInt(getServletConfig().getInitParameter("port"));
		username = getServletConfig().getInitParameter("username");
		password = getServletConfig().getInitParameter("password");
		
		props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		String subject = req.getParameter("subject");
		String text = req.getParameter("text");
		
		try {
			Message message = createMessage(from, to, subject, text);
			Transport.send(message);
			resp.getWriter().println("邮件传送成功");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Message createMessage(String from, String to, String subject, String text) throws MessagingException {
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(text);
		
		return message;
	}
}
