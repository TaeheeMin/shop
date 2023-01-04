package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Notice;

public class NoticeDao {
	// 1) list
	// 1-1) count(페이징 처리)
	
	// 1-2) list
	public Notice selectNoticeList(Connection conn) throws Exception {
		Notice notice = new Notice();
		String sql = "SELECT notice_title noticeTitle, createdate FROM notice";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			notice.setNoticeTitle(rs.getString("noticeTitle"));
			notice.setCreatedate(rs.getString("createdate"));
		}
		System.out.println(notice.getNoticeTitle());
		return notice;
	}
	
	// 1-3) list One
	public ArrayList<Notice> selectNoticeOne(Connection conn, int noticeCode) throws Exception {
		ArrayList<Notice> list = new ArrayList<Notice>();
		String sql = "SELECT notice_title noticeTitle, notice_content noticeContent, createdate FROM notice WHERE notice_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, noticeCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Notice n = new Notice();
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setCreatedate(rs.getString("createdate"));
			list.add(n);
		}
		return list;
	}
	
	// 2) insert
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
	
	// 4) delete
}
