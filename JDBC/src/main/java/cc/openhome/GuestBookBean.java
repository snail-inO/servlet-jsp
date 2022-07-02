package cc.openhome;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestBookBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jdbcUri = "jdbc:h2:tcp://localhost/F:/h2/test";
	private String username = "root";
	private String password = "123";
	
	public void setMessage(Message message) {
		try (Connection conn = DriverManager.getConnection(jdbcUri, username, password);
				Statement statement = conn.createStatement()) {
			statement.executeUpdate("INSERT INTO t_message(name, email, msg) VALUES('"
					+ message.getName() + "', '" + message.getEmail() + "', '"
					+ message.getMsg() + "')");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Message> getMessages() {
		try (Connection conn = DriverManager.getConnection(jdbcUri, username, password);
				Statement statement = conn.createStatement()) {
			ResultSet result = statement.executeQuery("SELECT * FROM t_message");
			List<Message> messages = new ArrayList<>();
			while (result.next()) {
				Message message = new Message();
				message.setId(result.getLong(1));
				message.setName(result.getString(2));
				message.setEmail(result.getString(3));
				message.setMsg(result.getString(4));
				messages.add(message);
			}
			return messages;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
