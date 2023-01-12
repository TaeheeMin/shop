package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Notice;
import vo.Orders;
import vo.Review;

public class ReviewDao {
		// 리뷰목록 페이징
		public int selectReviewCount(Connection conn) throws Exception {
			int row = 0;
			String sql = "SELECT COUNT(*) FROM review";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				row = rs.getInt("COUNT(*)");
			}
			return row;
		}
		//리뷰 리스트 		
			public ArrayList<HashMap<String, Object>> selectReviewList(Connection conn, int beginRow, int rowPerPage) throws Exception {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();		
			String sql = "SELECT "
					+ "o.order_code orderCode"
					+ "	, r.review_memo reviewMemo"
					+ "	, r.createdate createdate"
					+ "	, o.customer_id customerId"
					+ "	, g.goods_title goodsTitle"
					+ " , gi.filename filename"
					+ "	FROM review r INNER JOIN orders o"
					+ "	ON r.order_code = o.order_code"
					+ " INNER JOIN goods g"
					+ " ON o.order_code = g.goods_code"
					+ "	INNER JOIN goods_img gi"
					+ " ON g.goods_code = gi.goods_code"
					+ " LIMIT ?,?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
		         HashMap<String, Object> m = new HashMap<String, Object>();
		         m.put("orderCode", rs.getInt("orderCode"));
		         m.put("reviewMemo", rs.getString("reviewMemo"));
		         m.put("customerId", rs.getString("customerId"));
		         m.put("createdate", rs.getString("createdate"));
		         m.put("goodsTitle", rs.getString("goodsTitle"));
		         m.put("filename", rs.getString("filename"));
		         list.add(m);
			}
			return list;
		}
	
		// 리뷰추가 주문한상품의 고객만 
	   public int addReview(Connection conn, Review review) throws Exception {
		      int reviewRow = 0;	    
		      String sql = "INSERT INTO review(review_memo, order_code) VALUES (?,?)";		      
		      PreparedStatement stmt = conn.prepareStatement(sql);		      
		      stmt.setString(1, review.getReviewMemo());
		      stmt.setInt(2, review.getOrderCode());
		      reviewRow = stmt.executeUpdate();
		      return reviewRow;
		   }
	   // 리뷰삭제 관리자용
	   public int removeReview(Connection conn, int orderCode) throws Exception {
		   int row = 0;
		   String sql = "DELETE FROM review WHERE order_code = ?";	      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, orderCode);		    
		      row = stmt.executeUpdate();
		      return row;
	   }
	   
	   //리뷰삭제 본인용 
	   public int removeReview(Connection conn, int orderCode, Orders orders)throws Exception {
		   int row = 0;
		   String sql = "DELETE "
		   		+ "	FROM review re INNER JOIN orders o "
		   		+ "	ON re.order_code = o.order_code"
		   		+ "WHERE re.order_code = ? AND o.customer_id = ?";	      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setInt(1, orderCode);
		      stmt.setString(2, orders.getCustomerId());
		      row = stmt.executeUpdate();
		      return row;
	   }
	   	
	   //리뷰수정 본인만
	   public int modifyReview(Connection conn, Review review, Orders orders) throws Exception {
		   int row = 0;
		   String sql = "UPDATE review re inner JOIN orders o"
		   		+ "	ON re.order_code = o.order_code"
		   		+ "	SET re.review_memo = ?"
		   		+ "	WHERE re.order_code = ? AND o.customer_id = ?";
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, review.getReviewMemo());
		      stmt.setInt(2, review.getOrderCode());
		      stmt.setString(3, orders.getCustomerId());
		      row = stmt.executeUpdate();
		      return row;
	   }
	   
	   
}
