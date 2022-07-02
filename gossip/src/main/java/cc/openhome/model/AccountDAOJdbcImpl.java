package cc.openhome.model;

import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

public class AccountDAOJdbcImpl implements AccountDAO {
	private JdbcTemplate jdbc;
	
	public AccountDAOJdbcImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void createAccount(Account acct) {
		jdbc.update("INSERT INTO t_account(name, email, encrypt, slat) VALUES(?, ?, ?, ?)",
				acct.getName(),
				acct.getEmail(),
				acct.getEncrypt(),
				acct.getSalt());
		jdbc.update("INSERT INTO t_account_role(name, role) VALUES(?, 'unverified')", acct.getName());
	}

	@Override
	public Optional<Account> accountBy(String name) {
		var results = jdbc.queryForList("SELECT * FROM t_message WHERE name = ?", name);
		
		if (results.isEmpty()) {
			return Optional.empty();
		}
		
		var row = results.get(0);
		return Optional.of(new Account(
				row.get("NAME").toString(),
				row.get("EMAIL").toString(),
				row.get("ENCRYPT").toString(),
				row.get("SALT").toString()
				));
	}

	@Override
	public Optional<Account> accountByEmail(String email) {
		var results = jdbc.queryForList("SELECT * FROM t_account WHERE email = ?", email);
		
		if (results.isEmpty())
			return Optional.empty();
		
		var row = results.get(0);
		return Optional.of(new Account(
			row.get("NAME").toString(),
			row.get("EMAIL").toString(),
			row.get("ENCRYPT").toString(),
			row.get("SALT").toString()));
	}

	@Override
	public void activateAccount(Account acct) {
		jdbc.update("UPDATE t_account_role SET role = ? WHERE name = ?", "member", acct.getName());
	}

	@Override
	public void updateEncryptSalt(String name, String encrypt, String salt) {
		jdbc.update("UPDATE t_account SET encrypt = ?, salt = ? WHERE name = ?",
			encrypt, salt, name);
	}
}
