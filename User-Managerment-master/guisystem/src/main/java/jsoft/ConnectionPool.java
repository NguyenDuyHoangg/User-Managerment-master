package jsoft;

import java.sql.*;


public interface ConnectionPool {
	//Phương thức lấy kết nối
	public Connection getConnection(String objectName) throws SQLException;
	
	//Phương thức thu hồi kết nối
	public void releaseConnectionPool(Connection con, String objectName) throws SQLException;
}
