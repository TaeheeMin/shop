package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	// 2) get
	public ArrayList<QuestionComment> selectQuestionComment(Connection conn, int questionCode) throws Exception {
		ArrayList<QuestionComment> list = new ArrayList<QuestionComment>();
		String sql = "SELECT"
				+ " comment_memo commentMemo"
				+ ", createdate"
				+ " FROM question_comment"
				+ " WHERE question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			QuestionComment q = new QuestionComment();
			q.setCommentMemo(rs.getString("commentMemo"));
			q.setCreatedate(rs.getString("createdate"));
			list.add(q);
		}
		return list;
	}
}
