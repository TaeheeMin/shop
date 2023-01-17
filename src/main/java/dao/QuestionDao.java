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
	// 2-0) 관리자list count
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
	// 2-1) 관리자list
	public ArrayList<HashMap<String, Object>> selectQuestionByAdmin(Connection conn, int beginRow, int endRow, String category) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " q.question_code questionCode"
				+ ", q.orders_code ordersCode"
				+ ", g.goods_title goodsTitle"
				+ ", img.filename filename"
				+ ", q.category"
				+ ", q.question_title questionTitle"
				+ ", q.question_memo questionMemo"
				+ ", q.createdate"
				+ ", o.customer_id customerId"
				+ ", g.goods_code goodsCode"
				+ " FROM question q INNER JOIN orders o"
				+ " ON q.orders_code = o.order_code"
				+ " INNER JOIN goods g"
				+ " ON o.goods_code = g.goods_code"
				+ " INNER JOIN goods_img img"
				+ " ON g.goods_code = img.goods_code"
				+ " WHERE q.category LIKE ?"
				+ " ORDER BY q.question_code DESC"
				+ " LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + category + "%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, endRow);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("questionCode", rs.getInt("questionCode"));
			m.put("ordersCode", rs.getInt("ordersCode"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("filename", rs.getString("filename"));
			m.put("category", rs.getString("category"));
			m.put("questionTitle", rs.getString("questionTitle"));
			m.put("createdate", rs.getString("createdate"));
			m.put("customerId", rs.getString("customerId"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			list.add(m);
		}
		return list;
	}

	// 2-2) 회원 list
	public ArrayList<HashMap<String, Object>> selectQuestionBycustomer(Connection conn, String customerId) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
				+ " q.question_code questionCode"
				+ ", q.orders_code ordersCode"
				+ ", g.goods_title goodsTitle"
				+ ", img.filename filename"
				+ ", q.category category"
				+ ", q.question_title questionTitle"
				+ ", q.createdate createdate"
				+ " FROM question q INNER JOIN orders o"
				+ " ON q.orders_code = o.order_code"
				+ " INNER JOIN goods g"
				+ " ON o.goods_code = g.goods_code"
				+ " INNER JOIN goods_img img"
				+ " ON g.goods_code = img.goods_code"
				+ " WHERE o.customer_id = ?"
				+ " ORDER BY q.question_code DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("questionCode", rs.getInt("questionCode"));
			m.put("ordersCode", rs.getInt("ordersCode"));
			m.put("goodsTitle", rs.getString("goodsTitle"));
			m.put("filename", rs.getString("filename"));
			m.put("category", rs.getString("category"));
			m.put("questionTitle", rs.getString("questionTitle"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
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
			m.put("goodsTitle", rs.getString("goodsTitle"));
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
