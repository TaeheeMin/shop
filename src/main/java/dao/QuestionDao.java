package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import vo.Question;

public class QuestionDao {
	// 1) insert
	public int insertQuestion(Connection conn, Question question) throws Exception {
		int row = 0;
		String sql = "INSERT INTO question("
				+ "orders_code"
				+ ", category"
				+ ", question_title"
				+ ", question_memo"
				+ ", createdate"
				+ ") VALUES (?,?,?,?,NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, question.getOrdersCode());
		stmt.setString(2, question.getCategory());
		stmt.setString(3, question.getQuestionTitle());
		stmt.setString(4, question.getQuestionMemo());
		row = stmt.executeUpdate();
		return row;
	}
	
	// 2) list
	// 2-1) 관리자list
	public int selectQuestionCount(Connection conn) throws Exception {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM question";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			count = rs.getInt("COUNT(*)");
		}
		return count;
	}
	
	public ArrayList<Question> selectQuestionByAdmin(Connection conn) throws Exception {
		ArrayList<Question> list = new ArrayList<Question>();
		String sql = "SELECT"
				+ " question_code questionCode"
				+ ", orders_code ordersCode"
				+ ", category"
				+ ", question_title questionTitle"
				+ ", question_memo questionMemo"
				+ ", createdate"
				+ " FROM question"
				+ " ORDER BY question_code DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Question q = new Question();
			q.setQuestionCode(rs.getInt("questionCode"));
			q.setOrdersCode(rs.getInt("ordersCode"));
			q.setCategory(rs.getString("category"));
			q.setQuestionTitle(rs.getString("questionTitle"));
			q.setQuestionMemo(rs.getString("questionMemo"));
			q.setCreatedate(rs.getString("createdate"));
			list.add(q);
		}
		return list;
	}
	
	// 2-2) 회원 list
	public ArrayList<Question> selectQuestionBycustomer(Connection conn, String customerId) throws Exception {
		ArrayList<Question> list = new ArrayList<Question>();
		String sql = "SELECT"
				+ " question_code questionCode"
				+ ", q.orders_code ordersCode"
				+ ", q.category category"
				+ ", q.question_title questionTitle"
				+ ", q.createdate createdate"
				+ ", g.goods_title goodsTitle"
				+ " FROM question q INNER JOIN orders o"
				+ " ON q.orders_code = o.order_code"
				+ " INNER JOIN goods g"
				+ " ON o.goods_code = g.goods_code"
				+ " WHERE o.customer_id = ?"
				+ " ORDER BY q.createdate DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Question question = new Question();
			question.setQuestionCode(rs.getInt("questionCode"));
			question.setOrdersCode(rs.getInt("ordersCode"));
			question.setCategory(rs.getString("category"));
			question.setQuestionTitle(rs.getString("questionTitle"));
			question.setCreatedate(rs.getString("createdate"));
			list.add(question);
		}
		return list;
	}
	
	// 2-3) 문의사항One
	public ArrayList<HashMap<String, Object>> selectQuestionOne(Connection conn, int questionCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT "
				+ " q.question_code questionCode"
				+ ", q.orders_code odersCode"
				+ ", q.category category"
				+ ", q.question_title questionTitle"
				+ ", q.question_memo qusetionMemo"
				+ ", q.createdate createdate"
				+ ", g.goods_title goodsTitle"
				+ ", img.filename filename"
				+ " FROM question q INNER JOIN orders o"
				+ " ON q.orders_code = o.order_code"
				+ " INNER JOIN goods g"
				+ " ON o.goods_code = g.goods_code"
				+ " INNER JOIN goods_img img"
				+ " ON g.goods_code = img.goods_code"
				+ " WHERE q.question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("questionCode", rs.getInt("questionCode"));
			m.put("odersCode", rs.getString("odersCode"));
			m.put("category", rs.getString("category"));
			m.put("questionTitle", rs.getString("questionTitle"));
			m.put("qusetionMemo", rs.getString("qusetionMemo"));
			m.put("createdate", rs.getString("createdate"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	
	// 3) update
	public int updateQuestion(Connection conn, Question question) throws Exception {
		int row = 0;
		String sql = "UPDATE question SET"
				+ " category = ?"
				+ ", question_title = ?"
				+ ", question_memo = ?"
				+ " WHERE question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, question.getCategory());
		stmt.setString(2, question.getQuestionTitle());
		stmt.setString(3, question.getQuestionMemo());
		stmt.setInt(4, question.getQuestionCode());
		return row;
	}
	
	// 4) delete
	public int deleteQuestion(Connection conn, int questionCode) throws Exception {
		int row = 0;
		String sql = "DELETE FROM question WHERE question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		row = stmt.executeUpdate();
		return row;
	}
}
