package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbBean {
	private String jdbcUri;
	private String username;
	private String password;
	
	public boolean isConnectedOK() {
		try (Connection conn = DriverManager.getConnection(jdbcUri, username, password)) {
			return !conn.isClosed();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setPassword(String password) {
        this.password = password;
    }
    
    public void setJdbcUri(String jdbcUri) {
        this.jdbcUri = jdbcUri;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
