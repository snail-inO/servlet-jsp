package cc.openhome;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TFilesInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource;

	public TFilesInfo() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/demo");
		} catch (NamingException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public List<ColumnInfo> getAllColumnInfo() {
		try (Connection conn = dataSource.getConnection();) {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet crs = meta.getColumns(null, null, "T_FILES", null);
			
			List<ColumnInfo> infos = new ArrayList<ColumnInfo>();
			while (crs.next()) {
				ColumnInfo info = new ColumnInfo();
				info.setName(crs.getString("COLUMN_NAME"));
				info.setType(crs.getString("TYPE_NAME"));
				info.setSize(crs.getInt("COLUMN_SIZE"));
				info.setNullable(crs.getBoolean("IS_NULLABLE"));
				info.setDef(crs.getString("COLUMN_DEF"));
				infos.add(info);
			}
			
			return infos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
