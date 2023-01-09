package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SiteCounterDao {
	// 오늘 첫 접속자 발생시, insert (todayCount가 0일때)
	public void insertCount(Connection conn) throws Exception {
		String sql = "INSERT INTO site_counter("
					+ "counter_date, counter_num) VALUES(CURDATE(), 1)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
	}
	
	// 오늘 첫 접속자가 아닐시, update (todayCount가 0이 아닐때)
	public void updateCount(Connection conn) throws Exception {
		String sql = "UPDATE site_counter SET counter_num = counter_num+1"
					+ " WHERE counter_date = CURDATE()";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
	}
	
	// 오늘 접속자 수 확인 select
	public int selectTodayCount(Connection conn) throws Exception {
		int todayCount = 0;

		String sql = "SELECT counter_num todayCount FROM site_counter"
					+ " WHERE counter_date = CURDATE()";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			todayCount = rs.getInt("todayCount");
		}
		return todayCount;
	}
	
	// 전체 접속자 수 확인 select
	public int selectTotalCount(Connection conn) throws Exception {
		int totalCount = 0;

		String sql = "SELECT SUM(counter_num) totalCount FROM site_counter";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("totalCount");
		}
		return totalCount;
	}
}
