package cc.openhome.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class JdbcTemplate {
	private DataSource dataSource;
	
	public JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	class DefaultPreparedStatementSetter implements PreparedStatementSetter {
		private Object[] params;
		
		DefaultPreparedStatementSetter(Object[] params) {
			this.params = params;
		}
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			for (var i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
	}
	
	public int update(String sql, final Object... params) {
		return update(sql, new DefaultPreparedStatementSetter(params));
	}
	
	public int update(String sql, PreparedStatementSetter psst) {
		return ((Integer) execute(sql, psst));
	}
	
	public List<Map> queryForList(String sql) {
		return queryForList(sql, new Object[]{});
	}
	
	public List<Map> queryForList(String sql, final Object... params) {
		return queryForList(sql, new DefaultPreparedStatementSetter(params));
	}
	
	public List<Map> queryForList(String sql, PreparedStatementSetter psst) {
		return (List<Map>) execute(sql, psst);
	}
	
	public Object execute(String sql, PreparedStatementSetter psst) {
		try (var conn = dataSource.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			psst.setValues(stmt);
			return stmt.execute() ? rows(stmt) : stmt.getUpdateCount();
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	private List<Map> rows(PreparedStatement stmt) throws SQLException {
		var rs = stmt.getResultSet();
		var columnNames = columnNames(rs);
		
		var rows = new ArrayList<Map>();
		while (rs.next()) {
			var row = new HashMap<String, Object>();
			for (var columnName : columnNames) {
				row.put(columnName, rs.getObject(columnName));
			}
			rows.add(row);
		}
		return rows;
	}
	
	private List<String> columnNames(ResultSet rs) throws SQLException {
		var columnNames = new ArrayList<String>();
		var meta = rs.getMetaData();
		var columnCount = meta.getColumnCount();
		for (var i = 1; i <= columnCount; i++) {
			columnNames.add(meta.getColumnName(i));
		}
		
		return columnNames;
	}
}
