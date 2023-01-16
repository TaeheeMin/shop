package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import vo.*;

public class NoticeDao {
	// 1) list
	// 1-1) count(페이징 처리)
	// 1-2) list
	public ArrayList<Notice> selectNoticeList(Connection conn) throws Exception {
		ArrayList<Notice> notice = new ArrayList<Notice>();
		String sql = "SELECT notice_code noticeCode, notice_title noticeTitle, notice_content noticeContent, createdate FROM notice";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Notice n = new Notice();
			n.setNoticeCode(rs.getInt("noticeCode"));
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setNoticeContent(rs.getString("noticeContent"));
			n.setCreatedate(rs.getString("createdate"));
			notice.add(n);
		}
		return notice;
	}
	
	// 1-3) list One
	public Notice selectNoticeOne(Connection conn, int noticeCode) throws Exception {
		Notice notice = new Notice();
		String sql = "SELECT notice_code noticeCode, notice_title noticeTitle, notice_content noticeContent, createdate FROM notice WHERE notice_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, noticeCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			notice.setNoticeCode(rs.getInt("noticeCode"));
			notice.setNoticeTitle(rs.getString("noticeTitle"));
			notice.setNoticeContent(rs.getString("noticeContent"));
			notice.setCreatedate(rs.getString("createdate"));
		}
		return notice;
	}
	
	// 2) insert => 매니저만 가능 (로그인 기능 완성 후 로그인세션으로 권한처리)
	public int inesrtNotice(Connection conn, Notice notice) throws Exception {
		int row = 0;
		String sql = "INSERT INTO notice("
				+ " notice_title"
				+ ", notice_content"
				+ ", emp_id"
				+ ", createdate"
				+ ") VALUES(?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, notice.getNoticeTitle());
		stmt.setString(2, notice.getNoticeContent());
		stmt.setString(3, notice.getEmpId());
		row = stmt.executeUpdate();
		return row;
		
	}
	
	// 3) update
	public int updateNotice(Connection conn, Notice notice) throws Exception {
		int result = 0;
		PreparedStatement stmt = null;
		String sql = "UPDATE notice SET notice_title = ?, notice_content = ? WHERE notice_code = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, notice.getNoticeTitle());
		stmt.setString(2, notice.getNoticeContent());
		stmt.setInt(3, notice.getNoticeCode());
		result = stmt.executeUpdate();
		return result;
	}
	
	// 4) delete
	public int deleteBoard(Connection conn, int noticeCode) throws Exception {
		int row = 0;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM notice WHERE notice_code = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, noticeCode);
		row = stmt.executeUpdate();
		return row;
	}
	
}