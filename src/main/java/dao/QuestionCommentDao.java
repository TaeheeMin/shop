package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Question;
import vo.QuestionComment;

public class QuestionCommentDao {
	// 1) insert
	public int isertComment(Connection conn, QuestionComment questionComment) throws Exception {
		int row = 0;
		String sql = "INSERT INTO question_comment("
				+ " question_code"
				+ ", comment_memo"
				+ ", createdate"
				+ ") VALUES (?,?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionComment.getQuestionCode());
		stmt.setString(2,questionComment.getCommentMemo());
		row = stmt.executeUpdate();
		return row;
	}
	
	// 1-2)insert 체크
	// question code 존재하면 이미 답변 등록->insert못함
	public boolean insertCheck(Connection conn, int questionCode) throws Exception {
		boolean questionCh = false;
		String sql = "SELECT * FROM question_comment WHERE question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			questionCh = true;
		}
		System.out.println("questionCh : " + questionCh);
		return questionCh;
	}
	
	// 2) get
	public ArrayList<HashMap<String, Object>> selectQuestionComment(Connection conn, int questionCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " comment_memo commentMemo"
				+ ", createdate"
				+ ", question_code questionCode"
				+ " FROM question_comment"
				+ " WHERE question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("questionCode", rs.getInt("questionCode"));
			m.put("commentMemo", rs.getString("commentMemo"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}
		return list;
	}
	
	// 3) list
	public ArrayList<HashMap<String, Object>> selectAllQuestionComment(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " qc.comment_code commentCode"
				+ ", q.question_code questionCode"
				+ " FROM question_comment qc INNER JOIN question q"
				+ " ON qc.question_code = q.question_code";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("commentCode", rs.getInt("commentCode"));
			m.put("questionCode", rs.getInt("questionCode"));
			list.add(m);
		}
		return list;
	} 
}
