package util;

import java.sql.*;

public class DBUtil {
	public static Connection getConnection() throws Exception {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String dbUrl = "jdbc:mariadb://localhost:3306/shop";
		String dbUser = "root";
		String dbPw = "java1234";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPw);
		conn.setAutoCommit(false); // 오토커밋 안되게끔 통일
		return conn;
	}
}
