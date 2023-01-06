package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Notice;
import vo.Question;

public class QuestionDao {
	// 1) insert
	public int insertQuestion(Connection conn, Question question) throws Exception {
		int row = 0;
		String sql = "INSERT INTO question(orders_code, category, question_memo, createdate) VALUES (?,?,?,NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, question.getOrdersCode());
		stmt.setString(2, question.getCategory());
		stmt.setString(3, question.getQuestionMemo());
		row = stmt.executeUpdate();
		return row;
	}
	
	// 2) list
	// 2-1) 관리자list
	public ArrayList<Question> selectAllQuestion(Connection conn) throws Exception {
		ArrayList<Question> list = new ArrayList<Question>();
		String sql = "SELECT"
				+ " question_code questionCode"
				+ ", orders_code ordersCode"
				+ ", category"
				+ ", question_memo questionMemo"
				+ ", createdate"
				+ " FROM question";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Question q = new Question();
			q.setQuestionCode(rs.getInt("questionCode"));
			q.setOrdersCode(rs.getInt("ordersCode"));
			q.setCategory(rs.getString("category"));
			q.setQuestionMemo(rs.getString("questionMemo"));
			q.setCreatedate(rs.getString("createdate"));
			list.add(q);
		}
		return list;
	}
	
	// 2-2) 회원별 list
	
	// 3) update
	
	// 4) delete
	
}
