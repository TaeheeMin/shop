package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Customer;
import vo.PwHistory;

public class PwHistoryDao {
	
	// 1) pwHistory 이력 추가
	public int addPwHistory(Connection conn, PwHistory pwHistory) throws Exception {
		int addPwHistory = 0;
		
		String sql = "INSERT INTO pw_history(customer_id, pw, createdate)"
					+ " VALUES(?,PASSWORD(?),NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, pwHistory.getCustomerId());
		stmt.setString(2, pwHistory.getPw());
		addPwHistory = stmt.executeUpdate();
		
		return addPwHistory;
	}
	
	// 2) 데이터 총 갯수 (최대 3개로 제한예정임)
	public int ttlCntPwHistory(Connection conn, PwHistory pwHistory) throws Exception {
		int ttlCntPwHistory = 0;
		
		String sql = "SELECT COUNT(*) FROM pw_history WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, pwHistory.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			ttlCntPwHistory = rs.getInt("COUNT(*)");
		}
		return ttlCntPwHistory;
	}
	
	// 3) pwHistory 이력 삭제
	public int removePwHistory(Connection conn, PwHistory pwHistory) throws Exception {
		int removePwHistory = 0;
		
		String sql = "DELETE from pw_history WHERE customer_id = ? AND createdate = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, pwHistory.getCustomerId());
		stmt.setString(2, pwHistory.getCreatedate());
		System.out.println("[pwHistoryDao]");
		System.out.println("(1)pwHistory.getCustomerId() : "+pwHistory.getCustomerId());
		System.out.println("(2)pwHistory.getCreatedate() : "+pwHistory.getCreatedate());
		
		removePwHistory = stmt.executeUpdate();
		System.out.println(removePwHistory);
		
		return removePwHistory;
	}
	
	// 4) pwHistory select (생성날짜가 가장 오래된 것)
	public PwHistory selectOldestPw(Connection conn, PwHistory pwHistory) throws Exception {
		PwHistory selectOldestPw = null;
		
		String sql = "SELECT customer_id, createdate FROM pw_history"
					+ " WHERE customer_id = ?"
					+ " ORDER BY createdate"
					+ " LIMIT 0,1";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, pwHistory.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			selectOldestPw = new PwHistory();
			selectOldestPw.setCustomerId(rs.getString("customer_id"));
			selectOldestPw.setCreatedate(rs.getString("createdate"));
		}
		
		return selectOldestPw;
	}
	
	// 5) 회원탈퇴 시, pwHistory도 삭제
	public int removePwHistoryByRemoveCustomer(Connection conn, Customer customer) throws Exception {
		int removePwHistory = 0;
		
		String sql = "DELETE FROM pw_history WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		removePwHistory = stmt.executeUpdate();
		
		return removePwHistory;
	}
	
	// 6) 비밀번호 수정 시, 중복체크를 위한 select
	public boolean selectPwHistoryCk(Connection conn, Customer customer) throws Exception {
		boolean checkPw = false;
		
		String sql = "SELECT * FROM pw_history WHERE customer_id = ? AND customer_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			checkPw = true;
		}
		return checkPw;
	}
}
