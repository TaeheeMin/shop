package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Notice;
import vo.Orders;
import vo.Review;

public class ReviewDao {
		//리뷰 리스트 
		//이너조인 하여 다른목록 봐야함 추후예정
		public ArrayList<Review> selectReviewList(Connection conn) throws Exception {
			ArrayList<Review> review = new ArrayList<Review>();
			String sql = "SELECT order_code orderCode, review_memo reviewMemo, createdate FROM review";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Review r = new Review();
				r.setOrderCode(rs.getInt("orderCode"));
				r.setReviewMemo(rs.getString("reviewMemo"));
				r.setCreatedate(rs.getString("createdate"));
				review.add(r);
			}
			return review;
		}
	
		// 리뷰추가 주문한상품의 고객만 
	   public int addReview(Connection conn, Review review,Orders orders) throws Exception {
		      int row = 0;	    
		      String sql = "INSERT INTO review(review_memo) VALUES (?)";		      
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, review.getReviewMemo());		    
		      row = stmt.executeUpdate();
		      return row;
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
		   		+ "ON re.order_code = o.order_code"
		   		+ "SET re.review_memo = ?"
		   		+ "WHERE re.order_code = ? AND o.customer_id = ?";
		      PreparedStatement stmt = conn.prepareStatement(sql);
		      stmt.setString(1, review.getReviewMemo());
		      stmt.setInt(2, review.getOrderCode());
		      stmt.setString(3, orders.getCustomerId());
		      row = stmt.executeUpdate();
		      return row;
	   }
	   
	   
}
