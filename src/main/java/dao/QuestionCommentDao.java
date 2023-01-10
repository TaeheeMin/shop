package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		stmt.setInt(1, questionComment.getQuestion_code());
		stmt.setString(2,questionComment.getComment_memo());
		row = stmt.executeUpdate();
		return row;
	}
}
